/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iot.service;

import iot.dao.entity.CustomerMaster;
import iot.dao.entity.OrderDetail;
import iot.dao.entity.OrderDetailInfo;
import iot.dao.entity.OrderInfo;
import iot.dao.entity.OrderMaster;
import iot.dao.repository.CustomerMasterDAO;
import iot.dao.repository.OrderDetailDAO;
import iot.dao.repository.OrderMasterDAO;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import org.eclipse.persistence.sdo.SDOConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

/**
 *
 * @author hatanococoro
 */

@Service
public class OrderService {
    
    @Autowired
    private EntityManagerFactory emf;
    
    public List<OrderInfo> getOrderList(){
        
        OrderMasterDAO omdao = new OrderMasterDAO(emf);
        List<OrderMaster> orders = omdao.findOrderMasterEntities();
        
        CustomerMasterDAO cmdao = new CustomerMasterDAO(emf);
        List<OrderInfo> ordersNew = new ArrayList<OrderInfo>();
        for(int i=0;i<orders.size();i++){
    
        CustomerMaster cm = cmdao.findCustomerMasterById(orders.get(i).getCustomerId());
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setCustomerId(orders.get(i).getCustomerId());
        orderInfo.setCustomerName(cm.getCustomerName());
        orderInfo.setOrderDate(orders.get(i).getOrderDate());
        orderInfo.setOrderId(orders.get(i).getOrderId());

        ordersNew.add(i,orderInfo);
    }
        return ordersNew;
    }
    
    public List<OrderDetailInfo> getDetails(String orderId){
        
        OrderDetailDAO oddao = new OrderDetailDAO(emf);
        OrderMaster orderMasterId = getOrderMasterId(orderId);
        List<OrderDetail> orderDetails = oddao.findOrderDetailByOrderMId(orderMasterId);
        
        List<OrderDetailInfo> orderDetailInfos = new ArrayList<OrderDetailInfo>();
        for(int i = 0;i < orderDetails.size();i++){
        
            OrderDetailInfo odi = new OrderDetailInfo();
            odi.setOrderMasterId_int(orderDetails.get(i).getOrderMasterId().getOrderMasterId());
            odi.setProductName(orderDetails.get(i).getProductId().getProductName());
            odi.setOrderPrice(orderDetails.get(i).getOrderPrice());
            odi.setOrderQty(orderDetails.get(i).getOrderQty());
            
            orderDetailInfos.add(odi);
        }

        return orderDetailInfos;
    }
    
    public OrderMaster getOrderMasterId(String orderId){
    
        OrderMasterDAO omdao = new OrderMasterDAO(emf);
        OrderMaster om = omdao.findOrderMasterByOrderId(orderId);
        
        return om;
    }
}
