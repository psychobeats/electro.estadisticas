package com.AMQApp.repositorios;

import com.AMQApp.entidades.Encuesta;
import com.AMQApp.entidades.Voto;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EncuestaRepositorio extends JpaRepository<Encuesta, String> {
    
    @Query("SELECT e FROM Encuesta e WHERE e.titulo = :titulo")
    public Encuesta buscarPorTitulo (@Param("titulo") String titulo);
    
//    @Query("SELECT e FROM Encuesta e WHERE e.alta = :true")
//    public List<Encuesta> buscarEncuestasPorAlta(@Param("alta") Boolean alta);
    
    @Query("SELECT e FROM Encuesta e ORDER BY SIZE(e.votos) DESC")
    public List<Encuesta> ordenarPorVotos();
}

     
