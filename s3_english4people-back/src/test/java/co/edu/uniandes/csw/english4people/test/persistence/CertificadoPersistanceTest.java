/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.english4people.test.persistence;


import co.edu.uniandes.csw.english4people.entities.CertificadoEntity;
import co.edu.uniandes.csw.english4people.persistence.CertificadoPersistence;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author Juan Andres Santiago
 */
@RunWith(Arquillian.class)
public class CertificadoPersistanceTest {
    @Deployment
    public static JavaArchive createDeployment(){
    return ShrinkWrap.create(JavaArchive.class).addPackage(CertificadoEntity.class.getPackage()).addPackage(CertificadoPersistence.class.getPackage()).addAsManifestResource("META-INF/persistence.xml","persistence.xml").addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    @Inject
    UserTransaction utx;

    private List<CertificadoEntity> data = new ArrayList<CertificadoEntity>();
    
    @Inject
    CertificadoPersistence cp;
    
    @PersistenceContext
    private EntityManager em;
    
    
     @Before
    public void configTest() {
        try {
            utx.begin();
            em.joinTransaction();
            clearData();
            insertData();
            utx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                utx.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }
    
    private void clearData() {
        em.createQuery("delete from CertificadoEntity").executeUpdate();
    }

    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {

            CertificadoEntity entity = factory.manufacturePojo(CertificadoEntity.class);

            em.persist(entity);

            data.add(entity);
        }
    }    
    
    @Test
    public void createTest()
    {
        PodamFactory factory = new PodamFactoryImpl();
        CertificadoEntity certificado = factory.manufacturePojo(CertificadoEntity.class);
        CertificadoEntity result = cp.create(certificado);
        
        Assert.assertNotNull(result);
        
        CertificadoEntity entity = em.find(CertificadoEntity.class, result.getId());
        
        Assert.assertEquals(certificado.getNombre(), entity.getNombre());
        Assert.assertEquals(certificado.getIdioma(), entity.getIdioma());
    }

    @Test
    public void getCertificadosTest() {
        List<CertificadoEntity> list = cp.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (CertificadoEntity ent : list) {
            boolean found = false;
            for (CertificadoEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    
    @Test
    public void getCertificadoTest() {
        CertificadoEntity entity = data.get(0);
        CertificadoEntity newEntity = cp.find(entity.getId());
        Assert.assertNotNull(newEntity);
        
        Assert.assertEquals(entity.getNombre(), newEntity.getNombre());
        Assert.assertEquals(entity.getIdioma(), newEntity.getIdioma());
    }
    
    @Test
    public void updateCerticadoTest() {
        CertificadoEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        CertificadoEntity newEntity = factory.manufacturePojo(CertificadoEntity.class);

        newEntity.setId(entity.getId());

        cp.update(newEntity);
        
        CertificadoEntity resp = em.find(CertificadoEntity.class, entity.getId());
        
        Assert.assertEquals(newEntity.getNombre(), resp.getNombre());
        Assert.assertEquals(newEntity.getIdioma(), resp.getIdioma());
    }
    
    @Test
    public void deleteProfesoresTest() {
        CertificadoEntity entity = data.get(0);
        cp.delete(entity.getId());
        CertificadoEntity deleted = em.find(CertificadoEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
}
