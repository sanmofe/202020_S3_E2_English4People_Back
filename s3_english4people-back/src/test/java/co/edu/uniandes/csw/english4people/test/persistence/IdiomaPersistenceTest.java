/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.english4people.test.persistence;

import co.edu.uniandes.csw.english4people.entities.IdiomaEntity;
import co.edu.uniandes.csw.english4people.persistence.IdiomaPersistence;
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
public class IdiomaPersistenceTest
{
    @Inject
    private IdiomaPersistence ip;
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    UserTransaction utx;

    private List<IdiomaEntity> data = new ArrayList<IdiomaEntity>();
    
    @Deployment
    public static JavaArchive createDeployment()
    {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(IdiomaEntity.class.getPackage())
                .addPackage(IdiomaPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
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
        em.createQuery("delete from IdiomaEntity").executeUpdate();
    }

    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {

            IdiomaEntity entity = factory.manufacturePojo(IdiomaEntity.class);

            em.persist(entity);

            data.add(entity);
        }
    }    
    
    @Test
    public void createTest()
    {
        PodamFactory factory = new PodamFactoryImpl();
        IdiomaEntity idioma = factory.manufacturePojo(IdiomaEntity.class);
        IdiomaEntity result = ip.create(idioma);
        
        Assert.assertNotNull(result);
        
        IdiomaEntity entity = em.find(IdiomaEntity.class, result.getId());
        
        Assert.assertEquals(idioma.getEspanol(), entity.getEspanol());
        Assert.assertEquals(idioma.getIngles(), entity.getIngles());
        Assert.assertEquals(idioma.getFrances(), entity.getFrances());
        Assert.assertEquals(idioma.getItaliano(), entity.getItaliano());
        Assert.assertEquals(idioma.getPortugues(), entity.getPortugues());
        Assert.assertEquals(idioma.getAleman(), entity.getAleman());
        Assert.assertEquals(idioma.getJapones(), entity.getJapones());
        Assert.assertEquals(idioma.getCoreano(), entity.getCoreano());
        Assert.assertEquals(idioma.getMandarin(), entity.getMandarin());
    }
    
    @Test
    public void getIdiomasTest() {
        List<IdiomaEntity> list = ip.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (IdiomaEntity ent : list) {
            boolean found = false;
            for (IdiomaEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    
    @Test
    public void getIdiomaTest() {
        IdiomaEntity entity = data.get(0);
        IdiomaEntity newEntity = ip.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getEspanol(), newEntity.getEspanol());
        Assert.assertEquals(entity.getIngles(), newEntity.getIngles());
        Assert.assertEquals(entity.getFrances(), newEntity.getFrances());
        Assert.assertEquals(entity.getItaliano(), newEntity.getItaliano());
        Assert.assertEquals(entity.getPortugues(), newEntity.getPortugues());
        Assert.assertEquals(entity.getAleman(), newEntity.getAleman());
        Assert.assertEquals(entity.getJapones(), newEntity.getJapones());
        Assert.assertEquals(entity.getCoreano(), newEntity.getCoreano());
        Assert.assertEquals(entity.getMandarin(), newEntity.getMandarin());
    }
    
    @Test
    public void updateIdiomaTest() {
        IdiomaEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        IdiomaEntity newEntity = factory.manufacturePojo(IdiomaEntity.class);

        newEntity.setId(entity.getId());

        ip.update(newEntity);

        IdiomaEntity resp = em.find(IdiomaEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getEspanol(), resp.getEspanol());
        Assert.assertEquals(newEntity.getIngles(), resp.getIngles());
        Assert.assertEquals(newEntity.getFrances(), resp.getFrances());
        Assert.assertEquals(newEntity.getItaliano(), resp.getItaliano());
        Assert.assertEquals(newEntity.getPortugues(), resp.getPortugues());
        Assert.assertEquals(newEntity.getAleman(), resp.getAleman());
        Assert.assertEquals(newEntity.getJapones(), resp.getJapones());
        Assert.assertEquals(newEntity.getCoreano(), resp.getCoreano());
        Assert.assertEquals(newEntity.getMandarin(), resp.getMandarin());
    }
    
    @Test
    public void deleteIdiomaTest() {
        IdiomaEntity entity = data.get(0);
        ip.delete(entity.getId());
        IdiomaEntity deleted = em.find(IdiomaEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
}
