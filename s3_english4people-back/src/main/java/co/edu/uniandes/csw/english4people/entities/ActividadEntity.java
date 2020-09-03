/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.english4people.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import uk.co.jemos.podam.common.PodamExclude;
/*
import javax.persistence.ManyToOne;
import uk.co.jemos.podam.common.PodamExclude;*/

/**
 *
 * @author Juan Diego Castellanos Bonilla 
 */
@Entity
public class ActividadEntity extends BaseEntity implements Serializable {
    
   @Temporal(TemporalType.TIMESTAMP)
   private Date fecha;
   
   private String tipo;
   
   private String descripcion;
    
    @PodamExclude
    @ManyToOne
    private ProfesoresEntity profesor;
    
    @PodamExclude
    @ManyToMany
    private List<EstudianteEntity> estudiantes = new ArrayList<EstudianteEntity>();
   
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
     * @return the tipo
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
   
}
