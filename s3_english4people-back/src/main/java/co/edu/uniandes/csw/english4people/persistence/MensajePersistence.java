/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.english4people.persistence;

import co.edu.uniandes.csw.english4people.entities.MensajeEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Juan David Becerra Romero
 */
@Stateless
public class MensajePersistence {
    
    @PersistenceContext(unitName = "english4peoplePU")
    protected EntityManager em;
    
    public MensajeEntity create(MensajeEntity mensaje) {
        em.persist(mensaje);
        return mensaje;
    }
    
    public List<MensajeEntity> findAll()
    {
        TypedQuery query = em.createQuery("select u from MensajeEntity u", MensajeEntity.class);
        return query.getResultList();
    }
    
    public MensajeEntity find(Long mensajeId)
    {
        return em.find(MensajeEntity.class, mensajeId);
    }
    
    public MensajeEntity update(MensajeEntity mensajeEntity)
    {
        return em.merge(mensajeEntity);
    }
    
    public void delete(Long mensajeId)
    {
        MensajeEntity entity = em.find(MensajeEntity.class, mensajeId);
        em.remove(entity);
    }
    
}
