/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iot.dao.repository;

import iot.dao.entity.User;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author hatanococoro
 */
public class UserDaoImplTest {
    
    public UserDaoImplTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getAllUser method, of class UserDaoImpl.
     */
//    @Test
//    public void testGetAllUser() {

//        UserDaoImpl instance = new UserDaoImpl();
//        List<User> result = instance.getAllUser();
//    }
    
    @Test
    public void testGetUser() {
       
         UserDaoImpl instance = new UserDaoImpl();
         String username = "admin";
         String password = "admin";
         User result = instance.getUserByname(username, password);
         
         System.out.println(result.getUserName());
        
    }
    
//     @Test
//    public void testAddUser() {
//        System.out.println("testAddUser");
//        UserDaoImpl instance = new UserDaoImpl();
//        
//        User u = new User();        
//      u.setLoginId(44);
//       u.setUserId("0070");
//        u.setPassword("00000");
//        u.setUserName("帕奇");
//       
//        instance.addUser(u);
//
//    }
    
//     @Test
//    public void testDelUser() {
//        System.out.println("testDelUser");
//        UserDaoImpl instance = new UserDaoImpl();
//        
//        instance.delUser(9);
//
//    }
}
