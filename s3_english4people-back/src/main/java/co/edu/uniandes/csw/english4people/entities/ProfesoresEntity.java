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
import javax.persistence.OneToMany;
import uk.co.jemos.podam.common.PodamExclude;
/*
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import uk.co.jemos.podam.common.PodamExclude;
*/

/**
 *
 * @author Juan Andrés Santiago Vasquez
 */
@Entity
public class ProfesoresEntity extends BaseEntity implements Serializable {

    /**
     * @return the certificados
     */
    public List<CertificadoEntity> getCertificados() {
        return certificados;
    }

    /**
     * @param certificados the certificados to set
     */
    public void setCertificados(List<CertificadoEntity> certificados) {
        this.certificados = certificados;
    }

    /**
     * @return the calificaciones
     */
    public List<CalificacionEntity> getCalificaciones() {
        return calificaciones;
    }

    /**
     * @param calificaciones the calificaciones to set
     */
    public void setCalificaciones(List<CalificacionEntity> calificaciones) {
        this.calificaciones = calificaciones;
    }

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
     * @return the idiomas
     */
    public IdiomaEntity getIdiomas() {
        return idiomas;
    }

    /**
     * @param idiomas the idiomas to set
     */
    public void setIdiomas(IdiomaEntity idiomas) {
        this.idiomas = idiomas;
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
     * @return the horariosDisponible
     */
    public List<HorarioEntity> getHorariosDisponible() {
        return horariosDisponible;
    }

    /**
     * @param horariosDisponible the horariosDisponible to set
     */
    public void setHorariosDisponible(List<HorarioEntity> horariosDisponible) {
        this.horariosDisponible = horariosDisponible;
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
    
    private String informacionAcademica;
    
    private String canalYoutube;
    
    @PodamExclude
    @OneToMany(
            mappedBy= "profesor",
            cascade = CascadeType.PERSIST,
            fetch = FetchType.EAGER,
            orphanRemoval = true
    )
    private List<CertificadoEntity> certificados =
            new ArrayList<CertificadoEntity>();

    @PodamExclude
    @OneToMany(
            mappedBy = "profesor",
            cascade = CascadeType.PERSIST,
            fetch = FetchType.EAGER,
            orphanRemoval = true
    )
    private List<CalificacionEntity> calificaciones =
            new ArrayList<CalificacionEntity>();
    
    @PodamExclude
    @OneToMany(
            mappedBy = "profesor",
            cascade = CascadeType.PERSIST,
            fetch = FetchType.EAGER,
            orphanRemoval = true
    )
    private List<ChatEntity> chats = new ArrayList<ChatEntity>();
    
    @PodamExclude
    private IdiomaEntity idiomas;
    
    @PodamExclude
    @OneToMany(
            mappedBy= "profesor",
            cascade = CascadeType.PERSIST,
            fetch = FetchType.EAGER,
            orphanRemoval = true
    )
    private List<ClaseEntity> clases =
            new ArrayList<ClaseEntity>();
       
    @PodamExclude
    @OneToMany(
            mappedBy = "profesor",
            cascade = CascadeType.PERSIST,
            fetch = FetchType.EAGER,
            orphanRemoval = true
    )
    private List<ActividadEntity> actividades = new ArrayList<ActividadEntity>();
    
    @PodamExclude
    private List<HorarioEntity> horariosDisponible =
            new ArrayList<HorarioEntity>();
    
    @PodamExclude
    @OneToMany(
            mappedBy = "profesor",
            cascade = CascadeType.PERSIST,
            fetch = FetchType.EAGER,
            orphanRemoval = true
    )
    private List<ContratoEntity> contratos =
            new ArrayList<ContratoEntity>();
    
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
