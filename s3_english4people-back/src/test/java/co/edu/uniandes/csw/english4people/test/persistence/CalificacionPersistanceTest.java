package co.edu.uniandes.csw.english4people.test.persistence;

import co.edu.uniandes.csw.english4people.entities.CalificacionEntity;
import co.edu.uniandes.csw.english4people.persistence.CalificacionPersistence;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Before;
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
    UserTransaction utx;
    private List<CalificacionEntity> data = new ArrayList<CalificacionEntity>();
    
    @Inject
    CalificacionPersistence cp;
    
    @PersistenceContext
    private EntityManager em;
    
    @Before
    public void configTest() {
        try {
            utx.begin();
            em.joinTransaction();
            clearData();
            insertData();
            utx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                utx.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }
    
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
               
    }    
    
    private void clearData() {
        em.createQuery("delete from CalificacionEntity").executeUpdate();
    }
        
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {

            CalificacionEntity entity = factory.manufacturePojo(CalificacionEntity.class);

            em.persist(entity);

            data.add(entity);
        }
    }    
    
        @Test
    public void getCalificacionesTest() {
        List<CalificacionEntity> list = cp.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (CalificacionEntity ent : list) {
            boolean found = false;
            for (CalificacionEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    
    @Test
    public void getCalificacionTest() {
        CalificacionEntity entity = data.get(0);
        CalificacionEntity newEntity = cp.find(entity.getId());
        Assert.assertNotNull(newEntity);
        
        Assert.assertEquals(entity.getCalificacionNumerica(), newEntity.getCalificacionNumerica());
        Assert.assertEquals(entity.getComentario(), newEntity.getComentario());
        Assert.assertEquals(entity.getNombreEstudiante(), newEntity.getNombreEstudiante());
        
    }
    
    @Test
    public void updateCalificacionTest() {
        CalificacionEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        CalificacionEntity newEntity = factory.manufacturePojo(CalificacionEntity.class);

        newEntity.setId(entity.getId());

        cp.update(newEntity);
        
        CalificacionEntity resp = em.find(CalificacionEntity.class, entity.getId());
        
        Assert.assertEquals(resp.getCalificacionNumerica(), newEntity.getCalificacionNumerica());
        Assert.assertEquals(resp.getComentario(), newEntity.getComentario());
        Assert.assertEquals(resp.getNombreEstudiante(), newEntity.getNombreEstudiante());
    }
    
    @Test
    public void deleteCalificacionTest() {
        CalificacionEntity entity = data.get(0);
        cp.delete(entity.getId());
        CalificacionEntity deleted = em.find(CalificacionEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
}