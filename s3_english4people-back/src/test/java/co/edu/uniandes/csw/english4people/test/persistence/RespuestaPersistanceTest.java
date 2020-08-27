import co.edu.uniandes.csw.english4people.entities.RespuestaEntity;
import co.edu.uniandes.csw.english4people.persistence.RespuestaPersistence;
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
public class RespuestaPersistanceTest {
    @Deployment
    public static JavaArchive createDeployment(){
    return ShrinkWrap.create(JavaArchive.class).addPackage(RespuestaEntity.class.getPackage()).addPackage(RespuestaPersistence.class.getPackage()).addAsManifestResource("META-INF/persistence.xml","persistence.xml").addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    @Inject
    RespuestaPersistence rp;
    
    @PersistenceContext
    private EntityManager em;
    
    @Test
    public void createTest()
    {
        PodamFactory factory = new PodamFactoryImpl();
        RespuestaEntity respuesta = factory.manufacturePojo(RespuestaEntity.class);
        RespuestaEntity result = rp.create(respuesta);
        Assert.assertNotNull(result);
        RespuestaEntity entity = em.find(RespuestaEntity.class, result.getId());
        
        Assert.assertEquals(respuesta.getComentario(), entity.getComentario());
    }    
}