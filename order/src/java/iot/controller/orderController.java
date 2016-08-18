/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iot.controller;

import iot.dao.entity.CustomerMaster;
import iot.dao.entity.OrderInfo;
import iot.dao.entity.OrderMaster;
import iot.dao.repository.CustomerMasterDAO;
import iot.dao.repository.OrderMasterDAO;
import iot.service.OrderService;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author hatanococoro
 */
@Controller
@RequestMapping("/orderList")
public class orderController {
    
    @Autowired
    private OrderService orderService;
    
    @RequestMapping(value = "queryList",method = RequestMethod.GET)
    public String getOrderList(ModelMap model){
        
        List<OrderInfo> orders = orderService.getOrderList();
        model.addAttribute("orderList", orders);
        return "orderList";
        
    }
}
