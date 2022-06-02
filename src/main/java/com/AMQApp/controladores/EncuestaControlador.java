package com.AMQApp.controladores;

import com.AMQApp.entidades.Usuario;
import com.AMQApp.errores.ErrorServicio;
import com.AMQApp.servicios.EncuestaServicio;
import com.AMQApp.servicios.UsuarioServicio;
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
    
    @Autowired
    private UsuarioServicio usuarioServicio;
    
    
    @GetMapping("/crearEnc")
    public String crearEnc(ModelMap modelo,@RequestParam String id) throws ErrorServicio{
        Usuario usuario = usuarioServicio.buscarPorId(id);
        modelo.addAttribute("usuario", usuario);
        
        
        return "CrearEncuesta.html";
    }
    
//    @GetMapping("/modificar")
//    public String modificar(ModelMap modelo, @RequestParam String id){
//        
//        Autor autor = autorServicio.buscarPorId(id);
//        modelo.addAttribute("autor", autor);
//        return "modificarAutor.html";
//    }

    
    
    @PostMapping("/crear")
    public String crear(@RequestParam(required=false) String usuarioId, @RequestParam(required=false) String titulo, @RequestParam(required=false) String opcion1, @RequestParam(required=false) String opcion2) throws ParseException, ErrorServicio{
        try{
            usuarioServicio.crearEncuesta(usuarioId, titulo, opcion1, opcion2);
        }catch(ErrorServicio e){
            e.getMessage();
        }
        return "index.html";
    }
}
