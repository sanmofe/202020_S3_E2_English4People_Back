package co.edu.uniandes.csw.english4people.test.persistence;

import co.edu.uniandes.csw.english4people.entities.CalificacionEntity;
import co.edu.uniandes.csw.english4people.persistence.CalificacionPersistence;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

@RunWith(Arquillian.class)
public class CalificacionPersistanceTest {
    @Deployment
    public static JavaArchive createDeployment(){
    return ShrinkWrap.create(JavaArchive.class).addPackage(CalificacionEntity.class.getPackage()).addPackage(CalificacionPersistence.class.getPackage()).addAsManifestResource("META-INF/persistence.xml","persistence.xml").addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    @Inject
    CalificacionPersistence cp;
    
    @PersistenceContext
    private EntityManager em;
    
    @Test
    public void createTest()
    {
        PodamFactory factory = new PodamFactoryImpl();
        CalificacionEntity calificacion = factory.manufacturePojo(CalificacionEntity.class);
        CalificacionEntity result = cp.create(calificacion);
        Assert.assertNotNull(result);
        CalificacionEntity entity = em.find(CalificacionEntity.class, result.getId());
        
        Assert.assertEquals(calificacion.getCalificacionNumerica(), entity.getCalificacionNumerica());
        Assert.assertEquals(calificacion.getComentario(), entity.getComentario());
        Assert.assertEquals(calificacion.getNombreEstudiante(), entity.getNombreEstudiante());
        Assert.assertEquals(calificacion.getFecha(), entity.getFecha());
        
        
    }    
}