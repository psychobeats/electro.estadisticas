package com.AMQApp.repositorios;

import com.AMQApp.entidades.ResultadosPorcentajes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResultadosPorcentajesRepositorio extends JpaRepository<ResultadosPorcentajes, String>{
    
}
