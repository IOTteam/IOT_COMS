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
@Table(name = "customer_master")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CustomerMaster.findAll", query = "SELECT c FROM CustomerMaster c"),
    @NamedQuery(name = "CustomerMaster.findByCustomerId", query = "SELECT c FROM CustomerMaster c WHERE c.customerId = :customerId"),
    @NamedQuery(name = "CustomerMaster.findByCustomerName", query = "SELECT c FROM CustomerMaster c WHERE c.customerName = :customerName"),
    @NamedQuery(name = "CustomerMaster.findByCustomerMail", query = "SELECT c FROM CustomerMaster c WHERE c.customerMail = :customerMail"),
    @NamedQuery(name = "CustomerMaster.findByCustomerPhone", query = "SELECT c FROM CustomerMaster c WHERE c.customerPhone = :customerPhone"),
    @NamedQuery(name = "CustomerMaster.findByCustomerMasterId", query = "SELECT c FROM CustomerMaster c WHERE c.customerMasterId = :customerMasterId")})
public class CustomerMaster implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "customer_id")
    private String customerId;
    @Basic(optional = false)
    @Column(name = "customer_name")
    private String customerName;
    @Basic(optional = false)
    @Column(name = "customer_mail")
    private String customerMail;
    @Basic(optional = false)
    @Column(name = "customer_phone")
    private String customerPhone;
    @Id
    @Basic(optional = false)
    @Column(name = "customer_master_id")
    private Integer customerMasterId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customerMasterId")
    private Collection<CustomerPrice> customerPriceCollection;

    public CustomerMaster() {
    }

    public CustomerMaster(Integer customerMasterId) {
        this.customerMasterId = customerMasterId;
    }

    public CustomerMaster(Integer customerMasterId, String customerId, String customerName, String customerMail, String customerPhone) {
        this.customerMasterId = customerMasterId;
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerMail = customerMail;
        this.customerPhone = customerPhone;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerMail() {
        return customerMail;
    }

    public void setCustomerMail(String customerMail) {
        this.customerMail = customerMail;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public Integer getCustomerMasterId() {
        return customerMasterId;
    }

    public void setCustomerMasterId(Integer customerMasterId) {
        this.customerMasterId = customerMasterId;
    }

    @XmlTransient
    public Collection<CustomerPrice> getCustomerPriceCollection() {
        return customerPriceCollection;
    }

    public void setCustomerPriceCollection(Collection<CustomerPrice> customerPriceCollection) {
        this.customerPriceCollection = customerPriceCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (customerMasterId != null ? customerMasterId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CustomerMaster)) {
            return false;
        }
        CustomerMaster other = (CustomerMaster) object;
        if ((this.customerMasterId == null && other.customerMasterId != null) || (this.customerMasterId != null && !this.customerMasterId.equals(other.customerMasterId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "iot.dao.entity.CustomerMaster[ customerMasterId=" + customerMasterId + " ]";
    }
    
}
