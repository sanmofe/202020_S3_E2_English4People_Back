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
public class IdiomaLogicTest
{
    private PodamFactory factory = new PodamFactoryImpl();
    
    @Inject
    private IdiomaLogic idiomaLogic;
    
    @PersistenceContext
    private EntityManager em;
    
    @Deployment
    public static JavaArchive createDeployment()
    {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(IdiomaEntity.class.getPackage())
                .addPackage(IdiomaLogic.class.getPackage())
                .addPackage(IdiomaPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    @Test
    public void createIdioma () throws BusinessLogicException
    {
        IdiomaEntity newEntity = factory.manufacturePojo(IdiomaEntity.class);
        IdiomaEntity result = idiomaLogic.createIdioma(newEntity);
        Assert.assertNotNull(result);
        
        IdiomaEntity entity = em.find(IdiomaEntity.class, result.getId());
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
    
    @Test (expected = BusinessLogicException.class)
    public void createIdiomaSinIdioma () throws BusinessLogicException
    {
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
}
