/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.english4people.test.persistence;




import co.edu.uniandes.csw.english4people.entities.MaterialDeClaseEntity;
import co.edu.uniandes.csw.english4people.persistence.MaterialDeClasePersistence;
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
public class MaterialDeClasePersistanceTest {
    @Deployment
    public static JavaArchive createDeployment(){
    return ShrinkWrap.create(JavaArchive.class).addPackage(MaterialDeClaseEntity.class.getPackage()).addPackage(MaterialDeClasePersistence.class.getPackage()).addAsManifestResource("META-INF/persistence.xml","persistence.xml").addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    @Inject
    MaterialDeClasePersistence mdc;
    
    @PersistenceContext
    private EntityManager em;
    
     @Inject
    UserTransaction utx;
    private List<MaterialDeClaseEntity> data = new ArrayList<MaterialDeClaseEntity>();
    
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
        MaterialDeClaseEntity material = factory.manufacturePojo(MaterialDeClaseEntity.class);
        MaterialDeClaseEntity result = mdc.create(material);
        Assert.assertNotNull(result);
        MaterialDeClaseEntity entity = em.find(MaterialDeClaseEntity.class, result.getId());
        
        Assert.assertEquals(material.getEnlaceMaterial(), entity.getEnlaceMaterial());
    }

 private void clearData() {
        em.createQuery("delete from MaterialDeClaseEntity").executeUpdate();
    }
     
     private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            
            MaterialDeClaseEntity entity = factory.manufacturePojo(MaterialDeClaseEntity.class);
            em.persist(entity);

            data.add(entity);
        }
    }
     
    @Test
    public void getClasesTest() {
        List<MaterialDeClaseEntity> list = mdc.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (MaterialDeClaseEntity ent : list) {
            boolean found = false;
            for (MaterialDeClaseEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    
    @Test
    public void getClaseTest() {
        MaterialDeClaseEntity entity = data.get(0);
        MaterialDeClaseEntity newEntity = mdc.find(entity.getId());
        Assert.assertNotNull(newEntity);
        
        Assert.assertEquals(entity.getEnlaceMaterial(), newEntity.getEnlaceMaterial());
    }
    
    @Test
    public void updateClaseTest() {
        MaterialDeClaseEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        MaterialDeClaseEntity newEntity = factory.manufacturePojo(MaterialDeClaseEntity.class);

        newEntity.setId(entity.getId());

        mdc.update(newEntity);
        
        MaterialDeClaseEntity resp = em.find(MaterialDeClaseEntity.class, entity.getId());
        
        Assert.assertEquals(resp.getEnlaceMaterial(), newEntity.getEnlaceMaterial());
    }
    
     @Test
    public void deleteClaseTest() {
        MaterialDeClaseEntity entity = data.get(0);
        mdc.delete(entity.getId());
        MaterialDeClaseEntity deleted = em.find(MaterialDeClaseEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
        
}
