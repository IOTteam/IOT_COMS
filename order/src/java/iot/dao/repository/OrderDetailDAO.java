/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iot.dao.repository;

import iot.dao.entity.OrderDetail;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import iot.dao.entity.OrderMaster;
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
public class OrderDetailDAO implements Serializable {

    public OrderDetailDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(OrderDetail orderDetail) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            OrderMaster orderMasterId = orderDetail.getOrderMasterId();
            if (orderMasterId != null) {
                orderMasterId = em.getReference(orderMasterId.getClass(), orderMasterId.getOrderMasterId());
                orderDetail.setOrderMasterId(orderMasterId);
            }
            ProductMaster productId = orderDetail.getProductId();
            if (productId != null) {
                productId = em.getReference(productId.getClass(), productId.getProductMasterId());
                orderDetail.setProductId(productId);
            }
            em.persist(orderDetail);
            if (orderMasterId != null) {
                orderMasterId.getOrderDetailCollection().add(orderDetail);
                orderMasterId = em.merge(orderMasterId);
            }
            if (productId != null) {
                productId.getOrderDetailCollection().add(orderDetail);
                productId = em.merge(productId);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findOrderDetail(orderDetail.getOrderDetailId()) != null) {
                throw new PreexistingEntityException("OrderDetail " + orderDetail + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(OrderDetail orderDetail) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            OrderDetail persistentOrderDetail = em.find(OrderDetail.class, orderDetail.getOrderDetailId());
            OrderMaster orderMasterIdOld = persistentOrderDetail.getOrderMasterId();
            OrderMaster orderMasterIdNew = orderDetail.getOrderMasterId();
            ProductMaster productIdOld = persistentOrderDetail.getProductId();
            ProductMaster productIdNew = orderDetail.getProductId();
            if (orderMasterIdNew != null) {
                orderMasterIdNew = em.getReference(orderMasterIdNew.getClass(), orderMasterIdNew.getOrderMasterId());
                orderDetail.setOrderMasterId(orderMasterIdNew);
            }
            if (productIdNew != null) {
                productIdNew = em.getReference(productIdNew.getClass(), productIdNew.getProductMasterId());
                orderDetail.setProductId(productIdNew);
            }
            orderDetail = em.merge(orderDetail);
            if (orderMasterIdOld != null && !orderMasterIdOld.equals(orderMasterIdNew)) {
                orderMasterIdOld.getOrderDetailCollection().remove(orderDetail);
                orderMasterIdOld = em.merge(orderMasterIdOld);
            }
            if (orderMasterIdNew != null && !orderMasterIdNew.equals(orderMasterIdOld)) {
                orderMasterIdNew.getOrderDetailCollection().add(orderDetail);
                orderMasterIdNew = em.merge(orderMasterIdNew);
            }
            if (productIdOld != null && !productIdOld.equals(productIdNew)) {
                productIdOld.getOrderDetailCollection().remove(orderDetail);
                productIdOld = em.merge(productIdOld);
            }
            if (productIdNew != null && !productIdNew.equals(productIdOld)) {
                productIdNew.getOrderDetailCollection().add(orderDetail);
                productIdNew = em.merge(productIdNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = orderDetail.getOrderDetailId();
                if (findOrderDetail(id) == null) {
                    throw new NonexistentEntityException("The orderDetail with id " + id + " no longer exists.");
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
            OrderDetail orderDetail;
            try {
                orderDetail = em.getReference(OrderDetail.class, id);
                orderDetail.getOrderDetailId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The orderDetail with id " + id + " no longer exists.", enfe);
            }
            OrderMaster orderMasterId = orderDetail.getOrderMasterId();
            if (orderMasterId != null) {
                orderMasterId.getOrderDetailCollection().remove(orderDetail);
                orderMasterId = em.merge(orderMasterId);
            }
            ProductMaster productId = orderDetail.getProductId();
            if (productId != null) {
                productId.getOrderDetailCollection().remove(orderDetail);
                productId = em.merge(productId);
            }
            em.remove(orderDetail);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<OrderDetail> findOrderDetailEntities() {
        return findOrderDetailEntities(true, -1, -1);
    }

    public List<OrderDetail> findOrderDetailEntities(int maxResults, int firstResult) {
        return findOrderDetailEntities(false, maxResults, firstResult);
    }

    private List<OrderDetail> findOrderDetailEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(OrderDetail.class));
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

    public OrderDetail findOrderDetail(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(OrderDetail.class, id);
        } finally {
            em.close();
        }
    }

    public int getOrderDetailCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<OrderDetail> rt = cq.from(OrderDetail.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
