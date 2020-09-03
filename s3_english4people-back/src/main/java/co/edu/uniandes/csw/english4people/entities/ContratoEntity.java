/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.english4people.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author Sara Plazas
 */
@Entity
public class ContratoEntity extends BaseEntity implements Serializable{
    
    private Integer numeroHoras;
    
    private Float costo;
    
    private String medioPago;
    
    /**
    @PodamExclude
    @OneToOne
    private HorarioEntity horario;
    */

    /**
     * 
     * @return the numeroHoras
     */
    public Integer getNumeroHoras() {
        return numeroHoras;
    }

    /**
     * 
     * @return the costo
     */
    public Float getCosto() {
        return costo;
    }

    /**
     * 
     * @return the medioPago
     */
    public String getMedioPago() {
        return medioPago;
    }

    /**
     * 
     * @param numeroHoras the numeroHoras to set
     */
    public void setNumeroHoras(Integer numeroHoras) {
        this.numeroHoras = numeroHoras;
    }

    /**
     * 
     * @param costo the costo to set
     */
    public void setCosto(Float costo) {
        this.costo = costo;
    }

    /**
     * 
     * @param medioPago the medioPago to set
     */
    public void setMedioPago(String medioPago) {
        this.medioPago = medioPago;
    }
    
}
