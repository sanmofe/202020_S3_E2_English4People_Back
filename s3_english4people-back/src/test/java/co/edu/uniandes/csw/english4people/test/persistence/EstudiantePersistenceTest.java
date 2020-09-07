/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.english4people.test.persistence;

import co.edu.uniandes.csw.english4people.entities.EstudianteEntity;
import co.edu.uniandes.csw.english4people.persistence.EstudiantePersistence;
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
public class EstudiantePersistenceTest {

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class).addPackage(EstudianteEntity.class.getPackage()).addPackage(EstudiantePersistence.class.getPackage()).addAsManifestResource("META-INF/persistence.xml", "persistence.xml").addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    @Inject
    EstudiantePersistence ep;

    @PersistenceContext
    private EntityManager em;

    @Inject
    UserTransaction utx;

    private List<EstudianteEntity> data = new ArrayList<EstudianteEntity>();

    private void clearData() {
        em.createQuery("delete from EstudianteEntity").executeUpdate();
    }

    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {

            EstudianteEntity entity = factory.manufacturePojo(EstudianteEntity.class);

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
        EstudianteEntity estudiante = factory.manufacturePojo(EstudianteEntity.class);
        EstudianteEntity result = ep.create(estudiante);

        Assert.assertNotNull(result);

        EstudianteEntity entity = em.find(EstudianteEntity.class, result.getId());

        Assert.assertEquals(estudiante.getNombre(), entity.getNombre());
        Assert.assertEquals(estudiante.getIdentificacion(), entity.getIdentificacion());
        Assert.assertEquals(estudiante.getLogin(), entity.getLogin());
        Assert.assertEquals(estudiante.getCorreo(), entity.getCorreo());
        Assert.assertEquals(estudiante.getContrasena(), entity.getContrasena());
        Assert.assertEquals(estudiante.getChats().size(), entity.getChats().size());
        Assert.assertEquals(estudiante.getClases().size(), entity.getClases().size());
        Assert.assertEquals(estudiante.getActividades().size(), entity.getActividades().size());
        Assert.assertEquals(estudiante.getContratos().size(), entity.getContratos().size());
    }

    @Test
    public void getEstudiantesTest() {
        List<EstudianteEntity> list = ep.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (EstudianteEntity ent : list) {
            boolean found = false;
            for (EstudianteEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    @Test
    public void getEstudianteTest() {
        EstudianteEntity entity = data.get(0);
        EstudianteEntity newEntity = ep.find(entity.getId());
        Assert.assertNotNull(newEntity);

        Assert.assertEquals(entity.getNombre(), newEntity.getNombre());
        Assert.assertEquals(entity.getIdentificacion(), newEntity.getIdentificacion());
        Assert.assertEquals(entity.getLogin(), newEntity.getLogin());
        Assert.assertEquals(entity.getCorreo(), newEntity.getCorreo());
        Assert.assertEquals(entity.getContrasena(), newEntity.getContrasena());

    }

    @Test
    public void updateEstudianteTest() {
        EstudianteEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        EstudianteEntity newEntity = factory.manufacturePojo(EstudianteEntity.class);

        newEntity.setId(entity.getId());

        ep.update(newEntity);

        EstudianteEntity resp = em.find(EstudianteEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getNombre(), resp.getNombre());
        Assert.assertEquals(newEntity.getIdentificacion(), resp.getIdentificacion());
        Assert.assertEquals(newEntity.getLogin(), resp.getLogin());
        Assert.assertEquals(newEntity.getCorreo(), resp.getCorreo());
        Assert.assertEquals(newEntity.getContrasena(), resp.getContrasena());
    }

    @Test
    public void deleteEstudianteTest() {
        EstudianteEntity entity = data.get(0);
        ep.delete(entity.getId());
        EstudianteEntity deleted = em.find(EstudianteEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

}
