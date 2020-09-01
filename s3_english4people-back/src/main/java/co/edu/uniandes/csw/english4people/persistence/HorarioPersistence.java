/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.english4people.persistence;

import co.edu.uniandes.csw.english4people.entities.HorarioEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Sara Plazas
 */

//Stateless porque no me interesa  guardan información del cliente específico que están atendiendo
//                 no tiene la necesidad de guardar una relación entre un usuario y un bean particular
@Stateless
public class HorarioPersistence {
    
    //PersistenceContext -> conjunto de entidades tal que para cada persistencia hay una entidad unica. Las entidades son manegadas en el contexto de persistencia.
    @PersistenceContext (unitName = "english4peoplePU")
    //EntityManager -> el elemento principal de JPA para acceder a la base de datos
    protected EntityManager em;
    
     public HorarioEntity create(HorarioEntity horario)
    {
        em.persist(horario);
        
        return horario;
    }
     
     public List<HorarioEntity> findAll() {
        Query q = em.createQuery("select u from HorarioEntity u");
        return q.getResultList();
    }
     
     public HorarioEntity find(Long horarioId) {
        return em.find(HorarioEntity.class, horarioId);
    }
     
     public HorarioEntity update(HorarioEntity horario) {
        return em.merge(horario);
    }
    
     public void delete(Long horarioId) {
        HorarioEntity horario = em.find(HorarioEntity.class, horarioId);
        em.remove(horario);
    }
    
}
