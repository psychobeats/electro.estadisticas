package com.AMQApp.controladores;

import com.AMQApp.entidades.Encuesta;
import com.AMQApp.entidades.ResultadosPorcentajes;
import com.AMQApp.errores.ErrorServicio;
import com.AMQApp.repositorios.EncuestaRepositorio;
import com.AMQApp.servicios.ResultadosPorcentajesServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/resultados")
public class ResultadosPorcentajesControlador {
    
    @Autowired
    private EncuestaRepositorio encuestaRepositorio;
    
    @Autowired
    private ResultadosPorcentajesServicio resultadosPorcentajesServicio;
    
    @GetMapping("/visualizacion")
    public String votacion(ModelMap modelo, @RequestParam(required=false) String idEncuesta) throws ErrorServicio{
        resultadosPorcentajesServicio.calcularPorcentajes(idEncuesta);
        Encuesta encuesta = encuestaRepositorio.getById(idEncuesta);
        
        ResultadosPorcentajes resultados = encuesta.getResultados();
        
        modelo.addAttribute("resultados" , resultados);
        
        modelo.addAttribute("encuesta", encuesta);
        return "QueryVer.html";
    }
}
