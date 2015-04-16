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
    
    
    public DBConnectorTest() {}
    
    
    @AfterClass
    public static void tearDownClass() {
        
        DBConnector.getInstance().close();
    }
    
    @Before
    public void setUp() {}
    
    @After
    public void tearDown() {}

    /**
     * Test of getInstance method, of class DBConnector.
     */
    @Test
    public void test_A_DBConnector_GetInstance() {
        System.out.println("Testing :: DBConnector.getInstance()");
        
        //  Get a Instance of DBConnector
        DBConnector result1 = DBConnector.getInstance();
        assertNotNull(result1);
        
        //  Do singleton object DBConnector work?
        DBConnector result2 = DBConnector.getInstance();
        assertSame(result1, result2);
    } // End of test testGetInstance()

   
    
    /**
     * Test of getConnection method, of class DBConnector.
     */
    @Test
    public void test_B_DBConnector_GetConnection() {
        System.out.println("Testing :: DBConnector.getConnection()");
        
        DBConnector.getInstance().setDBType(DBType.ORACLE_THIN);
        
        //  Get a connection to the data base
        Connection result1 = DBConnector.getInstance().getConnection();
        assertNotNull(result1);
        
        //  Get the samme connection to the data base
        Connection result2 = DBConnector.getInstance().getConnection();
        assertSame(result1, result2);
       
    } // End of method :: testGetConnection()

   

    /**
     * Test of close method, of class DBConnector.
     */
    @Test
    public void test_C_DBConnector_Close() {
        System.out.println("Testing :: DBConnector.close()");
        
        //  Get a connection to the data base
        Connection oldConnection = DBConnector.getInstance().getConnection();
        //  Closing the connection
        DBConnector.getInstance().close();
        //  Initiates a new connection
        Connection newConnection = DBConnector.getInstance().getConnection();
        
        //  See if the old and the new connections are the same
        assertNotSame(newConnection, oldConnection);
    } // End of method :: testClose()
} // End of Class :: DBConnectorTest