package com.AMQApp.entidades;

import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Encuesta {
    
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    @Column(nullable = false, unique = true)
    private String titulo;
    @Column(nullable = false)
    private String [] opciones;
    @Temporal(TemporalType.TIMESTAMP)
    private Date inicio;
    @Temporal(TemporalType.TIMESTAMP)
    private Date caducidad;
    private Integer totalVotos;
    private Integer totalMujeres;
    private Integer totalHombres;
    private Integer totalOtros;
    
    @OneToMany
    private List<Voto> votos;
    
    @OneToOne
    private ResultadosPorcentajes resultados;
    private Boolean alta;

    public Encuesta() {
    }

    public Encuesta(String titulo, String[] opciones, Date inicio, Date caducidad, Integer totalVotos, Integer totalMujeres, Integer totalHombres, Integer totalOtros, List<Voto> votos, ResultadosPorcentajes resultados, Boolean alta) {
        this.titulo = titulo;
        this.opciones = opciones;
        this.inicio = inicio;
        this.caducidad = caducidad;
        this.totalVotos = totalVotos;
        this.totalMujeres = totalMujeres;
        this.totalHombres = totalHombres;
        this.totalOtros = totalOtros;
        this.votos = votos;
        this.resultados = resultados;
        this.alta = alta;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String[] getOpciones() {
        return opciones;
    }

    public void setOpciones(String[] opciones) {
        this.opciones = opciones;
    }

    public Date getInicio() {
        return inicio;
    }

    public void setInicio(Date inicio) {
        this.inicio = inicio;
    }

    public Date getCaducidad() {
        return caducidad;
    }

    public void setCaducidad(Date caducidad) {
        this.caducidad = caducidad;
    }

    public Integer getTotalVotos() {
        return totalVotos;
    }

    public void setTotalVotos(Integer totalVotos) {
        this.totalVotos = totalVotos;
    }

    public Integer getTotalMujeres() {
        return totalMujeres;
    }

    public void setTotalMujeres(Integer totalMujeres) {
        this.totalMujeres = totalMujeres;
    }

    public Integer getTotalHombres() {
        return totalHombres;
    }

    public void setTotalHombres(Integer totalHombres) {
        this.totalHombres = totalHombres;
    }

    public Integer getTotalOtros() {
        return totalOtros;
    }

    public void setTotalOtros(Integer totalOtros) {
        this.totalOtros = totalOtros;
    }

    public List<Voto> getVotos() {
        return votos;
    }

    public void setVotos(List<Voto> votos) {
        this.votos = votos;
    }

    public ResultadosPorcentajes getResultados() {
        return resultados;
    }

    public void setResultados(ResultadosPorcentajes resultados) {
        this.resultados = resultados;
    }

    public Boolean getAlta() {
        return alta;
    }

    public void setAlta(Boolean alta) {
        this.alta = alta;
    }
    
    
}
