package com.AMQApp.repositorios;

import com.AMQApp.entidades.Usuario;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, String>{
    
    @Query("SELECT u FROM Usuario u WHERE u.alias = :ALIAS")
    public List<Usuario> buscarPorAlias(@Param("ALIAS") String alias);
    
    @Query("SELECT u FROM Usuario u WHERE u.email = :EMAIL")
    public Usuario buscarPorEmail(@Param("EMAIL") String email);
}
