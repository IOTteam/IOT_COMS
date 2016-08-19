/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iot.dao.repository;

import iot.dao.entity.ProductMaster;
import java.util.List;
import javax.persistence.EntityManager;
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
public class ProductMasterJpaControllerTest {
    
    public ProductMasterJpaControllerTest() {
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
     * Test of getEntityManager method, of class ProductMasterDAO.
     */
    @Test
    public void testGetEntityManager() {
        System.out.println("getEntityManager");
        ProductMasterDAO instance = null;
        EntityManager expResult = null;
        EntityManager result = instance.getEntityManager();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of create method, of class ProductMasterDAO.
     */
    @Test
    public void testCreate() throws Exception {
        System.out.println("create");
        ProductMaster productMaster = null;
        ProductMasterDAO instance = null;
        instance.create(productMaster);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of edit method, of class ProductMasterDAO.
     */
    @Test
    public void testEdit() throws Exception {
        System.out.println("edit");
        ProductMaster productMaster = null;
        ProductMasterDAO instance = null;
        instance.edit(productMaster);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of destroy method, of class ProductMasterDAO.
     */
    @Test
    public void testDestroy() throws Exception {
        System.out.println("destroy");
        Integer id = null;
        ProductMasterDAO instance = null;
        instance.destroy(id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findProductMasterEntities method, of class ProductMasterDAO.
     */
    @Test
    public void testFindProductMasterEntities_0args() {
        System.out.println("findProductMasterEntities");
        ProductMasterDAO instance = null;
        List<ProductMaster> expResult = null;
        List<ProductMaster> result = instance.findProductMasterEntities();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findProductMasterEntities method, of class ProductMasterDAO.
     */
    @Test
    public void testFindProductMasterEntities_int_int() {
        System.out.println("findProductMasterEntities");
        int maxResults = 0;
        int firstResult = 0;
        ProductMasterDAO instance = null;
        List<ProductMaster> expResult = null;
        List<ProductMaster> result = instance.findProductMasterEntities(maxResults, firstResult);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findProductMaster method, of class ProductMasterDAO.
     */
    @Test
    public void testFindProductMaster() {
        System.out.println("findProductMaster");
        Integer id = null;
        ProductMasterDAO instance = null;
        ProductMaster expResult = null;
        ProductMaster result = instance.findProductMaster(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getProductMasterCount method, of class ProductMasterDAO.
     */
    @Test
    public void testGetProductMasterCount() {
        System.out.println("getProductMasterCount");
        ProductMasterDAO instance = null;
        int expResult = 0;
        int result = instance.getProductMasterCount();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
