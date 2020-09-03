/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.english4people.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import uk.co.jemos.podam.common.PodamExclude;
/*
import uk.co.jemos.podam.common.PodamExclude;
import javax.persistence.ManyToOne;*/
/**
 *
 * @author Juan Diego Castellanos Bonilla
 */
@Entity
public class ClaseEntity extends BaseEntity implements Serializable {

    /**
     * @return the materialDeClase
     */
    public List<MaterialDeClaseEntity> getMaterialDeClase() {
        return materialDeClase;
    }

    /**
     * @param materialDeClase the materialDeClase to set
     */
    public void setMaterialDeClase(List<MaterialDeClaseEntity> materialDeClase) {
        this.materialDeClase = materialDeClase;
    }

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
     * @return the idioma
     */
    public IdiomaEntity getIdioma() {
        return idioma;
    }

    /**
     * @param idioma the idioma to set
     */
    public void setIdioma(IdiomaEntity idioma) {
        this.idioma = idioma;
    }
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    
    private String tipoClase;

    @PodamExclude
    @OneToMany(
        mappedBy = "clase",
    	cascade = CascadeType.PERSIST,
    	fetch = FetchType.EAGER,
    	orphanRemoval = true
    )
    private List<MaterialDeClaseEntity> materialDeClase = new ArrayList<MaterialDeClaseEntity>();
    
    @PodamExclude
    @ManyToOne
    private ProfesoresEntity profesor;
    
    @PodamExclude
    @ManyToOne
    private EstudianteEntity estudiante;
    
    @PodamExclude
    private IdiomaEntity idioma;
    
    /**
     * @return the fecha
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    /**
     * @return the tipoClase
     */
    public String getTipoClase() {
        return tipoClase;
    }

    /**
     * @param tipoClase the tipoClase to set
     */
    public void setTipoClase(String tipoClase) {
        this.tipoClase = tipoClase;
    }
}
