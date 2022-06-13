package com.AMQApp.controladores;

import com.AMQApp.entidades.Encuesta;
import com.AMQApp.entidades.Usuario;
import com.AMQApp.enums.Pais;
import com.AMQApp.enums.Sexo;
import com.AMQApp.errores.ErrorServicio;
import com.AMQApp.servicios.UsuarioServicio;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/usuario")
public class UsuarioControlador {
    
    @Autowired
    private UsuarioServicio usuarioServicio;
    
    @GetMapping("/registro")
    public String registro(ModelMap modelo){
        modelo.addAttribute("sexos", Sexo.values());
        modelo.addAttribute("paises", Pais.values());
        return "formUsuario.html";
    }
    
    @PostMapping("/registrar")
    public String registrar(ModelMap modelo, @RequestParam(required=false) String alias,@RequestParam(required=false) Sexo sexo,@RequestParam(required=false) String email,@RequestParam(required=false) Pais pais,@RequestParam(required=false) String nacimiento,@RequestParam(required=false) String clave,@RequestParam(required=false) String claveValidar) throws ParseException{
        try{
            usuarioServicio.crear(alias, sexo, email, pais, nacimiento, clave, claveValidar);
        }catch(ErrorServicio e){
            modelo.addAttribute("sexos", Sexo.values());
            modelo.addAttribute("paises", Pais.values());
            modelo.put("alias", alias);
            modelo.put("email", email);
            modelo.put("sexo", sexo);
            modelo.put("pais", pais);
            modelo.put("nacimiento", nacimiento);
            modelo.put("clave", clave);
            modelo.put("claveValidar", claveValidar);
            modelo.put("error", e.getMessage());
            return "formUsuario.html";
        }
        return "logIn.html";
    }
    
    @GetMapping("/edicion")
    public String edicion(ModelMap modelo, String id) throws ErrorServicio{
        Usuario usuario = usuarioServicio.buscarPorId(id);
        modelo.addAttribute("usuario", usuario);
        modelo.addAttribute("sexos", Sexo.values());
        modelo.addAttribute("paises", Pais.values());
        return "EditarUsuario.html";
    }
    
    @PostMapping("/editar")
    public String editar(@RequestParam(required=false) String idUsuario, @RequestParam(required=false) String alias,@RequestParam Sexo sexo,@RequestParam(required=false) String email,@RequestParam Pais pais,@RequestParam(required=false) String nacimiento,@RequestParam(required=false) String clave,@RequestParam(required=false) String claveValidar) throws ParseException{
        try{
            usuarioServicio.modificar(idUsuario, alias, sexo, pais, nacimiento, clave, claveValidar);
        }catch(ErrorServicio e){
            e.getMessage();
        }
        return "IndexIniciado.html";
    }
    
    @GetMapping("/misQuerys")
    public String misQuerys(ModelMap modelo, @RequestParam(required=false) String idUsuario) throws ErrorServicio {
        List<Encuesta> encuestas = usuarioServicio.listaQuerysUsuario(idUsuario);
        if (encuestas.size() > 0) {
            modelo.put("encuestas", encuestas);
            return "misQuerys.html";
        } else {
            modelo.put("error", "Aún no creaste ninguna Query :S");
            return "IndexIniciado.html";
        }
        
    }
    
    @GetMapping("/perfil")
    public String perfil(){
        return "perfil";
    }
    
    
    
    
}
