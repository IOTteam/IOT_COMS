/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iot.controller;

import iot.dao.entity.User;
import iot.dao.repository.UserDaoImpl;
import javax.servlet.http.HttpServletRequest;
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
@RequestMapping("/login")
public class loginController {
    
    @RequestMapping(method = RequestMethod.GET)
    public String loginPage(ModelMap model){
        
        return "login";
        
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public String loginAction(@RequestParam("username") String username,@RequestParam("password") String password,ModelMap model){
        
        UserDaoImpl udi = new UserDaoImpl();
        User user = udi.getUserByname(username,password);
        
        if (user.getPassword().equals("")&&user.getUserName().equals("")){
            return "no";
        }else{
            model.addAttribute("user", user.getUserName());
            return "redirect:index";
        }
    }
    
    
}
