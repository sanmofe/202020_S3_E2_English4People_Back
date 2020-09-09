/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.english4people.ejb;

import co.edu.uniandes.csw.english4people.entities.ActividadEntity;
import co.edu.uniandes.csw.english4people.entities.CalificacionEntity;
import co.edu.uniandes.csw.english4people.entities.CertificadoEntity;
import co.edu.uniandes.csw.english4people.entities.ChatEntity;
import co.edu.uniandes.csw.english4people.entities.ClaseEntity;
import co.edu.uniandes.csw.english4people.entities.ContratoEntity;
import co.edu.uniandes.csw.english4people.entities.HorarioEntity;
import co.edu.uniandes.csw.english4people.entities.ProfesoresEntity;
import co.edu.uniandes.csw.english4people.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.english4people.persistence.ProfesoresPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Juan Andres Santiago
 */
@Stateless
public class ProfesoresLogic {

    @Inject
    private ProfesoresPersistence persistence;

    public ProfesoresEntity createProfesor(ProfesoresEntity profesor) throws BusinessLogicException {

        if (profesor.getNombre() == null || profesor.getNombre().isEmpty()) {
            throw new BusinessLogicException("El nombre del profesor esta vacío");
        }

        if (profesor.getIdentificacion() == 0) {
            throw new BusinessLogicException("La identificacion no puede ser 0");
        }

        if (profesor.getLogin() == null || profesor.getLogin().isEmpty()) {
            throw new BusinessLogicException("El login del profesor esta vacío");
        }

        if (profesor.getCorreo() == null || profesor.getCorreo().isEmpty()) {
            throw new BusinessLogicException("El correo del profesor esta vacío");
        }

        if (profesor.getContrasena() == null || profesor.getContrasena().isEmpty()) {
            throw new BusinessLogicException("La contraseña esta vacía");
        }

        if (profesor.getInformacionAcademica() == null || profesor.getInformacionAcademica().isEmpty()) {
            throw new BusinessLogicException("La informacion academica del profesor no puede estar vacia");
        }

        persistence.create(profesor);
        return profesor;
    }

    public List<ProfesoresEntity> getProfesores() {
        List<ProfesoresEntity> profesores = persistence.findAll();
        return profesores;
    }

    public ProfesoresEntity getProfesor(Long profesoresId) {
        ProfesoresEntity profesorEntity = persistence.find(profesoresId);
        return profesorEntity;
    }

    public ProfesoresEntity updateProfesor(Long ProfesorId, ProfesoresEntity profesor) throws BusinessLogicException {
        if (profesor.getNombre() == null || profesor.getNombre().isEmpty()) {
            throw new BusinessLogicException("El nombre del profesor esta vacío");
        }

        if (profesor.getIdentificacion() == 0) {
            throw new BusinessLogicException("La identificacion no puede ser 0");
        }
        
        if (profesor.getLogin() == null || profesor.getLogin().isEmpty()) {
            throw new BusinessLogicException("El login del profesor esta vacío");
        }

        if (profesor.getCorreo() == null || profesor.getCorreo().isEmpty()) {
            throw new BusinessLogicException("El correo del profesor esta vacío");
        }

        if (profesor.getContrasena() == null || profesor.getContrasena().isEmpty()) {
            throw new BusinessLogicException("La contraseña esta vacía");
        }

        if (profesor.getInformacionAcademica() == null || profesor.getInformacionAcademica().isEmpty()) {
            throw new BusinessLogicException("La informacion academica del profesor no puede estar vacia");
        }
        
        ProfesoresEntity newEntity = persistence.update(profesor);
        return newEntity;
    }
    
    public void deleteProfesor(Long profesorId) throws BusinessLogicException{
        
        List<CertificadoEntity> certificados = getProfesor(profesorId).getCertificados();
        if(certificados != null && !certificados.isEmpty()){
            throw new BusinessLogicException("No se puede borrar el profesor con id = "+profesorId+"porque tiene certificados asociados");
        }
        
        List<CalificacionEntity> calificaciones = getProfesor(profesorId).getCalificaciones();
        if(calificaciones != null && !calificaciones.isEmpty()){
            throw new BusinessLogicException("No se puede borrar el profesor con id = "+profesorId+"porque tiene calificaciones asociadas");
        }
        
        List<ChatEntity> chats = getProfesor(profesorId).getChats();
        if(chats != null && !chats.isEmpty()){
            throw new BusinessLogicException("No se puede borrar el profesor con id = "+profesorId+"porque tiene chats asociados");
        }
        
        List<ClaseEntity> clases = getProfesor(profesorId).getClases();
        if(clases != null && !clases.isEmpty()){
            throw new BusinessLogicException("No se puede borrar el profesor con id = "+profesorId+"porque tiene clases asociadas");
        }
        
        List<ActividadEntity> actividades = getProfesor(profesorId).getActividades();
        if(actividades != null && !actividades.isEmpty()){
            throw new BusinessLogicException("No se puede borrar el profesor con id = "+profesorId+"porque tiene actividades asociadas");
        }
        
        List<HorarioEntity> horarios = getProfesor(profesorId).getHorariosDisponible();
        if(horarios != null && !horarios.isEmpty()){
            throw new BusinessLogicException("No se puede borrar el profesor con id = "+profesorId+"porque tiene horarios asociados");
        }
        
        List<ContratoEntity> contratos = getProfesor(profesorId).getContratos();
        if(contratos != null && !contratos.isEmpty()){
            throw new BusinessLogicException("No se puede borrar el profesor con id = "+profesorId+"porque tiene contratos asociados");
        }
        persistence.delete(profesorId);
    }

}
