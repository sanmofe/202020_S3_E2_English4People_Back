/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.english4people.test.persistence;

import co.edu.uniandes.csw.english4people.entities.HorarioEntity;
import co.edu.uniandes.csw.english4people.persistence.HorarioPersistence;
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
 * @author estudiante
 */
@RunWith(Arquillian.class)
public class HorarioPersistenceTest {
    
    @Deployment
    public static JavaArchive createDeployment(){
        return ShrinkWrap.create(JavaArchive.class).addPackage(HorarioEntity.class.getPackage()).addPackage(HorarioPersistence.class.getPackage()).addAsManifestResource("META-INF/persistence.xml", "persistence.xml").addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    @Inject
    HorarioPersistence hp;
    
    @PersistenceContext
    private EntityManager em;
    
    @Test
    public void createTest(){
        PodamFactory factory = new PodamFactoryImpl();
        HorarioEntity contrato = factory.manufacturePojo(HorarioEntity.class);
        HorarioEntity result = hp.create(contrato);
        Assert.assertNotNull(result);
        
        HorarioEntity entity = em.find(HorarioEntity.class, result.getId());
        Assert.assertEquals(contrato.getHoraInicio(), entity.getHoraInicio());
        Assert.assertEquals(contrato.getHoraFin(), entity.getHoraFin());
    }
    
}
