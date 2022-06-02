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
    
    //@OneToOne
    //private Encuesta encuesta;
    
    private Integer totalVotos;
    private Integer totalHombres;
    private Integer totalMujeres;
    private Integer totalOtros;
    private Integer totalOpcion1;
    private Integer totalOpcion2;
    private Double porcentajeHombres;
    private Double porcentajeMujeres;
    private Double porcentajeOtros;
    private Double porcentajeOpcion1;
    private Double porcentajeOpcion2;
      
    private Boolean alta;

    public ResultadosPorcentajes() {
    }

    public ResultadosPorcentajes(String id, Integer totalVotos, Integer totalHombres, Integer totalMujeres, Integer totalOtros, Integer totalOpcion1, Integer totalOpcion2, Double porcentajeHombres, Double porcentajeMujeres, Double porcentajeOtros, Double porcentajeOpcion1, Double porcentajeOpcion2, Boolean alta) {
        this.id = id;
        this.totalVotos = totalVotos;
        this.totalHombres = totalHombres;
        this.totalMujeres = totalMujeres;
        this.totalOtros = totalOtros;
        this.totalOpcion1 = totalOpcion1;
        this.totalOpcion2 = totalOpcion2;
        this.porcentajeHombres = porcentajeHombres;
        this.porcentajeMujeres = porcentajeMujeres;
        this.porcentajeOtros = porcentajeOtros;
        this.porcentajeOpcion1 = porcentajeOpcion1;
        this.porcentajeOpcion2 = porcentajeOpcion2;
        this.alta = alta;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Integer getTotalOpcion1() {
        return totalOpcion1;
    }

    public void setTotalOpcion1(Integer totalOpcion1) {
        this.totalOpcion1 = totalOpcion1;
    }

    public Integer getTotalOpcion2() {
        return totalOpcion2;
    }

    public void setTotalOpcion2(Integer totalOpcion2) {
        this.totalOpcion2 = totalOpcion2;
    }

    public Double getPorcentajeHombres() {
        return porcentajeHombres;
    }

    public void setPorcentajeHombres(Double porcentajeHombres) {
        this.porcentajeHombres = porcentajeHombres;
    }

    public Double getPorcentajeMujeres() {
        return porcentajeMujeres;
    }

    public void setPorcentajeMujeres(Double porcentajeMujeres) {
        this.porcentajeMujeres = porcentajeMujeres;
    }

    public Double getPorcentajeOtros() {
        return porcentajeOtros;
    }

    public void setPorcentajeOtros(Double porcentajeOtros) {
        this.porcentajeOtros = porcentajeOtros;
    }

    public Double getPorcentajeOpcion1() {
        return porcentajeOpcion1;
    }

    public void setPorcentajeOpcion1(Double porcentajeOpcion1) {
        this.porcentajeOpcion1 = porcentajeOpcion1;
    }

    public Double getPorcentajeOpcion2() {
        return porcentajeOpcion2;
    }

    public void setPorcentajeOpcion2(Double porcentajeOpcion2) {
        this.porcentajeOpcion2 = porcentajeOpcion2;
    }

    public Boolean getAlta() {
        return alta;
    }

    public void setAlta(Boolean alta) {
        this.alta = alta;
    }
    
    
    
}
