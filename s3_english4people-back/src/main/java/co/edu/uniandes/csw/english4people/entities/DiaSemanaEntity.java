/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.english4people.entities;

import java.io.Serializable;
import javax.persistence.Entity;

/**
 *
 * @author Juan Diego Gonzalez Gomez
 */
@Entity
public class DiaSemanaEntity extends BaseEntity implements Serializable
{
    private Boolean lunes;
    
    private Boolean martes;
    
    private Boolean miercoles;
    
    private Boolean jueves;
    
    private Boolean viernes;
    
    private Boolean sabado;
    
    private Boolean domingo;
    
    /**
     * @return the lunes
     */
    public Boolean getLunes() {
        return lunes;
    }

    /**
     * @param lunes the lunes to set
     */
    public void setLunes(Boolean lunes) {
        this.lunes = lunes;
    }

    /**
     * @return the martes
     */
    public Boolean getMartes() {
        return martes;
    }

    /**
     * @param martes the martes to set
     */
    public void setMartes(Boolean martes) {
        this.martes = martes;
    }

    /**
     * @return the miercoles
     */
    public Boolean getMiercoles() {
        return miercoles;
    }

    /**
     * @param miercoles the miercoles to set
     */
    public void setMiercoles(Boolean miercoles) {
        this.miercoles = miercoles;
    }

    /**
     * @return the jueves
     */
    public Boolean getJueves() {
        return jueves;
    }

    /**
     * @param jueves the jueves to set
     */
    public void setJueves(Boolean jueves) {
        this.jueves = jueves;
    }

    /**
     * @return the viernes
     */
    public Boolean getViernes() {
        return viernes;
    }

    /**
     * @param viernes the viernes to set
     */
    public void setViernes(Boolean viernes) {
        this.viernes = viernes;
    }

    /**
     * @return the sabado
     */
    public Boolean getSabado() {
        return sabado;
    }

    /**
     * @param sabado the sabado to set
     */
    public void setSabado(Boolean sabado) {
        this.sabado = sabado;
    }

    /**
     * @return the domingo
     */
    public Boolean getDomingo() {
        return domingo;
    }

    /**
     * @param domingo the domingo to set
     */
    public void setDomingo(Boolean domingo) {
        this.domingo = domingo;
    }   
}
