/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.english4people.ejb;

import co.edu.uniandes.csw.english4people.entities.HorarioEntity;
import co.edu.uniandes.csw.english4people.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.english4people.persistence.HorarioPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Sara Plazas
 */

@Stateless
public class HorarioLogic {
    
    @Inject
    private HorarioPersistence persistence;

    public HorarioEntity createHorario(HorarioEntity horario) throws BusinessLogicException {
        
        if (horario.getHoraInicio().after(horario.getHoraFin())) {
            throw new BusinessLogicException("La hora inicial no puede ser mas tarde que la final");
        }
        
        horario = persistence.create(horario);
        return horario;
        
    }

    public List<HorarioEntity> getHorario() {
        List<HorarioEntity> horarios = persistence.findAll();
        return horarios;
    }

    public HorarioEntity getHorario(Long horariosId) {
        HorarioEntity horarioEntity = persistence.find(horariosId);
        return horarioEntity;
    }

    public HorarioEntity updateHorario(HorarioEntity horarioEntity) throws BusinessLogicException {
        //if (horarioEntity.getHoraInicio().after(horarioEntity.getHoraFin())) {
        //    throw new BusinessLogicException("La hora de inicio no puede ser mas tarde que la final");
        //}
        
        HorarioEntity newEntity = persistence.update(horarioEntity);
        return newEntity;
    }
    
    public void deleteHorario(Long horariosId) throws BusinessLogicException {
        //if (persistence.find(horariosId).getProfesor() != null) {
        //    throw new BusinessLogicException("No se puede borrar el premio con id = " + horariosId + " porque tiene un profesor asociado");
        //}
        //if (persistence.find(horariosId).getDiaSemana() != null) {
        //    throw new BusinessLogicException("No se puede borrar el premio con id = " + horariosId + " porque tiene un dia de la semana asociado");
        //}
        persistence.delete(horariosId);
    }
}
