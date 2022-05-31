package com.AMQApp.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller("/")
public class PortalControlador {
    
    @GetMapping("/")
    public String index(){
        return "index.html";
    }
    
    @GetMapping("/login")
    public String login(){
        return "Login.html";
    }
    
    @GetMapping("/loginExitoso")
    public String loginExito(){
        return "LoginExitoso.html";
    }
}
