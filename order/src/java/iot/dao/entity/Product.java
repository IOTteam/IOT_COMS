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
public class Product {


    private String productId;
    private String productName;
    private String productSpec;
    private double productPrice;
    private Integer productMasterId;

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
    
    
}
