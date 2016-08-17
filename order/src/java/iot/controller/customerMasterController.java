/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iot.controller;

import iot.dao.entity.CustomerMaster;
import iot.dao.entity.User;
import iot.dao.repository.CustomerMasterDAO;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author hatanococoro
 */
@Controller
@RequestMapping("/CustInfo")
public class customerMasterController {
    
    @RequestMapping(method = RequestMethod.GET)
    public String customerQueryAll(ModelMap model){
         
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("orderPU");
        CustomerMasterDAO cmdao = new CustomerMasterDAO(emf);
        //CustomerMaster cm = cmdao.findCustomerMaster(1);
        List<CustomerMaster> cms = cmdao.findCustomerMasterEntities();
        model.addAttribute("cmList", cms);
        return "CustInfo";
        
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public String customerQuery(@RequestParam("customerId") String customerId,@RequestParam("customerName") String customerName,ModelMap model){
         
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("orderPU");
        CustomerMasterDAO cmdao = new CustomerMasterDAO(emf);
        List<CustomerMaster> cms;
        if (customerId.length() > 0 && customerName.length() == 0) {
            cms = cmdao.findCustomerMasterById(customerId);
        }else if(customerId.length() == 0 && customerName.length() > 0){
            cms = cmdao.findCustomerMasterByName(customerName);
        }else{
            cms = cmdao.findCustomerMasterByNameId(customerId, customerName);
        }
        
        model.addAttribute("cmList", cms);
        return "CustInfo";
    }
    
    @RequestMapping(value = "add",method = RequestMethod.POST)
    public String customerAddPage(ModelMap model){
         
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("orderPU");
        CustomerMasterDAO cmdao = new CustomerMasterDAO(emf);
        int count = cmdao.getCustomerMasterCount();

        model.addAttribute("count", count+1);
        return "custAdd";
        
    }
    
    @RequestMapping(value = "custAdd",method = RequestMethod.POST)
    public String customerAdd(@RequestParam("customerId") String customerId,@RequestParam("customerName") String customerName,
            @RequestParam("customerMail") String customerMail,@RequestParam("customerPhone") String customerPhone,ModelMap model) throws Exception{
         
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("orderPU");
        CustomerMasterDAO cmdao = new CustomerMasterDAO(emf);
        CustomerMaster cmNew = new CustomerMaster();
        cmNew.setCustomerId(customerId);
        cmNew.setCustomerMail(customerMail);
        cmNew.setCustomerName(customerName);
        cmNew.setCustomerPhone(customerPhone);
        cmNew.setCustomerMasterId(Integer.parseInt(customerId));
        cmdao.create(cmNew);

        List<CustomerMaster> cms = cmdao.findCustomerMasterById(customerId);
        model.addAttribute("cmsList", cms);
        return "CustInfo";
        
    }
}
