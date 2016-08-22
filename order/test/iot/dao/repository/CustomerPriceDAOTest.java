/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iot.dao.repository;

import iot.dao.entity.CustomerMaster;
import iot.dao.entity.CustomerPrice;
import iot.dao.entity.ProductMaster;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
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
public class CustomerPriceDAOTest {
    
    public CustomerPriceDAOTest() {
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
     * Test of findCustomerPriceByCustProdRange method, of class CustomerPriceDAO.
     */
    @Test
    public void testFindCustomerPriceByCustProdRange() {
        System.out.println("findCustomerPriceByCustProdRange");
//        CustomerMaster customerMasterId = null;
//        ProductMaster productMasterId = null;
//        int orderQty = 0;
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("orderPU");
        CustomerPriceDAO instance = new CustomerPriceDAO(emf);
        //CustomerPrice expResult = null;
        List<CustomerPrice> result = instance.findCustomerPriceEntities();
         System.out.println("单价" + result.get(0).getRangePrice());
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }


    
}
