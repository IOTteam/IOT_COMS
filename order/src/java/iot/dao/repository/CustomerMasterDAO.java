/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iot.dao.repository;

import iot.dao.entity.CustomerMaster;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import iot.dao.entity.CustomerPrice;
import iot.dao.repository.exceptions.IllegalOrphanException;
import iot.dao.repository.exceptions.NonexistentEntityException;
import iot.dao.repository.exceptions.PreexistingEntityException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

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
        if (customerMaster.getCustomerPriceCollection() == null) {
            customerMaster.setCustomerPriceCollection(new ArrayList<CustomerPrice>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<CustomerPrice> attachedCustomerPriceCollection = new ArrayList<CustomerPrice>();
            for (CustomerPrice customerPriceCollectionCustomerPriceToAttach : customerMaster.getCustomerPriceCollection()) {
                customerPriceCollectionCustomerPriceToAttach = em.getReference(customerPriceCollectionCustomerPriceToAttach.getClass(), customerPriceCollectionCustomerPriceToAttach.getCustomerPriceId());
                attachedCustomerPriceCollection.add(customerPriceCollectionCustomerPriceToAttach);
            }
            customerMaster.setCustomerPriceCollection(attachedCustomerPriceCollection);
            em.persist(customerMaster);
            for (CustomerPrice customerPriceCollectionCustomerPrice : customerMaster.getCustomerPriceCollection()) {
                CustomerMaster oldCustomerMasterIdOfCustomerPriceCollectionCustomerPrice = customerPriceCollectionCustomerPrice.getCustomerMasterId();
                customerPriceCollectionCustomerPrice.setCustomerMasterId(customerMaster);
                customerPriceCollectionCustomerPrice = em.merge(customerPriceCollectionCustomerPrice);
                if (oldCustomerMasterIdOfCustomerPriceCollectionCustomerPrice != null) {
                    oldCustomerMasterIdOfCustomerPriceCollectionCustomerPrice.getCustomerPriceCollection().remove(customerPriceCollectionCustomerPrice);
                    oldCustomerMasterIdOfCustomerPriceCollectionCustomerPrice = em.merge(oldCustomerMasterIdOfCustomerPriceCollectionCustomerPrice);
                }
            }
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

    public void edit(CustomerMaster customerMaster) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CustomerMaster persistentCustomerMaster = em.find(CustomerMaster.class, customerMaster.getCustomerMasterId());
            Collection<CustomerPrice> customerPriceCollectionOld = persistentCustomerMaster.getCustomerPriceCollection();
            Collection<CustomerPrice> customerPriceCollectionNew = customerMaster.getCustomerPriceCollection();
            List<String> illegalOrphanMessages = null;
            for (CustomerPrice customerPriceCollectionOldCustomerPrice : customerPriceCollectionOld) {
                if (!customerPriceCollectionNew.contains(customerPriceCollectionOldCustomerPrice)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain CustomerPrice " + customerPriceCollectionOldCustomerPrice + " since its customerMasterId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<CustomerPrice> attachedCustomerPriceCollectionNew = new ArrayList<CustomerPrice>();
            for (CustomerPrice customerPriceCollectionNewCustomerPriceToAttach : customerPriceCollectionNew) {
                customerPriceCollectionNewCustomerPriceToAttach = em.getReference(customerPriceCollectionNewCustomerPriceToAttach.getClass(), customerPriceCollectionNewCustomerPriceToAttach.getCustomerPriceId());
                attachedCustomerPriceCollectionNew.add(customerPriceCollectionNewCustomerPriceToAttach);
            }
            customerPriceCollectionNew = attachedCustomerPriceCollectionNew;
            customerMaster.setCustomerPriceCollection(customerPriceCollectionNew);
            customerMaster = em.merge(customerMaster);
            for (CustomerPrice customerPriceCollectionNewCustomerPrice : customerPriceCollectionNew) {
                if (!customerPriceCollectionOld.contains(customerPriceCollectionNewCustomerPrice)) {
                    CustomerMaster oldCustomerMasterIdOfCustomerPriceCollectionNewCustomerPrice = customerPriceCollectionNewCustomerPrice.getCustomerMasterId();
                    customerPriceCollectionNewCustomerPrice.setCustomerMasterId(customerMaster);
                    customerPriceCollectionNewCustomerPrice = em.merge(customerPriceCollectionNewCustomerPrice);
                    if (oldCustomerMasterIdOfCustomerPriceCollectionNewCustomerPrice != null && !oldCustomerMasterIdOfCustomerPriceCollectionNewCustomerPrice.equals(customerMaster)) {
                        oldCustomerMasterIdOfCustomerPriceCollectionNewCustomerPrice.getCustomerPriceCollection().remove(customerPriceCollectionNewCustomerPrice);
                        oldCustomerMasterIdOfCustomerPriceCollectionNewCustomerPrice = em.merge(oldCustomerMasterIdOfCustomerPriceCollectionNewCustomerPrice);
                    }
                }
            }
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

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
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
            List<String> illegalOrphanMessages = null;
            Collection<CustomerPrice> customerPriceCollectionOrphanCheck = customerMaster.getCustomerPriceCollection();
            for (CustomerPrice customerPriceCollectionOrphanCheckCustomerPrice : customerPriceCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This CustomerMaster (" + customerMaster + ") cannot be destroyed since the CustomerPrice " + customerPriceCollectionOrphanCheckCustomerPrice + " in its customerPriceCollection field has a non-nullable customerMasterId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
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
    
     public List<CustomerMaster> findCustomerMasterById(String CustomerId) {
        EntityManager em = getEntityManager();
        try {
           Query query = em.createQuery("SELECT c FROM CustomerMaster c WHERE c.customerId = :customerId");
            query.setParameter("customerId", CustomerId);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
     
     public List<CustomerMaster> findCustomerMasterByName(String CustomerName) {
        EntityManager em = getEntityManager();
        try {
            Query query = em.createQuery("SELECT c FROM CustomerMaster c WHERE c.customerName = :customerName");
            query.setParameter("customerName", CustomerName);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
     
      public List<CustomerMaster> findCustomerMasterByNameId(String id,String name) {
        EntityManager em = getEntityManager();
        try {
            Query query = em.createQuery("SELECT c FROM CustomerMaster c WHERE c.customerId = :customerId and c.customerName = :customerName");
            query.setParameter("customerId", id);
            query.setParameter("customerName", name);
            return query.getResultList();
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
    
}