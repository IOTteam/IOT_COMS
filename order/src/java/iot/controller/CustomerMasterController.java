/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iot.controller;

import iot.dao.entity.CustomerMaster;
import iot.dao.repository.CustomerMasterJpaController;
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
 * @author GaryLiu
 */
@Controller
@RequestMapping("/CustInfo")
public class CustomerMasterController {
    
    @RequestMapping(method = RequestMethod.GET)//前端传来的请求类型为GET,若是该类型的请求有2个，则需要设置value值以区分
    public String customerQueryString(ModelMap modelMap){//modelmap用以存储controller执行相关操作的结果
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("orderPU");//创建一个factory，orderPU位置：persistence-unit name="orderPU"，名字唯一
        CustomerMasterJpaController controller = new CustomerMasterJpaController(emf);//CustomerMasterJpaController为通过实体类（customerMaster）创建的JPA控制器
        List<CustomerMaster> customerMasters = controller.findCustomerMasterEntities();//CustomerMasterJpaController自动生成的方法，查询所有的customerMaster，存在LIST中返回
        modelMap.addAttribute("cmList", customerMasters);//将结果返回至前端cmlist
        return "CustInfo";
    } 
    @RequestMapping(method = RequestMethod.POST)//前端传来的请求类型为POST,若是该类型的请求有2个，则需要设置value值以区分
    public String queryByidnameString(@RequestParam("customerId") String customerId,@RequestParam("customerName") String customerName,ModelMap modelMap){//RequestParam将前端输入的值作为函数的参数。最后的modelMap需要有
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("orderPU");//创建一个factory，orderPU位置：persistence-unit name="orderPU"，名字唯一
        CustomerMasterJpaController controller = new CustomerMasterJpaController(emf);//CustomerMasterJpaController为通过实体类（customerMaster）创建的JPA控制器
        List<CustomerMaster> customerMasters = controller.queryCustomerMasterByIdName(customerName, customerId);
        //自动生成的函数中有使用主键查询的函数，但此处需要按name、ID、name+ID3种方式查询。因此需要在CustomerMasterJpaController中实现queryCustomerMasterByIdName函数
        if (customerMasters.size()==0) {//size为0说明查无此人，应向前端反馈错误
            modelMap.addAttribute("cmqf","queryFailed");
        }
        modelMap.addAttribute("cmList", customerMasters);
        return "CustInfo";
    }
}
