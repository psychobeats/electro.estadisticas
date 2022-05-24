package com.AMQApp.repositorios;

import com.AMQApp.entidades.EstadisticasSitio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EstadisticasSitioRepositorio extends JpaRepository<EstadisticasSitio, String>{
    
}
