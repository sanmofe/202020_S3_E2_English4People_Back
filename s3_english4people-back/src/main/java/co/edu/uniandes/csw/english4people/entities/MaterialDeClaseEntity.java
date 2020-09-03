/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.english4people.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import uk.co.jemos.podam.common.PodamExclude;

/*
import javax.persistence.FetchType;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import uk.co.jemos.podam.common.PodamExclude;*/

/**
 *
 * @author Juan Diego Castellanos Bonilla
 */
@Entity
public class MaterialDeClaseEntity extends BaseEntity implements Serializable
{

    /**
     * @return the clase
     */
    public ClaseEntity getClase() {
        return clase;
    }

    /**
     * @param clase the clase to set
     */
    public void setClase(ClaseEntity clase) {
        this.clase = clase;
    }
    private String enlaceMaterial;
    
    @PodamExclude
    @ManyToOne
    private ClaseEntity clase;
    
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
