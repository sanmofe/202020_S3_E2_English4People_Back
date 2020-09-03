
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

    /**
     * @return the respuesta
     */
    public RespuestaEntity getRespuesta() {
        return respuesta;
    }

    /**
     * @param respuesta the respuesta to set
     */
    public void setRespuesta(RespuestaEntity respuesta) {
        this.respuesta = respuesta;
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
    
    private Double calificacionNumerica;
    
    private String comentario;
    
    private String nombreEstudiante;
    
    @PodamExclude
    private RespuestaEntity respuesta;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    
    @PodamExclude
    @ManyToOne
    private ProfesoresEntity profesor;

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
