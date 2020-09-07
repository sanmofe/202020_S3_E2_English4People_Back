package co.edu.uniandes.csw.english4people.test.persistence;

import co.edu.uniandes.csw.english4people.entities.RespuestaEntity;
import co.edu.uniandes.csw.english4people.persistence.RespuestaPersistence;
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
public class RespuestaPersistanceTest {

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class).addPackage(RespuestaEntity.class.getPackage()).addPackage(RespuestaPersistence.class.getPackage()).addAsManifestResource("META-INF/persistence.xml", "persistence.xml").addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    @Inject
    UserTransaction utx;
    private List<RespuestaEntity> data = new ArrayList<RespuestaEntity>();

    @Inject
    RespuestaPersistence rp;

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
    public void createTest() {
        PodamFactory factory = new PodamFactoryImpl();
        RespuestaEntity respuesta = factory.manufacturePojo(RespuestaEntity.class);
        RespuestaEntity result = rp.create(respuesta);
        Assert.assertNotNull(result);
        RespuestaEntity entity = em.find(RespuestaEntity.class, result.getId());

        Assert.assertEquals(respuesta.getFecha(), entity.getFecha());
        Assert.assertEquals(respuesta.getComentario(), entity.getComentario());
    }

    private void clearData() {
        em.createQuery("delete from RespuestaEntity").executeUpdate();
    }

    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {

            RespuestaEntity entity = factory.manufacturePojo(RespuestaEntity.class);

            em.persist(entity);

            data.add(entity);
        }
    }

    @Test
    public void getRespuestasTest() {
        List<RespuestaEntity> list = rp.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (RespuestaEntity ent : list) {
            boolean found = false;
            for (RespuestaEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    @Test
    public void getRespuestaTest() {
        RespuestaEntity entity = data.get(0);
        RespuestaEntity newEntity = rp.find(entity.getId());
        Assert.assertNotNull(newEntity);

        Assert.assertEquals(entity.getFecha(), newEntity.getFecha());
        Assert.assertEquals(entity.getComentario(), newEntity.getComentario());

    }

    @Test
    public void updateRespuestaTest() {
        RespuestaEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        RespuestaEntity newEntity = factory.manufacturePojo(RespuestaEntity.class);

        newEntity.setId(entity.getId());

        rp.update(newEntity);

        RespuestaEntity resp = em.find(RespuestaEntity.class, entity.getId());

        Assert.assertEquals(resp.getFecha(), newEntity.getFecha());
        Assert.assertEquals(resp.getComentario(), newEntity.getComentario());
    }

    @Test
    public void deleteRespuestaTest() {
        RespuestaEntity entity = data.get(0);
        rp.delete(entity.getId());
        RespuestaEntity deleted = em.find(RespuestaEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
}
