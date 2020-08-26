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
 * @author Juan David Becerra Romero
 */
@Entity
public class EstudianteEntity extends BaseEntity implements Serializable {

    private String nombre;
    private int identificacion;
    private String login;
    private String correo;
    private String contrasena;
    
    /**
     * @return el nombre
     */
    public String getNombre()
    {
    return nombre;
    }
    /**
     * @return la identificacion
     */
    public int getIdentificacion()
    {
    return identificacion;
    }
    /**
     * @return el login
     */
    public String getLogin()
    {
    return login;
    }
    /**
     * @return el correo
     */
    public String getCorreo()
    {
    return correo;
    }
    /**
     * @return la contrasena
     */
    public String getContrasena()
    {
    return contrasena;
    }
    
    /**
     * @param nombre 
     */
    public void setNombre(String nombre)
    {
    this.nombre = nombre;
    }
    /**
     * @param id
     */
    public void setIdentificacion(int id)
    {
    this.identificacion = id;
    }
    /**
     * @param login 
     */
    public void setLogin(String login)
    {
    this.login = login;
    }
    /**
     * @param correo 
     */
    public void setCorreo(String correo)
    {
    this.correo = correo;
    }
    /**
     * @param contrasena 
     */
    public void setContrasena(String contrasena)
    {
    this.contrasena = contrasena;
    }
    
   
    
    

    
}
