/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.english4people.persistence;


import co.edu.uniandes.csw.english4people.entities.MaterialDeClaseEntity;
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
public class MaterialDeClasePersistence {
    
    @PersistenceContext (unitName = "english4peoplePU")
    protected EntityManager em;
     public MaterialDeClaseEntity create(MaterialDeClaseEntity material)
    {
        em.persist(material);
        
        return material;
    }
     public List< MaterialDeClaseEntity> findAll()
    {
        TypedQuery query = em.createQuery("select u from MaterialDeClaseEntity u",  MaterialDeClaseEntity.class);
        return query.getResultList();
    }
    
    public  MaterialDeClaseEntity find(Long materialDeClaseId)
    {
        return em.find( MaterialDeClaseEntity.class, materialDeClaseId);
    }
    
     public  MaterialDeClaseEntity update( MaterialDeClaseEntity materialDeClaseEntity)
    {
        return em.merge(materialDeClaseEntity);
    }
    
    public void delete(Long materialDeClaseId)
    {
         MaterialDeClaseEntity entity = em.find( MaterialDeClaseEntity.class, materialDeClaseId);
        em.remove(entity);
    }
}
