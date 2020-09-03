/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.english4people.entities;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.*;
/**
 *
 * @author Juan David Becerra Romero
 */
@Entity
public class MensajeEntity extends BaseEntity implements Serializable{

    private String contenido;
    private Date fecha;
    
     /*
    @PodamExclude
    @ManyToOne
    private List<ChatEntity> chats = new ArrayList<ChatEntity>();
    */
    
    
     /**
     * @return el contenido del mensaje
     */
    public String getContenido()
    {
        return contenido;
    }
     /**
     * @return la fecha
     */
     public Date getFecha()
    {
        return fecha; 
    }
     
     /**
     * @param contenido el contenido
     */
    public void setContenido(String contenido)
    {
        this.contenido = contenido;
    }
    /**
     * @param fecha la fecha
     */
    public void setFecha(Date fecha)
    {
        this.fecha = fecha;
    
    }
    
}
