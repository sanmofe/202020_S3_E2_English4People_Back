/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.english4people.ejb;

import co.edu.uniandes.csw.english4people.entities.DiaSemanaEntity;
import co.edu.uniandes.csw.english4people.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.english4people.persistence.DiaSemanaPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Juan Diego Gonzalez Gomez
 */
@Stateless
public class DiaSemanaLogic {

    @Inject
    private DiaSemanaPersistence persistence;

    public DiaSemanaEntity createDiaSemana(DiaSemanaEntity diaSemana) throws BusinessLogicException {
        if (diaSemana.getLunes() || diaSemana.getMartes() || diaSemana.getMiercoles() || diaSemana.getJueves()
                || diaSemana.getViernes() || diaSemana.getSabado() || diaSemana.getDomingo()) {
            diaSemana = persistence.create(diaSemana);
            return diaSemana;
        } else {
            throw new BusinessLogicException("No hay ningún día de la semana seleccionado");
        }
    }

    public List<DiaSemanaEntity> getDiasSemana() {
        List<DiaSemanaEntity> diasSemana = persistence.findAll();
        return diasSemana;
    }

    public DiaSemanaEntity getDiaSemana(Long diaSemanaId) {
        DiaSemanaEntity diaSemanaEntity = persistence.find(diaSemanaId);
        return diaSemanaEntity;
    }

    public DiaSemanaEntity updateDiaSemana(Long diaSemanaId, DiaSemanaEntity diaSemanaEntity) throws BusinessLogicException {
        if (diaSemanaEntity.getLunes()|| diaSemanaEntity.getMartes()|| diaSemanaEntity.getMiercoles()|| diaSemanaEntity.getJueves()
                || diaSemanaEntity.getViernes()|| diaSemanaEntity.getSabado()|| diaSemanaEntity.getDomingo()) {
            DiaSemanaEntity newEntity = persistence.update(diaSemanaEntity);
            return newEntity;
        } else {
            throw new BusinessLogicException("No hay ningún dia de la semana seleccionado");
        }
    }

    public void deleteDiaSemana(Long diaSemanaId) {
        persistence.delete(diaSemanaId);
    }
}
