package com.AMQApp.repositorios;

import com.AMQApp.entidades.Encuesta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EncuestaRepositorio extends JpaRepository<Encuesta, String> {
    
    @Query("SELECT e FROM Encuesta e WHERE e.titulo LIKE :%titulo%")
    public Encuesta buscarPorTitulo (@Param("titulo") String titulo);
}
