
package co.edu.uniandes.csw.english4people.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import uk.co.jemos.podam.common.PodamExclude;

@Entity
public class RespuestaEntity extends BaseEntity implements Serializable {
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    
    private String comentario;

    @PodamExclude
    @OneToOne
    private CalificacionEntity calificacion;
    
    public CalificacionEntity getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(CalificacionEntity calificacion) {
        this.calificacion = calificacion;
    }
    
    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
    
}
