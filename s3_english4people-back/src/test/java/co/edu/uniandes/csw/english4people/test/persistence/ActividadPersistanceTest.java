/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.english4people.test.persistence;


import co.edu.uniandes.csw.english4people.entities.ActividadEntity;
import co.edu.uniandes.csw.english4people.persistence.ActividadPersistence;
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
public class ActividadPersistanceTest {
    @Deployment
    public static JavaArchive createDeployment(){
    return ShrinkWrap.create(JavaArchive.class).addPackage(ActividadEntity.class.getPackage()).addPackage(ActividadPersistence.class.getPackage()).addAsManifestResource("META-INF/persistence.xml","persistence.xml").addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    @Inject
    UserTransaction utx;
    
    private List<ActividadEntity> data =  new ArrayList<ActividadEntity>();
    
    @Inject
    ActividadPersistence ac;
    
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
        ActividadEntity actividad = factory.manufacturePojo(ActividadEntity.class);
        ActividadEntity result = ac.create(actividad);
        
        Assert.assertNotNull(result);
        
        ActividadEntity entity = em.find(ActividadEntity.class, result.getId());
        
        Assert.assertEquals(actividad.getFecha(), entity.getFecha());
        Assert.assertEquals(actividad.getTipo(), entity.getTipo());
        Assert.assertEquals(actividad.getDescripcion(), entity.getDescripcion());
        Assert.assertNull(actividad.getProfesor());
        Assert.assertEquals(actividad.getEstudiantes().size(), entity.getEstudiantes().size());
    }    
    
     private void clearData() {
        em.createQuery("delete from ActividadEntity").executeUpdate();
    }
     
     private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            
            ActividadEntity entity = factory.manufacturePojo(ActividadEntity.class);
            em.persist(entity);

            data.add(entity);
        }
    }
     
    @Test
    public void getActividadesTest() {
        List<ActividadEntity> list = ac.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (ActividadEntity ent : list) {
            boolean found = false;
            for (ActividadEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    
    @Test
    public void getActividadTest() {
        ActividadEntity entity = data.get(0);
        ActividadEntity newEntity = ac.find(entity.getId());
        Assert.assertNotNull(newEntity);
        
        Assert.assertEquals(entity.getFecha(), newEntity.getFecha());
        Assert.assertEquals(entity.getTipo(), newEntity.getTipo());
        Assert.assertEquals(entity.getDescripcion(), newEntity.getDescripcion());
    }
    @Test
    public void updateActividadTest() {
        ActividadEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        ActividadEntity newEntity = factory.manufacturePojo(ActividadEntity.class);

        newEntity.setId(entity.getId());

        ac.update(newEntity);
        
        ActividadEntity resp = em.find(ActividadEntity.class, entity.getId());
        
        Assert.assertEquals(resp.getFecha(), newEntity.getFecha());
        Assert.assertEquals(resp.getTipo(), newEntity.getTipo());
        Assert.assertEquals(resp.getDescripcion(), newEntity.getDescripcion());
    }
    
    @Test
    public void deleteActividadTest() {
        ActividadEntity entity = data.get(0);
        ac.delete(entity.getId());
        ActividadEntity deleted = em.find(ActividadEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
}
