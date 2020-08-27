/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.english4people.test.persistence;

import co.edu.uniandes.csw.english4people.entities.ProfesoresEntity;
import co.edu.uniandes.csw.english4people.persistence.ProfesoresPersistence;
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
 * @author Juan Andres Santiago Vasquez
 */

@RunWith(Arquillian.class)
public class ProfesoresPersistanceTest {
    
     @Deployment
    public static JavaArchive createDeployment(){
    return ShrinkWrap.create(JavaArchive.class).addPackage(ProfesoresEntity.class.getPackage()).addPackage(ProfesoresPersistence.class.getPackage()).addAsManifestResource("META-INF/persistence.xml","persistence.xml").addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    @Inject
    ProfesoresPersistence mdc;
    
    @PersistenceContext
    private EntityManager em;
    
    @Test
    public void createTest()
    {
        PodamFactory factory = new PodamFactoryImpl();
        ProfesoresEntity material = factory.manufacturePojo(ProfesoresEntity.class);
        ProfesoresEntity result = mdc.create(material);
        Assert.assertNotNull(result);
        ProfesoresEntity entity = em.find(ProfesoresEntity.class, result.getId());
        
        Assert.assertEquals(material.getNombre(), entity.getNombre());
        Assert.assertEquals(material.getIdentificacion(), entity.getIdentificacion());
        Assert.assertEquals(material.getLogin(), entity.getLogin());
        Assert.assertEquals(material.getCorreo(), entity.getCorreo());
        Assert.assertEquals(material.getContrasena(), entity.getContrasena());
        Assert.assertEquals(material.getInformacionAcademica(), entity.getInformacionAcademica());
        Assert.assertEquals(material.getCanalYoutube(), entity.getCanalYoutube());
        
    }    
    
}
