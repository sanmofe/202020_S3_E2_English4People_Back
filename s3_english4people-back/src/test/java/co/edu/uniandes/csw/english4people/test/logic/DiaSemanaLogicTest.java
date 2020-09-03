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
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author Juan Diego Gonzalez Gomez
 */
@RunWith(Arquillian.class)
public class DiaSemanaLogicTest
{
    private PodamFactory factory = new PodamFactoryImpl();
    
    @Inject
    private DiaSemanaLogic diaSemanaLogic;
    
    @PersistenceContext
    private EntityManager em;
    
    @Deployment
    public static JavaArchive createDeployment()
    {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(DiaSemanaEntity.class.getPackage())
                .addPackage(DiaSemanaLogic.class.getPackage())
                .addPackage(DiaSemanaPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
        
    @Test
    public void createDiaSemana () throws BusinessLogicException
    {
        DiaSemanaEntity newEntity = factory.manufacturePojo(DiaSemanaEntity.class);
        DiaSemanaEntity result = diaSemanaLogic.createDiaSemana(newEntity);
        Assert.assertNotNull(result);
        
        DiaSemanaEntity entity = em.find(DiaSemanaEntity.class, result.getId());
        Assert.assertEquals(entity.getLunes(), result.getLunes());
        Assert.assertEquals(entity.getMartes(), result.getMartes());
        Assert.assertEquals(entity.getMiercoles(), result.getMiercoles());
        Assert.assertEquals(entity.getJueves(), result.getJueves());
        Assert.assertEquals(entity.getViernes(), result.getViernes());
        Assert.assertEquals(entity.getSabado(), result.getSabado());
        Assert.assertEquals(entity.getDomingo(), result.getDomingo());
    }
    
    @Test (expected = BusinessLogicException.class)
    public void createDiaSemanaSinDia () throws BusinessLogicException
    {
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
}
