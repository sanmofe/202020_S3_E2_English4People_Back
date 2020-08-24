/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.english4people.entities;

import javax.persistence.Entity;

/**
 *
 * @author estudiante
 */
@Entity
public class IdiomaEntity extends BaseEntity{

        private boolean espanol;
    
    private boolean ingles;
    
    private boolean frances;
    
    private boolean italiano;
    
    private boolean portugues;
    
    private boolean aleman;
    
    private boolean japones;
    
    private boolean coreano;
    
    private boolean mandarin;
    
    /**
     * @return the espanol
     */
    public boolean getEspanol() {
        return espanol;
    }

    /**
     * @param espanol the espanol to set
     */
    public void setEspanol(boolean espanol) {
        this.espanol = espanol;
    }

    /**
     * @return the ingles
     */
    public boolean getIngles() {
        return ingles;
    }

    /**
     * @param ingles the ingles to set
     */
    public void setIngles(boolean ingles) {
        this.ingles = ingles;
    }

    /**
     * @return the frances
     */
    public boolean getFrances() {
        return frances;
    }

    /**
     * @param frances the frances to set
     */
    public void setFrances(boolean frances) {
        this.frances = frances;
    }

    /**
     * @return the italiano
     */
    public boolean getItaliano() {
        return italiano;
    }

    /**
     * @param italiano the italiano to set
     */
    public void setItaliano(boolean italiano) {
        this.italiano = italiano;
    }

    /**
     * @return the portugues
     */
    public boolean getPortugues() {
        return portugues;
    }

    /**
     * @param portugues the portugues to set
     */
    public void setPortugues(boolean portugues) {
        this.portugues = portugues;
    }

    /**
     * @return the aleman
     */
    public boolean getAleman() {
        return aleman;
    }

    /**
     * @param aleman the aleman to set
     */
    public void setAleman(boolean aleman) {
        this.aleman = aleman;
    }

    /**
     * @return the japones
     */
    public boolean getJapones() {
        return japones;
    }

    /**
     * @param japones the japones to set
     */
    public void setJapones(boolean japones) {
        this.japones = japones;
    }

    /**
     * @return the coreano
     */
    public boolean getCoreano() {
        return coreano;
    }

    /**
     * @param coreano the coreano to set
     */
    public void setCoreano(boolean coreano) {
        this.coreano = coreano;
    }

    /**
     * @return the mandarin
     */
    public boolean getMandarin() {
        return mandarin;
    }

    /**
     * @param mandarin the mandarin to set
     */
    public void setMandarin(boolean mandarin) {
        this.mandarin = mandarin;
    }
}
