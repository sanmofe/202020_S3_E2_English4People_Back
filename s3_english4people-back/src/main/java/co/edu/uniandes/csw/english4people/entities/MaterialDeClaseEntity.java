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
 * @author Juan Diego Castellanos Bonilla
 */
@Entity
public class MaterialDeClaseEntity extends BaseEntity implements Serializable {
    
    private String enlaceMaterial;

    /**
     * @return the enlaceMaterial
     */
    public String getEnlaceMaterial() {
        return enlaceMaterial;
    }

    /**
     * @param enlaceMaterial the enlaceMaterial to set
     */
    public void setEnlaceMaterial(String enlaceMaterial) {
        this.enlaceMaterial = enlaceMaterial;
    }

}
