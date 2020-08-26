
package co.edu.uniandes.csw.english4people.persistence;

import co.edu.uniandes.csw.english4people.entities.CalificacionEntity;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class CalificacionPersistence {
    @PersistenceContext (unitName = "english4peoplePU")
    protected EntityManager em;
    public CalificacionEntity create(CalificacionEntity respuesta){
        em.persist(respuesta);
        return respuesta;
    }
}
