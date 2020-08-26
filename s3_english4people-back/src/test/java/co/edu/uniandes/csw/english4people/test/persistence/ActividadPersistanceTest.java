/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.english4people.test.persistence;


import co.edu.uniandes.csw.english4people.entities.ActividadEntity;
import co.edu.uniandes.csw.english4people.persistence.ActividadPersistence;
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
 * @author Juan Diego Castellanos Bonilla
 */
@RunWith(Arquillian.class)
public class ActividadPersistanceTest {
    @Deployment
    public static JavaArchive createDeployment(){
    return ShrinkWrap.create(JavaArchive.class).addPackage(ActividadEntity.class.getPackage()).addPackage(ActividadPersistence.class.getPackage()).addAsManifestResource("META-INF/persistence.xml","persistence.xml").addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    @Inject
    ActividadPersistence ac;
    
    @PersistenceContext
    private EntityManager em;
    
    @Test
    public void createTest()
    {
        PodamFactory factory = new PodamFactoryImpl();
        ActividadEntity actividad = factory.manufacturePojo(ActividadEntity.class);
        ActividadEntity result = ac.create(actividad);
        
        Assert.assertNotNull(result);
        
        ActividadEntity entity = em.find(ActividadEntity.class, result.getId());
        
        Assert.assertEquals(actividad.getFecha(), entity.getFecha());
        Assert.assertEquals(actividad.getTipo(), entity.getTipo());
        Assert.assertEquals(actividad.getDescripcion(), entity.getDescripcion());
    }    
}
