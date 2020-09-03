
package co.edu.uniandes.csw.english4people.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import uk.co.jemos.podam.common.PodamExclude;

@Entity
public class CalificacionEntity extends BaseEntity implements Serializable{
    
    private Double calificacionNumerica;
    
    private String comentario;
    
    private String nombreEstudiante;
    
    /*
    @PodamExclude
    @OneToOne(mappedBy = "calificacion", fetch=FetchType.LAZY)
    private RespuestaEntity respuesta;
    */
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    
    /*
    @ManyToOne
    private ProfesoresEntity profesor;
    */
    /*
    public RespuestaEntity getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(RespuestaEntity respuesta) {
        this.respuesta = respuesta;
    }
    */

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
