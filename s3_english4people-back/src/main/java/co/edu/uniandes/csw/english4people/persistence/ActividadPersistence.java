/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.english4people.persistence;

import co.edu.uniandes.csw.english4people.entities.ActividadEntity;
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
public class ActividadPersistence {
    
    @PersistenceContext (unitName = "english4peoplePU")
    protected EntityManager em;
            
    public ActividadEntity create(ActividadEntity actividad)
    {
       em.persist(actividad);
       
       return actividad;
    }
    
    public List<ActividadEntity> findAll()
    {
        TypedQuery query = em.createQuery("select u from ActividadEntity u", ActividadEntity.class);
        return query.getResultList();
    }
    
    public ActividadEntity find(Long calificacionId)
    {
        return em.find(ActividadEntity.class, calificacionId);
    }
    
     public ActividadEntity update(ActividadEntity actividadesEntity)
    {
        return em.merge(actividadesEntity);
    }
    
    public void delete(Long actividadesId)
    {
        ActividadEntity entity = em.find(ActividadEntity.class, actividadesId);
        em.remove(entity);
    }
}
