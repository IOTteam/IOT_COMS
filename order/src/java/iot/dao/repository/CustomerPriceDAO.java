/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iot.dao.repository;

import iot.dao.entity.CustomerMaster;
import iot.dao.entity.CustomerPrice;
import iot.dao.entity.ProductMaster;
import iot.dao.repository.exceptions.NonexistentEntityException;
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
public class CustomerPriceDAO implements Serializable {

    public CustomerPriceDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(CustomerPrice customerPrice) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(customerPrice);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CustomerPrice customerPrice) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            customerPrice = em.merge(customerPrice);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = customerPrice.getCustomerPriceId();
                if (findCustomerPrice(id) == null) {
                    throw new NonexistentEntityException("The customerPrice with id " + id + " no longer exists.");
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
            CustomerPrice customerPrice;
            try {
                customerPrice = em.getReference(CustomerPrice.class, id);
                customerPrice.getCustomerPriceId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The customerPrice with id " + id + " no longer exists.", enfe);
            }
            em.remove(customerPrice);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<CustomerPrice> findCustomerPriceEntities() {
        return findCustomerPriceEntities(true, -1, -1);
    }

    public List<CustomerPrice> findCustomerPriceEntities(int maxResults, int firstResult) {
        return findCustomerPriceEntities(false, maxResults, firstResult);
    }

    private List<CustomerPrice> findCustomerPriceEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CustomerPrice.class));
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

    public CustomerPrice findCustomerPrice(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CustomerPrice.class, id);
        } finally {
            em.close();
        }
    }
    
    public CustomerPrice findCustomerPriceByCustProdRange(CustomerMaster customerMasterId,ProductMaster productMasterId,int orderQty) {
        EntityManager em = getEntityManager();
        try {
            Query query = em.createQuery("SELECT c FROM CustomerPrice c WHERE c.customerMasterId = :customerMasterId and c.productMasterId = :productMasterId AND c.range <:orderQty order by c.range DESC ");
            //查询结果以range为准降序排列
            query.setParameter("customerMasterId", customerMasterId);//赋值
            query.setParameter("productMasterId", productMasterId);//赋值
            query.setParameter("orderQty", orderQty);//赋值
            return (CustomerPrice)query.getResultList().get(0);//查询结果取第一个结果
        } finally {
            em.close();
        }
    }

    public int getCustomerPriceCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CustomerPrice> rt = cq.from(CustomerPrice.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
