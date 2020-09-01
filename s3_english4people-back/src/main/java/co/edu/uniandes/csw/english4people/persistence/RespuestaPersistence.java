
package co.edu.uniandes.csw.english4people.persistence;

import co.edu.uniandes.csw.english4people.entities.RespuestaEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
public class RespuestaPersistence {
    @PersistenceContext (unitName = "english4peoplePU")
    protected EntityManager em;
    
    public RespuestaEntity create(RespuestaEntity respuesta){
        em.persist(respuesta);
        return respuesta;
    }
    
    public List<RespuestaEntity> findAll()
    {
        TypedQuery query = em.createQuery("select u from RespuestaEntity u", RespuestaEntity.class);
        return query.getResultList();
    }
    
    public RespuestaEntity find(Long respuestaId)
    {
        return em.find(RespuestaEntity.class, respuestaId);
    }
    
    public RespuestaEntity update(RespuestaEntity respuestaEntity)
    {
        return em.merge(respuestaEntity);
    }
    
    public void delete(Long respuestaId)
    {
        RespuestaEntity entity = em.find(RespuestaEntity.class, respuestaId);
        em.remove(entity);
    }
}