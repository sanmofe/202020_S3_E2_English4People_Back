/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.english4people.persistence;

import co.edu.uniandes.csw.english4people.entities.HorarioEntity;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Sara Plazas
 */
@Stateless
public class HorarioPersistence {
    
    @PersistenceContext (unitName = "english4peoplePU")
    protected EntityManager em;
    
     public HorarioEntity create(HorarioEntity horario)
    {
        em.persist(horario);
        
        return horario;
    }
    
}
