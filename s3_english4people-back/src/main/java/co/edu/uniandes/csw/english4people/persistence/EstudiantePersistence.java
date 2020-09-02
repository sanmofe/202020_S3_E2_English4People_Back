/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.english4people.persistence;


import co.edu.uniandes.csw.english4people.entities.EstudianteEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
/**
 * @author Juan David Becerra Romero
 */
@Stateless
public class EstudiantePersistence {
    
    @PersistenceContext(unitName = "english4peoplePU")
    protected EntityManager em;
    
    public EstudianteEntity create(EstudianteEntity estudiante) {
        em.persist(estudiante);
        return estudiante;
    }
    
    public List<EstudianteEntity> findAll()
    {
        TypedQuery query = em.createQuery("select u from EstudianteEntity u", EstudianteEntity.class);
        return query.getResultList();
    }
    
    public EstudianteEntity find(Long estudianteId)
    {
        return em.find(EstudianteEntity.class, estudianteId);
    }
    
    public EstudianteEntity update(EstudianteEntity estudianteEntity)
    {
        return em.merge(estudianteEntity);
    }
    
    public void delete(Long estudianteId)
    {
        EstudianteEntity entity = em.find(EstudianteEntity.class, estudianteId);
        em.remove(entity);
    }

  }
    

