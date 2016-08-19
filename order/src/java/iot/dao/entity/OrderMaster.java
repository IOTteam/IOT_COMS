/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iot.dao.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author hatanococoro
 */
@Entity
@Table(name = "order_master")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OrderMaster.findAll", query = "SELECT o FROM OrderMaster o"),
    @NamedQuery(name = "OrderMaster.findByOrderId", query = "SELECT o FROM OrderMaster o WHERE o.orderId = :orderId"),
    @NamedQuery(name = "OrderMaster.findByOrderDate", query = "SELECT o FROM OrderMaster o WHERE o.orderDate = :orderDate"),
    @NamedQuery(name = "OrderMaster.findByCustomerId", query = "SELECT o FROM OrderMaster o WHERE o.customerId = :customerId"),
    @NamedQuery(name = "OrderMaster.findByOrderMasterId", query = "SELECT o FROM OrderMaster o WHERE o.orderMasterId = :orderMasterId")})
public class OrderMaster implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "order_id")
    private String orderId;
    @Basic(optional = false)
    @Column(name = "order_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date orderDate;
    @Basic(optional = false)
    @Column(name = "customer_id")
    private String customerId;
    @Id
   // @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "order_master_id")
    private Integer orderMasterId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "orderMasterId")
    private Collection<OrderDetail> orderDetailCollection;

    public OrderMaster() {
    }

    public OrderMaster(Integer orderMasterId) {
        this.orderMasterId = orderMasterId;
    }

    public OrderMaster(Integer orderMasterId, String orderId, Date orderDate, String customerId) {
        this.orderMasterId = orderMasterId;
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.customerId = customerId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public Integer getOrderMasterId() {
        return orderMasterId;
    }

    public void setOrderMasterId(Integer orderMasterId) {
        this.orderMasterId = orderMasterId;
    }

    @XmlTransient
    public Collection<OrderDetail> getOrderDetailCollection() {
        return orderDetailCollection;
    }

    public void setOrderDetailCollection(Collection<OrderDetail> orderDetailCollection) {
        this.orderDetailCollection = orderDetailCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (orderMasterId != null ? orderMasterId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OrderMaster)) {
            return false;
        }
        OrderMaster other = (OrderMaster) object;
        if ((this.orderMasterId == null && other.orderMasterId != null) || (this.orderMasterId != null && !this.orderMasterId.equals(other.orderMasterId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "iot.dao.entity.OrderMaster[ orderMasterId=" + orderMasterId + " ]";
    }
    
}
