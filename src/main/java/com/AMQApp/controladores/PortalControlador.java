package com.AMQApp.controladores;

import com.AMQApp.entidades.Usuario;
import com.AMQApp.errores.ErrorServicio;
import com.AMQApp.servicios.EncuestaServicio;
import com.AMQApp.servicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller("/")
public class PortalControlador {
    
    @Autowired
    private UsuarioServicio usuarioServicio;
    
    @Autowired
    private EncuestaServicio encuestaServicio;
    
    @GetMapping("/")
    public String index(ModelMap modelo){
//        try{
//            modelo.put("querys", encuestaServicio.topFive());
//        }catch(ErrorServicio e){
//            modelo.put("error", e.getMessage());
//        }
        return "indexInicial.html";
    }
    
    @GetMapping("/login")
    public String login(ModelMap modelo,@RequestParam(required = false) String error, @RequestParam(required = false) String logout){
        if (error != null) {
            modelo.put("error", "Usuario y/o contraseña incorrect@s");
        }
        if (logout != null) {
            modelo.put("logout", "Dejaste de mover tu Query :(");
        }
        return "logIn.html";
    }


    
    @GetMapping("/loginExitoso")
    public String loginExito(ModelMap modelo){
//         try{
//            modelo.put("querys", encuestaServicio.topFive());
//        }catch(ErrorServicio e){
//            modelo.put("error", e.getMessage());
//        }
      return "IndexIniciado.html";
    }
    
    @GetMapping("/preguntasFrecuentes")
    public String preguntasFrecuentes(){
        return "preguntasFrecuentes";
    }
    
    @GetMapping("/preguntasFrecuentesIn")
    public String preguntasFrecuentesIn(){
        return "preguntasFrecuentesIniciado";
    }
    
}
