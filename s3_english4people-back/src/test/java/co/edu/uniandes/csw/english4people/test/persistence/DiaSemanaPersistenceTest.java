/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.english4people.test.persistence;

import co.edu.uniandes.csw.english4people.entities.DiaSemanaEntity;
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
public class DiaSemanaPersistenceTest
{
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
    
    @Inject
    private DiaSemanaPersistence ip;
    
    @PersistenceContext
    private EntityManager em;
    
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
}
