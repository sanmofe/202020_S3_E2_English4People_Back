/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.english4people.entities;

/**
 *
 * @author Sara Plazas
 */
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author Sara Plazas
 */
@Entity
public class HorarioEntity extends BaseEntity implements Serializable{

    /**
     * @return the diaSemana
     */
    public DiaSemanaEntity getDiaSemana() {
        return diaSemana;
    }

    /**
     * @param diaSemana the diaSemana to set
     */
    public void setDiaSemana(DiaSemanaEntity diaSemana) {
        this.diaSemana = diaSemana;
    }

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
    
     @Temporal(TemporalType.TIMESTAMP)
    private Date horaInicio;
    
     @Temporal(TemporalType.TIMESTAMP)
    private Date horaFin;
     
    @PodamExclude
    private DiaSemanaEntity diaSemana;

    @PodamExclude
    @ManyToOne
    private ProfesoresEntity profesor;
    
     /**
      * 
      * @return the horaInicio
      */
    public Date getHoraInicio() {
        return horaInicio;
    }
    
    /**
     * 
     * @return the horaFin
     */
    public Date getHoraFin() {
        return horaFin;
    }

    /**
     * 
     * @param horaInicio the horaInicio to set
     */
    public void setHoraInicio(Date horaInicio) {
        this.horaInicio = horaInicio;
    }

    /**
     * 
     * @param horaFin the horaFin to set
     */
    public void setHoraFin(Date horaFin) {
        this.horaFin = horaFin;
    }
    
}
