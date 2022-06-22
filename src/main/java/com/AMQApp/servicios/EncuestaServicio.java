package com.AMQApp.servicios;

import com.AMQApp.entidades.Encuesta;
import com.AMQApp.entidades.ResultadosPorcentajes;
import com.AMQApp.entidades.Usuario;
import com.AMQApp.entidades.Voto;
import com.AMQApp.errores.ErrorServicio;
import com.AMQApp.repositorios.EncuestaRepositorio;
import com.AMQApp.repositorios.VotoRepositorio;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EncuestaServicio {
    
    
    @Autowired
    private EncuestaRepositorio encuestaRepositorio;
    
    @Autowired
    private ResultadosPorcentajesServicio resultadosPorcentajesServicio;
    
    @Autowired
    private VotoServicio votoServicio;
    
    @Autowired
    private VotoRepositorio votoRepositorio;
    
    
    
//    CONSULTAR:
//        -¿MEJOR RECIBIR LAS OPCIONES EN UN ARRAY POR SEPARADO COMO OPC1 Y OPC2?
    
//      HABLAR CON PILI POR LA POSIBILIDAD DE INCLUÍR NUEVA ENDIDAD: SITIO.
//      PARA QUE ALMACENE: -ESTADÍSTICAS SITIO
//                         -ArrayList de TODAS LAS ENCUESTAS CREADAS PARA MÉTODO BAJA POR CADUCIDAD
//  
    
    @Transactional
    public Encuesta crearEncuesta(String titulo, String opcion1, String opcion2, String caducidad) throws ErrorServicio, ParseException {
       Encuesta e1 = new Encuesta();
       validar(titulo, opcion1, opcion2);
            e1.setTitulo(titulo);
            e1.setOpcion1(opcion1);
            e1.setOpcion2(opcion2);
            Date inicio = new Date();
            e1.setInicio(inicio);
            
            if (caducidad != null && !caducidad.isEmpty()) {
                SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
                Date caducidad1 = formato.parse(caducidad);
                //validarNacimientoDate(caducidad1);
                e1.setCaducidad(caducidad1);
            } else {
                e1.setCaducidad(null);
            }

            List<Voto> votos = new ArrayList();
            e1.setVotos(votos);
            ResultadosPorcentajes resultados = resultadosPorcentajesServicio.crear();
            
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
            votoRepositorio.save(voto);
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
            if (encuesta.getCaducidad() != null) {
                if (encuesta.getCaducidad().before(actual)) {
                    encuesta.setAlta(false);
                    encuestaRepositorio.save(encuesta);
                }
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
    
    public List<Encuesta> topFive() throws ErrorServicio{
        List<Encuesta> encuestasOrdenadasPorVotos = encuestaRepositorio.ordenarPorVotos();
        List<Encuesta> encuestasTopFive = new ArrayList();
        if(encuestasOrdenadasPorVotos.isEmpty() || encuestasOrdenadasPorVotos == null){
            throw new ErrorServicio("Aún no hay Querys creadas en el sitio :(");
        } else if ( encuestasOrdenadasPorVotos.size() < 5 ){
            for(int i = 0; i< encuestasOrdenadasPorVotos.size(); i++){
            encuestasTopFive.add(encuestasOrdenadasPorVotos.get(i));
            }
        } else {
            for(int i = 0; i< 5; i++){
            encuestasTopFive.add(encuestasOrdenadasPorVotos.get(i));
            }
        }
        return encuestasTopFive;
    }
    
    public Encuesta buscarPorId(String id) throws ErrorServicio{
        Optional<Encuesta> respuesta = encuestaRepositorio.findById(id);
        if(respuesta.isPresent()){
            return respuesta.get();
        }else{
            throw new ErrorServicio("No se encontró la encuesta");
        }
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
