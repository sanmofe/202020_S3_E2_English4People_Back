/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.english4people.test.logic;

import co.edu.uniandes.csw.english4people.ejb.CertificadoLogic;
import co.edu.uniandes.csw.english4people.entities.CertificadoEntity;
import co.edu.uniandes.csw.english4people.entities.ProfesoresEntity;
import co.edu.uniandes.csw.english4people.exceptions.BusinessLogicException;
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
public class CertificadoLogicTest {
     private PodamFactory factory = new PodamFactoryImpl();
    
    @Inject
    private CertificadoLogic certificadoLogic;
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    private UserTransaction utx;
    
    private List<CertificadoEntity> data = new ArrayList<CertificadoEntity>();
    
    private List<ProfesoresEntity> dataProfesores = new ArrayList<ProfesoresEntity>();
    
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(CertificadoEntity.class.getPackage())
                .addPackage(CertificadoLogic.class.getPackage())
                .addPackage(CertificadoPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    @Before
    public void configTest() {
        try {
            utx.begin();
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
    
    private void clearData(){
        em.createQuery("delete from CertificadoEntity").executeUpdate();
    }
    
    private void insertData() {
        for (int i = 0; i < 3; i++) {
            ProfesoresEntity entity = factory.manufacturePojo(ProfesoresEntity.class);
            em.persist(entity);
            dataProfesores.add(entity);
        }
        
        for (int i = 0; i < 3; i++) {
            CertificadoEntity certificado = factory.manufacturePojo(CertificadoEntity.class);
            certificado.setProfesor(dataProfesores.get(1));
            em.persist(certificado);
            data.add(certificado);
        } 
    }
    
    @Test
    public void createCertificadoTest() throws BusinessLogicException{
        CertificadoEntity newEntity = factory.manufacturePojo(CertificadoEntity.class);
        newEntity.setProfesor(dataProfesores.get(1));
        CertificadoEntity result = certificadoLogic.createCertificado(dataProfesores.get(1).getId(),newEntity);
        Assert.assertNotNull(result);
        
        CertificadoEntity entity = em.find(CertificadoEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(),entity.getId());
        Assert.assertEquals(newEntity.getNombre(), entity.getNombre());
        Assert.assertEquals(newEntity.getIdioma(), entity.getIdioma());
    }
    
    @Test(expected = BusinessLogicException.class)
    public void createCertificadoConNombreInvalido() throws BusinessLogicException{
        CertificadoEntity newEntity = factory.manufacturePojo(CertificadoEntity.class);
        newEntity.setProfesor(dataProfesores.get(1));
        newEntity.setNombre("");
        CertificadoEntity result = certificadoLogic.createCertificado(dataProfesores.get(1).getId(),newEntity);
    }
    
    @Test(expected = BusinessLogicException.class)
    public void createCertificadoConNombreInvalido2() throws BusinessLogicException{
        CertificadoEntity newEntity = factory.manufacturePojo(CertificadoEntity.class);
        newEntity.setProfesor(dataProfesores.get(1));
        newEntity.setNombre(null);
        CertificadoEntity result = certificadoLogic.createCertificado(dataProfesores.get(1).getId(),newEntity);
    }
    
    
    @Test
    public void createCertificadoConNombreValido() throws BusinessLogicException{
        CertificadoEntity newEntity = factory.manufacturePojo(CertificadoEntity.class);
        newEntity.setProfesor(dataProfesores.get(1));
        newEntity.setNombre(data.get(0).getNombre());
        CertificadoEntity result = certificadoLogic.createCertificado(dataProfesores.get(1).getId(),newEntity);
    }
    
    @Test(expected = BusinessLogicException.class)
    public void createCertificadoConIidomaInvalido() throws BusinessLogicException{
        CertificadoEntity newEntity = factory.manufacturePojo(CertificadoEntity.class);
        newEntity.setProfesor(dataProfesores.get(1));
        newEntity.setIdioma("");
        CertificadoEntity result = certificadoLogic.createCertificado(dataProfesores.get(1).getId(),newEntity);
    }
    
    @Test(expected = BusinessLogicException.class)
    public void createCertificadoConIdiomaInvalido2() throws BusinessLogicException{
        CertificadoEntity newEntity = factory.manufacturePojo(CertificadoEntity.class);
        newEntity.setProfesor(dataProfesores.get(1));
        newEntity.setIdioma(null);
        CertificadoEntity result = certificadoLogic.createCertificado(dataProfesores.get(1).getId(),newEntity);
    }
    
    
    @Test
    public void createIdiomaConNombreValido() throws BusinessLogicException{
        CertificadoEntity newEntity = factory.manufacturePojo(CertificadoEntity.class);
        newEntity.setProfesor(dataProfesores.get(1));
        newEntity.setIdioma(data.get(0).getIdioma());
        CertificadoEntity result = certificadoLogic.createCertificado(dataProfesores.get(1).getId(),newEntity);
    }
    
    @Test
    public void getCertificadosTest(){
        List<CertificadoEntity> list = certificadoLogic.getCertificados(dataProfesores.get(1).getId());
        Assert.assertEquals(data.size(),list.size());
        for (CertificadoEntity entity: list){
            boolean found = false;
            for (CertificadoEntity storedEntity: data){
                if(entity.getId().equals(storedEntity.getId())){
                    found = true;
                }   
            }
            Assert.assertTrue(found);
        }
    }
    
    @Test
    public void getCertificadoTest(){
        CertificadoEntity entity = data.get(0);
        CertificadoEntity resultEntity = certificadoLogic.getCertificado(dataProfesores.get(1).getId(),entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getNombre(), resultEntity.getNombre());
        Assert.assertEquals(entity.getIdioma(), resultEntity.getIdioma());
    }
    
    @Test
    public void updateCertificadoTest() throws BusinessLogicException{
        CertificadoEntity entity = data.get(0);
        CertificadoEntity pojoEntity = factory.manufacturePojo(CertificadoEntity.class);
        pojoEntity.setId(entity.getId());
        certificadoLogic.updateCertificado(dataProfesores.get(1).getId(), pojoEntity);
        CertificadoEntity resp = em.find(CertificadoEntity.class, entity.getId());
        
        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getNombre(), resp.getNombre());
        Assert.assertEquals(pojoEntity.getIdioma(), resp.getIdioma());
    }
    
    @Test(expected = BusinessLogicException.class)
    public void upDateCertificadoConNombreInvalido() throws BusinessLogicException{
        CertificadoEntity entity = data.get(0);
        CertificadoEntity pojoEntity = factory.manufacturePojo(CertificadoEntity.class);
        pojoEntity.setNombre("");
        pojoEntity.setId(entity.getId());
        certificadoLogic.updateCertificado(dataProfesores.get(1).getId(), pojoEntity);   
    }
    
    @Test(expected = BusinessLogicException.class)
    public void updateCertificadoConNombreInvalido2() throws BusinessLogicException{
        CertificadoEntity entity = data.get(0);
        CertificadoEntity pojoEntity = factory.manufacturePojo(CertificadoEntity.class);
        pojoEntity.setNombre(null);
        pojoEntity.setId(entity.getId());
        certificadoLogic.updateCertificado(dataProfesores.get(1).getId(), pojoEntity); 
    }
    
    @Test(expected = BusinessLogicException.class)
    public void upDateCertificadoConIdiomaInvalido() throws BusinessLogicException{
        CertificadoEntity entity = data.get(0);
        CertificadoEntity pojoEntity = factory.manufacturePojo(CertificadoEntity.class);
        pojoEntity.setIdioma("");
        pojoEntity.setId(entity.getId());
        certificadoLogic.updateCertificado(dataProfesores.get(1).getId(), pojoEntity);   
    }
    
    @Test(expected = BusinessLogicException.class)
    public void updateCertificadoConIdiomaInvalido2() throws BusinessLogicException{
        CertificadoEntity entity = data.get(0);
        CertificadoEntity pojoEntity = factory.manufacturePojo(CertificadoEntity.class);
        pojoEntity.setIdioma(null);
        pojoEntity.setId(entity.getId());
        certificadoLogic.updateCertificado(dataProfesores.get(1).getId(), pojoEntity); 
    }
    
    @Test
    public void deleteCertificadoTest() throws BusinessLogicException{
        CertificadoEntity entity = data.get(0);
        certificadoLogic.deleteCertificado(dataProfesores.get(1).getId(),entity.getId());
        CertificadoEntity deleted = em.find(CertificadoEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    
    public void deleteCertificadoConProfesorNoAsociadoTest()throws BusinessLogicException{
        CertificadoEntity entity = data.get(0);
        certificadoLogic.deleteCertificado(dataProfesores.get(0).getId(),entity.getId());
    }
}
