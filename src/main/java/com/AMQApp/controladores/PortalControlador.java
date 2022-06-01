package com.AMQApp.controladores;

import com.AMQApp.entidades.Usuario;
import com.AMQApp.errores.ErrorServicio;
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
    
    @GetMapping("/")
    public String index(){
        return "index.html";
    }
    
    @GetMapping("/login")
    public String login(){
        return "Login.html";
    }
    
//    @PostMapping("/logincheck")
//    public String logear(ModelMap modelo, @RequestParam String email) throws ErrorServicio{
//        Usuario usuario = usuarioServicio.buscarPorEmail(email);
//        modelo.addAttribute("usuario", usuario);
//        modelo.put("usuario", usuario);
//        return "LoginExitoso.html";
//        
//    }

    
    @GetMapping("/loginExitoso")
    public String loginExito(){
//        Usuario usuario = usuarioServicio.buscarPorEmail(email);
//        if(usuario!=null){
//        modelo.addAttribute("usuario", usuario);
//        modelo.put("alias", usuario.getAlias());
//        return "LoginExitoso.html";
//        }else{
//            return "index.html";
//        }
    return "LoginExitoso.html";
    }
}
