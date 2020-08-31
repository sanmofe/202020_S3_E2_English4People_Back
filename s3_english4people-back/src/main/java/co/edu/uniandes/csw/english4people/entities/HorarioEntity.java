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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Sara Plazas
 */
@Entity
public class HorarioEntity extends BaseEntity implements Serializable{
    
     @Temporal(TemporalType.TIMESTAMP)
    private Date horaInicio;
    
     @Temporal(TemporalType.TIMESTAMP)
    private Date horaFin;

    public Date getHoraInicio() {
        return horaInicio;
    }
    
    public Date getHoraFin() {
        return horaFin;
    }

    public void setHoraInicio(Date horaIni) {
        this.horaInicio = horaIni;
    }

    public void setHoraFin(Date horaFina) {
        this.horaFin = horaFina;
    }
    
}
