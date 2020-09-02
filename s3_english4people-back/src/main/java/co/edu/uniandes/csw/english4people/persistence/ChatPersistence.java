/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.english4people.persistence;

import co.edu.uniandes.csw.english4people.entities.ChatEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Juan David Becerra Romero
 */
@Stateless
public class ChatPersistence {
    
    @PersistenceContext(unitName = "english4peoplePU")
    protected EntityManager em;
    
    public ChatEntity create(ChatEntity chat) {
        em.persist(chat);
        return chat;
    }
    
    public List<ChatEntity> findAll()
    {
        TypedQuery query = em.createQuery("select u from ChatEntity u", ChatEntity.class);
        return query.getResultList();
    }
    
    public ChatEntity find(Long chatId)
    {
        return em.find(ChatEntity.class, chatId);
    }
    
    public ChatEntity update(ChatEntity chatEntity)
    {
        return em.merge(chatEntity);
    }
    
    public void delete(Long chatId)
    {
        ChatEntity entity = em.find(ChatEntity.class, chatId);
        em.remove(entity);
    }
    
}
