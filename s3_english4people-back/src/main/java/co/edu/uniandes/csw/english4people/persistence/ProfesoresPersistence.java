/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.english4people.persistence;

import co.edu.uniandes.csw.english4people.entities.ProfesoresEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Juan Andres Santiago Vasquez
 */
@Stateless
public class ProfesoresPersistence {
    
    @PersistenceContext (unitName = "english4peoplePU")
    protected EntityManager em;
    
    public ProfesoresEntity create (ProfesoresEntity profesores)
    {
        em.persist(profesores);
        
        return profesores;
    }
    
    public List<ProfesoresEntity> findAll()
    {
        TypedQuery query = em.createQuery("select u from ProfesoresEntity u", ProfesoresEntity.class);
        return query.getResultList();
    }
    
    public ProfesoresEntity find(Long profesoresId)
    {
        return em.find(ProfesoresEntity.class, profesoresId);
    }
    
    public ProfesoresEntity update(ProfesoresEntity profesoresEntity)
    {
        return em.merge(profesoresEntity);
    }
    
    public void delete(Long profesoresId)
    {
        ProfesoresEntity entity = em.find(ProfesoresEntity.class, profesoresId);
        em.remove(entity);
    }
}
