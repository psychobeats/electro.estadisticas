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
    
    @Transactional
    public void crearEncuesta(String titulo, String[] opciones, Date inicio, Date caducidad, Integer totalVotos, Integer totalMujeres, Integer totalHombres, Integer totalOtros) throws ErrorServicio {
       if (titulo == null || titulo.isEmpty()) {
            throw new ErrorServicio("Debe ingresar el titulo de la Encuesta");            
        } else {
            Encuesta e1 = new Encuesta();
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
            encuestaRepositorio.save(e1);
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
    public void altaBajaEncuesta(String id)throws ErrorServicio {
        if (id == null || id.isEmpty()) {
            throw new ErrorServicio("No llegó el id de la consulta");
        }
        
        Optional <Encuesta> respuesta = encuestaRepositorio.findById(id);
        
        if (respuesta.isPresent()) {
            Encuesta e1 = respuesta.get();
            if (e1.getAlta()==true) {
                e1.setAlta(false);
            } else if (e1.getAlta()==false) {
                e1.setAlta(true);
            } 
        } else {
                throw new ErrorServicio("El id llegó, pero la encuesta no fue encontrada");
        }    
    }
            
    
}
