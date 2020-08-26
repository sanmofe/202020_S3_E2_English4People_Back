/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.english4people.test.persistence;

import co.edu.uniandes.csw.english4people.entities.EstudianteEntity;
import co.edu.uniandes.csw.english4people.persistence.EstudiantePersistence;
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
 * @author Juan David Becerra Romero
 */
@RunWith(Arquillian.class)
public class EstudiantePersistenceTest {
    
    @Deployment
    public static JavaArchive createDeployment()
    {
        return ShrinkWrap.create(JavaArchive.class).addPackage(EstudianteEntity.class.getPackage()).addPackage(EstudiantePersistence.class.getPackage()).addAsManifestResource("META-INF/persistence.xml","persistence.xml").addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
     @Inject
    EstudiantePersistence ep;
    
    @PersistenceContext
    private EntityManager em;
    
    @Test
    public void createTest()
    {
        PodamFactory factory = new PodamFactoryImpl();
        EstudianteEntity estudiante = factory.manufacturePojo(EstudianteEntity.class);
        EstudianteEntity result = ep.create(estudiante);
        
        Assert.assertNotNull(result);
        
        EstudianteEntity entity = em.find(EstudianteEntity.class, result.getId());
        
        Assert.assertEquals(estudiante.getNombre(), entity.getNombre());
        Assert.assertEquals(estudiante.getIdentificacion(), entity.getIdentificacion());
        Assert.assertEquals(estudiante.getLogin(), entity.getLogin());
        Assert.assertEquals(estudiante.getCorreo(), entity.getCorreo());
        Assert.assertEquals(estudiante.getContrasena(),  entity.getContrasena());
    }
    
}
