package com.AMQApp.controladores;

import com.AMQApp.errores.ErrorServicio;
import com.AMQApp.servicios.EncuestaServicio;
import java.text.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/encuesta")
public class EncuestaControlador {
    
    @Autowired
    private EncuestaServicio encuestaServicio;
    
    
    @GetMapping("/registro")
    public String registro(){
        return "CrearEncuesta.html";
    }
    
    
    @PostMapping("/crear")
    public String crear(@RequestParam(required=false) String titulo, @RequestParam(required=false) String opcion1, @RequestParam(required=false) String opcion2) throws ParseException, ErrorServicio{
        try{
            encuestaServicio.crearEncuesta(titulo, opcion1, opcion2);
        }catch(ErrorServicio e){
            e.getMessage();
        }
        return "index.html";
    }
}
