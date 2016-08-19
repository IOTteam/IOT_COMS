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
import iot.dao.entity.CustomerPrice;
import java.util.ArrayList;
import java.util.Collection;
import iot.dao.entity.OrderDetail;
import iot.dao.entity.ProductMaster;
import iot.dao.repository.exceptions.IllegalOrphanException;
import iot.dao.repository.exceptions.NonexistentEntityException;
import iot.dao.repository.exceptions.PreexistingEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author hatanococoro
 */
public class ProductMasterDAO implements Serializable {

    public ProductMasterDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ProductMaster productMaster) throws PreexistingEntityException, Exception {
        if (productMaster.getCustomerPriceCollection() == null) {
            productMaster.setCustomerPriceCollection(new ArrayList<CustomerPrice>());
        }
        if (productMaster.getOrderDetailCollection() == null) {
            productMaster.setOrderDetailCollection(new ArrayList<OrderDetail>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<CustomerPrice> attachedCustomerPriceCollection = new ArrayList<CustomerPrice>();
            for (CustomerPrice customerPriceCollectionCustomerPriceToAttach : productMaster.getCustomerPriceCollection()) {
                customerPriceCollectionCustomerPriceToAttach = em.getReference(customerPriceCollectionCustomerPriceToAttach.getClass(), customerPriceCollectionCustomerPriceToAttach.getCustomerPriceId());
                attachedCustomerPriceCollection.add(customerPriceCollectionCustomerPriceToAttach);
            }
            productMaster.setCustomerPriceCollection(attachedCustomerPriceCollection);
            Collection<OrderDetail> attachedOrderDetailCollection = new ArrayList<OrderDetail>();
            for (OrderDetail orderDetailCollectionOrderDetailToAttach : productMaster.getOrderDetailCollection()) {
                orderDetailCollectionOrderDetailToAttach = em.getReference(orderDetailCollectionOrderDetailToAttach.getClass(), orderDetailCollectionOrderDetailToAttach.getOrderDetailId());
                attachedOrderDetailCollection.add(orderDetailCollectionOrderDetailToAttach);
            }
            productMaster.setOrderDetailCollection(attachedOrderDetailCollection);
            em.persist(productMaster);
            for (CustomerPrice customerPriceCollectionCustomerPrice : productMaster.getCustomerPriceCollection()) {
                ProductMaster oldProductMasterIdOfCustomerPriceCollectionCustomerPrice = customerPriceCollectionCustomerPrice.getProductMasterId();
                customerPriceCollectionCustomerPrice.setProductMasterId(productMaster);
                customerPriceCollectionCustomerPrice = em.merge(customerPriceCollectionCustomerPrice);
                if (oldProductMasterIdOfCustomerPriceCollectionCustomerPrice != null) {
                    oldProductMasterIdOfCustomerPriceCollectionCustomerPrice.getCustomerPriceCollection().remove(customerPriceCollectionCustomerPrice);
                    oldProductMasterIdOfCustomerPriceCollectionCustomerPrice = em.merge(oldProductMasterIdOfCustomerPriceCollectionCustomerPrice);
                }
            }
            for (OrderDetail orderDetailCollectionOrderDetail : productMaster.getOrderDetailCollection()) {
                ProductMaster oldProductIdOfOrderDetailCollectionOrderDetail = orderDetailCollectionOrderDetail.getProductId();
                orderDetailCollectionOrderDetail.setProductId(productMaster);
                orderDetailCollectionOrderDetail = em.merge(orderDetailCollectionOrderDetail);
                if (oldProductIdOfOrderDetailCollectionOrderDetail != null) {
                    oldProductIdOfOrderDetailCollectionOrderDetail.getOrderDetailCollection().remove(orderDetailCollectionOrderDetail);
                    oldProductIdOfOrderDetailCollectionOrderDetail = em.merge(oldProductIdOfOrderDetailCollectionOrderDetail);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findProductMaster(productMaster.getProductMasterId()) != null) {
                throw new PreexistingEntityException("ProductMaster " + productMaster + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ProductMaster productMaster) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ProductMaster persistentProductMaster = em.find(ProductMaster.class, productMaster.getProductMasterId());
            Collection<CustomerPrice> customerPriceCollectionOld = persistentProductMaster.getCustomerPriceCollection();
            Collection<CustomerPrice> customerPriceCollectionNew = productMaster.getCustomerPriceCollection();
            Collection<OrderDetail> orderDetailCollectionOld = persistentProductMaster.getOrderDetailCollection();
            Collection<OrderDetail> orderDetailCollectionNew = productMaster.getOrderDetailCollection();
            List<String> illegalOrphanMessages = null;
            for (CustomerPrice customerPriceCollectionOldCustomerPrice : customerPriceCollectionOld) {
                if (!customerPriceCollectionNew.contains(customerPriceCollectionOldCustomerPrice)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain CustomerPrice " + customerPriceCollectionOldCustomerPrice + " since its productMasterId field is not nullable.");
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
            productMaster.setCustomerPriceCollection(customerPriceCollectionNew);
            Collection<OrderDetail> attachedOrderDetailCollectionNew = new ArrayList<OrderDetail>();
            for (OrderDetail orderDetailCollectionNewOrderDetailToAttach : orderDetailCollectionNew) {
                orderDetailCollectionNewOrderDetailToAttach = em.getReference(orderDetailCollectionNewOrderDetailToAttach.getClass(), orderDetailCollectionNewOrderDetailToAttach.getOrderDetailId());
                attachedOrderDetailCollectionNew.add(orderDetailCollectionNewOrderDetailToAttach);
            }
            orderDetailCollectionNew = attachedOrderDetailCollectionNew;
            productMaster.setOrderDetailCollection(orderDetailCollectionNew);
            productMaster = em.merge(productMaster);
            for (CustomerPrice customerPriceCollectionNewCustomerPrice : customerPriceCollectionNew) {
                if (!customerPriceCollectionOld.contains(customerPriceCollectionNewCustomerPrice)) {
                    ProductMaster oldProductMasterIdOfCustomerPriceCollectionNewCustomerPrice = customerPriceCollectionNewCustomerPrice.getProductMasterId();
                    customerPriceCollectionNewCustomerPrice.setProductMasterId(productMaster);
                    customerPriceCollectionNewCustomerPrice = em.merge(customerPriceCollectionNewCustomerPrice);
                    if (oldProductMasterIdOfCustomerPriceCollectionNewCustomerPrice != null && !oldProductMasterIdOfCustomerPriceCollectionNewCustomerPrice.equals(productMaster)) {
                        oldProductMasterIdOfCustomerPriceCollectionNewCustomerPrice.getCustomerPriceCollection().remove(customerPriceCollectionNewCustomerPrice);
                        oldProductMasterIdOfCustomerPriceCollectionNewCustomerPrice = em.merge(oldProductMasterIdOfCustomerPriceCollectionNewCustomerPrice);
                    }
                }
            }
            for (OrderDetail orderDetailCollectionOldOrderDetail : orderDetailCollectionOld) {
                if (!orderDetailCollectionNew.contains(orderDetailCollectionOldOrderDetail)) {
                    orderDetailCollectionOldOrderDetail.setProductId(null);
                    orderDetailCollectionOldOrderDetail = em.merge(orderDetailCollectionOldOrderDetail);
                }
            }
            for (OrderDetail orderDetailCollectionNewOrderDetail : orderDetailCollectionNew) {
                if (!orderDetailCollectionOld.contains(orderDetailCollectionNewOrderDetail)) {
                    ProductMaster oldProductIdOfOrderDetailCollectionNewOrderDetail = orderDetailCollectionNewOrderDetail.getProductId();
                    orderDetailCollectionNewOrderDetail.setProductId(productMaster);
                    orderDetailCollectionNewOrderDetail = em.merge(orderDetailCollectionNewOrderDetail);
                    if (oldProductIdOfOrderDetailCollectionNewOrderDetail != null && !oldProductIdOfOrderDetailCollectionNewOrderDetail.equals(productMaster)) {
                        oldProductIdOfOrderDetailCollectionNewOrderDetail.getOrderDetailCollection().remove(orderDetailCollectionNewOrderDetail);
                        oldProductIdOfOrderDetailCollectionNewOrderDetail = em.merge(oldProductIdOfOrderDetailCollectionNewOrderDetail);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = productMaster.getProductMasterId();
                if (findProductMaster(id) == null) {
                    throw new NonexistentEntityException("The productMaster with id " + id + " no longer exists.");
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
            ProductMaster productMaster;
            try {
                productMaster = em.getReference(ProductMaster.class, id);
                productMaster.getProductMasterId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The productMaster with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<CustomerPrice> customerPriceCollectionOrphanCheck = productMaster.getCustomerPriceCollection();
            for (CustomerPrice customerPriceCollectionOrphanCheckCustomerPrice : customerPriceCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This ProductMaster (" + productMaster + ") cannot be destroyed since the CustomerPrice " + customerPriceCollectionOrphanCheckCustomerPrice + " in its customerPriceCollection field has a non-nullable productMasterId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<OrderDetail> orderDetailCollection = productMaster.getOrderDetailCollection();
            for (OrderDetail orderDetailCollectionOrderDetail : orderDetailCollection) {
                orderDetailCollectionOrderDetail.setProductId(null);
                orderDetailCollectionOrderDetail = em.merge(orderDetailCollectionOrderDetail);
            }
            em.remove(productMaster);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ProductMaster> findProductMasterEntities() {
        return findProductMasterEntities(true, -1, -1);
    }

    public List<ProductMaster> findProductMasterEntities(int maxResults, int firstResult) {
        return findProductMasterEntities(false, maxResults, firstResult);
    }

    private List<ProductMaster> findProductMasterEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ProductMaster.class));
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

    public ProductMaster findProductMaster(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ProductMaster.class, id);
        } finally {
            em.close();
        }
    }
    
    public ProductMaster findProductMasterByproductId(String productId) {
        EntityManager em = getEntityManager();
        try {
            Query query = em.createQuery("SELECT p FROM ProductMaster p WHERE p.productId = :productId");
            query.setParameter("productId", productId);
            return (ProductMaster)query.getSingleResult();
        } finally {
            em.close();
        }
    }

    public int getProductMasterCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ProductMaster> rt = cq.from(ProductMaster.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
