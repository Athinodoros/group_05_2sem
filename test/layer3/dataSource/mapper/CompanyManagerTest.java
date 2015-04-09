/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package layer3.dataSource.mapper;

import java.sql.Connection;
import layer2.domain.bean.Company;
import layer3.dataSource.DBConnector;
import layer3.dataSource.DBType;
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
public class CompanyManagerTest {
    
    private static Connection conn;
    
    public CompanyManagerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        
        DBConnector.getInstance().setDBType(DBType.ORACLE_THIN);
        conn = DBConnector.getInstance().getConnection();
    }
    
    @AfterClass
    public static void tearDownClass() {
        
        DBConnector.getInstance().close();
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of insert method, of class CompanyManager.
     */
    @Test
    public void testInsert() {
        System.out.println("insert");
        
        Company bean = new Company("CompanyNameTest", 666);
        
        CompanyManager instance = new CompanyManager();
        
        boolean expResult = true;
        boolean result = instance.insert(conn, bean);
        assertEquals(expResult, result);

    } // End of method :: testInsert()
    
} // End of Class ::
