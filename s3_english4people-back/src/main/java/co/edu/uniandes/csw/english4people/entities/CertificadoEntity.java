/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.english4people.entities;


import javax.persistence.Entity;
import java.io.Serializable;
import javax.persistence.ManyToOne;
import uk.co.jemos.podam.common.PodamExclude;
/*
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import uk.co.jemos.podam.common.PodamExclude;
import uk.co.jemos.podam.common.PodamStrategyValue;
import co.edu.uniandes.csw.english4people.podam.DateStrategy;
*/

/**
 *
 * @author Juan Andres Santiago
 */

@Entity
public class CertificadoEntity extends BaseEntity implements Serializable{

    /**
     * @return the profesor
     */
    public ProfesoresEntity getProfesor() {
        return profesor;
    }

    /**
     * @param profesor the profesor to set
     */
    public void setProfesor(ProfesoresEntity profesor) {
        this.profesor = profesor;
    }
    
     private String nombre;
     
     private String idioma;
     
     @PodamExclude
     @ManyToOne
     private ProfesoresEntity profesor;
     
      /**
     * @return el nombre
     */
    public String getNombre(){
        return nombre;
    }
    
    /**
     * @param nombre the name to set
     */
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    
     /**
     * @return el idioma
     */
    public String getIdioma(){
        return idioma;
    }
    
    /**
     * @param idioma the language to set
     */
    public void setIdioma(String idioma){
        this.idioma = idioma;
    }
}
