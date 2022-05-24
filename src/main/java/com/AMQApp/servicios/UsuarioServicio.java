package com.AMQApp.servicios;

import com.AMQApp.entidades.Encuesta;
import com.AMQApp.entidades.Usuario;
import com.AMQApp.enums.Pais;
import com.AMQApp.enums.Sexo;
import com.AMQApp.errores.ErrorServicio;
import com.AMQApp.repositorios.UsuarioRepositorio;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioServicio {
    
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;
    
    @Autowired
    private EncuestaServicio encuestaServcio;
    
    @Transactional(propagation = Propagation.NESTED)
    public void crear(String alias, Sexo sexo, String email, Pais pais, Date nacimiento, String clave, String claveValidar) throws ErrorServicio{
        validar(alias, email, nacimiento, clave, claveValidar);
        Usuario usuario = new Usuario();
        usuario.setAlias(alias);
        usuario.setAlta(Boolean.TRUE);
        usuario.setEmail(email);
        usuario.setNacimiento(nacimiento);
        usuario.setPais(pais);
        usuario.setSexo(sexo);
        usuario.setClave(clave);
        List<Encuesta> encuestas = new ArrayList();
        usuario.setEncuestasCreadas(encuestas);
        usuarioRepositorio.save(usuario);
    }
    
    public void crearEncuesta(String id, String titulo, String[] opciones, 
            Date inicio, Date caducidad, Integer totalVotos, Integer totalMujeres, 
            Integer totalHombres, Integer totalOtros) throws ErrorServicio{
        
        Optional<Usuario> respuesta = usuarioRepositorio.findById(id);
        if(respuesta.isPresent()){
            Usuario usuario = respuesta.get();
        }else{
            throw new ErrorServicio("No se encontró el usuario");
        }
        
    }
    
    @Transactional(propagation = Propagation.NESTED)
    public void modificar(String id, String alias, Sexo sexo, String email, Pais pais, Date nacimiento, String clave, String claveValidar) throws ErrorServicio{
        validar(alias, email, nacimiento, clave, claveValidar);
        Optional<Usuario> respuesta = usuarioRepositorio.findById(id);
        if(respuesta.isPresent()){
            Usuario usuario = respuesta.get();
            usuario.setAlias(alias);
            usuario.setSexo(sexo);
            usuario.setEmail(email);
            usuario.setPais(pais);
            usuario.setNacimiento(nacimiento);
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
    
    public void validar(String alias, String email, Date nacimiento, String clave, String claveValidar) throws ErrorServicio{
        if(alias==null||alias.isEmpty()){
            throw new ErrorServicio("Debe indicar un alias");
        }
        if(nacimiento==null){
            throw new ErrorServicio("Debe indicar su fecha de nacimiento");
        }
        if(clave==null||clave.isEmpty()){
            throw new ErrorServicio("Debe indicar una clave");
        }else if (clave.length()<6){
            throw new ErrorServicio("La clave debe tener al menos 6 caracteres");
        }
        if(claveValidar==null||claveValidar.isEmpty()){
            throw new ErrorServicio("Debe repetir su clave");
        }else if (!clave.equals(claveValidar)) {
                throw new ErrorServicio("Las claves no coinciden");
            
        }
    }
    
}
