/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iot.dao.repository;

import iot.dao.entity.User;
import iot.dao.repository.UserDAO;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author hatanococoro
 */
public class UserDaoImpl implements UserDAO{
    
    @PersistenceContext(unitName = "orderPU")  
    private EntityManagerFactory emf;
    
    @Override
    public List<User> getAllUser(){

        emf = Persistence.createEntityManagerFactory("orderPU");
        EntityManager em = emf.createEntityManager();        
        Query query = em.createQuery("SELECT u FROM User u");
        List<User> users = query.getResultList();
        em.close();
        
        return users;
   }
    
    @Override
    public User getUser(String userId){

        emf = Persistence.createEntityManagerFactory("orderPU");
        EntityManager em = emf.createEntityManager();        
        Query query = em.createQuery("SELECT u FROM User u where u.userId=:userId");
        query.setParameter("userId", userId);
        User user =(User)query.getSingleResult();
        em.close();
        
        return user;
   }
    
    @Override
    public User getUserByname(String username){
        
        try {
            
        emf = Persistence.createEntityManagerFactory("orderPU");
        EntityManager em = emf.createEntityManager();        
        Query query = em.createQuery("SELECT u FROM User u where u.userName=:username");
        query.setParameter("username", username);
        User user =(User)query.getSingleResult();
        em.close();
        
        return user;
            
        } catch (Exception e) {
            
            return new  User();
            
        }
        
    }
    
    @Override
    public void addUser(User user){
    
        emf = Persistence.createEntityManagerFactory("orderPU");
        EntityManager em = emf.createEntityManager();        
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
        em.close();        
    }
    
    @Override
    public void editUser(User user){
    
        emf = Persistence.createEntityManagerFactory("orderPU");
        EntityManager em = emf.createEntityManager();        
        em.getTransaction().begin();
        User oldUser = em.find(User.class, user.getLoginId());
        oldUser.setPassword(user.getPassword());
        oldUser.setUserName(user.getUserName());
        em.persist(oldUser);
        em.getTransaction().commit();
        em.close();
        
    }
    
    @Override
    public void delUser(int loginId){
    
        emf = Persistence.createEntityManagerFactory("orderPU");
        EntityManager em = emf.createEntityManager();        
        em.getTransaction().begin();
        User oldUser = em.find(User.class, loginId);
        em.remove(oldUser);
        em.getTransaction().commit();
        
        em.close();
    }
   
    
}
