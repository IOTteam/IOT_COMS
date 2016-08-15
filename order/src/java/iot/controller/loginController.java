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

/**
 *
 * @author hatanococoro
 */
@Controller
@RequestMapping("/login")
public class loginController {
    
    @RequestMapping(method = RequestMethod.GET)
    public String hello(ModelMap model){
        System.out.println("iot.controller.loginController.helloController()");
        model.addAttribute("message", "hello world!");
        return "login";
        
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public String sayHello(HttpServletRequest request,ModelMap model){

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        System.out.println(username);
        
        UserDaoImpl udi = new UserDaoImpl();
        User user = udi.getUserByname("admin");
        
            if (user.getPassword().equals("admin")) {
                        model.addAttribute("name", user.getUserId() + user.getUserName());
                        return "hello";
            }else{
             return "no";
            }
    }
    
}
