/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iot.dao.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author hatanococoro
 */
@Entity
@Table(name = "product_master")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProductMaster.findAll", query = "SELECT p FROM ProductMaster p"),
    @NamedQuery(name = "ProductMaster.findByProductId", query = "SELECT p FROM ProductMaster p WHERE p.productId = :productId"),
    @NamedQuery(name = "ProductMaster.findByProductName", query = "SELECT p FROM ProductMaster p WHERE p.productName = :productName"),
    @NamedQuery(name = "ProductMaster.findByProductSpec", query = "SELECT p FROM ProductMaster p WHERE p.productSpec = :productSpec"),
    @NamedQuery(name = "ProductMaster.findByProductPrice", query = "SELECT p FROM ProductMaster p WHERE p.productPrice = :productPrice"),
    @NamedQuery(name = "ProductMaster.findByProductMasterId", query = "SELECT p FROM ProductMaster p WHERE p.productMasterId = :productMasterId")})
public class ProductMaster implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "product_id")
    private String productId;
    @Basic(optional = false)
    @Column(name = "product_name")
    private String productName;
    @Basic(optional = false)
    @Column(name = "product_spec")
    private String productSpec;
    @Basic(optional = false)
    @Column(name = "product_price")
    private double productPrice;
    @Id
    @Basic(optional = false)
    @Column(name = "product_master_id")
    private Integer productMasterId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productMasterId")
    private Collection<CustomerPrice> customerPriceCollection;
    @OneToMany(mappedBy = "productId")
    private Collection<OrderDetail> orderDetailCollection;

    public ProductMaster() {
    }

    public ProductMaster(Integer productMasterId) {
        this.productMasterId = productMasterId;
    }

    public ProductMaster(Integer productMasterId, String productId, String productName, String productSpec, double productPrice) {
        this.productMasterId = productMasterId;
        this.productId = productId;
        this.productName = productName;
        this.productSpec = productSpec;
        this.productPrice = productPrice;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductSpec() {
        return productSpec;
    }

    public void setProductSpec(String productSpec) {
        this.productSpec = productSpec;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public Integer getProductMasterId() {
        return productMasterId;
    }

    public void setProductMasterId(Integer productMasterId) {
        this.productMasterId = productMasterId;
    }

    @XmlTransient
    public Collection<CustomerPrice> getCustomerPriceCollection() {
        return customerPriceCollection;
    }

    public void setCustomerPriceCollection(Collection<CustomerPrice> customerPriceCollection) {
        this.customerPriceCollection = customerPriceCollection;
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
        hash += (productMasterId != null ? productMasterId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProductMaster)) {
            return false;
        }
        ProductMaster other = (ProductMaster) object;
        if ((this.productMasterId == null && other.productMasterId != null) || (this.productMasterId != null && !this.productMasterId.equals(other.productMasterId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "iot.dao.entity.ProductMaster[ productMasterId=" + productMasterId + " ]";
    }
    
}
