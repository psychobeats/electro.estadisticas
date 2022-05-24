package com.AMQApp.servicios;

import com.AMQApp.entidades.Usuario;
import com.AMQApp.entidades.Voto;
import com.AMQApp.errores.ErrorServicio;
import java.util.Date;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VotoServicio {
    
    @Transactional
    public Voto votar(Usuario usuario, String opcion) throws ErrorServicio{
        validar(usuario, opcion);
        
        Voto voto = new Voto();
        voto.setUsuario(usuario);
        voto.setOpcion(opcion);
        voto.setAlta(Boolean.TRUE);
        Date fecha = new Date();
        voto.setFecha(fecha);
        
        return voto;
        
    }
    
    public void validar(Usuario usuario, String opcion) throws ErrorServicio{
        if(usuario==null||usuario.toString().isEmpty()){
            throw new ErrorServicio("No existe el usuario");
        }
        if(opcion==null||opcion.isEmpty()){
            throw new ErrorServicio("Debe elegir una opción");
        }
    }
    
}
