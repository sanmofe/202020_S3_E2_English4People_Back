/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.english4people.test.logic;

import co.edu.uniandes.csw.english4people.ejb.DiaSemanaLogic;
import co.edu.uniandes.csw.english4people.entities.DiaSemanaEntity;
import co.edu.uniandes.csw.english4people.exceptions.BusinessLogicException;
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
public class DiaSemanaLogicTest {

    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private DiaSemanaLogic diaSemanaLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<DiaSemanaEntity> data = new ArrayList<DiaSemanaEntity>();

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(DiaSemanaEntity.class.getPackage())
                .addPackage(DiaSemanaLogic.class.getPackage())
                .addPackage(DiaSemanaPersistence.class.getPackage())
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
        em.createQuery("delete from DiaSemanaEntity").executeUpdate();
    }

    private void insertData() {
        for (int i = 0; i < 3; i++) {
            DiaSemanaEntity diaSemana = factory.manufacturePojo(DiaSemanaEntity.class);
            em.persist(diaSemana);
            data.add(diaSemana);
        }
    }

    /*
     *Prueba en la que sí se cumple la regla de negocio
     */
    @Test
    public void createDiaSemana() throws BusinessLogicException {
        DiaSemanaEntity newEntity = factory.manufacturePojo(DiaSemanaEntity.class);
        newEntity.setLunes(true);
        DiaSemanaEntity result = diaSemanaLogic.createDiaSemana(newEntity);
        Assert.assertNotNull(result);

        DiaSemanaEntity entity = em.find(DiaSemanaEntity.class, result.getId());
        Assert.assertEquals(entity.getId(), result.getId());
        Assert.assertEquals(entity.getLunes(), result.getLunes());
        Assert.assertEquals(entity.getMartes(), result.getMartes());
        Assert.assertEquals(entity.getMiercoles(), result.getMiercoles());
        Assert.assertEquals(entity.getJueves(), result.getJueves());
        Assert.assertEquals(entity.getViernes(), result.getViernes());
        Assert.assertEquals(entity.getSabado(), result.getSabado());
        Assert.assertEquals(entity.getDomingo(), result.getDomingo());
    }

    /*
     *Prueba en la que no se cumple la regla de negocio
     */
    @Test(expected = BusinessLogicException.class)
    public void createDiaSemanaSinDia() throws BusinessLogicException {
        DiaSemanaEntity newEntity = factory.manufacturePojo(DiaSemanaEntity.class);
        newEntity.setLunes(false);
        newEntity.setMartes(false);
        newEntity.setMiercoles(false);
        newEntity.setJueves(false);
        newEntity.setViernes(false);
        newEntity.setSabado(false);
        newEntity.setDomingo(false);

        DiaSemanaEntity result = diaSemanaLogic.createDiaSemana(newEntity);
    }
    
    @Test
    public void getDiasSemanaTest() {
        List<DiaSemanaEntity> list = diaSemanaLogic.getDiasSemana();
        Assert.assertEquals(data.size(), list.size());
        for (DiaSemanaEntity entity : list) {
            boolean found = false;
            for (DiaSemanaEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    @Test
    public void getDiaSemanaTest() {
        DiaSemanaEntity entity = data.get(0);
        DiaSemanaEntity resultEntity = diaSemanaLogic.getDiaSemana(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getLunes(), resultEntity.getLunes());
        Assert.assertEquals(entity.getMartes(), resultEntity.getMartes());
        Assert.assertEquals(entity.getMiercoles(), resultEntity.getMiercoles());
        Assert.assertEquals(entity.getJueves(), resultEntity.getJueves());
        Assert.assertEquals(entity.getViernes(), resultEntity.getViernes());
        Assert.assertEquals(entity.getSabado(), resultEntity.getSabado());
        Assert.assertEquals(entity.getDomingo(), resultEntity.getDomingo());
    }

    /*
     *Prueba en la que sí se cumple la regla de negocio
     */
    @Test
    public void updateDiaSemanaTest() throws BusinessLogicException {
        DiaSemanaEntity entity = data.get(0);
        DiaSemanaEntity pojoEntity = factory.manufacturePojo(DiaSemanaEntity.class);
        pojoEntity.setLunes(true);
        pojoEntity.setId(entity.getId());
        diaSemanaLogic.updateDiaSemana(pojoEntity.getId(), pojoEntity);
        DiaSemanaEntity resp = em.find(DiaSemanaEntity.class, entity.getId());
        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getLunes(), resp.getLunes());
        Assert.assertEquals(pojoEntity.getMartes(), resp.getMartes());
        Assert.assertEquals(pojoEntity.getMiercoles(), resp.getMiercoles());
        Assert.assertEquals(pojoEntity.getJueves(), resp.getJueves());
        Assert.assertEquals(pojoEntity.getViernes(), resp.getViernes());
        Assert.assertEquals(pojoEntity.getSabado(), resp.getSabado());
        Assert.assertEquals(pojoEntity.getDomingo(), resp.getDomingo());
    }

    /*
     *Prueba en la que no se cumple la regla de negocio
     */
    @Test(expected = BusinessLogicException.class)
    public void updateDiaSemanaSinDiaTest() throws BusinessLogicException {
        DiaSemanaEntity entity = data.get(0);
        DiaSemanaEntity pojoEntity = factory.manufacturePojo(DiaSemanaEntity.class);
        pojoEntity.setLunes(false);
        pojoEntity.setMartes(false);
        pojoEntity.setMiercoles(false);
        pojoEntity.setJueves(false);
        pojoEntity.setViernes(false);
        pojoEntity.setSabado(false);
        pojoEntity.setDomingo(false);
        pojoEntity.setId(entity.getId());
        diaSemanaLogic.updateDiaSemana(pojoEntity.getId(), pojoEntity);
    }

    @Test
    public void deleteDiaSemanaTest() throws BusinessLogicException {
        DiaSemanaEntity entity = data.get(0);
        diaSemanaLogic.deleteDiaSemana(entity.getId());
        DiaSemanaEntity deleted = em.find(DiaSemanaEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
}
