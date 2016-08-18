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
import iot.dao.entity.OrderMaster;
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
public class OrderMasterDAO implements Serializable {

    public OrderMasterDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(OrderMaster orderMaster) throws PreexistingEntityException, Exception {
        if (orderMaster.getOrderDetailCollection() == null) {
            orderMaster.setOrderDetailCollection(new ArrayList<OrderDetail>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<OrderDetail> attachedOrderDetailCollection = new ArrayList<OrderDetail>();
            for (OrderDetail orderDetailCollectionOrderDetailToAttach : orderMaster.getOrderDetailCollection()) {
                orderDetailCollectionOrderDetailToAttach = em.getReference(orderDetailCollectionOrderDetailToAttach.getClass(), orderDetailCollectionOrderDetailToAttach.getOrderDetailId());
                attachedOrderDetailCollection.add(orderDetailCollectionOrderDetailToAttach);
            }
            orderMaster.setOrderDetailCollection(attachedOrderDetailCollection);
            em.persist(orderMaster);
            for (OrderDetail orderDetailCollectionOrderDetail : orderMaster.getOrderDetailCollection()) {
                OrderMaster oldOrderMasterIdOfOrderDetailCollectionOrderDetail = orderDetailCollectionOrderDetail.getOrderMasterId();
                orderDetailCollectionOrderDetail.setOrderMasterId(orderMaster);
                orderDetailCollectionOrderDetail = em.merge(orderDetailCollectionOrderDetail);
                if (oldOrderMasterIdOfOrderDetailCollectionOrderDetail != null) {
                    oldOrderMasterIdOfOrderDetailCollectionOrderDetail.getOrderDetailCollection().remove(orderDetailCollectionOrderDetail);
                    oldOrderMasterIdOfOrderDetailCollectionOrderDetail = em.merge(oldOrderMasterIdOfOrderDetailCollectionOrderDetail);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findOrderMaster(orderMaster.getOrderMasterId()) != null) {
                throw new PreexistingEntityException("OrderMaster " + orderMaster + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(OrderMaster orderMaster) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            OrderMaster persistentOrderMaster = em.find(OrderMaster.class, orderMaster.getOrderMasterId());
            Collection<OrderDetail> orderDetailCollectionOld = persistentOrderMaster.getOrderDetailCollection();
            Collection<OrderDetail> orderDetailCollectionNew = orderMaster.getOrderDetailCollection();
            List<String> illegalOrphanMessages = null;
            for (OrderDetail orderDetailCollectionOldOrderDetail : orderDetailCollectionOld) {
                if (!orderDetailCollectionNew.contains(orderDetailCollectionOldOrderDetail)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain OrderDetail " + orderDetailCollectionOldOrderDetail + " since its orderMasterId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<OrderDetail> attachedOrderDetailCollectionNew = new ArrayList<OrderDetail>();
            for (OrderDetail orderDetailCollectionNewOrderDetailToAttach : orderDetailCollectionNew) {
                orderDetailCollectionNewOrderDetailToAttach = em.getReference(orderDetailCollectionNewOrderDetailToAttach.getClass(), orderDetailCollectionNewOrderDetailToAttach.getOrderDetailId());
                attachedOrderDetailCollectionNew.add(orderDetailCollectionNewOrderDetailToAttach);
            }
            orderDetailCollectionNew = attachedOrderDetailCollectionNew;
            orderMaster.setOrderDetailCollection(orderDetailCollectionNew);
            orderMaster = em.merge(orderMaster);
            for (OrderDetail orderDetailCollectionNewOrderDetail : orderDetailCollectionNew) {
                if (!orderDetailCollectionOld.contains(orderDetailCollectionNewOrderDetail)) {
                    OrderMaster oldOrderMasterIdOfOrderDetailCollectionNewOrderDetail = orderDetailCollectionNewOrderDetail.getOrderMasterId();
                    orderDetailCollectionNewOrderDetail.setOrderMasterId(orderMaster);
                    orderDetailCollectionNewOrderDetail = em.merge(orderDetailCollectionNewOrderDetail);
                    if (oldOrderMasterIdOfOrderDetailCollectionNewOrderDetail != null && !oldOrderMasterIdOfOrderDetailCollectionNewOrderDetail.equals(orderMaster)) {
                        oldOrderMasterIdOfOrderDetailCollectionNewOrderDetail.getOrderDetailCollection().remove(orderDetailCollectionNewOrderDetail);
                        oldOrderMasterIdOfOrderDetailCollectionNewOrderDetail = em.merge(oldOrderMasterIdOfOrderDetailCollectionNewOrderDetail);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = orderMaster.getOrderMasterId();
                if (findOrderMaster(id) == null) {
                    throw new NonexistentEntityException("The orderMaster with id " + id + " no longer exists.");
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
            OrderMaster orderMaster;
            try {
                orderMaster = em.getReference(OrderMaster.class, id);
                orderMaster.getOrderMasterId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The orderMaster with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<OrderDetail> orderDetailCollectionOrphanCheck = orderMaster.getOrderDetailCollection();
            for (OrderDetail orderDetailCollectionOrphanCheckOrderDetail : orderDetailCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This OrderMaster (" + orderMaster + ") cannot be destroyed since the OrderDetail " + orderDetailCollectionOrphanCheckOrderDetail + " in its orderDetailCollection field has a non-nullable orderMasterId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(orderMaster);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<OrderMaster> findOrderMasterEntities() {
        return findOrderMasterEntities(true, -1, -1);
    }

    public List<OrderMaster> findOrderMasterEntities(int maxResults, int firstResult) {
        return findOrderMasterEntities(false, maxResults, firstResult);
    }

    private List<OrderMaster> findOrderMasterEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(OrderMaster.class));
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

    public OrderMaster findOrderMaster(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(OrderMaster.class, id);
        } finally {
            em.close();
        }
    }

    public int getOrderMasterCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<OrderMaster> rt = cq.from(OrderMaster.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
