
package co.edu.uniandes.csw.english4people.persistence;

import co.edu.uniandes.csw.english4people.entities.RespuestaEntity;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class RespuestaPersistence {
    @PersistenceContext (unitName = "english4peoplePU")
    protected EntityManager em;
    public RespuestaEntity create(RespuestaEntity respuesta){
        em.persist(respuesta);
        return respuesta;
    }
    
}
