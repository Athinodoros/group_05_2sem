/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package layer3.dataSource.mapper;

import java.sql.Connection;
import java.util.Random;
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
//  from ::jUnit version 4.11
//  Furure updates :: allows for fix order execution of test methods in ascending order
//  @FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CompanyManagerTest {

    private static Connection conn;
    
    CompanyManager instance = new CompanyManager();
    
    Random random = new Random();

    private final String COMPANY_NAME           = "CompanyNameTest_" + random.nextInt(100_000_000);
    private final int BUDGET                    = 600;
    
    private final String COMPANY_NAME_UPDATED   = COMPANY_NAME;
    private final int BUDGET_UPDATED            = 700;

    private final Company company               = new Company(COMPANY_NAME, BUDGET);
    private final Company companyUpdated        = new Company(COMPANY_NAME_UPDATED, BUDGET_UPDATED);

    public CompanyManagerTest() {}

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
    public void test_A_Insert() {
        System.out.println("Testing :: CompanyManager.insert()");
        
        boolean expResult   = true;
        boolean result      = insertRow();

        assertEquals("        :: Row not inserted", expResult, result);
        
        // cleaning up :: by deleting a row
        deleteRow();
    } // End of method :: testInsert()

    /**
     * Test of getRow method, of class CompanyManager.
     */
    @Test
    public void test_B_GetRow() {
        System.out.println("Testing :: CompanyManager.getRow()");
        
        // Seting up :: by inserting a row
        insertRow();
        
        Company expResult   = company;
        Company result      = instance.getRow(conn, company.getCompanyName());

        assertTrue(
                "        :: Retrieved data is not as expected",
                expResult.toString().equals(result.toString()));
        
        // cleaning up :: by deleting a row
        deleteRow();
    } // End of method :: testgetRow

    /**
     * Test of update method, of class CompanyManager.
     */
    @Test
    public void test_C_Update() {
        System.out.println("Testing :: CompanyManager.Update()");

        // Seting up :: by inserting a row
        insertRow();
        
        Company expResult   = companyUpdated;
        Company result      = company;

        boolean isUpdated   = instance.update(conn, companyUpdated);

        if (isUpdated) {
            
            System.out.println("        :: Row updated ... now retrieving the same row from database ");
            result = instance.getRow(conn, companyUpdated.getCompanyName());
        } else {
            
            System.err.println("Something went wrong when updating the database");
        } // End of if-else()
        
        assertTrue(
                "        :: Retrieved data is not as expected",
                expResult.toString().equals(result.toString()));
        
        // cleaning up :: by deleting a row
        deleteRow();
    } // End of Method :: testUpdate()

    /**
     * Test of update method, of class CompanyManager.
     */
    @Test
    public void test_D_Delete() {
        System.out.println("Testing :: CompanyManager.delete()");

        // Seting up :: by inserting a row
        insertRow();
        
        boolean result = deleteRow();

        assertTrue("        :: Row not deleted", result);
    } // End of method :: testDelete()
    
    
    
    private boolean insertRow() {
        
        return instance.insert(conn, company);
    }
    
    private boolean deleteRow() {
        
        return instance.delete(conn, company.getCompanyName());
    }
    
} // End of Class :: CompanyManagerTest
