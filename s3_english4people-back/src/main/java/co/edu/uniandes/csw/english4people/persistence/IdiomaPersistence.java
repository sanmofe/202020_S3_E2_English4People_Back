/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.english4people.persistence;

import co.edu.uniandes.csw.english4people.entities.IdiomaEntity;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Juan Diego Gonzalez
 */
@Stateless
public class IdiomaPersistence {
    
    @PersistenceContext (unitName = "dispositivosPU")
    protected EntityManager em;
    
    public IdiomaEntity create(IdiomaEntity idioma)
    {
        em.persist(idioma);
        
        return idioma;
    }
    
}
