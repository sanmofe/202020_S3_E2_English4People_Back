/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.english4people.test.persistence;

import co.edu.uniandes.csw.english4people.entities.DiaSemanaEntity;
import co.edu.uniandes.csw.english4people.persistence.DiaSemanaPersistence;
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
 * @author Juan Diego Gonzalez Gomez
 */
@RunWith(Arquillian.class)
public class DiaSemanaPersistenceTest
{
    @Inject
    private DiaSemanaPersistence ip;
    
    @PersistenceContext
    private EntityManager em;    
    
    @Inject
    UserTransaction utx;

    private List<DiaSemanaEntity> data = new ArrayList<DiaSemanaEntity>();
    
    @Deployment
    public static JavaArchive createDeployment()
    {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(DiaSemanaEntity.class.getPackage())
                .addPackage(DiaSemanaPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml",
                        "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
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
        em.createQuery("delete from DiaSemanaEntity").executeUpdate();
    }

    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {

            DiaSemanaEntity entity = factory.manufacturePojo(DiaSemanaEntity.class);

            em.persist(entity);

            data.add(entity);
        }
    }
    
    @Test
    public void createTest()
    {
        PodamFactory factory = new PodamFactoryImpl();
        DiaSemanaEntity diaSemana = factory.manufacturePojo(DiaSemanaEntity.class);
        
        DiaSemanaEntity result = ip.create(diaSemana);
        Assert.assertNotNull(result);
        
        DiaSemanaEntity entity = em.find(DiaSemanaEntity.class, result.getId());
        
        Assert.assertEquals(diaSemana.getLunes(), entity.getLunes());
        Assert.assertEquals(diaSemana.getMartes(), entity.getMartes());
        Assert.assertEquals(diaSemana.getMiercoles(), entity.getMiercoles());
        Assert.assertEquals(diaSemana.getJueves(), entity.getJueves());
        Assert.assertEquals(diaSemana.getViernes(), entity.getViernes());
        Assert.assertEquals(diaSemana.getSabado(), entity.getSabado());
        Assert.assertEquals(diaSemana.getDomingo(), entity.getDomingo());
    }
    
    @Test
    public void getDiasSemanaTest() {
        List<DiaSemanaEntity> list = ip.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (DiaSemanaEntity ent : list) {
            boolean found = false;
            for (DiaSemanaEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    
    @Test
    public void getDiaSemanaTest() {
        DiaSemanaEntity entity = data.get(0);
        DiaSemanaEntity newEntity = ip.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getLunes(), newEntity.getLunes());
        Assert.assertEquals(entity.getMartes(), newEntity.getMartes());
        Assert.assertEquals(entity.getMiercoles(), newEntity.getMiercoles());
        Assert.assertEquals(entity.getJueves(), newEntity.getJueves());
        Assert.assertEquals(entity.getViernes(), newEntity.getViernes());
        Assert.assertEquals(entity.getSabado(), newEntity.getSabado());
        Assert.assertEquals(entity.getDomingo(), newEntity.getDomingo());
    }
    
    @Test
    public void updateDiaSemanaTest() {
        DiaSemanaEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        DiaSemanaEntity newEntity = factory.manufacturePojo(DiaSemanaEntity.class);

        newEntity.setId(entity.getId());

        ip.update(newEntity);

        DiaSemanaEntity resp = em.find(DiaSemanaEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getLunes(), resp.getLunes());
        Assert.assertEquals(newEntity.getMartes(), resp.getMartes());
        Assert.assertEquals(newEntity.getMiercoles(), resp.getMiercoles());
        Assert.assertEquals(newEntity.getJueves(), resp.getJueves());
        Assert.assertEquals(newEntity.getViernes(), resp.getViernes());
        Assert.assertEquals(newEntity.getSabado(), resp.getSabado());
        Assert.assertEquals(newEntity.getDomingo(), resp.getDomingo());
    }
    
    @Test
    public void deleteDiaSemanaTest() {
        DiaSemanaEntity entity = data.get(0);
        ip.delete(entity.getId());
        DiaSemanaEntity deleted = em.find(DiaSemanaEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
}
