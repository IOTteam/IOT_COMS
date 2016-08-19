/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iot.controller;

import iot.dao.entity.CustomerMaster;
import iot.dao.entity.OrderDetail;
import iot.dao.entity.OrderDetailInfo;
import iot.dao.entity.OrderInfo;
import iot.dao.entity.OrderMaster;
import iot.dao.repository.CustomerMasterDAO;
import iot.dao.repository.OrderMasterDAO;
import iot.service.OrderService;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 *
 * @author hatanococoro
 */
@Controller
@RequestMapping("/orderList")
@SessionAttributes("orderMasterId")
public class orderController {
    
    @Autowired
    private OrderService orderService;
    //查询订单头档，返回视图
    @RequestMapping(value = "queryList",method = RequestMethod.GET)
    public String getOrderList(ModelMap model){
        
        List<OrderInfo> orders = orderService.getOrderList();
        model.addAttribute("orderList", orders);
        return "orderList";
        
    }
    //查询订单详细信息
    @RequestMapping(value = "detailQuery",method = RequestMethod.GET)
    public String getDetail(@RequestParam("orderId") String orderId,ModelMap model){
        
        List<OrderDetailInfo> orders = orderService.getDetails(orderId);
        int orderMasterId = orderService.getOrderMasterId(orderId).getOrderMasterId();
        model.addAttribute("orderMasterId", orderMasterId);
        model.addAttribute("detailList", orders);
        return "orderDetail";
        
    }
    //跳转到新增订单头档页面
    @RequestMapping(value = "orderAdd",method = RequestMethod.GET)
    public String addOrderMasterPage(ModelMap model){
        
        model.addAttribute("count",orderService.getOrderCount().get("count"));
        model.addAttribute("date",orderService.getOrderCount().get("date"));
        return "orderAdd";
        
    }
    //新增订单头档
    @RequestMapping(value = "orderAdd",method = RequestMethod.POST)
    public String addOrderMaster(@RequestParam("orderId") String orderId,@RequestParam("orderDate") String orderDate,
            @RequestParam("customerId") String customerId,ModelMap model) throws Exception{
        
        orderService.addOrderMaster(orderId, orderDate, customerId);
        return "redirect:/orderList/queryList";
        
    }
    //跳转到信息订单身档页面
    @RequestMapping(value = "orderDetailAdd",method = RequestMethod.GET)
    public String addOrderDtailPage(@ModelAttribute("orderMasterId") int orderMasterId,ModelMap model){
        
        model.addAttribute("orderMId", orderMasterId);
        return "orderDetailAdd";
        
    }
    //新增订单详细信息
    @RequestMapping(value = "orderDetailAdd",method = RequestMethod.POST)
    public String addOrderDtail(@ModelAttribute("orderMasterId") int orderMasterId,@RequestParam("productId") String productId,
            @RequestParam("orderQty") String orderQty,@RequestParam("orderPrice") String orderPrice,ModelMap model) throws Exception{
        
        orderService.addOrderDtail(orderMasterId, productId, orderQty, orderPrice);
        
        model.addAttribute("orderId", orderMasterId);
        return "redirect:/orderList/detailQuery";
        
    }
    
}
