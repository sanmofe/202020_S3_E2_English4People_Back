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
public class IdiomaEntity extends BaseEntity implements Serializable
{
    private Boolean espanol;
    
    private Boolean ingles;
    
    private Boolean frances;
    
    private Boolean italiano;
    
    private Boolean portugues;
    
    private Boolean aleman;
    
    private Boolean japones;
    
    private Boolean coreano;
    
    private Boolean mandarin;
    
    /**
     * @return the espanol
     */
    public Boolean getEspanol() {
        return espanol;
    }

    /**
     * @param espanol the espanol to set
     */
    public void setEspanol(Boolean espanol) {
        this.espanol = espanol;
    }

    /**
     * @return the ingles
     */
    public Boolean getIngles() {
        return ingles;
    }

    /**
     * @param ingles the ingles to set
     */
    public void setIngles(Boolean ingles) {
        this.ingles = ingles;
    }

    /**
     * @return the frances
     */
    public Boolean getFrances() {
        return frances;
    }

    /**
     * @param frances the frances to set
     */
    public void setFrances(Boolean frances) {
        this.frances = frances;
    }

    /**
     * @return the italiano
     */
    public Boolean getItaliano() {
        return italiano;
    }

    /**
     * @param italiano the italiano to set
     */
    public void setItaliano(Boolean italiano) {
        this.italiano = italiano;
    }

    /**
     * @return the portugues
     */
    public Boolean getPortugues() {
        return portugues;
    }

    /**
     * @param portugues the portugues to set
     */
    public void setPortugues(Boolean portugues) {
        this.portugues = portugues;
    }

    /**
     * @return the aleman
     */
    public Boolean getAleman() {
        return aleman;
    }

    /**
     * @param aleman the aleman to set
     */
    public void setAleman(Boolean aleman) {
        this.aleman = aleman;
    }

    /**
     * @return the japones
     */
    public Boolean getJapones() {
        return japones;
    }

    /**
     * @param japones the japones to set
     */
    public void setJapones(Boolean japones) {
        this.japones = japones;
    }

    /**
     * @return the coreano
     */
    public Boolean getCoreano() {
        return coreano;
    }

    /**
     * @param coreano the coreano to set
     */
    public void setCoreano(Boolean coreano) {
        this.coreano = coreano;
    }

    /**
     * @return the mandarin
     */
    public Boolean getMandarin() {
        return mandarin;
    }

    /**
     * @param mandarin the mandarin to set
     */
    public void setMandarin(Boolean mandarin) {
        this.mandarin = mandarin;
    }
}
