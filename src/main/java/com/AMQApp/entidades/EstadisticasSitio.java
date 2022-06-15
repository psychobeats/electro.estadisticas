package com.AMQApp.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import org.hibernate.annotations.GenericGenerator;


@Entity
public class EstadisticasSitio {
    
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    
    private Integer totalMujeresVotantes;
    private Integer totalHombresVotantes;
    private Integer totalOtrosVotantes;
    private Integer totalArgentinosVotantes;
    private Integer totalBrasilerosVotantes;
    private Integer totalColombianosVotantes;
    private Integer totalPeruanosVotantes;
    private Integer totalChilenosVotantes;
    private Integer totalVenezolanosVotantes;
    private Integer totalEcuatorianosVotantes;
    private Integer totalBolivianosVotantes;
    private Integer totalUruguayosVotantes;
    private Integer totalParaguayosVotantes;
    private Integer totalMexicanosVotantes;

    public EstadisticasSitio() {
    }

    public EstadisticasSitio(Integer totalMujeresVotantes, Integer totalHombresVotantes, Integer totalOtrosVotantes, Integer totalArgentinosVotantes, Integer totalBrasilerosVotantes, Integer totalColombianosVotantes, Integer totalPeruanosVotantes, Integer totalChilenosVotantes, Integer totalVenezolanosVotantes, Integer totalEcuatorianosVotantes, Integer totalBolivianosVotantes, Integer totalUruguayosVotantes, Integer totalParaguayosVotantes, Integer totalMexicanosVotantes) {
        this.totalMujeresVotantes = totalMujeresVotantes;
        this.totalHombresVotantes = totalHombresVotantes;
        this.totalOtrosVotantes = totalOtrosVotantes;
        this.totalArgentinosVotantes = totalArgentinosVotantes;
        this.totalBrasilerosVotantes = totalBrasilerosVotantes;
        this.totalColombianosVotantes = totalColombianosVotantes;
        this.totalPeruanosVotantes = totalPeruanosVotantes;
        this.totalChilenosVotantes = totalChilenosVotantes;
        this.totalVenezolanosVotantes = totalVenezolanosVotantes;
        this.totalEcuatorianosVotantes = totalEcuatorianosVotantes;
        this.totalBolivianosVotantes = totalBolivianosVotantes;
        this.totalUruguayosVotantes = totalUruguayosVotantes;
        this.totalParaguayosVotantes = totalParaguayosVotantes;
        this.totalMexicanosVotantes = totalMexicanosVotantes;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getTotalMujeresVotantes() {
        return totalMujeresVotantes;
    }

    public void setTotalMujeresVotantes(Integer totalMujeresVotantes) {
        this.totalMujeresVotantes = totalMujeresVotantes;
    }

    public Integer getTotalHombresVotantes() {
        return totalHombresVotantes;
    }

    public void setTotalHombresVotantes(Integer totalHombresVotantes) {
        this.totalHombresVotantes = totalHombresVotantes;
    }

    public Integer getTotalOtrosVotantes() {
        return totalOtrosVotantes;
    }

    public void setTotalOtrosVotantes(Integer totalOtrosVotantes) {
        this.totalOtrosVotantes = totalOtrosVotantes;
    }

    public Integer getTotalArgentinosVotantes() {
        return totalArgentinosVotantes;
    }

    public void setTotalArgentinosVotantes(Integer totalArgentinosVotantes) {
        this.totalArgentinosVotantes = totalArgentinosVotantes;
    }

    public Integer getTotalBrasilerosVotantes() {
        return totalBrasilerosVotantes;
    }

    public void setTotalBrasilerosVotantes(Integer totalBrasilerosVotantes) {
        this.totalBrasilerosVotantes = totalBrasilerosVotantes;
    }

    public Integer getTotalColombianosVotantes() {
        return totalColombianosVotantes;
    }

    public void setTotalColombianosVotantes(Integer totalColombianosVotantes) {
        this.totalColombianosVotantes = totalColombianosVotantes;
    }

    public Integer getTotalPeruanosVotantes() {
        return totalPeruanosVotantes;
    }

    public void setTotalPeruanosVotantes(Integer totalPeruanosVotantes) {
        this.totalPeruanosVotantes = totalPeruanosVotantes;
    }

    public Integer getTotalChilenosVotantes() {
        return totalChilenosVotantes;
    }

    public void setTotalChilenosVotantes(Integer totalChilenosVotantes) {
        this.totalChilenosVotantes = totalChilenosVotantes;
    }

    public Integer getTotalVenezolanosVotantes() {
        return totalVenezolanosVotantes;
    }

    public void setTotalVenezolanosVotantes(Integer totalVenezolanosVotantes) {
        this.totalVenezolanosVotantes = totalVenezolanosVotantes;
    }

    public Integer getTotalEcuatorianosVotantes() {
        return totalEcuatorianosVotantes;
    }

    public void setTotalEcuatorianosVotantes(Integer totalEcuatorianosVotantes) {
        this.totalEcuatorianosVotantes = totalEcuatorianosVotantes;
    }

    public Integer getTotalBolivianosVotantes() {
        return totalBolivianosVotantes;
    }

    public void setTotalBolivianosVotantes(Integer totalBolivianosVotantes) {
        this.totalBolivianosVotantes = totalBolivianosVotantes;
    }

    public Integer getTotalUruguayosVotantes() {
        return totalUruguayosVotantes;
    }

    public void setTotalUruguayosVotantes(Integer totalUruguayosVotantes) {
        this.totalUruguayosVotantes = totalUruguayosVotantes;
    }

    public Integer getTotalParaguayosVotantes() {
        return totalParaguayosVotantes;
    }

    public void setTotalParaguayosVotantes(Integer totalParaguayosVotantes) {
        this.totalParaguayosVotantes = totalParaguayosVotantes;
    }

    public Integer getTotalMexicanosVotantes() {
        return totalMexicanosVotantes;
    }

    public void setTotalMexicanosVotantes(Integer totalMexicanosVotantes) {
        this.totalMexicanosVotantes = totalMexicanosVotantes;
    }
    
}
