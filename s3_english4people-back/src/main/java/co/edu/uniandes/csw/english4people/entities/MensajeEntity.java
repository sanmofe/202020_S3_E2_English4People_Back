/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.english4people.entities;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.*;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import uk.co.jemos.podam.common.PodamExclude;
/**
 *
 * @author Juan David Becerra Romero
 */
@Entity
public class MensajeEntity extends BaseEntity implements Serializable{

    /**
     * @return the chat
     */
    public ChatEntity getChat() {
        return chat;
    }

    /**
     * @param chat the chat to set
     */
    public void setChat(ChatEntity chat) {
        this.chat = chat;
    }

    private String contenido;
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    
    @PodamExclude
    @ManyToOne
    private ChatEntity chat;
    
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
