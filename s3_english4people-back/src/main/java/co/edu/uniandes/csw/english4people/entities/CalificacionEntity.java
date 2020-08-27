
package co.edu.uniandes.csw.english4people.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class CalificacionEntity extends BaseEntity implements Serializable{
    
    private Double calificacionNumerica;
    
    private String comentario;
    
    private String nombreEstudiante;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    

    public Double getCalificacionNumerica() {
        return calificacionNumerica;
    }

    public void setCalificacionNumerica(Double calificacionNumerica) {
        this.calificacionNumerica = calificacionNumerica;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getNombreEstudiante() {
        return nombreEstudiante;
    }

    public void setNombreEstudiante(String nombreEstudiante) {
        this.nombreEstudiante = nombreEstudiante;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
}
