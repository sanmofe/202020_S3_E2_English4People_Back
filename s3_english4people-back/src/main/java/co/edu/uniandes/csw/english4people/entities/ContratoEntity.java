/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.english4people.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author Sara Plazas
 */
@Entity
public class ContratoEntity extends BaseEntity implements Serializable{

    /**
     * @return the horarios
     */
    public List<HorarioEntity> getHorarios() {
        return horarios;
    }

    /**
     * @param horarios the horarios to set
     */
    public void setHorarios(List<HorarioEntity> horarios) {
        this.horarios = horarios;
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
    
    private Integer numeroHoras;
    
    private Float costo;
    
    private String medioPago;
    
    @PodamExclude
    @ManyToOne
    private ProfesoresEntity profesor;

    @PodamExclude
    @ManyToOne
    private EstudianteEntity estudiante;
    
    @PodamExclude
    private List<HorarioEntity> horarios = new ArrayList<>();

    /**
     * 
     * @return the numeroHoras
     */
    public Integer getNumeroHoras() {
        return numeroHoras;
    }

    /**
     * 
     * @return the costo
     */
    public Float getCosto() {
        return costo;
    }

    /**
     * 
     * @return the medioPago
     */
    public String getMedioPago() {
        return medioPago;
    }

    /**
     * 
     * @param numeroHoras the numeroHoras to set
     */
    public void setNumeroHoras(Integer numeroHoras) {
        this.numeroHoras = numeroHoras;
    }

    /**
     * 
     * @param costo the costo to set
     */
    public void setCosto(Float costo) {
        this.costo = costo;
    }

    /**
     * 
     * @param medioPago the medioPago to set
     */
    public void setMedioPago(String medioPago) {
        this.medioPago = medioPago;
    }
    
}
