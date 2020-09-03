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
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author Juan David Becerra Romero
 */
@Entity
public class EstudianteEntity extends BaseEntity implements Serializable {

    /**
     * @return the chats
     */
    public List<ChatEntity> getChats() {
        return chats;
    }

    /**
     * @param chats the chats to set
     */
    public void setChats(List<ChatEntity> chats) {
        this.chats = chats;
    }

    /**
     * @return the clases
     */
    public List<ClaseEntity> getClases() {
        return clases;
    }

    /**
     * @param clases the clases to set
     */
    public void setClases(List<ClaseEntity> clases) {
        this.clases = clases;
    }

    /**
     * @return the actividades
     */
    public List<ActividadEntity> getActividades() {
        return actividades;
    }

    /**
     * @param actividades the actividades to set
     */
    public void setActividades(List<ActividadEntity> actividades) {
        this.actividades = actividades;
    }

    /**
     * @return the contratos
     */
    public List<ContratoEntity> getContratos() {
        return contratos;
    }

    /**
     * @param contratos the contratos to set
     */
    public void setContratos(List<ContratoEntity> contratos) {
        this.contratos = contratos;
    }

    private String nombre;
    private Integer identificacion;
    private String login;
    private String correo;
    private String contrasena;
    
    @PodamExclude
    @OneToMany(
            mappedBy= "estudiante",
            cascade = CascadeType.PERSIST,
            fetch = FetchType.EAGER,
            orphanRemoval = true
    )
    private List<ChatEntity> chats =
            new ArrayList<ChatEntity>();
  
    @PodamExclude
    @OneToMany(
            mappedBy= "estudiante",
            cascade = CascadeType.PERSIST,
            fetch = FetchType.EAGER,
            orphanRemoval = true
    )
    private List<ClaseEntity> clases =
            new ArrayList<ClaseEntity>();
    
    @PodamExclude
    @ManyToMany (mappedBy = "estudiantes")
    private List<ActividadEntity> actividades = new ArrayList<>();
    
    @PodamExclude
    @OneToMany(
            mappedBy= "estudiante",
            cascade = CascadeType.PERSIST,
            fetch = FetchType.EAGER,
            orphanRemoval = true
    )
    private List<ContratoEntity> contratos =
            new ArrayList<ContratoEntity>();
    
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
    public Integer getIdentificacion()
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
    public void setIdentificacion(Integer id)
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
