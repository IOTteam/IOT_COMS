/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iot.dao.repository;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import iot.dao.entity.CustomerMaster;
import iot.dao.entity.CustomerPrice;
import iot.dao.entity.ProductMaster;
import iot.dao.repository.exceptions.NonexistentEntityException;
import iot.dao.repository.exceptions.PreexistingEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author hatanococoro
 */
public class CustomerPriceJpaController implements Serializable {

    public CustomerPriceJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(CustomerPrice customerPrice) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CustomerMaster customerMasterId = customerPrice.getCustomerMasterId();
            if (customerMasterId != null) {
                customerMasterId = em.getReference(customerMasterId.getClass(), customerMasterId.getCustomerMasterId());
                customerPrice.setCustomerMasterId(customerMasterId);
            }
            ProductMaster productMasterId = customerPrice.getProductMasterId();
            if (productMasterId != null) {
                productMasterId = em.getReference(productMasterId.getClass(), productMasterId.getProductMasterId());
                customerPrice.setProductMasterId(productMasterId);
            }
            em.persist(customerPrice);
            if (customerMasterId != null) {
                customerMasterId.getCustomerPriceCollection().add(customerPrice);
                customerMasterId = em.merge(customerMasterId);
            }
            if (productMasterId != null) {
                productMasterId.getCustomerPriceCollection().add(customerPrice);
                productMasterId = em.merge(productMasterId);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCustomerPrice(customerPrice.getCustomerPriceId()) != null) {
                throw new PreexistingEntityException("CustomerPrice " + customerPrice + " already exists.", ex);
            }
            throw ex;
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
            CustomerPrice persistentCustomerPrice = em.find(CustomerPrice.class, customerPrice.getCustomerPriceId());
            CustomerMaster customerMasterIdOld = persistentCustomerPrice.getCustomerMasterId();
            CustomerMaster customerMasterIdNew = customerPrice.getCustomerMasterId();
            ProductMaster productMasterIdOld = persistentCustomerPrice.getProductMasterId();
            ProductMaster productMasterIdNew = customerPrice.getProductMasterId();
            if (customerMasterIdNew != null) {
                customerMasterIdNew = em.getReference(customerMasterIdNew.getClass(), customerMasterIdNew.getCustomerMasterId());
                customerPrice.setCustomerMasterId(customerMasterIdNew);
            }
            if (productMasterIdNew != null) {
                productMasterIdNew = em.getReference(productMasterIdNew.getClass(), productMasterIdNew.getProductMasterId());
                customerPrice.setProductMasterId(productMasterIdNew);
            }
            customerPrice = em.merge(customerPrice);
            if (customerMasterIdOld != null && !customerMasterIdOld.equals(customerMasterIdNew)) {
                customerMasterIdOld.getCustomerPriceCollection().remove(customerPrice);
                customerMasterIdOld = em.merge(customerMasterIdOld);
            }
            if (customerMasterIdNew != null && !customerMasterIdNew.equals(customerMasterIdOld)) {
                customerMasterIdNew.getCustomerPriceCollection().add(customerPrice);
                customerMasterIdNew = em.merge(customerMasterIdNew);
            }
            if (productMasterIdOld != null && !productMasterIdOld.equals(productMasterIdNew)) {
                productMasterIdOld.getCustomerPriceCollection().remove(customerPrice);
                productMasterIdOld = em.merge(productMasterIdOld);
            }
            if (productMasterIdNew != null && !productMasterIdNew.equals(productMasterIdOld)) {
                productMasterIdNew.getCustomerPriceCollection().add(customerPrice);
                productMasterIdNew = em.merge(productMasterIdNew);
            }
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
            CustomerMaster customerMasterId = customerPrice.getCustomerMasterId();
            if (customerMasterId != null) {
                customerMasterId.getCustomerPriceCollection().remove(customerPrice);
                customerMasterId = em.merge(customerMasterId);
            }
            ProductMaster productMasterId = customerPrice.getProductMasterId();
            if (productMasterId != null) {
                productMasterId.getCustomerPriceCollection().remove(customerPrice);
                productMasterId = em.merge(productMasterId);
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
