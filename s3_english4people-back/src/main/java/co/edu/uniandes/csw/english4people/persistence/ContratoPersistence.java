/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.english4people.persistence;

import co.edu.uniandes.csw.english4people.entities.ContratoEntity;
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
public class ContratoPersistence {
    
    //PersistenceContext -> conjunto de entidades tal que para cada persistencia hay una entidad unica. Las entidades son manegadas en el contexto de persistencia.
    @PersistenceContext (unitName = "english4peoplePU")
    //EntityManager -> el elemento principal de JPA para acceder a la base de datos
    protected EntityManager em;
    
     public ContratoEntity create(ContratoEntity contrato)
    {
        em.persist(contrato);
        
        return contrato;
    }
     
     public List<ContratoEntity> findAll() {
        Query q = em.createQuery("select u from ContratoEntity u");
        return q.getResultList();
    }
     
     public ContratoEntity find(Long contratoId) {
        return em.find(ContratoEntity.class, contratoId);
    }
     
     public ContratoEntity update(ContratoEntity contrato) {
        return em.merge(contrato);
    }
    
     public void delete(Long contratoId) {
        ContratoEntity contrato = em.find(ContratoEntity.class, contratoId);
        em.remove(contrato);
    }
}
