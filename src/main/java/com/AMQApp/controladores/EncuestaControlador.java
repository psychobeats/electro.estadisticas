package com.AMQApp.controladores;

import com.AMQApp.entidades.Encuesta;
import com.AMQApp.entidades.Usuario;
import com.AMQApp.entidades.Voto;
import com.AMQApp.errores.ErrorServicio;
import com.AMQApp.repositorios.EncuestaRepositorio;
import com.AMQApp.servicios.EncuestaServicio;
import com.AMQApp.servicios.UsuarioServicio;
import java.text.ParseException;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Controller
@RequestMapping("/encuesta")
public class EncuestaControlador {
    
    @Autowired
    private EncuestaServicio encuestaServicio;
    
    @Autowired
    private EncuestaRepositorio encuestaRepositorio;
    
    @Autowired
    private UsuarioServicio usuarioServicio;

    
    @GetMapping("/crearEnc")
    public String crearEnc(ModelMap modelo,@RequestParam String id) throws ErrorServicio{
        Usuario usuario = usuarioServicio.buscarPorId(id);
        modelo.addAttribute("usuario", usuario);
        
        
        return "encuesta.html";
    }
    
//    @GetMapping("/modificar")
//    public String modificar(ModelMap modelo, @RequestParam String id){
//        
//        Autor autor = autorServicio.buscarPorId(id);
//        modelo.addAttribute("autor", autor);
//        return "modificarAutor.html";
//    }

    
    
    @PostMapping("/crear")
    public String crear(ModelMap modelo, @RequestParam(required=false) String usuarioId, @RequestParam(required=false) String titulo, @RequestParam(required=false) String opcion1, @RequestParam(required=false) String opcion2) throws ParseException, ErrorServicio{
        try{
            usuarioServicio.crearEncuesta(usuarioId, titulo, opcion1, opcion2);
        }catch(ErrorServicio e){
            modelo.put("titulo", titulo);
            modelo.put("opcion1", opcion1);
            modelo.put("opcion2", opcion2);
            modelo.put("error", e.getMessage());
            return "encuesta.html";
        }
        return "redirect:/loginExitoso";
    }
    
     
    @GetMapping("/listar")
    public String listar(ModelMap modelo, @RequestParam(required=false) String idUsuario) throws ErrorServicio {
        List<Encuesta> encuestas = encuestaServicio.listaDeEncuestas();
        modelo.put("encuestas", encuestas);
        return "ListaEncuestas.html";
    }
    
    @GetMapping("/votacion")
    public String votacion(ModelMap modelo, @RequestParam(required=false) String idEncuesta, @RequestParam(required=false) String idUsuario) throws ErrorServicio{
        Encuesta encuesta = encuestaRepositorio.getById(idEncuesta);
        Usuario usuario = usuarioServicio.buscarPorId(idUsuario);
        Boolean validarUsuario = false;
        Boolean validarEncuesta = false;
        
        
        
        for (Encuesta auxEncuesta: usuario.getEncuestasCreadas()) {
            if (encuesta.getId().equals(auxEncuesta.getId())) {
                validarEncuesta = true;
            }
        }
        
        
        
            for(Voto aux : encuesta.getVotos()){
                if(aux.getUsuario()==usuario){
                    validarUsuario = true;
                }
            }
            if(validarUsuario){
                modelo.put("error", "Ya moviste esta Query!");
                List<Encuesta> encuestas = encuestaServicio.listaDeEncuestas();
                modelo.put("encuestas", encuestas);
                return "ListaEncuestas.html";
            }else if (validarEncuesta) {
                modelo.put("error", "No podés votar tu propia encuesta >:(");
                List<Encuesta> encuestas = encuestaServicio.listaDeEncuestas();
                modelo.put("encuestas", encuestas);
                return "ListaEncuestas.html";
            } else {
                modelo.addAttribute("encuesta", encuesta);
                return "queryVotar.html";
            }      
    }
    
    @GetMapping("/votar")
    public String votar(ModelMap modelo, @RequestParam(required=false) String idEncuesta, @RequestParam(required=false) String idUsuario, @RequestParam(required=false) String opcion){
        try{
            Usuario usuario = usuarioServicio.buscarPorId(idUsuario);
            
            encuestaServicio.votarEncuesta(idEncuesta, usuario, opcion);
            
        }catch(ErrorServicio ex){
            
            //modelo.put("opcion", opcion);
            modelo.put("error", ex.getMessage());
            return "ListaEncuestas.html";
        }
        //modelo.put("mensaje", "Se ha votado la encuesta correctamente");
        return "redirect:/encuesta/listar";
    }
    
      @GetMapping("/baja")
    public String darBaja(ModelMap modelo, @RequestParam(required=false) String id, @RequestParam(required=false) String idUsuario){
        try{
            encuestaServicio.bajaEncuesta(id);
            List<Encuesta> encuestas = usuarioServicio.listaQuerysUsuario(idUsuario);
            modelo.put("encuestas", encuestas);
            return "misQuerys";
        }catch(ErrorServicio ex){
            modelo.put("error", ex.getMessage());
            return "misQuerys";
        }
        
       
    }
     @GetMapping("/alta")
    public String darAlta(ModelMap modelo, @RequestParam(required=false) String id, @RequestParam(required=false) String idUsuario){
        try{
            //Usuario usuario= usuarioServicio.buscarPorId(idUsuario);
            //ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
            //HttpSession session = attr.getRequest().getSession(true);
            //session.setAttribute("usuariosession", usuario);
            //modelo.put("usuariosession", session.getAttribute("usuariosession"));
            //UserDetails user = usuarioServicio.loadUserByUsername(usuario.getEmail());
           
            encuestaServicio.altaEncuesta(id);
            List<Encuesta> encuestas = usuarioServicio.listaQuerysUsuario(idUsuario);
            modelo.put("encuestas", encuestas);
            return "misQuerys";
        }catch(ErrorServicio ex){
            modelo.put("error", ex.getMessage());
            return "misQuerys";
        }
        
    }
    
     @GetMapping("/eliminar")
    public String eliminar(ModelMap modelo, @RequestParam(required=false) String id){
        try{
            encuestaServicio.eliminar(id);
        }catch(ErrorServicio ex){
            modelo.put("error", ex.getMessage());
            
        }
        return "misQuerys";
    }
    
}
