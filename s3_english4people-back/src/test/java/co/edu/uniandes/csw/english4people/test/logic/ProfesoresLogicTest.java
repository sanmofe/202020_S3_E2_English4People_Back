/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.english4people.test.logic;

import co.edu.uniandes.csw.english4people.ejb.ProfesoresLogic;
import co.edu.uniandes.csw.english4people.entities.ActividadEntity;
import co.edu.uniandes.csw.english4people.entities.CalificacionEntity;
import co.edu.uniandes.csw.english4people.entities.CertificadoEntity;
import co.edu.uniandes.csw.english4people.entities.ChatEntity;
import co.edu.uniandes.csw.english4people.entities.ClaseEntity;
import co.edu.uniandes.csw.english4people.entities.ContratoEntity;
import co.edu.uniandes.csw.english4people.entities.HorarioEntity;
import co.edu.uniandes.csw.english4people.entities.ProfesoresEntity;
import co.edu.uniandes.csw.english4people.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.english4people.persistence.ProfesoresPersistence;
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
public class ProfesoresLogicTest {
    
    private PodamFactory factory = new PodamFactoryImpl();
    
    @Inject
    private ProfesoresLogic profesorLogic;
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    private UserTransaction utx;
    
    private List<ProfesoresEntity> data = new ArrayList<ProfesoresEntity>();
    
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ProfesoresEntity.class.getPackage())
                .addPackage(ProfesoresLogic.class.getPackage())
                .addPackage(ProfesoresPersistence.class.getPackage())
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

    private void clearData() {
        em.createQuery("delete from ProfesoresEntity").executeUpdate();
        em.createQuery("delete from CertificadoEntity").executeUpdate();
        em.createQuery("delete from CalificacionEntity").executeUpdate();
        em.createQuery("delete from ChatEntity").executeUpdate();
        em.createQuery("delete from ClaseEntity").executeUpdate();
        em.createQuery("delete from ActividadEntity").executeUpdate();
        em.createQuery("delete from HorarioEntity").executeUpdate();
        em.createQuery("delete from ContratoEntity").executeUpdate();
    }

    private void insertData() {
        for (int i = 0; i < 3; i++) {
            ProfesoresEntity profesor = factory.manufacturePojo(ProfesoresEntity.class);
            em.persist(profesor);
            data.add(profesor);
        }
    }
    
    @Test
    public void createProfesorTest() throws BusinessLogicException{
        ProfesoresEntity newEntity = factory.manufacturePojo(ProfesoresEntity.class);
        ProfesoresEntity result = profesorLogic.createProfesor(newEntity);
        Assert.assertNotNull(result);
        
        ProfesoresEntity entity = em.find(ProfesoresEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(),entity.getId());
        Assert.assertEquals(newEntity.getNombre(), entity.getNombre());
        Assert.assertEquals(newEntity.getIdentificacion(), entity.getIdentificacion());
        Assert.assertEquals(newEntity.getLogin(), entity.getLogin());
        Assert.assertEquals(newEntity.getCorreo(), entity.getCorreo());
        Assert.assertEquals(newEntity.getContrasena(), entity.getContrasena());
        Assert.assertEquals(newEntity.getInformacionAcademica(), entity.getInformacionAcademica());
        
    }
    
    @Test(expected = BusinessLogicException.class)
    public void createProfesorConNombreInvalido() throws BusinessLogicException{
        ProfesoresEntity newEntity = factory.manufacturePojo(ProfesoresEntity.class);
        newEntity.setNombre("");
        ProfesoresEntity result = profesorLogic.createProfesor(newEntity);
    }
    
    @Test(expected = BusinessLogicException.class)
    public void createProfesorConNombreInvalido2() throws BusinessLogicException{
        ProfesoresEntity newEntity = factory.manufacturePojo(ProfesoresEntity.class);
        newEntity.setNombre(null);
        ProfesoresEntity result = profesorLogic.createProfesor(newEntity);
    }
    
    
    @Test
    public void createProfesorConNombreValido() throws BusinessLogicException{
        ProfesoresEntity newEntity = factory.manufacturePojo(ProfesoresEntity.class);
        newEntity.setNombre(data.get(0).getNombre());
        ProfesoresEntity result = profesorLogic.createProfesor(newEntity);
    }
    
    
    
    @Test (expected = BusinessLogicException.class)
    public void createProfesorConIdentificacionInvalida() throws BusinessLogicException{
        ProfesoresEntity newEntity = factory.manufacturePojo(ProfesoresEntity.class);
        newEntity.setIdentificacion(0);
        ProfesoresEntity result = profesorLogic.createProfesor(newEntity);
    }
       
    @Test
    public void createProfesorConIdentificacionValida() throws BusinessLogicException{
        ProfesoresEntity newEntity = factory.manufacturePojo(ProfesoresEntity.class);
        newEntity.setIdentificacion(data.get(0).getIdentificacion());
        ProfesoresEntity result = profesorLogic.createProfesor(newEntity);
    }
    
    @Test (expected = BusinessLogicException.class)
    public void createProfesorConLoginInvalido() throws BusinessLogicException{
        ProfesoresEntity newEntity = factory.manufacturePojo(ProfesoresEntity.class);
        newEntity.setLogin("");
        ProfesoresEntity result = profesorLogic.createProfesor(newEntity);
    }
    
    @Test (expected = BusinessLogicException.class)
    public void createProfesorConLoginInvalido2() throws BusinessLogicException{
        ProfesoresEntity newEntity = factory.manufacturePojo(ProfesoresEntity.class);
        newEntity.setLogin(null);
        ProfesoresEntity result = profesorLogic.createProfesor(newEntity);
    }
    
    @Test
    public void createProfesorConLoginValido() throws BusinessLogicException{
        ProfesoresEntity newEntity = factory.manufacturePojo(ProfesoresEntity.class);
        newEntity.setLogin(data.get(0).getLogin());
        ProfesoresEntity result = profesorLogic.createProfesor(newEntity);
    }
    
    @Test(expected = BusinessLogicException.class)
    public void createProfesorConCorreoInvalido() throws BusinessLogicException{
        ProfesoresEntity newEntity = factory.manufacturePojo(ProfesoresEntity.class);
        newEntity.setCorreo("");
        ProfesoresEntity result = profesorLogic.createProfesor(newEntity);
    }
    
    @Test(expected = BusinessLogicException.class)
    public void createProfesorConCorreoInvalido2() throws BusinessLogicException{
        ProfesoresEntity newEntity = factory.manufacturePojo(ProfesoresEntity.class);
        newEntity.setCorreo(null);
        ProfesoresEntity result = profesorLogic.createProfesor(newEntity);
    }
    
    @Test
    public void createProfesorConCorreoValido() throws BusinessLogicException{
        ProfesoresEntity newEntity = factory.manufacturePojo(ProfesoresEntity.class);
        newEntity.setCorreo(data.get(0).getCorreo());
        ProfesoresEntity result = profesorLogic.createProfesor(newEntity);
    }
    
    
    @Test(expected = BusinessLogicException.class)
    public void createProfesorConContrasenaInvalida() throws BusinessLogicException{
        ProfesoresEntity newEntity = factory.manufacturePojo(ProfesoresEntity.class);
        newEntity.setContrasena("");
        ProfesoresEntity result = profesorLogic.createProfesor(newEntity);
    }
    
    @Test(expected = BusinessLogicException.class)
    public void createProfesorConContrasenaInvalido2() throws BusinessLogicException{
        ProfesoresEntity newEntity = factory.manufacturePojo(ProfesoresEntity.class);
        newEntity.setContrasena(null);
        ProfesoresEntity result = profesorLogic.createProfesor(newEntity);
    }
    
    @Test
    public void createProfesorConContrasenaValido() throws BusinessLogicException{
        ProfesoresEntity newEntity = factory.manufacturePojo(ProfesoresEntity.class);
        newEntity.setContrasena(data.get(0).getContrasena());
        ProfesoresEntity result = profesorLogic.createProfesor(newEntity);
    }
    
    @Test(expected = BusinessLogicException.class)
    public void createProfesorConInformacionAcademicaInvalida() throws BusinessLogicException{
        ProfesoresEntity newEntity = factory.manufacturePojo(ProfesoresEntity.class);
        newEntity.setInformacionAcademica("");
        ProfesoresEntity result = profesorLogic.createProfesor(newEntity);
    }
    
    @Test(expected = BusinessLogicException.class)
    public void createProfesorConInformacionAcademicaInvalida2() throws BusinessLogicException{
        ProfesoresEntity newEntity = factory.manufacturePojo(ProfesoresEntity.class);
        newEntity.setInformacionAcademica(null);
        ProfesoresEntity result = profesorLogic.createProfesor(newEntity);
    }
    
    @Test
    public void createProfesorConInformacionAcademicaValido() throws BusinessLogicException{
        ProfesoresEntity newEntity = factory.manufacturePojo(ProfesoresEntity.class);
        newEntity.setInformacionAcademica(data.get(0).getInformacionAcademica());
        ProfesoresEntity result = profesorLogic.createProfesor(newEntity);
    }
    
    
    @Test
    public void getProfesoresTest(){
        List<ProfesoresEntity> list = profesorLogic.getProfesores();
        Assert.assertEquals(data.size(),list.size());
        for (ProfesoresEntity entity: list){
            boolean found = false;
            for (ProfesoresEntity storedEntity: data){
                if(entity.getId().equals(storedEntity.getId())){
                    found = true;
                }   
            }
            Assert.assertTrue(found);
        }
    }
    
    @Test
    public void getProfesorTest(){
        ProfesoresEntity entity = data.get(0);
        ProfesoresEntity resultEntity = profesorLogic.getProfesor(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getNombre(), resultEntity.getNombre());
        Assert.assertEquals(entity.getIdentificacion(), resultEntity.getIdentificacion());
        Assert.assertEquals(entity.getLogin(), resultEntity.getLogin());
        Assert.assertEquals(entity.getCorreo(), resultEntity.getCorreo());
        Assert.assertEquals(entity.getContrasena(), resultEntity.getContrasena());
        Assert.assertEquals(entity.getInformacionAcademica(), resultEntity.getInformacionAcademica());
        Assert.assertEquals(entity.getCanalYoutube(), resultEntity.getCanalYoutube());
    }
    
    
    @Test
    public void updateProfesorTest() throws BusinessLogicException{
        ProfesoresEntity entity = data.get(0);
        ProfesoresEntity pojoEntity = factory.manufacturePojo(ProfesoresEntity.class);
        pojoEntity.setId(entity.getId());
        profesorLogic.updateProfesor(pojoEntity.getId(), pojoEntity);
        ProfesoresEntity resp = em.find(ProfesoresEntity.class, entity.getId());
        
        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getNombre(), resp.getNombre());
        Assert.assertEquals(pojoEntity.getIdentificacion(), resp.getIdentificacion());
        Assert.assertEquals(pojoEntity.getLogin(), resp.getLogin());
        Assert.assertEquals(pojoEntity.getCorreo(), resp.getCorreo());
        Assert.assertEquals(pojoEntity.getContrasena(), resp.getContrasena());
        Assert.assertEquals(pojoEntity.getInformacionAcademica(), resp.getInformacionAcademica());
        Assert.assertEquals(pojoEntity.getCanalYoutube(), resp.getCanalYoutube());
    }
    
    @Test(expected = BusinessLogicException.class)
    public void upDateProfesorConNombreInvalido() throws BusinessLogicException{
        ProfesoresEntity entity = data.get(0);
        ProfesoresEntity pojoEntity = factory.manufacturePojo(ProfesoresEntity.class);
        pojoEntity.setNombre("");
        pojoEntity.setId(entity.getId());
        profesorLogic.updateProfesor(pojoEntity.getId(), pojoEntity);   
    }
    
    @Test(expected = BusinessLogicException.class)
    public void updateProfesorConNombreInvalido2() throws BusinessLogicException{
        ProfesoresEntity entity = data.get(0);
        ProfesoresEntity pojoEntity = factory.manufacturePojo(ProfesoresEntity.class);
        pojoEntity.setNombre(null);
        pojoEntity.setId(entity.getId());
        profesorLogic.updateProfesor(pojoEntity.getId(), pojoEntity); 
    }
    
    /**
     * @throws co.edu.uniandes.csw.english4people.exceptions.BusinessLogicException
     * 
     */
    @Test (expected = BusinessLogicException.class)
    public void upDateProfesorConIdentificacionInvalida() throws BusinessLogicException{
        ProfesoresEntity entity = data.get(0);
        ProfesoresEntity pojoEntity = factory.manufacturePojo(ProfesoresEntity.class);
        pojoEntity.setIdentificacion(0);
        pojoEntity.setId(entity.getId());
        profesorLogic.updateProfesor(pojoEntity.getId(), pojoEntity);   
    }
    
    
    @Test (expected = BusinessLogicException.class)
    public void upDateProfesorConLoginInvalido() throws BusinessLogicException{
        ProfesoresEntity entity = data.get(0);
        ProfesoresEntity pojoEntity = factory.manufacturePojo(ProfesoresEntity.class);
        pojoEntity.setLogin("");
        pojoEntity.setId(entity.getId());
        profesorLogic.updateProfesor(pojoEntity.getId(), pojoEntity);   
    }
    
    @Test (expected = BusinessLogicException.class)
    public void updateProfesorConLoginInvalido2() throws BusinessLogicException{
        ProfesoresEntity entity = data.get(0);
        ProfesoresEntity pojoEntity = factory.manufacturePojo(ProfesoresEntity.class);
        pojoEntity.setLogin(null);
        pojoEntity.setId(entity.getId());
        profesorLogic.updateProfesor(pojoEntity.getId(), pojoEntity); 
    }
    
    @Test (expected = BusinessLogicException.class)
    public void upDateProfesorConCorreoInvalido() throws BusinessLogicException{
        ProfesoresEntity entity = data.get(0);
        ProfesoresEntity pojoEntity = factory.manufacturePojo(ProfesoresEntity.class);
        pojoEntity.setCorreo("");
        pojoEntity.setId(entity.getId());
        profesorLogic.updateProfesor(pojoEntity.getId(), pojoEntity);   
    }
    
    @Test (expected = BusinessLogicException.class)
    public void updateProfesorConCorreoInvalido2() throws BusinessLogicException{
        ProfesoresEntity entity = data.get(0);
        ProfesoresEntity pojoEntity = factory.manufacturePojo(ProfesoresEntity.class);
        pojoEntity.setCorreo(null);
        pojoEntity.setId(entity.getId());
        profesorLogic.updateProfesor(pojoEntity.getId(), pojoEntity); 
    }
    
    @Test (expected = BusinessLogicException.class)
    public void upDateProfesorConContrasenaInvalida() throws BusinessLogicException{
        ProfesoresEntity entity = data.get(0);
        ProfesoresEntity pojoEntity = factory.manufacturePojo(ProfesoresEntity.class);
        pojoEntity.setContrasena("");
        pojoEntity.setId(entity.getId());
        profesorLogic.updateProfesor(pojoEntity.getId(), pojoEntity);   
    }
    
    @Test (expected = BusinessLogicException.class)
    public void updateProfesorConContrasenaInvalida2() throws BusinessLogicException{
        ProfesoresEntity entity = data.get(0);
        ProfesoresEntity pojoEntity = factory.manufacturePojo(ProfesoresEntity.class);
        pojoEntity.setContrasena(null);
        pojoEntity.setId(entity.getId());
        profesorLogic.updateProfesor(pojoEntity.getId(), pojoEntity); 
    }
    
    @Test (expected = BusinessLogicException.class)
    public void upDateProfesorConInformacionAcademicaInvalida() throws BusinessLogicException{
        ProfesoresEntity entity = data.get(0);
        ProfesoresEntity pojoEntity = factory.manufacturePojo(ProfesoresEntity.class);
        pojoEntity.setInformacionAcademica("");
        pojoEntity.setId(entity.getId());
        profesorLogic.updateProfesor(pojoEntity.getId(), pojoEntity);   
    }
    
    @Test (expected = BusinessLogicException.class)
    public void updateProfesorConInformacionAcademicaInvalida2() throws BusinessLogicException{
        ProfesoresEntity entity = data.get(0);
        ProfesoresEntity pojoEntity = factory.manufacturePojo(ProfesoresEntity.class);
        pojoEntity.setInformacionAcademica(null);
        pojoEntity.setId(entity.getId());
        profesorLogic.updateProfesor(pojoEntity.getId(), pojoEntity); 
    }
    
    @Test
    public void deleteProfesorTest() throws BusinessLogicException{
        ProfesoresEntity entity = data.get(0);
        profesorLogic.deleteProfesor(entity.getId());
        ProfesoresEntity deleted = em.find(ProfesoresEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    
    @Test
    public void deleteProfesorWithCertificadoTest()throws BusinessLogicException{
        ProfesoresEntity entity = data.get(1);
        profesorLogic.deleteProfesor(entity.getId());
    }
    
    
    @Test
    public void deleteProfesorWithCalificacionTest()throws BusinessLogicException{
        ProfesoresEntity entity = data.get(1);
        profesorLogic.deleteProfesor(entity.getId());
    }
    
    
    @Test
    public void deleteProfesorWithChatTest()throws BusinessLogicException{
        ProfesoresEntity entity = data.get(1);
        profesorLogic.deleteProfesor(entity.getId());
    }
    
    @Test
    public void deleteProfesorWithClaseTest()throws BusinessLogicException{
        ProfesoresEntity entity = data.get(1);
        profesorLogic.deleteProfesor(entity.getId());
    }
    
    @Test
    public void deleteProfesorWithActividadTest()throws BusinessLogicException{
        ProfesoresEntity entity = data.get(1);
        profesorLogic.deleteProfesor(entity.getId());
    }
    
    
    @Test
    public void deleteProfesorWithHorarioTest()throws BusinessLogicException{
        ProfesoresEntity entity = data.get(1);
        profesorLogic.deleteProfesor(entity.getId());
    }
    
    
    @Test
    public void deleteProfesorWithContratoTest()throws BusinessLogicException{
        ProfesoresEntity entity = data.get(1);
        profesorLogic.deleteProfesor(entity.getId());
    }
    
}
