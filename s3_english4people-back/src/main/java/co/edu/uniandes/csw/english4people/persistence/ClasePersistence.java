/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.english4people.persistence;


import co.edu.uniandes.csw.english4people.entities.ClaseEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Juan Diego Castellanos Bonilla
 */
@Stateless
public class ClasePersistence {
    
    @PersistenceContext (unitName = "english4peoplePU")
    protected EntityManager em;
    
     public ClaseEntity create(ClaseEntity clase)
    {
        em.persist(clase);
        
        return clase;
    }
     
     public List<ClaseEntity> findAll()
    {
        TypedQuery query = em.createQuery("select u from ClaseEntity u", ClaseEntity.class);
        return query.getResultList();
    }
    
    public ClaseEntity find(Long claseId)
    {
        return em.find(ClaseEntity.class, claseId);
    }
    
     public ClaseEntity update(ClaseEntity clasesEntity)
    {
        return em.merge(clasesEntity);
    }
    
    public void delete(Long clasesId)
    {
        ClaseEntity entity = em.find(ClaseEntity.class, clasesId);
        em.remove(entity);
    }
    
}
