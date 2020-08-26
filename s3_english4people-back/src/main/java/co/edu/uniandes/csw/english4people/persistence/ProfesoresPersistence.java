/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.english4people.persistence;

import co.edu.uniandes.csw.english4people.entities.ProfesoresEntity;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Juan Andres Santiago Vasquez
 */
@Stateless
public class ProfesoresPersistence {
    
    @PersistenceContext(unitName = "english4peoplePU")
    protected EntityManager em;
    
    public ProfesoresEntity create (ProfesoresEntity profesores){
        em.persist(profesores);
        
        return profesores;
    }
}
