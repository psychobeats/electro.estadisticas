package com.AMQApp.servicios;

import com.AMQApp.entidades.Encuesta;
import com.AMQApp.entidades.ResultadosPorcentajes;
import com.AMQApp.entidades.Voto;
import com.AMQApp.errores.ErrorServicio;
import com.AMQApp.repositorios.EncuestaRepositorio;
import com.AMQApp.repositorios.ResultadosPorcentajesRepositorio;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResultadosPorcentajesServicio {
    
    @Autowired
    ResultadosPorcentajesRepositorio resultadosPorcentajesRepositorio;
    
    @Autowired
    EncuestaRepositorio encuestaRepositorio;
    
    public ResultadosPorcentajes crear(){
        ResultadosPorcentajes resultados = new ResultadosPorcentajes();
        resultados.setAlta(true);
            
        resultados.setTotalVotos(0);
        resultados.setTotalHombres(0);
        resultados.setTotalMujeres(0);
        resultados.setTotalOtros(0);
            
        resultadosPorcentajesRepositorio.save(resultados);
        
        return resultados;
    }
    
    public void actualizarVotos(String id) throws ErrorServicio{
        Optional<Encuesta> resultado = encuestaRepositorio.findById(id);
        
        if (resultado.isPresent()) {
            Encuesta e1 = resultado.get();
            
            List<Voto> votos = e1.getVotos();
            Integer totalVotos = votos.size();
            Integer totalHombres = 0;
            Integer totalMujeres = 0;
            Integer totalOtros = 0;
            
            for (Voto voto : votos) {
                if (voto.getUsuario().getSexo().toString().equalsIgnoreCase("hombre")) {
                    totalHombres++;
                } else if (voto.getUsuario().getSexo().toString().equalsIgnoreCase("mujer")) {
                    totalMujeres++;
                } else if (voto.getUsuario().getSexo().toString().equalsIgnoreCase("otro")) {
                    totalOtros++;
                }
            }
            e1.getResultados().setTotalVotos(totalVotos);
            e1.getResultados().setTotalHombres(totalHombres);
            e1.getResultados().setTotalMujeres(totalMujeres);
            e1.getResultados().setTotalOtros(totalOtros);
            encuestaRepositorio.save(e1);
        } else {
            throw new ErrorServicio("No se encontró una encuesta con ese ID");
        }
    }
    
    public Double porcentajeHombres(String id) throws ErrorServicio
    {
        Optional <Encuesta> resultado = encuestaRepositorio.findById(id);
        
        if (resultado.isPresent()) {
            Double porcentajeHombres;
            Encuesta e1 = resultado.get();
            porcentajeHombres = (double) ((e1.getResultados().getTotalHombres() * 100) / e1.getResultados().getTotalVotos());
            return porcentajeHombres;
        }else {
            throw new ErrorServicio("No se encontró una encuesta con ese ID");
        }
    }
    
    public Double porcentajeMujeres(String id) throws ErrorServicio
    {
        Optional <Encuesta> resultado = encuestaRepositorio.findById(id);
        
        if (resultado.isPresent()) {
            Double porcentajeMujeres;
            Encuesta e1 = resultado.get();
            porcentajeMujeres = (double) ((e1.getResultados().getTotalHombres() * 100) / e1.getResultados().getTotalVotos());
            return porcentajeMujeres;
        }else {
            throw new ErrorServicio("No se encontró una encuesta con ese ID");
        }
    }
    
    public Double porcentajeOtros(String id) throws ErrorServicio
    {
        Optional <Encuesta> resultado = encuestaRepositorio.findById(id);
        
        if (resultado.isPresent()) {
            Double porcentajeOtros;
            Encuesta e1 = resultado.get();
            porcentajeOtros = (double) ((e1.getResultados().getTotalHombres() * 100) / e1.getResultados().getTotalVotos());
            return porcentajeOtros;
        }else {
            throw new ErrorServicio("No se encontró una encuesta con ese ID");
        }
    }
    
    public void calcularPorcentajes(String id) throws ErrorServicio
    {
        actualizarVotos(id);
        Double porcentajeHombres = porcentajeHombres(id);
        Double porcentajeMujeres = porcentajeMujeres(id);
        Double porcentajeOtros = porcentajeOtros(id);
    }
        
}
