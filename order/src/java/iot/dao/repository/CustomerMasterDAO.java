/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iot.dao.repository;

import iot.dao.entity.CustomerMaster;
import iot.dao.repository.exceptions.NonexistentEntityException;
import iot.dao.repository.exceptions.PreexistingEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author hatanococoro
 */
public class CustomerMasterDAO implements Serializable {

    public CustomerMasterDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(CustomerMaster customerMaster) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(customerMaster);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCustomerMaster(customerMaster.getCustomerMasterId()) != null) {
                throw new PreexistingEntityException("CustomerMaster " + customerMaster + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CustomerMaster customerMaster) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            customerMaster = em.merge(customerMaster);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = customerMaster.getCustomerMasterId();
                if (findCustomerMaster(id) == null) {
                    throw new NonexistentEntityException("The customerMaster with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CustomerMaster customerMaster;
            try {
                customerMaster = em.getReference(CustomerMaster.class, id);
                customerMaster.getCustomerMasterId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The customerMaster with id " + id + " no longer exists.", enfe);
            }
            em.remove(customerMaster);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<CustomerMaster> findCustomerMasterEntities() {
        return findCustomerMasterEntities(true, -1, -1);
    }

    public List<CustomerMaster> findCustomerMasterEntities(int maxResults, int firstResult) {
        return findCustomerMasterEntities(false, maxResults, firstResult);
    }

    private List<CustomerMaster> findCustomerMasterEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CustomerMaster.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public CustomerMaster findCustomerMaster(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CustomerMaster.class, id);
        } finally {
            em.close();
        }
    }

    public int getCustomerMasterCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CustomerMaster> rt = cq.from(CustomerMaster.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
        public List<CustomerMaster> queryCustomerMasterByIdName(String customerName,String customerId){
        //此函数实现按name、ID、name+ID3种方式查询customer，实现逻辑为当name为空时使用ID查询，ID为空时使用Name查询，若都不为空则利用2个参数一起查询
       EntityManager em = getEntityManager();//创建实体管理
       try{
           if (customerId.length()==0) {
               Query queryByName ;//创建一个查询
               queryByName = em.createQuery("SELECT c FROM CustomerMaster c WHERE c.customerName = :customerName");//拼接查询语句
               queryByName.setParameter("customerName", customerName);//将参数赋值给查询语句中的customerName
               return queryByName.getResultList();//返回查询结果，结果存在一个list中
           }
           else if (customerName.length()==0) {
               Query queryById ;
               queryById = em.createQuery("SELECT c FROM CustomerMaster c WHERE c.customerId = :customerId");
               queryById.setParameter("customerId", customerId);
               return queryById.getResultList();
           }
           else{
                Query queryByIdName ;
                queryByIdName = em.createQuery("SELECT c FROM CustomerMaster c WHERE c.customerId = :customerId and c.customerName = :customerName");
                queryByIdName.setParameter("customerId", customerId);
                queryByIdName.setParameter("customerName", customerName);
                return queryByIdName.getResultList();
           }
       }finally{
           em.close();//关闭实体管理
       }
    }
    
    public CustomerMaster findCustomerMasterById(String customerId){
      
       EntityManager em = getEntityManager();//创建实体管理
       try{

               Query queryById ;
               queryById = em.createQuery("SELECT c FROM CustomerMaster c WHERE c.customerId = :customerId");
               queryById.setParameter("customerId", customerId);
               return (CustomerMaster)queryById.getSingleResult();

       }finally{
           em.close();//关闭实体管理
       }
    }
    
}
   
