/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package layer3.dataSource;

import java.sql.Connection;
import java.sql.SQLException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


/**
 *
 * @author bo
 */
public class DBConnectorTest {
    
    private static StringBuffer output = new StringBuffer();
    
    
    public DBConnectorTest() {
    }
    
    
    @AfterClass
    public static void tearDownClass() {
        System.out.println(output.toString());
        DBConnector.getInstance().close();
    }
    
    @Before
    public void setUp() {
        output.append("\n");
        
    }
    
    @After
    public void tearDown() {
        output.append("\n");
    }

    /**
     * Test of getInstance method, of class DBConnector.
     */
    @Test
    public void testGetInstance() {
        
        output.append("----------- Test GetInstance -----------------\n\n");
        
        DBConnector result1 = DBConnector.getInstance();
        output.append("Test 1. Can there be established a late instantiation of object DBConnector\n");
        assertNotNull(result1);
        
        DBConnector result2 = DBConnector.getInstance();
        output.append("Test 2. Do singleton object DBConnector work\n");
        assertSame(result1, result2);
        
     
    } // End of test testGetInstance()

   
    
    /**
     * Test of getConnection method, of class DBConnector.
     */
    @Test
    public void testGetConnection() {
        output.append("---------- getConnection -----------\n\n");
        
        DBConnector.getInstance().setDBType(DBType.ORACLE_THIN);
        
        output.append("Test 1. Get a connection to the data base\n");
        Connection result1 = DBConnector.getInstance().getConnection();
        assertNotNull(result1);
        
        output.append("Test 2. Get the samme connection to the data base\n");
        Connection result2 = DBConnector.getInstance().getConnection();
        assertSame(result1, result2);
       
    } // End of method :: testGetConnection()

   

    /**
     * Test of close method, of class DBConnector.
     */
    @Test
    public void testClose() {
        output.append("------------- close Connection --------------\n\n");
        
        Connection oldConnection = DBConnector.getInstance().getConnection();
        output.append("closing the connection\n");
        DBConnector.getInstance().close();
        output.append("initiates a new connection\n");
        Connection newConnection = DBConnector.getInstance().getConnection();
        
        output.append("Test 1. See if the old and the new connections are the same\n");
        assertNotSame(newConnection, oldConnection);
    } // End of method :: testClose()

    
    
} // End of Class :: DBConnectorTest










///**
// *
// * @author bo
// */
//public class DBConnectorTest {
//    
//    public DBConnectorTest() {
//    }
//    
//    @BeforeClass
//    public static void setUpClass() {
//    }
//    
//    @AfterClass
//    public static void tearDownClass() {
//    }
//    
//    @Before
//    public void setUp() {
//    }
//    
//    @After
//    public void tearDown() {
//    }
//
//    /**
//     * Test of getInstance method, of class DBConnector.
//     */
//    @Test
//    public void testGetInstance() {
//        System.out.println("getInstance");
//        DBConnector expResult = null;
//        DBConnector result = DBConnector.getInstance();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getConnection method, of class DBConnector.
//     */
//    @Test
//    public void testGetConnection() {
//        System.out.println("getConnection");
//        DBConnector instance = null;
//        Connection expResult = null;
//        Connection result = instance.getConnection();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setDBType method, of class DBConnector.
//     */
//    @Test
//    public void testSetDBType() {
//        System.out.println("setDBType");
//        DBType dbType = null;
//        DBConnector instance = null;
//        instance.setDBType(dbType);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of close method, of class DBConnector.
//     */
//    @Test
//    public void testClose() {
//        System.out.println("close");
//        DBConnector instance = null;
//        instance.close();
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of processException method, of class DBConnector.
//     */
//    @Test
//    public void testProcessException() {
//        System.out.println("processException");
//        SQLException e = null;
//        DBConnector.processException(e);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//    
//}
