/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.english4people.persistence;

import co.edu.uniandes.csw.english4people.entities.ActividadEntity;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Juan Diego Castellanos Bonilla
 */
@Stateless
public class ActividadPersistence {
    
    @PersistenceContext (unitName = "UniversidadPU")
    protected EntityManager em;
            
            
    public ActividadEntity create(ActividadEntity actividad)
    {
       em.persist(actividad);
       
       return actividad;
    }
    
}
