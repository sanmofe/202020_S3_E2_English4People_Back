/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.english4people.persistence;

import co.edu.uniandes.csw.english4people.entities.IdiomaEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Juan Diego Gonzalez Gomez
 */
@Stateless
public class IdiomaPersistence {
    
    @PersistenceContext (unitName = "english4peoplePU")
    protected EntityManager em;
    
    public IdiomaEntity create(IdiomaEntity idioma)
    {
        em.persist(idioma);
        return idioma;
    }
    
    public List<IdiomaEntity> findAll()
    {
        TypedQuery query = em.createQuery("select u from IdiomaEntity u", IdiomaEntity.class);
        return query.getResultList();
    }
    
    public IdiomaEntity find(Long idiomaId)
    {
        return em.find(IdiomaEntity.class, idiomaId);
    }
    
    public IdiomaEntity update(IdiomaEntity idiomaEntity)
    {
        return em.merge(idiomaEntity);
    }
    
    public void delete(Long idiomaId)
    {
        IdiomaEntity entity = em.find(IdiomaEntity.class, idiomaId);
        em.remove(entity);
    }
}
