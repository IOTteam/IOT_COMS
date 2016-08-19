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
import iot.dao.entity.ProductMaster;
import iot.dao.repository.CustomerMasterDAO;
import iot.dao.repository.OrderDetailDAO;
import iot.dao.repository.OrderMasterDAO;
import iot.dao.repository.ProductMasterDAO;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author hatanococoro
 */

@Service
public class OrderService {
    
    @Autowired
    private EntityManagerFactory emf;
    //查询全部订单头档
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
    //通过订单ID获取订单详细信息，返回一个LIST
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
    //通过订单ID（orderId）获取订单实体
    public OrderMaster getOrderMasterId(String orderId){
    
        OrderMasterDAO omdao = new OrderMasterDAO(emf);
        OrderMaster om = omdao.findOrderMasterByOrderId(orderId);
        
        return om;
    }
    //获取订单头档数量及当前时间
    public HashMap getOrderCount(){
    
        OrderMasterDAO omdao = new OrderMasterDAO(emf);
        int count = omdao.getOrderMasterCount() + 2;
        
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = sdf.format(d);
        
        HashMap<String,Object> hm = new HashMap<String, Object>();
        hm.put("date", date);
        hm.put("count", count);
        
        return hm;
    }
    //添加订单头档
    public void addOrderMaster(String orderId,String orderDate,String customerId) throws Exception{
    
        OrderMasterDAO omdao = new OrderMasterDAO(emf);
        
        OrderMaster om = new  OrderMaster();
        
        Date d = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        d = sdf.parse(orderDate);
        
        om.setCustomerId(customerId);
        om.setOrderId(orderId);
        om.setOrderDate(d);
        om.setOrderMasterId(Integer.parseInt(orderId));
        
        omdao.create(om);

    }
    //添加订单详细信息
    public void addOrderDtail(int orderMasterId,String productId,String orderQty,String orderPrice) throws Exception{
        
            OrderMasterDAO omdao = new OrderMasterDAO(emf);
            OrderMaster omId = omdao.findOrderMaster(orderMasterId);
            
            ProductMasterDAO pmdao = new ProductMasterDAO(emf);
            ProductMaster pmId = pmdao.findProductMasterByproductId(productId);
            
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrderMasterId(omId);
            orderDetail.setProductId(pmId);
            orderDetail.setOrderQty(Integer.parseInt(orderQty));
            orderDetail.setOrderPrice(Float.parseFloat(orderPrice));
            
            OrderDetailDAO oddao = new OrderDetailDAO(emf);
            oddao.create(orderDetail);
        }
}
