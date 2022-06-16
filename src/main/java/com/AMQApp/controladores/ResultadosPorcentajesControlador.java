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
    public String visualizacion(ModelMap modelo, @RequestParam(required=false) String idEncuesta){
        
        Encuesta encuesta = encuestaRepositorio.getById(idEncuesta);
        
        if (encuesta.getVotos() == null || encuesta.getVotos().size() == 0) {
            modelo.put("mensaje", "Todavía no movieron esta Query :(");
            return "QueryVer.html";
        } else {
            try{
            resultadosPorcentajesServicio.calcularPorcentajes(idEncuesta);
            ResultadosPorcentajes resultados = encuesta.getResultados();
            modelo.addAttribute("resultados" , resultados);
            modelo.addAttribute("encuesta", encuesta);
            return "graficoResultados.html";
            }catch(ErrorServicio e){
                modelo.put("error", e.getMessage());
                return "Error";
            }
        }  
        
    }
}
