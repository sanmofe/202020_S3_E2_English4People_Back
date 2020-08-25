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
    private boolean lunes;
    
    private boolean martes;
    
    private boolean miercoles;
    
    private boolean jueves;
    
    private boolean viernes;
    
    private boolean sabado;
    
    private boolean domingo;
    
    /**
     * @return the lunes
     */
    public boolean getLunes() {
        return lunes;
    }

    /**
     * @param lunes the lunes to set
     */
    public void setLunes(boolean lunes) {
        this.lunes = lunes;
    }

    /**
     * @return the martes
     */
    public boolean getMartes() {
        return martes;
    }

    /**
     * @param martes the martes to set
     */
    public void setMartes(boolean martes) {
        this.martes = martes;
    }

    /**
     * @return the miercoles
     */
    public boolean getMiercoles() {
        return miercoles;
    }

    /**
     * @param miercoles the miercoles to set
     */
    public void setMiercoles(boolean miercoles) {
        this.miercoles = miercoles;
    }

    /**
     * @return the jueves
     */
    public boolean getJueves() {
        return jueves;
    }

    /**
     * @param jueves the jueves to set
     */
    public void setJueves(boolean jueves) {
        this.jueves = jueves;
    }

    /**
     * @return the viernes
     */
    public boolean getViernes() {
        return viernes;
    }

    /**
     * @param viernes the viernes to set
     */
    public void setViernes(boolean viernes) {
        this.viernes = viernes;
    }

    /**
     * @return the sabado
     */
    public boolean getSabado() {
        return sabado;
    }

    /**
     * @param sabado the sabado to set
     */
    public void setSabado(boolean sabado) {
        this.sabado = sabado;
    }

    /**
     * @return the domingo
     */
    public boolean getDomingo() {
        return domingo;
    }

    /**
     * @param domingo the domingo to set
     */
    public void setDomingo(boolean domingo) {
        this.domingo = domingo;
    }
    
}
