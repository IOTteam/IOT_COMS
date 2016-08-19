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
public class OrderDetailInfo extends OrderDetail{
    
        private int productId_int;
        private int orderMasterId_int;
        private String productName;

    public int getProductId_int() {
        return productId_int;
    }

    public void setProductId_int(int productId_int) {
        this.productId_int = productId_int;
    }

    public int getOrderMasterId_int() {
        return orderMasterId_int;
    }

    public void setOrderMasterId_int(int orderMasterId_int) {
        this.orderMasterId_int = orderMasterId_int;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
        
        
        
}
