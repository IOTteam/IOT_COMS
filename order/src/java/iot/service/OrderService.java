/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iot.service;

import iot.dao.entity.CustomerMaster;
import iot.dao.entity.OrderInfo;
import iot.dao.entity.OrderMaster;
import iot.dao.repository.CustomerMasterDAO;
import iot.dao.repository.OrderMasterDAO;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManagerFactory;
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
}
