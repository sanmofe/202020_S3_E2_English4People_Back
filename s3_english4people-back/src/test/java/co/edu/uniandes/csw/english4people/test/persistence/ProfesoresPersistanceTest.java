/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.english4people.test.persistence;

import co.edu.uniandes.csw.english4people.entities.ProfesoresEntity;
import co.edu.uniandes.csw.english4people.persistence.ProfesoresPersistence;
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
 * @author Juan Andres Santiago Vasquez
 */

@RunWith(Arquillian.class)
public class ProfesoresPersistanceTest {
    
    
    @Inject
    UserTransaction utx;

    private List<ProfesoresEntity> data = new ArrayList<ProfesoresEntity>();
    
    @Inject
    ProfesoresPersistence pp;
    
    @PersistenceContext
    private EntityManager em;
    
    @Deployment
    public static JavaArchive createDeployment(){
    return ShrinkWrap.create(JavaArchive.class).addPackage(ProfesoresEntity.class.getPackage()).addPackage(ProfesoresPersistence.class.getPackage()).addAsManifestResource("META-INF/persistence.xml","persistence.xml").addAsManifestResource("META-INF/beans.xml", "beans.xml");
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
    
    private void clearData() {
        em.createQuery("delete from ProfesoresEntity").executeUpdate();
    }

    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {

            ProfesoresEntity entity = factory.manufacturePojo(ProfesoresEntity.class);

            em.persist(entity);

            data.add(entity);
        }
    }    
    
    
    @Test
    public void createTest()
    {
        PodamFactory factory = new PodamFactoryImpl();
        ProfesoresEntity profesor = factory.manufacturePojo(ProfesoresEntity.class);
        ProfesoresEntity result = pp.create(profesor);
        Assert.assertNotNull(result);
        ProfesoresEntity entity = em.find(ProfesoresEntity.class, result.getId());
        
        Assert.assertEquals(profesor.getNombre(), entity.getNombre());
        Assert.assertEquals(profesor.getIdentificacion(), entity.getIdentificacion());
        Assert.assertEquals(profesor.getLogin(), entity.getLogin());
        Assert.assertEquals(profesor.getCorreo(), entity.getCorreo());
        Assert.assertEquals(profesor.getContrasena(), entity.getContrasena());
        Assert.assertEquals(profesor.getInformacionAcademica(), entity.getInformacionAcademica());
        Assert.assertEquals(profesor.getCanalYoutube(), entity.getCanalYoutube());
        
    }    
    
    @Test
    public void getProfesoresTest() {
        List<ProfesoresEntity> list = pp.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (ProfesoresEntity ent : list) {
            boolean found = false;
            for (ProfesoresEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    
    @Test
    public void getProfesorTest() {
        ProfesoresEntity entity = data.get(0);
        ProfesoresEntity newEntity = pp.find(entity.getId());
        Assert.assertNotNull(newEntity);
        
        Assert.assertEquals(entity.getNombre(), newEntity.getNombre());
        Assert.assertEquals(entity.getIdentificacion(), newEntity.getIdentificacion());
        Assert.assertEquals(entity.getLogin(), newEntity.getLogin());
        Assert.assertEquals(entity.getCorreo(), newEntity.getCorreo());
        Assert.assertEquals(entity.getContrasena(), newEntity.getContrasena());
        Assert.assertEquals(entity.getInformacionAcademica(), newEntity.getInformacionAcademica());
        Assert.assertEquals(entity.getCanalYoutube(), newEntity.getCanalYoutube());
    }
    
    @Test
    public void updateProfesorTest() {
        ProfesoresEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        ProfesoresEntity newEntity = factory.manufacturePojo(ProfesoresEntity.class);

        newEntity.setId(entity.getId());

        pp.update(newEntity);

        ProfesoresEntity resp = em.find(ProfesoresEntity.class, entity.getId());
        
        Assert.assertEquals(newEntity.getNombre(), resp.getNombre());
        Assert.assertEquals(newEntity.getIdentificacion(), resp.getIdentificacion());
        Assert.assertEquals(newEntity.getLogin(), resp.getLogin());
        Assert.assertEquals(newEntity.getCorreo(), resp.getCorreo());
        Assert.assertEquals(newEntity.getContrasena(), resp.getContrasena());
        Assert.assertEquals(newEntity.getInformacionAcademica(), resp.getInformacionAcademica());
        Assert.assertEquals(newEntity.getCanalYoutube(), resp.getCanalYoutube());
    }
    
    @Test
    public void deleteProfesoresTest() {
        ProfesoresEntity entity = data.get(0);
        pp.delete(entity.getId());
        ProfesoresEntity deleted = em.find(ProfesoresEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    
}
