/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iot.dao.entity;

/**
 *
 * @author hatanococoro
 */
public class CustomerPriceInfo {
    
    public String customerId;
    public String productId;
    public String preferentialMin;
    public String preferentialMax;
    public String preferentialCredit;

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getPreferentialMin() {
        return preferentialMin;
    }

    public void setPreferentialMin(String preferentialMin) {
        this.preferentialMin = preferentialMin;
    }

    public String getPreferentialMax() {
        return preferentialMax;
    }

    public void setPreferentialMax(String preferentialMax) {
        this.preferentialMax = preferentialMax;
    }

    public String getPreferentialCredit() {
        return preferentialCredit;
    }

    public void setPreferentialCredit(String preferentialCredit) {
        this.preferentialCredit = preferentialCredit;
    }
    
    
}
