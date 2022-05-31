package com.AMQApp.entidades;

import com.AMQApp.enums.Pais;
import com.AMQApp.enums.Rol;
import com.AMQApp.enums.Sexo;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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
    private Sexo sexo;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private Pais pais;
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date nacimiento;
    @Column(nullable = false)
    private String clave;
    private Boolean alta;
    @Enumerated(EnumType.STRING)
    private Rol rol;
    
    @OneToMany
    private List<Encuesta> encuestasCreadas;

    public Usuario() {
    }

    public Usuario(String id, String alias, Sexo sexo, String email, Pais pais, Date nacimiento, String clave, Boolean alta, Rol rol, List<Encuesta> encuestasCreadas) {
        this.id = id;
        this.alias = alias;
        this.sexo = sexo;
        this.email = email;
        this.pais = pais;
        this.nacimiento = nacimiento;
        this.clave = clave;
        this.alta = alta;
        this.rol=rol;
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

    public void setSexo(Sexo sexo) {
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

    public void setPais(Pais pais) {
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

    public Boolean getAlta() {
        return alta;
    }

    public void setAlta(Boolean alta) {
        this.alta = alta;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }
    
    
    

    public List<Encuesta> getEncuestasCreadas() {
        return encuestasCreadas;
    }

    public void setEncuestasCreadas(List<Encuesta> encuestasCreadas) {
        this.encuestasCreadas = encuestasCreadas;
    }
    
    

    
}
