/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iot.controller;

import iot.dao.entity.CustomerMaster;
import iot.dao.repository.CustomerMasterDAO;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
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
@RequestMapping("index")
public class indexPageController{
    
    @RequestMapping(method = RequestMethod.GET)
    public String indexPageController(@RequestParam("user") String user,ModelMap model){
        
        model.addAttribute("user", user);
        return "index";
        
    }
        
}
