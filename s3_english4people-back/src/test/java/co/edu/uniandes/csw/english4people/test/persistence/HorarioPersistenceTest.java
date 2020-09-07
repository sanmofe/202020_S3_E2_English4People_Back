/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.english4people.test.persistence;

import co.edu.uniandes.csw.english4people.entities.HorarioEntity;
import co.edu.uniandes.csw.english4people.persistence.HorarioPersistence;
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
 * @author Sara Plazas
 */
//RunWith Arquillian porque ejecuta un servidor de aplicaciones y una base de datos temporal
@RunWith(Arquillian.class)
public class HorarioPersistenceTest {

    /**
     * Deployment para que Arquillian sepa que es Contiene lo que se quiere
     * probar
     *
     * @return El archivo .jar que se deplegara en el servidor de aplicaciones
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class).addPackage(HorarioEntity.class.getPackage()).addPackage(HorarioPersistence.class.getPackage()).addAsManifestResource("META-INF/persistence.xml", "persistence.xml").addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    //"Inicializa" e inyecta lo que se quiere probar (clase persistance)
    //New clase de persistencia
    @Inject
    HorarioPersistence hp;

    //PersistenceContext -> conjunto de entidades tal que para cada persistencia hay una entidad unica. Las entidades son manegadas en el contexto de persistencia.
    @PersistenceContext
    //El elemento principal de JPA para acceder a la base de datos
    private EntityManager em;

    //"Inicializa" e inyecta UserTransaccion que es una interface que permite a la aplicacion manejar transacciones
    @Inject
    UserTransaction utx;

    //Lista de datos
    private List<HorarioEntity> data = new ArrayList<HorarioEntity>();

    /**
     * Configuración inicial de la prueba. Prepara los atributos para correr las
     * pruebas desde cero
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
     * Limpia las tablas que están implicadas en la prueba (limpia la
     * informacion de pruebas anteriores)
     */
    private void clearData() {
        em.createQuery("delete from HorarioEntity").executeUpdate();
    }

    /**
     * Inserta los informacion nueva iniciales para el correcto funcionamiento
     * de las pruebas (correr de 0).
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            HorarioEntity entity = factory.manufacturePojo(HorarioEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }

    @Test
    public void createTest() {
        PodamFactory factory = new PodamFactoryImpl();
        HorarioEntity horario = factory.manufacturePojo(HorarioEntity.class);
        HorarioEntity result = hp.create(horario);
        Assert.assertNotNull(result);

        HorarioEntity entity = em.find(HorarioEntity.class, result.getId());
        Assert.assertEquals(horario.getHoraInicio(), entity.getHoraInicio());
        Assert.assertEquals(horario.getHoraFin(), entity.getHoraFin());
        Assert.assertNull(horario.getDiaSemana());
    }

    @Test
    public void getContratosTest() {
        List<HorarioEntity> list = hp.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (HorarioEntity ent : list) {
            boolean found = false;
            for (HorarioEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    @Test
    public void getContratoTest() {
        HorarioEntity entity = data.get(0);
        HorarioEntity newEntity = hp.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getHoraInicio(), newEntity.getHoraInicio());
        Assert.assertEquals(entity.getHoraFin(), newEntity.getHoraFin());
    }

    @Test
    public void updateTest() {
        HorarioEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        HorarioEntity newEntity = factory.manufacturePojo(HorarioEntity.class);

        newEntity.setId(entity.getId());

        hp.update(newEntity);

        HorarioEntity resp = em.find(HorarioEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getHoraInicio(), resp.getHoraInicio());
        Assert.assertEquals(newEntity.getHoraFin(), resp.getHoraFin());
    }

    @Test
    public void deleteTest() {
        HorarioEntity entity = data.get(0);
        hp.delete(entity.getId());
        HorarioEntity deleted = em.find(HorarioEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

}
