/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.english4people.test.persistence;

import co.edu.uniandes.csw.english4people.entities.ClaseEntity;
import co.edu.uniandes.csw.english4people.persistence.ClasePersistence;
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
public class ClasePersistanceTest {
    
    @Deployment
    public static JavaArchive createDeployment(){
    return ShrinkWrap.create(JavaArchive.class).addPackage(ClaseEntity.class.getPackage()).addPackage(ClasePersistence.class.getPackage()).addAsManifestResource("META-INF/persistence.xml","persistence.xml").addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    @Inject
    ClasePersistence cp;
    
    @PersistenceContext
    private EntityManager em;
    
    @Test
    public void createTest()
    {
        PodamFactory factory = new PodamFactoryImpl();
        ClaseEntity clase = factory.manufacturePojo(ClaseEntity.class);
        ClaseEntity result = cp.create(clase);
        
        Assert.assertNotNull(result);
        
        ClaseEntity entity = em.find(ClaseEntity.class, result.getId());
        
        Assert.assertEquals(clase.getFecha(), entity.getFecha());
        Assert.assertEquals(clase.getTipoClase(), entity.getTipoClase());
    }
}
