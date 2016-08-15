/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iot.dao.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author hatanococoro
 */
@Entity
@Table(name = "order_detail")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OrderDetail.findAll", query = "SELECT o FROM OrderDetail o"),
    @NamedQuery(name = "OrderDetail.findByOrderQty", query = "SELECT o FROM OrderDetail o WHERE o.orderQty = :orderQty"),
    @NamedQuery(name = "OrderDetail.findByOrderPrice", query = "SELECT o FROM OrderDetail o WHERE o.orderPrice = :orderPrice"),
    @NamedQuery(name = "OrderDetail.findByOrderDetailId", query = "SELECT o FROM OrderDetail o WHERE o.orderDetailId = :orderDetailId")})
public class OrderDetail implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "order_qty")
    private int orderQty;
    @Basic(optional = false)
    @Column(name = "order_price")
    private float orderPrice;
    @Id
    @Basic(optional = false)
    @Column(name = "order_detail_id")
    private Integer orderDetailId;
    @JoinColumn(name = "order_master_id", referencedColumnName = "order_master_id")
    @ManyToOne(optional = false)
    private OrderMaster orderMasterId;
    @JoinColumn(name = "product_id", referencedColumnName = "product_id")
    @ManyToOne
    private ProductMaster productId;

    public OrderDetail() {
    }

    public OrderDetail(Integer orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    public OrderDetail(Integer orderDetailId, int orderQty, float orderPrice) {
        this.orderDetailId = orderDetailId;
        this.orderQty = orderQty;
        this.orderPrice = orderPrice;
    }

    public int getOrderQty() {
        return orderQty;
    }

    public void setOrderQty(int orderQty) {
        this.orderQty = orderQty;
    }

    public float getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(float orderPrice) {
        this.orderPrice = orderPrice;
    }

    public Integer getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(Integer orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    public OrderMaster getOrderMasterId() {
        return orderMasterId;
    }

    public void setOrderMasterId(OrderMaster orderMasterId) {
        this.orderMasterId = orderMasterId;
    }

    public ProductMaster getProductId() {
        return productId;
    }

    public void setProductId(ProductMaster productId) {
        this.productId = productId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (orderDetailId != null ? orderDetailId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OrderDetail)) {
            return false;
        }
        OrderDetail other = (OrderDetail) object;
        if ((this.orderDetailId == null && other.orderDetailId != null) || (this.orderDetailId != null && !this.orderDetailId.equals(other.orderDetailId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "iot.dao.entity.OrderDetail[ orderDetailId=" + orderDetailId + " ]";
    }
    
}
