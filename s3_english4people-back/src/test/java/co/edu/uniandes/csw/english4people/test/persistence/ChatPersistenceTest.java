/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.english4people.test.persistence;
import co.edu.uniandes.csw.english4people.entities.ChatEntity;
import co.edu.uniandes.csw.english4people.persistence.ChatPersistence;
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
 * @author Juan David Becerra Romero
 */
@RunWith(Arquillian.class)
public class ChatPersistenceTest {
    @Deployment
    public static JavaArchive createDeployment()
    {
        return ShrinkWrap.create(JavaArchive.class).addPackage(ChatEntity.class.getPackage()).addPackage(ChatPersistence.class.getPackage()).addAsManifestResource("META-INF/persistence.xml","persistence.xml").addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    @Inject
    ChatPersistence cp;
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    UserTransaction utx;

    private List<ChatEntity> data = new ArrayList<ChatEntity>();
    
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
        em.createQuery("delete from ChatEntity").executeUpdate();
    }

    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {

            ChatEntity entity = factory.manufacturePojo(ChatEntity.class);

            em.persist(entity);

            data.add(entity);
        }
    }
        
    @Test
    public void createTest()
    {
        PodamFactory factory = new PodamFactoryImpl();
        ChatEntity chat = factory.manufacturePojo(ChatEntity.class);
        ChatEntity result = cp.create(chat);
        
        Assert.assertNotNull(result);
        
        ChatEntity entity = em.find(ChatEntity.class, result.getId());
       
       
    }
    
    @Test
    public void getChatsTest() {
        List<ChatEntity> list = cp.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (ChatEntity ent : list) {
            boolean found = false;
            for (ChatEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    
    @Test
    public void getChatTest() {
        ChatEntity entity = data.get(0);
        ChatEntity newEntity = cp.find(entity.getId());
        Assert.assertNotNull(newEntity);
        
    }
    
    @Test
    public void updateChatTest() {
        ChatEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        ChatEntity newEntity = factory.manufacturePojo(ChatEntity.class);

        newEntity.setId(entity.getId());

        cp.update(newEntity);

        ChatEntity resp = em.find(ChatEntity.class, entity.getId());
        
        
    }
    
    @Test
    public void deleteChatTest() {
        ChatEntity entity = data.get(0);
        cp.delete(entity.getId());
        ChatEntity deleted = em.find(ChatEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    
}
