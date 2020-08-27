/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.english4people.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Temporal;

/**
 *
 * @author Juan Diego Castellanos Bonilla
 */
@Entity
public class ClaseEntity extends BaseEntity implements Serializable {
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fecha;
    
    private String tipoClase;

    /**
     * @return the fecha
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    /**
     * @return the tipoClase
     */
    public String getTipoClase() {
        return tipoClase;
    }

    /**
     * @param tipoClase the tipoClase to set
     */
    public void setTipoClase(String tipoClase) {
        this.tipoClase = tipoClase;
    }
}
