package com.AMQApp.servicios;

import com.AMQApp.entidades.Encuesta;
import com.AMQApp.entidades.Usuario;
import com.AMQApp.enums.Pais;
import com.AMQApp.enums.Rol;
import com.AMQApp.enums.Sexo;
import com.AMQApp.errores.ErrorServicio;
import com.AMQApp.repositorios.UsuarioRepositorio;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Service
public class UsuarioServicio implements UserDetailsService{
    
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;
    
    @Autowired
    private EncuestaServicio encuestaServicio;
    
    @Transactional(propagation = Propagation.REQUIRED)
    public void crear(String alias, Sexo sexo, String email, Pais pais, String nacimiento, String clave, String claveValidar) throws ErrorServicio, ParseException{
        System.out.println(nacimiento);
        validar(alias, email, nacimiento, clave, claveValidar);
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        Date nacimiento1 = formato.parse(nacimiento);
        validarNacimientoDate(nacimiento1);
        Usuario usuario = new Usuario();
        usuario.setAlias(alias);
        usuario.setAlta(Boolean.TRUE);
        usuario.setEmail(email);
        usuario.setNacimiento(nacimiento1);
        usuario.setPais(pais);
        usuario.setSexo(sexo);
        
        String claveEncriptada = new BCryptPasswordEncoder().encode(clave);
        usuario.setClave(claveEncriptada);
        usuario.setRol(Rol.USUARIO);
        List<Encuesta> encuestas = new ArrayList();
        usuario.setEncuestasCreadas(encuestas);
        usuarioRepositorio.save(usuario);
    }
    
    public void crearEncuesta(String idUsuario, String titulo, String opcion1, String opcion2) throws ErrorServicio{

        Optional<Usuario> respuesta = usuarioRepositorio.findById(idUsuario);
        if(respuesta.isPresent()){
            Usuario usuario = respuesta.get();
            Encuesta e1 = encuestaServicio.crearEncuesta(titulo, opcion1, opcion2);
            usuario.getEncuestasCreadas().add(e1);
            usuarioRepositorio.save(usuario);
        }else{
            throw new ErrorServicio("No se encontró el usuario");
        }
        
    }
    
    @Transactional(propagation = Propagation.NESTED)
    public void modificar(String id, String alias, Sexo sexo, String email, Pais pais, String nacimiento, String clave, String claveValidar) throws ErrorServicio, ParseException{
        System.out.println(nacimiento);
        validar(alias, email, nacimiento, clave, claveValidar);
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        Date nacimiento1 = formato.parse(nacimiento);
        validarNacimientoDate(nacimiento1);
        Optional<Usuario> respuesta = usuarioRepositorio.findById(id);
        if(respuesta.isPresent()){
            Usuario usuario = respuesta.get();
            usuario.setAlias(alias);
            usuario.setSexo(sexo);
            usuario.setEmail(email);
            usuario.setPais(pais);
            usuario.setNacimiento(nacimiento1);
            usuario.setClave(clave);
            usuarioRepositorio.save(usuario);
        }else{
            throw new ErrorServicio("No se encontró el usuario");
        }
    }
    
    @Transactional(propagation = Propagation.NESTED)
    public void darBaja(String id) throws ErrorServicio{
        Optional<Usuario> respuesta = usuarioRepositorio.findById(id);
        if(respuesta.isPresent()){
            Usuario usuario = respuesta.get();
            usuario.setAlta(Boolean.FALSE);
            usuarioRepositorio.save(usuario);
        }else{
            throw new ErrorServicio("No se encontró el usuario");
        }
    }
    
    @Transactional(propagation = Propagation.NESTED)
    public void darAlta(String id) throws ErrorServicio{
        Optional<Usuario> respuesta = usuarioRepositorio.findById(id);
        if(respuesta.isPresent()){
            Usuario usuario = respuesta.get();
            usuario.setAlta(Boolean.TRUE);
            usuarioRepositorio.save(usuario);
        }else{
            throw new ErrorServicio("No se encontró el usuario");
        }
    }
    
    @Transactional(readOnly=true)
    public List<Usuario> listar() throws ErrorServicio{
        List<Usuario> usuarios = usuarioRepositorio.findAll();
        if(usuarios.isEmpty()||usuarios==null){
            throw new ErrorServicio("No se pudo cargar la lista de usuarios del repositorio");
        }else{
        return usuarioRepositorio.findAll();
        }
    }
    
    public Usuario buscarPorId(String id) throws ErrorServicio{
        Optional<Usuario> respuesta = usuarioRepositorio.findById(id);
        if(respuesta.isPresent()){
            return respuesta.get();
        }else{
            throw new ErrorServicio("No se encontró el usuario");
        }
    }
    
    public Usuario buscarPorEmail(String email) throws ErrorServicio{
        System.out.println(email);
        if(usuarioRepositorio.buscarPorEmail(email)!=null){
            return usuarioRepositorio.buscarPorEmail(email);
        }else{
            throw new ErrorServicio("No se encontró ningún usuario con el email indicado");
        }    
    }
    
    public List<Usuario> buscarPorAlias(String alias) throws ErrorServicio{
        if(usuarioRepositorio.buscarPorAlias(alias)!=null){
        return usuarioRepositorio.buscarPorAlias(alias);
        }else{
            throw new ErrorServicio("No se encontró ningún usuario con el alias indicado");
        }
    }
    
    public void borrar(String id) throws ErrorServicio{
        Optional<Usuario> respuesta = usuarioRepositorio.findById(id);
        if(respuesta.isPresent()){
            usuarioRepositorio.delete(respuesta.get());
        }else{
            throw new ErrorServicio("No se encontró el usuario");
        }
    }
    
    public void validar(String alias, String email,String nacimiento, String clave, String claveValidar) throws ErrorServicio{
        if(alias==null||alias.isEmpty()){
            throw new ErrorServicio("Debe indicar un alias");
        }
        System.out.println("LAS VALIDACIONES ALIAS CORRECTAS");
        if(email==null||email.isEmpty()){
            throw new ErrorServicio("Debe indicar un alias");
        }
        if(nacimiento==null|| nacimiento.isEmpty()){
            throw new ErrorServicio("Debe indicar su fecha de nacimiento");
        }
        System.out.println("LA VALIDACIÓN DEL NACIMIENTO Fue CORRECTA");
        
        System.out.println("LAS VALIDACIONES EMAIL CORRECTAS");
        if(clave==null||clave.isEmpty()){
            throw new ErrorServicio("Debe indicar una clave");
        }
        if (clave.length()<6){
            throw new ErrorServicio("La clave debe tener al menos 6 caracteres");
        }
        System.out.println("LAS VALIDACIONES CLAVE CORRECTAS");

        if(claveValidar==null||claveValidar.isEmpty()){
            throw new ErrorServicio("Debe repetir su clave");
        }
        if (!clave.equals(claveValidar)) {
            throw new ErrorServicio("Las claves no coinciden");
        }
        System.out.println("LAS VALIDACIONES CLAVE_VALIDAR CORRECTAS");
    }

    public void validarNacimientoDate(Date nacimiento1) throws ErrorServicio {
        if(nacimiento1==null){
            throw new ErrorServicio("Debe indicar su fecha de nacimiento");
        }
        System.out.println("LA VALIDACIÓN DEL NACIMIENTO Fue CORRECTA");
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepositorio.buscarPorEmail(email);
        if(usuario!= null){
            List<GrantedAuthority> permisos = new ArrayList<>();
            GrantedAuthority p1 = new SimpleGrantedAuthority("ROLE_"+usuario.getRol());
            permisos.add(p1);
            
            ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
            HttpSession session = attr.getRequest().getSession(true);
            session.setAttribute("usuariosession", usuario);
            
            User user = new User(usuario.getEmail(), usuario.getClave(), permisos);
            return user;
        }else{
            return null;
        }
    }
    
    
    
}
