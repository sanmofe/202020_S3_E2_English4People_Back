
package co.edu.uniandes.csw.english4people.persistence;

import co.edu.uniandes.csw.english4people.entities.CalificacionEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
public class CalificacionPersistence {
    @PersistenceContext (unitName = "english4peoplePU")
    protected EntityManager em;
    
    public CalificacionEntity create(CalificacionEntity respuesta){
        em.persist(respuesta);
        return respuesta;
    }
    
    public List<CalificacionEntity> findAll()
    {
        TypedQuery query = em.createQuery("select u from CalificacionEntity u", CalificacionEntity.class);
        return query.getResultList();
    }
    
    public CalificacionEntity find(Long calificacionId)
    {
        return em.find(CalificacionEntity.class, calificacionId);
    }
    
    public CalificacionEntity update(CalificacionEntity calificacionEntity)
    {
        return em.merge(calificacionEntity);
    }
    
    public void delete(Long calificacionId)
    {
        CalificacionEntity entity = em.find(CalificacionEntity.class, calificacionId);
        em.remove(entity);
    }
}
