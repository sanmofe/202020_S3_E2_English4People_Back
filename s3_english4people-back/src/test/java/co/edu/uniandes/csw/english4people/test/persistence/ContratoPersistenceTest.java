/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.english4people.test.persistence;

import co.edu.uniandes.csw.english4people.entities.ContratoEntity;
import co.edu.uniandes.csw.english4people.persistence.ContratoPersistence;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.junit.Assert;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author Sara Plazas
 */

//RunWith Arquillian porque ejecuta un servidor de aplicaciones y una base de datos temporal
@RunWith(Arquillian.class)
public class ContratoPersistenceTest {
    
    /**
     * Deployment para que Arquillian sepa que es
     * Contiene lo que se quiere probar
     * @return El archivo .jar que se deplegara en el servidor de aplicaciones
     */
    @Deployment
    public static JavaArchive createDeployment(){
        return ShrinkWrap.create(JavaArchive.class).addPackage(ContratoEntity.class.getPackage()).addPackage(ContratoPersistence.class.getPackage()).addAsManifestResource("META-INF/persistence.xml", "persistence.xml").addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    //"Inicializa" e inyecta lo que se quiere probar (clase persistance)
    //New clase
    @Inject
    ContratoPersistence cp;
    
    //El elemento principal de JPA para acceder a la base de datos
    @PersistenceContext
    private EntityManager em;
    
    //"Inicializa" e inyecta UserTransaccion que es una interface que permite a la aplicacion manejar transacciones
    @Inject
    UserTransaction utx;
    
    private List<ContratoEntity> data = new ArrayList<ContratoEntity>();
    
     /**
     * Configuración inicial de la prueba.
     * Prepara los atributos para correr las pruebas desde cero
     */
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

    /**
     * Limpia las tablas que están implicadas en la prueba (limpia la informacion de pruebas anteriores)
     */
    private void clearData() {
        em.createQuery("delete from ContratoEntity").executeUpdate();
    }

    /**
     * Inserta los informacion nueva iniciales para el correcto funcionamiento de las pruebas (correr de 0).
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            ContratoEntity entity = factory.manufacturePojo(ContratoEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }
    
    @Test
    public void createTest(){
        PodamFactory factory = new PodamFactoryImpl();
        ContratoEntity contrato = factory.manufacturePojo(ContratoEntity.class);
        ContratoEntity result = cp.create(contrato);
        Assert.assertNotNull(result);
        
        ContratoEntity entity = em.find(ContratoEntity.class, result.getId());
        Assert.assertEquals(contrato.getNumeroHoras(), entity.getNumeroHoras());
        Assert.assertEquals(contrato.getCosto(), entity.getCosto());
        Assert.assertEquals(contrato.getMedioPago(), entity.getMedioPago());
    }
    
     @Test
    public void getContratosTest() {
        List<ContratoEntity> list = cp.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (ContratoEntity ent : list) {
            boolean found = false;
            for (ContratoEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    
    @Test
    public void getContratoTest() {
        ContratoEntity entity = data.get(0);
        ContratoEntity newEntity = cp.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getCosto(), newEntity.getCosto());
        Assert.assertEquals(entity.getMedioPago(), newEntity.getMedioPago());
        Assert.assertEquals(entity.getNumeroHoras(), newEntity.getNumeroHoras());
    }
    
    @Test
    public void updateTest() {
        ContratoEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        ContratoEntity newEntity = factory.manufacturePojo(ContratoEntity.class);

        newEntity.setId(entity.getId());

        cp.update(newEntity);

        ContratoEntity resp = em.find(ContratoEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getCosto(), resp.getCosto());
        Assert.assertEquals(newEntity.getMedioPago(), resp.getMedioPago());
        Assert.assertEquals(newEntity.getNumeroHoras(), resp.getNumeroHoras());
    }
    
    @Test
    public void deleteTest() {
        ContratoEntity entity = data.get(0);
        cp.delete(entity.getId());
        ContratoEntity deleted = em.find(ContratoEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
}
