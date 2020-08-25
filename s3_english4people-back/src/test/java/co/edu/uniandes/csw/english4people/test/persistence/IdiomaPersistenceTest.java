/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.english4people.test.persistence;

import co.edu.uniandes.csw.english4people.entities.IdiomaEntity;
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
 * @author Juan Diego Gonzalez
 */
@RunWith(Arquillian.class)
public class IdiomaPersistenceTest {
   
    private IdiomaEntity idioma;
    
    @Deployment
    public static JavaArchive createDeployment()
    {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(IdiomaEntity.class.getPackage())
                .addPackage(IdiomaPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml",
                        "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    @Inject
    private IdiomaPersistence ip;
    
    @PersistenceContext
    private EntityManager em;
    
    @Test
    public void createTest()
    {
        PodamFactory factory = new PodamFactoryImpl();
        idioma = factory.manufacturePojo(IdiomaEntity.class);
        
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
}
