/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.english4people.persistence;

import co.edu.uniandes.csw.english4people.entities.DiaSemanaEntity;
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
public class DiaSemanaPersistence
{
    @PersistenceContext (unitName = "english4peoplePU")
    protected EntityManager em;
    
    public DiaSemanaEntity create(DiaSemanaEntity diaSemana)
    {
       em.persist(diaSemana);
       return diaSemana;
    }
    
    public List<DiaSemanaEntity> findAll()
    {
        TypedQuery query = em.createQuery("select u from DiaSemanaEntity u", DiaSemanaEntity.class);
        return query.getResultList();
    }
    
    public DiaSemanaEntity find(Long diaSemanaId)
    {
        return em.find(DiaSemanaEntity.class, diaSemanaId);
    }
    
    public DiaSemanaEntity update(DiaSemanaEntity diaSemanaEntity)
    {
        return em.merge(diaSemanaEntity);
    }
    
    public void delete(Long diaSemanaId)
    {
        DiaSemanaEntity entity = em.find(DiaSemanaEntity.class, diaSemanaId);
        em.remove(entity);
    }
}
