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
    private String opcion1;
    @Column(nullable = false)
    private String opcion2;
    @Temporal(TemporalType.TIMESTAMP)
    private Date inicio;
    @Temporal(TemporalType.TIMESTAMP)
    private Date caducidad;
//    private Integer totalVotos;
//    private Integer totalMujeres;
//    private Integer totalHombres;
//    private Integer totalOtros;
    
    @OneToMany
    private List<Voto> votos;
    
    @OneToOne
    private ResultadosPorcentajes resultados;
    private Boolean alta;

    public Encuesta() {
    }

    public Encuesta(String id, String titulo, String opcion1, String opcion2, Date inicio, Date caducidad, List<Voto> votos, ResultadosPorcentajes resultados, Boolean alta) {
        this.id = id;
        this.titulo = titulo;
        this.opcion1 = opcion1;
        this.opcion2 = opcion2;
        this.inicio = inicio;
        this.caducidad = caducidad;
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

    public String getOpcion1() {
        return opcion1;
    }

    public void setOpcion1(String opcion1) {
        this.opcion1 = opcion1;
    }

    public String getOpcion2() {
        return opcion2;
    }

    public void setOpcion2(String opcion2) {
        this.opcion2 = opcion2;
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
