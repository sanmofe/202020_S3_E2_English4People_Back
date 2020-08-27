/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.english4people.test.persistence;

import co.edu.uniandes.csw.english4people.entities.ContratoEntity;
import co.edu.uniandes.csw.english4people.persistence.ContratoPersistence;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import junit.framework.Assert;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author Sara Plazas
 */
@RunWith(Arquillian.class)
public class ContratoPersistenceTest {
    
    @Deployment
    public static JavaArchive createDeployment(){
        return ShrinkWrap.create(JavaArchive.class).addPackage(ContratoEntity.class.getPackage()).addPackage(ContratoPersistence.class.getPackage()).addAsManifestResource("META-INF/persistence.xml", "persistence.xml").addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    @Inject
    ContratoPersistence cp;
    
    @PersistenceContext
    private EntityManager em;
    
    @Test
    public void createTest(){
        PodamFactory factory = new PodamFactoryImpl();
        ContratoEntity contrato = factory.manufacturePojo(ContratoEntity.class);
        ContratoEntity result = cp.create(contrato);
        Assert.assertNotNull(result);
        
        ContratoEntity entity = em.find(ContratoEntity.class, result.getId());
        Assert.assertEquals(contrato.getClass(), entity.getClass());
    }
    
}
