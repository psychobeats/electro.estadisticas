package com.AMQApp.entidades;

import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Usuario {
    
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    @Column(nullable = false, unique = true)
    private String alias;
    @Column(nullable = false)
    private Enum sexo;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private Enum pais;
    @Column(nullable = false)
    private Date nacimiento;
    @Column(nullable = false)
    private String clave;
    private String claveValidar;
    private Boolean alta;
    
    @OneToMany
    private List<Encuesta> encuestasCreadas;

    public Usuario() {
    }

    public Usuario(String id, String alias, Enum sexo, String email, Enum pais, Date nacimiento, String clave, String claveValidar, Boolean alta, List<Encuesta> encuestasCreadas) {
        this.id = id;
        this.alias = alias;
        this.sexo = sexo;
        this.email = email;
        this.pais = pais;
        this.nacimiento = nacimiento;
        this.clave = clave;
        this.claveValidar = claveValidar;
        this.alta = alta;
        this.encuestasCreadas = encuestasCreadas;
    }

    

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public Enum getSexo() {
        return sexo;
    }

    public void setSexo(Enum sexo) {
        this.sexo = sexo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Enum getPais() {
        return pais;
    }

    public void setPais(Enum pais) {
        this.pais = pais;
    }

    public Date getNacimiento() {
        return nacimiento;
    }

    public void setNacimiento(Date nacimiento) {
        this.nacimiento = nacimiento;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getClaveValidar() {
        return claveValidar;
    }

    public void setClaveValidar(String claveValidar) {
        this.claveValidar = claveValidar;
    }

    public Boolean getAlta() {
        return alta;
    }

    public void setAlta(Boolean alta) {
        this.alta = alta;
    }

    public List<Encuesta> getEncuestasCreadas() {
        return encuestasCreadas;
    }

    public void setEncuestasCreadas(List<Encuesta> encuestasCreadas) {
        this.encuestasCreadas = encuestasCreadas;
    }

    
}
