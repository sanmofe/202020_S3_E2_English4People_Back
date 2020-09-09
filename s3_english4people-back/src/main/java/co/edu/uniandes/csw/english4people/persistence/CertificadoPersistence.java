/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.english4people.persistence;

import co.edu.uniandes.csw.english4people.entities.CertificadoEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Juan Andres Santiago
 */

@Stateless
public class CertificadoPersistence {
     @PersistenceContext (unitName = "english4peoplePU")
    protected EntityManager em;
            
            
    public CertificadoEntity create(CertificadoEntity certificado)
    {
       em.persist(certificado);
       
       return certificado;
    }
    
     public List<CertificadoEntity> findAll()
    {
        TypedQuery query = em.createQuery("select u from CertificadoEntity u", CertificadoEntity.class);
        return query.getResultList();
    }
    
    public CertificadoEntity find(Long profesoresId, Long certificadoId)
    {
        return em.find(CertificadoEntity.class, certificadoId);
    }
    
    public CertificadoEntity update(CertificadoEntity certificadoEntity)
    {
        return em.merge(certificadoEntity);
    }
    
    public void delete(Long certificadoId)
    {
        CertificadoEntity entity = em.find(CertificadoEntity.class, certificadoId);
        em.remove(entity);
    }
}
