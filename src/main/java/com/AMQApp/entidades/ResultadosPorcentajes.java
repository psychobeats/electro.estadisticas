package com.AMQApp.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import org.hibernate.annotations.GenericGenerator;


@Entity
public class ResultadosPorcentajes {
    
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    
    @OneToOne
    private Encuesta encuesta;
    
    private Integer totalVotos;
    private Integer totalHombres;
    private Integer totalMujeres;
    private Integer totalOtros;
    
    private Boolean alta;

    public ResultadosPorcentajes() {
    }

    public ResultadosPorcentajes(Encuesta encuesta, Integer totalVotos, Integer totalHombres, Integer totalMujeres, Integer totalOtros, Boolean alta) {
        this.encuesta = encuesta;
        this.totalVotos = totalVotos;
        this.totalHombres = totalHombres;
        this.totalMujeres = totalMujeres;
        this.totalOtros = totalOtros;
        this.alta = alta;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Encuesta getEncuesta() {
        return encuesta;
    }

    public void setEncuesta(Encuesta encuesta) {
        this.encuesta = encuesta;
    }

    public Integer getTotalVotos() {
        return totalVotos;
    }

    public void setTotalVotos(Integer totalVotos) {
        this.totalVotos = totalVotos;
    }

    public Integer getTotalHombres() {
        return totalHombres;
    }

    public void setTotalHombres(Integer totalHombres) {
        this.totalHombres = totalHombres;
    }

    public Integer getTotalMujeres() {
        return totalMujeres;
    }

    public void setTotalMujeres(Integer totalMujeres) {
        this.totalMujeres = totalMujeres;
    }

    public Integer getTotalOtros() {
        return totalOtros;
    }

    public void setTotalOtros(Integer totalOtros) {
        this.totalOtros = totalOtros;
    }

    public Boolean getAlta() {
        return alta;
    }

    public void setAlta(Boolean alta) {
        this.alta = alta;
    }
    
    
}
