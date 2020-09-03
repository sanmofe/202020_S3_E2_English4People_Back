/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.english4people.entities;

import javax.persistence.Entity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import uk.co.jemos.podam.common.PodamExclude;
/**
 *
 * @author Juan David Becerra Romero
 */
@Entity
public class ChatEntity extends BaseEntity implements Serializable{

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

    /**
     * @return the estudiante
     */
    public EstudianteEntity getEstudiante() {
        return estudiante;
    }

    /**
     * @param estudiante the estudiante to set
     */
    public void setEstudiante(EstudianteEntity estudiante) {
        this.estudiante = estudiante;
    }

    /**
     * @return the mensajes
     */
    public List<MensajeEntity> getMensajes() {
        return mensajes;
    }

    /**
     * @param mensajes the mensajes to set
     */
    public void setMensajes(List<MensajeEntity> mensajes) {
        this.mensajes = mensajes;
    }
    
    @PodamExclude
    @ManyToOne
    private ProfesoresEntity profesor;
    
    @PodamExclude
    @ManyToOne
    private EstudianteEntity estudiante;
    
    @PodamExclude
    @OneToMany (mappedBy = "chat")
    private List<MensajeEntity> mensajes = new ArrayList<MensajeEntity>();
}
