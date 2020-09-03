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
import javax.persistence.ManyToOne;
import uk.co.jemos.podam.common.PodamExclude;
/**
 *
 * @author Juan David Becerra Romero
 */
@Entity
public class ChatEntity extends BaseEntity implements Serializable{
    
    @PodamExclude
    @ManyToOne
    private ProfesoresEntity profesor;
    /*
    @PodamExclude
    @ManyToOne
    private List<EstudianteEntity> estudiantes = new ArrayList<EstudianteEntity>();
    */
    
   //private List<MensajeEntity> mensajes = new ArrayList<MensajeEntity>();
}
