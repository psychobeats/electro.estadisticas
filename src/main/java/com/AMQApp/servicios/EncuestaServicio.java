package com.AMQApp.servicios;

import com.AMQApp.entidades.Encuesta;
import com.AMQApp.entidades.ResultadosPorcentajes;
import com.AMQApp.entidades.Usuario;
import com.AMQApp.entidades.Voto;
import com.AMQApp.errores.ErrorServicio;
import com.AMQApp.repositorios.EncuestaRepositorio;
import com.AMQApp.repositorios.ResultadosPorcentajesRepositorio;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;

@Service
public class EncuestaServicio {
    
    
    @Autowired
    private EncuestaRepositorio encuestaRepositorio;
    
    @Autowired
    private ResultadosPorcentajesRepositorio resultadosPorcentajesRepositorio;
    
    @Autowired
    private VotoServicio votoServicio;
    
    
    
//    CONSULTAR:
//        -¿MEJOR RECIBIR LAS OPCIONES EN UN ARRAY POR SEPARADO COMO OPC1 Y OPC2?
    
//      HABLAR CON PILI POR LA POSIBILIDAD DE INCLUÍR NUEVA ENDIDAD: SITIO.
//      PARA QUE ALMACENE: -ESTADÍSTICAS SITIO
//                         -ArrayList de TODAS LAS ENCUESTAS CREADAS PARA MÉTODO BAJA POR CADUCIDAD
//  
    
    @Transactional
    public Encuesta crearEncuesta(String titulo, String opcion1, String opcion2) throws ErrorServicio {
       Encuesta e1 = new Encuesta();
       validar(titulo, opcion1, opcion2);
            e1.setTitulo(titulo);
            e1.setOpcion1(opcion1);
            e1.setOpcion2(opcion2);
            Date inicio = new Date();
            e1.setInicio(inicio);
 
            Date caducidad = inicio;
            caducidad.setDate(inicio.getDate() + 1);

            e1.setCaducidad(caducidad);

            List<Voto> votos = new ArrayList();
            e1.setVotos(votos);
            ResultadosPorcentajes resultados = new ResultadosPorcentajes();
            resultados.setAlta(true);
            
            resultados.setTotalVotos(0);
            resultados.setTotalHombres(0);
            resultados.setTotalMujeres(0);
            resultados.setTotalOtros(0);
            
            resultadosPorcentajesRepositorio.save(resultados);
            
            e1.setResultados(resultados);
            e1.setAlta(true);
       
       encuestaRepositorio.save(e1);
       return e1;
    }
    
    public void votarEncuesta(String idEncuesta, Usuario usuario, String opcion) throws ErrorServicio{
        Optional<Encuesta> respuesta = encuestaRepositorio.findById(idEncuesta);
        if(respuesta.isPresent()){
            Encuesta encuesta = respuesta.get();
            Voto voto = votoServicio.votar(usuario, opcion);
            encuesta.getVotos().add(voto);
            encuestaRepositorio.save(encuesta);
        }else{
            throw new ErrorServicio("No existe una encuesta con el id indicado");
        }
    }
    
    @Transactional
    public Encuesta consultarPorTitulo(String titulo) throws ErrorServicio{
        if (titulo == null || titulo.isEmpty()) {
            throw new ErrorServicio("Debe ingresar el título de la Encuesta");
        }

        Encuesta resultado = encuestaRepositorio.buscarPorTitulo(titulo);
        
        if (resultado == null) {
             throw new ErrorServicio("¡No se ha encontrado esta Encuesta!");
        }
        return resultado;
    }
    
    //  HABLAR CON PILI POR LA POSIBILIDAD DE INCLUÍR NUEVA ENDIDAD: SITIO.
    //  PARA QUE ALMACENE: -ESTADÍSTICAS SITIO
    //                     -ArrayList de TODAS LAS ENCUESTAS CREADAS PARA MÉTODO BAJA POR CADUCIDAD
    //                     -ArrayList de TODOS LOS USUARIOS CREADOS
    

    @Transactional
    public void bajaPorCaducidad(List<Encuesta> encuestas)
    {
        for (Encuesta encuesta : encuestas) {
            Date actual = new Date();
            if (encuesta.getCaducidad().before(actual)) {
                encuesta.setAlta(false);
            }
        }
    }
    
    @Transactional
    public void altaEncuesta(String id)throws ErrorServicio {
        if (id == null || id.isEmpty()) {
            throw new ErrorServicio("No llegó el id de la consulta");
        }
        
        Optional <Encuesta> respuesta = encuestaRepositorio.findById(id);
        
        if (respuesta.isPresent()) {
            Encuesta e1 = respuesta.get();
            if (e1.getAlta()==false) {
                e1.setAlta(true);
            } else if (e1.getAlta()==true) {
                throw new ErrorServicio("La encuesta ya está dada de alta");
            } 
        } else {
                throw new ErrorServicio("El id llegó, pero la encuesta no fue encontrada");
        }    
    }
       
    
    @Transactional
    public void bajaEncuesta(String id)throws ErrorServicio {
        if (id == null || id.isEmpty()) {
            throw new ErrorServicio("No llegó el id de la consulta");
        }
        
        Optional <Encuesta> respuesta = encuestaRepositorio.findById(id);
        
        if (respuesta.isPresent()) {
            Encuesta e1 = respuesta.get();
            if (e1.getAlta()==true) {
                e1.setAlta(false);
            } else if (e1.getAlta()==false) {
                throw new ErrorServicio("La encuesta ya está dada de baja");
            } 
        } else {
                throw new ErrorServicio("El id llegó, pero la encuesta no fue encontrada");
        }    
    }

    @Transactional
    public List<Encuesta> listaDeEncuestas() throws ErrorServicio
    {
        List<Encuesta> encuestas = encuestaRepositorio.findAll();
        if (encuestas.isEmpty() || encuestas == null) {
            throw new ErrorServicio("No se pudo cargar el List de encuestas desde el repositorio");
        }        
        bajaPorCaducidad(encuestas);

        return encuestas;
    }
    
    
    public void validar(String titulo, String opcion1, String opcion2) throws ErrorServicio {
        if (titulo.isEmpty() || titulo == null) {
            throw new ErrorServicio("No ingresó ningún TITULO");
        }
        if (opcion1.isEmpty() || opcion1 == null) {
            throw new ErrorServicio("No se ingreso la OPCION 1");
        }
        if (opcion2.isEmpty() || opcion2 == null) {
            throw new ErrorServicio("No se ingreso la OPCION 2");
        } 
    }
}
