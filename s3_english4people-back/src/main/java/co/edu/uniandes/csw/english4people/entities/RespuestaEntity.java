
package co.edu.uniandes.csw.english4people.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class RespuestaEntity extends BaseEntity implements Serializable {
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    
    private String comentario;
    
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
