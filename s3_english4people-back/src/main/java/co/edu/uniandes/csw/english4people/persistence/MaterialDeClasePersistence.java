/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.english4people.persistence;


import co.edu.uniandes.csw.english4people.entities.MaterialDeClaseEntity;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Juan Diego Castellanos Bonilla
 */
@Stateless
public class MaterialDeClasePersistence {
    
    @PersistenceContext (unitName = "UniversidadPU")
    protected EntityManager em;
     public MaterialDeClaseEntity create(MaterialDeClaseEntity material)
    {
        em.persist(material);
        
        return material;
    }
}
