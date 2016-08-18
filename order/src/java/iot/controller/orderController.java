/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iot.controller;

import iot.dao.entity.OrderMaster;
import iot.dao.repository.OrderMasterDAO;
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
    private EntityManagerFactory emf;
    
    
    
    @RequestMapping(value = "queryList",method = RequestMethod.GET)
    public String getOrderList(ModelMap model){
        
        OrderMasterDAO omdao = new OrderMasterDAO(emf);
        List<OrderMaster> orders = omdao.findOrderMasterEntities();
        model.addAttribute("orderList", orders);
        return "orderList";
        
    }
}
