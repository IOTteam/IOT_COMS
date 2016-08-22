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
import iot.dao.entity.OrderDetail;
import iot.dao.entity.ProductMaster;
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
public class ProductMasterDAO implements Serializable {

    public ProductMasterDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ProductMaster productMaster) throws PreexistingEntityException, Exception {
        if (productMaster.getOrderDetailCollection() == null) {
            productMaster.setOrderDetailCollection(new ArrayList<OrderDetail>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<OrderDetail> attachedOrderDetailCollection = new ArrayList<OrderDetail>();
            for (OrderDetail orderDetailCollectionOrderDetailToAttach : productMaster.getOrderDetailCollection()) {
                orderDetailCollectionOrderDetailToAttach = em.getReference(orderDetailCollectionOrderDetailToAttach.getClass(), orderDetailCollectionOrderDetailToAttach.getOrderDetailId());
                attachedOrderDetailCollection.add(orderDetailCollectionOrderDetailToAttach);
            }
            productMaster.setOrderDetailCollection(attachedOrderDetailCollection);
            em.persist(productMaster);
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

    public void edit(ProductMaster productMaster) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ProductMaster persistentProductMaster = em.find(ProductMaster.class, productMaster.getProductMasterId());
            Collection<OrderDetail> orderDetailCollectionOld = persistentProductMaster.getOrderDetailCollection();
            Collection<OrderDetail> orderDetailCollectionNew = productMaster.getOrderDetailCollection();
            Collection<OrderDetail> attachedOrderDetailCollectionNew = new ArrayList<OrderDetail>();
            for (OrderDetail orderDetailCollectionNewOrderDetailToAttach : orderDetailCollectionNew) {
                orderDetailCollectionNewOrderDetailToAttach = em.getReference(orderDetailCollectionNewOrderDetailToAttach.getClass(), orderDetailCollectionNewOrderDetailToAttach.getOrderDetailId());
                attachedOrderDetailCollectionNew.add(orderDetailCollectionNewOrderDetailToAttach);
            }
            orderDetailCollectionNew = attachedOrderDetailCollectionNew;
            productMaster.setOrderDetailCollection(orderDetailCollectionNew);
            productMaster = em.merge(productMaster);
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

    public void destroy(Integer id) throws NonexistentEntityException {
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
