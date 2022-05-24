package com.AMQApp.servicios;

import com.AMQApp.entidades.Encuesta;
import com.AMQApp.entidades.ResultadosPorcentajes;
import com.AMQApp.entidades.Voto;
import com.AMQApp.errores.ErrorServicio;
import com.AMQApp.repositorios.EncuestaRepositorio;
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
    
    
    
//    CONSULTAR:
//        -¿MEJOR RECIBIR LAS OPCIONES EN UN ARRAY POR SEPARADO COMO OPC1 Y OPC2?
    
//      HABLAR CON PILI POR LA POSIBILIDAD DE INCLUÍR NUEVA ENDIDAD: SITIO.
//      PARA QUE ALMACENE: -ESTADÍSTICAS SITIO
//                         -ArrayList de TODAS LAS ENCUESTAS CREADAS PARA MÉTODO BAJA POR CADUCIDAD
//  
    
    @Transactional
    public Encuesta crearEncuesta(String titulo, String[] opciones, Date inicio, Date caducidad, Integer totalVotos, Integer totalMujeres, Integer totalHombres, Integer totalOtros) throws ErrorServicio {
       Encuesta e1 = new Encuesta();
       if (titulo == null || titulo.isEmpty()) {
            throw new ErrorServicio("Debe ingresar el titulo de la Encuesta");            
        } else {
            e1.setTitulo(titulo);
            e1.setOpciones(opciones);
            e1.setInicio(inicio);
            e1.setCaducidad(caducidad);
            e1.setTotalVotos(totalVotos);
            e1.setTotalMujeres(totalMujeres);
            e1.setTotalHombres(totalHombres);
            e1.setTotalOtros(totalOtros);
            List<Voto> votos = new ArrayList();
            e1.setVotos(votos);
            ResultadosPorcentajes resultados = new ResultadosPorcentajes();
            e1.setResultados(resultados);
 
            e1.setAlta(true);
            
            validar(titulo, opciones, inicio, caducidad, totalVotos, totalMujeres, totalHombres, totalOtros, votos, resultados);
        }
       encuestaRepositorio.save(e1);
       return e1;
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
    
    
    public void validar(String titulo, String[] opciones, Date inicio, Date caducidad, Integer totalVotos, Integer totalMujeres, Integer totalHombres, Integer totalOtros, List<Voto> votos,ResultadosPorcentajes resultados) throws ErrorServicio {
        if (titulo.isEmpty() || titulo == null) {
            throw new ErrorServicio("No ingresó ningún TITULO");
        }
        if (opciones.length == 0 || opciones == null) {
            throw new ErrorServicio("No se ingresaron todas las OPCIONES");
        }
        if (inicio.toString().isEmpty() || inicio == null) {
            throw new ErrorServicio("No ingresó ninguna FECHA DE INICIO");
        }
        if (caducidad.toString().isEmpty() || caducidad == null) {
            throw new ErrorServicio("No ingresó ninguna FECHA DE CADUCIDAD");
        }
        if (totalVotos.toString().isEmpty()|| totalVotos == null) {
            throw new ErrorServicio("No llegó el TOTAL DE VOTOS");
        }
        if (totalMujeres.toString().isEmpty()|| totalMujeres == null) {
            throw new ErrorServicio("No llegó el TOTAL DE MUJERES VOTANTES");
        }
        if (totalHombres.toString().isEmpty()|| totalHombres == null) {
            throw new ErrorServicio("No llegó el TOTAL DE HOMBRES VOTANTES");
        }
        if (totalOtros.toString().isEmpty()|| totalOtros == null) {
            throw new ErrorServicio("No llegó el TOTAL DE OTROS VOTANTES");
        }
        if (votos.isEmpty()|| votos == null) {
            throw new ErrorServicio("No se cargó el LIST DE VOTOS");
        }
        if (resultados.toString().isEmpty() || resultados == null) {
            throw new ErrorServicio("No se cargó la CLASE RESULTADOS");
        }  
    }
}
