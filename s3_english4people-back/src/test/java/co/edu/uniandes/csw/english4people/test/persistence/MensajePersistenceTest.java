/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.english4people.test.persistence;

import co.edu.uniandes.csw.english4people.entities.MensajeEntity;
import co.edu.uniandes.csw.english4people.persistence.MensajePersistence;
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

/**
 *
 * @author Juan David Becerra Romero
 */
@RunWith(Arquillian.class)
public class MensajePersistenceTest {

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class).addPackage(MensajeEntity.class.getPackage()).addPackage(MensajePersistence.class.getPackage()).addAsManifestResource("META-INF/persistence.xml", "persistence.xml").addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    @Inject
    MensajePersistence mp;

    @PersistenceContext
    private EntityManager em;

    @Inject
    UserTransaction utx;

    private List<MensajeEntity> data = new ArrayList<MensajeEntity>();

    private void clearData() {
        em.createQuery("delete from MensajeEntity").executeUpdate();
    }

    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {

            MensajeEntity entity = factory.manufacturePojo(MensajeEntity.class);

            em.persist(entity);

            data.add(entity);
        }
    }

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
        MensajeEntity mensaje = factory.manufacturePojo(MensajeEntity.class);
        mensaje.setChat(null);
        MensajeEntity result = mp.create(mensaje);

        Assert.assertNotNull(result);

        MensajeEntity entity = em.find(MensajeEntity.class, result.getId());

        Assert.assertEquals(mensaje.getContenido(), entity.getContenido());
        Assert.assertEquals(mensaje.getFecha(), entity.getFecha());
        Assert.assertNull(mensaje.getChat());
    }

    @Test
    public void getMensajesTest() {
        List<MensajeEntity> list = mp.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (MensajeEntity ent : list) {
            boolean found = false;
            for (MensajeEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    @Test
    public void getMensajeTest() {
        MensajeEntity entity = data.get(0);
        MensajeEntity newEntity = mp.find(entity.getId());
        Assert.assertNotNull(newEntity);

        Assert.assertEquals(entity.getContenido(), newEntity.getContenido());
        Assert.assertEquals(entity.getFecha(), newEntity.getFecha());

    }

    @Test
    public void updateMensajeTest() {
        MensajeEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        MensajeEntity newEntity = factory.manufacturePojo(MensajeEntity.class);

        newEntity.setId(entity.getId());

        mp.update(newEntity);

        MensajeEntity resp = em.find(MensajeEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getContenido(), resp.getContenido());
        Assert.assertEquals(newEntity.getFecha(), resp.getFecha());

    }

    @Test
    public void deleteMensajeTest() {
        MensajeEntity entity = data.get(0);
        mp.delete(entity.getId());
        MensajeEntity deleted = em.find(MensajeEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

}
