/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.english4people.ejb;

import co.edu.uniandes.csw.english4people.entities.ContratoEntity;
import co.edu.uniandes.csw.english4people.entities.ProfesoresEntity;
import co.edu.uniandes.csw.english4people.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.english4people.persistence.ContratoPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Sara Plazas
 */

@Stateless
public class ContratoLogic {
    
    @Inject
    private ContratoPersistence persistence;

    public ContratoEntity createContrato(ContratoEntity contrato) throws BusinessLogicException {
        
        if (contrato.getCosto() <= 0) {
            throw new BusinessLogicException("El costo es invalido");
        }
        if (contrato.getNumeroHoras() <= 0) {
            throw new BusinessLogicException("El numero de horas de clase es inválido");
        }
        
        contrato = persistence.create(contrato);
        return contrato;
        
    }

    public List<ContratoEntity> getContrato() {
        List<ContratoEntity> contratos = persistence.findAll();
        return contratos;
    }

    public ContratoEntity getContrato(Long contratosId) {
        ContratoEntity contratoEntity = persistence.find(contratosId);
        return contratoEntity;
    }

    public ContratoEntity updateContrato(ContratoEntity contratoEntity) throws BusinessLogicException {
        /**
        if (contratoEntity.getCosto() <= 0) {
            throw new BusinessLogicException("El costo es inválido");
        }
        if (contratoEntity.getNumeroHoras() <= 0) {
            throw new BusinessLogicException("El numero de horas es inválido");
        }
        */
        
        ContratoEntity newEntity = persistence.update(contratoEntity);
        return newEntity;
    }
    
    public void deleteContrato(Long contratosId) throws BusinessLogicException {
        /**
        if (persistence.find(contratosId).getProfesor() != null) {
            throw new BusinessLogicException("No se puede borrar el premio con id = " + contratosId + " porque tiene un profesor asociado");
        }
        if (persistence.find(contratosId).getEstudiante() != null) {
            throw new BusinessLogicException("No se puede borrar el premio con id = " + contratosId + " porque tiene un estudiante asociado");
        }
        if (persistence.find(contratosId).getHorario() != null) {
            throw new BusinessLogicException("No se puede borrar el premio con id = " + contratosId + " porque tiene un horario asociado");
        }
        */
        persistence.delete(contratosId);
    }
}
