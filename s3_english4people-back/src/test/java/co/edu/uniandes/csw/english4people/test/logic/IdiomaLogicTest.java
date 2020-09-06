/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.english4people.test.logic;

import co.edu.uniandes.csw.english4people.ejb.IdiomaLogic;
import co.edu.uniandes.csw.english4people.entities.IdiomaEntity;
import co.edu.uniandes.csw.english4people.exceptions.BusinessLogicException;
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
public class IdiomaLogicTest {

    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private IdiomaLogic idiomaLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<IdiomaEntity> data = new ArrayList<IdiomaEntity>();

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(IdiomaEntity.class.getPackage())
                .addPackage(IdiomaLogic.class.getPackage())
                .addPackage(IdiomaPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    @Before
    public void configTest() {
        try {
            utx.begin();
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
        for (int i = 0; i < 3; i++) {
            IdiomaEntity idioma = factory.manufacturePojo(IdiomaEntity.class);
            em.persist(idioma);
            data.add(idioma);
        }
    }

    /*
     *Prueba en la que sí se cumple la regla de negocio
     */
    @Test
    public void createIdioma() throws BusinessLogicException {
        IdiomaEntity newEntity = factory.manufacturePojo(IdiomaEntity.class);
        newEntity.setAleman(true);
        IdiomaEntity result = idiomaLogic.createIdioma(newEntity);
        Assert.assertNotNull(result);

        IdiomaEntity entity = em.find(IdiomaEntity.class, result.getId());
        Assert.assertEquals(entity.getId(), result.getId());
        Assert.assertEquals(entity.getEspanol(), result.getEspanol());
        Assert.assertEquals(entity.getIngles(), result.getIngles());
        Assert.assertEquals(entity.getFrances(), result.getFrances());
        Assert.assertEquals(entity.getItaliano(), result.getItaliano());
        Assert.assertEquals(entity.getPortugues(), result.getPortugues());
        Assert.assertEquals(entity.getAleman(), result.getAleman());
        Assert.assertEquals(entity.getJapones(), result.getJapones());
        Assert.assertEquals(entity.getCoreano(), result.getCoreano());
        Assert.assertEquals(entity.getMandarin(), result.getMandarin());
    }

    /*
     *Prueba en la que no se cumple la regla de negocio
     */
    @Test(expected = BusinessLogicException.class)
    public void createIdiomaSinIdioma() throws BusinessLogicException {
        IdiomaEntity newEntity = factory.manufacturePojo(IdiomaEntity.class);
        newEntity.setAleman(false);
        newEntity.setCoreano(false);
        newEntity.setEspanol(false);
        newEntity.setFrances(false);
        newEntity.setIngles(false);
        newEntity.setItaliano(false);
        newEntity.setJapones(false);
        newEntity.setMandarin(false);
        newEntity.setPortugues(false);

        IdiomaEntity result = idiomaLogic.createIdioma(newEntity);
    }

    @Test
    public void getIdiomasTest() {
        List<IdiomaEntity> list = idiomaLogic.getIdiomas();
        Assert.assertEquals(data.size(), list.size());
        for (IdiomaEntity entity : list) {
            boolean found = false;
            for (IdiomaEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    @Test
    public void getIdiomaTest() {
        IdiomaEntity entity = data.get(0);
        IdiomaEntity resultEntity = idiomaLogic.getIdioma(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getEspanol(), resultEntity.getEspanol());
        Assert.assertEquals(entity.getIngles(), resultEntity.getIngles());
        Assert.assertEquals(entity.getFrances(), resultEntity.getFrances());
        Assert.assertEquals(entity.getItaliano(), resultEntity.getItaliano());
        Assert.assertEquals(entity.getPortugues(), resultEntity.getPortugues());
        Assert.assertEquals(entity.getAleman(), resultEntity.getAleman());
        Assert.assertEquals(entity.getJapones(), resultEntity.getJapones());
        Assert.assertEquals(entity.getCoreano(), resultEntity.getCoreano());
        Assert.assertEquals(entity.getMandarin(), resultEntity.getMandarin());
    }

    /*
     *Prueba en la que sí se cumple la regla de negocio
     */
    @Test
    public void updateIdiomaTest() throws BusinessLogicException {
        IdiomaEntity entity = data.get(0);
        IdiomaEntity pojoEntity = factory.manufacturePojo(IdiomaEntity.class);
        pojoEntity.setAleman(true);
        pojoEntity.setId(entity.getId());
        idiomaLogic.updateIdioma(pojoEntity.getId(), pojoEntity);
        IdiomaEntity resp = em.find(IdiomaEntity.class, entity.getId());
        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getEspanol(), resp.getEspanol());
        Assert.assertEquals(pojoEntity.getIngles(), resp.getIngles());
        Assert.assertEquals(pojoEntity.getFrances(), resp.getFrances());
        Assert.assertEquals(pojoEntity.getItaliano(), resp.getItaliano());
        Assert.assertEquals(pojoEntity.getPortugues(), resp.getPortugues());
        Assert.assertEquals(pojoEntity.getAleman(), resp.getAleman());
        Assert.assertEquals(pojoEntity.getJapones(), resp.getJapones());
        Assert.assertEquals(pojoEntity.getCoreano(), resp.getCoreano());
        Assert.assertEquals(pojoEntity.getMandarin(), resp.getMandarin());
    }

    /*
     *Prueba en la que no se cumple la regla de negocio
     */
    @Test(expected = BusinessLogicException.class)
    public void updateIdiomaSinIdiomaTest() throws BusinessLogicException {
        IdiomaEntity entity = data.get(0);
        IdiomaEntity pojoEntity = factory.manufacturePojo(IdiomaEntity.class);
        pojoEntity.setAleman(false);
        pojoEntity.setCoreano(false);
        pojoEntity.setEspanol(false);
        pojoEntity.setFrances(false);
        pojoEntity.setIngles(false);
        pojoEntity.setItaliano(false);
        pojoEntity.setJapones(false);
        pojoEntity.setMandarin(false);
        pojoEntity.setPortugues(false);
        pojoEntity.setId(entity.getId());
        idiomaLogic.updateIdioma(pojoEntity.getId(), pojoEntity);
    }

    @Test
    public void deleteIdiomaTest() throws BusinessLogicException {
        IdiomaEntity entity = data.get(0);
        idiomaLogic.deleteIdioma(entity.getId());
        IdiomaEntity deleted = em.find(IdiomaEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
}
