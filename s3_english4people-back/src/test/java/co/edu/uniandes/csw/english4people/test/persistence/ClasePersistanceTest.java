/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.english4people.test.persistence;

import co.edu.uniandes.csw.english4people.entities.ClaseEntity;
import co.edu.uniandes.csw.english4people.persistence.ClasePersistence;
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
 * @author Juan Diego Castellanos Bonilla
 */
@RunWith(Arquillian.class)
public class ClasePersistanceTest {

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class).addPackage(ClaseEntity.class.getPackage()).addPackage(ClasePersistence.class.getPackage()).addAsManifestResource("META-INF/persistence.xml", "persistence.xml").addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    @Inject
    ClasePersistence cp;

    @PersistenceContext
    private EntityManager em;

    @Inject
    UserTransaction utx;
    private List<ClaseEntity> data = new ArrayList<ClaseEntity>();

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
        ClaseEntity clase = factory.manufacturePojo(ClaseEntity.class);
        clase.setProfesor(null);
        clase.setEstudiante(null);
        clase.setIdioma(null);
        ClaseEntity result = cp.create(clase);

        Assert.assertNotNull(result);

        ClaseEntity entity = em.find(ClaseEntity.class, result.getId());

        Assert.assertEquals(clase.getFecha(), entity.getFecha());
        Assert.assertEquals(clase.getTipoClase(), entity.getTipoClase());
        Assert.assertEquals(clase.getMaterialDeClase().size(), entity.getMaterialDeClase().size());
        Assert.assertNull(clase.getProfesor());
        Assert.assertNull(clase.getEstudiante());
        Assert.assertNull(clase.getIdioma());
    }

    private void clearData() {
        em.createQuery("delete from ClaseEntity").executeUpdate();
    }

    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {

            ClaseEntity entity = factory.manufacturePojo(ClaseEntity.class);
            em.persist(entity);

            data.add(entity);
        }
    }

    @Test
    public void getClasesTest() {
        List<ClaseEntity> list = cp.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (ClaseEntity ent : list) {
            boolean found = false;
            for (ClaseEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    @Test
    public void getClaseTest() {
        ClaseEntity entity = data.get(0);
        ClaseEntity newEntity = cp.find(entity.getId());
        Assert.assertNotNull(newEntity);

        Assert.assertEquals(entity.getFecha(), newEntity.getFecha());
        Assert.assertEquals(entity.getTipoClase(), newEntity.getTipoClase());
    }

    @Test
    public void updateClaseTest() {
        ClaseEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        ClaseEntity newEntity = factory.manufacturePojo(ClaseEntity.class);

        newEntity.setId(entity.getId());

        cp.update(newEntity);

        ClaseEntity resp = em.find(ClaseEntity.class, entity.getId());

        Assert.assertEquals(resp.getFecha(), newEntity.getFecha());
        Assert.assertEquals(resp.getTipoClase(), newEntity.getTipoClase());
    }

    @Test
    public void deleteClaseTest() {
        ClaseEntity entity = data.get(0);
        cp.delete(entity.getId());
        ClaseEntity deleted = em.find(ClaseEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
}
