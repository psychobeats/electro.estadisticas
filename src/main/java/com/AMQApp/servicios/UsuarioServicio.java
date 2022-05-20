package com.AMQApp.servicios;

import com.AMQApp.entidades.Usuario;
import com.AMQApp.enums.Pais;
import com.AMQApp.enums.Sexo;
import com.AMQApp.errores.ErrorServicio;
import com.AMQApp.repositorios.UsuarioRepositorio;
import java.util.Date;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioServicio {
    
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;
    
    @Transactional(propagation = Propagation.NESTED)
    public void crearUsuario(String alias, Sexo sexo, String email, Pais pais, Date nacimiento, String clave, String claveValidar) throws ErrorServicio{
        validar(alias, email, nacimiento, clave, claveValidar);
        Usuario usuario = new Usuario();
        usuario.setAlias(alias);
        usuario.setAlta(Boolean.TRUE);
        usuario.setEmail(email);
        usuario.setNacimiento(nacimiento);
        usuario.setPais(pais);
        usuario.setSexo(sexo);
        usuario.setClave(clave);
        usuarioRepositorio.save(usuario);
    }
    
    @Transactional(propagation = Propagation.NESTED)
    public void modificarUsuario(String id, String alias, Sexo sexo, String email, Pais pais, Date nacimiento, String clave, String claveValidar) throws ErrorServicio{
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
            throw new ErrorServicio("El usuario no se encuentra en la base de datos");
        }
    }
    
    @Transactional(propagation = Propagation.NESTED)
    public void darBaja(String id){
        
    }
    
    public void validar(String alias, String email, Date nacimiento, String clave, String claveValidar) throws ErrorServicio{
        if(alias==null||alias.isEmpty()){
            throw new ErrorServicio("Debe indicar un alias");
        }
        if(nacimiento==null){
            throw new ErrorServicio("Debe indicar su fecha de nacimiento");
        }
        if(clave==null||clave.isEmpty()){
            throw new ErrorServicio("Debe indica una clave");
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
