/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.english4people.entities;

import javax.persistence.Entity;
import java.io.Serializable;

/**
 *
 * @author Juan Andrés Santiago Vasquez
 */
@Entity
public class ProfesoresEntity extends BaseEntity implements Serializable {
    
    private String nombre;
    
    private Integer identificacion;
    
    private String login; 
    
    private String correo;
    
    private String contrasena;
    
    private String informacionAcademica;
    
    private String canalYoutube;
    
    
    /**
     * @return el nombre
     */
    public String getNombre(){
        return nombre;
    }
    
    /**
     * @param nombre the name to set
     */
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    
    /**
     * @return la identificacion
     */
    public Integer getIdentificacion(){
        return identificacion;
    }
    
    /**
     * @param identificacion the identificacion to set
     */
    public void setIdentificacion(Integer identificacion){
        this.identificacion = identificacion;
    }
    
    /**
     * @return el login del usuario.
     */
    public String getLogin(){
        return login;
    }
    
    /**
     * @param login the login to set
     */
    public void setLogin(String login){
        this.login = login;
    }
    
    /**
     * @return el correo
     */
    public String getCorreo(){
        return correo;
    }
    
    /**
     * @param correo the correo to set
     */
    public void setCorreo(String correo){
        this.correo = correo;
    }
    
    /**
     * @return la contraseña
     */
    public String getContrasena(){
        return contrasena;
    }
    
    /**
     * @param contrasena the contrasena to set
     */
    public void setContrasena(String contrasena){
        this.contrasena = contrasena;
    }
    
    /**
     * @return la informacion academica
     */
    public String getInformacionAcademica(){
        return informacionAcademica;
    }
    
    /**
     * @param informacionAcademica the informacionAcademica to set
     */
    public void setInformacionAcademica(String informacionAcademica){
        this.informacionAcademica = informacionAcademica;
    }
    
    /**
     * @return el link del canal de youtube
     */
    public String getCanalYoutube(){
        return canalYoutube;
    }
    
    /**
     * @param canalYoutube the canalYoutube to set
     */
    public void setCanalYoutube(String canalYoutube){
        this.canalYoutube = canalYoutube;
    }
    
}
