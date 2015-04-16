/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package layer3.dataSource.mapper;

import java.sql.Connection;
import java.util.*;
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
    
    CompanyManager classUnderTest = new CompanyManager();
    
    Random random = new Random();

    private final String COMPANY_NAME           = "CompanyNameTest_" + random.nextInt(100_000_000);
    private final int BUDGET                    = 600;

    private final Company company               = new Company(COMPANY_NAME, BUDGET);

    public CompanyManagerTest() {}

    
    
    @BeforeClass
    public static void setUpClass() {

        DBConnector.getInstance().setDBType(DBType.ORACLE_THIN_TEST_DATABASE);
        conn = DBConnector.getInstance().getConnection();
    }

    @AfterClass
    public static void tearDownClass() {

        DBConnector.getInstance().close();
    }

    @Before
    public void setUp() {
        
        classUnderTest.deleteAllRows(conn, "yes");
    }

    @After
    public void tearDown() {}

    
    
    
    /**
     * Test of insert method, of class CompanyManager.
     */
    @Test
    public void test_A_CompanyManager_Insert() {
        System.out.println("Testing :: CompanyManager.insert()");
        
        boolean result = classUnderTest.insert(conn, company);

        assertTrue("        :: Row not inserted", result);
    } // End of method :: testInsert()

    
    
    /**
     * Test of update method, of class CompanyManager.
     */
    @Test
    public void test_B_CompanyManager_Delete() {
        System.out.println("Testing :: CompanyManager.delete()");

        // Setting up test by inserting a row
        classUnderTest.insert(conn, company);
        
        boolean result = classUnderTest.delete(conn, company.getCompanyName());

        assertTrue("        :: Row not deleted", result);
    } // End of method :: testDelete()
    
    
    
    /**
     * Test of getRow method, of class CompanyManager.
     */
    @Test
    public void test_C_CompanyManager_GetRow() {
        System.out.println("Testing :: CompanyManager.getRow()");
        
        // Setting up test by inserting a row
        classUnderTest.insert(conn, company);
        
        Company result = classUnderTest.getRow(conn, company.getCompanyName());

        assertNotNull("        :: Retrieved data is not as expected", result);
        
    } // End of method :: testgetRow

    
    
    
     /**
     * Test of update method, of class CompanyManager.
     */
    @Test
    public void test_D_CompanyManager_Update() {
        System.out.println("Testing :: CompanyManager.Update()");

        // Setting up test by inserting a row
        classUnderTest.insert(conn, company);
        
        Company companyUpdated = new Company(company);
        companyUpdated.setBudget(BUDGET + 100);

        // update company data in the database
        boolean isUpdated   = classUnderTest.update(conn, companyUpdated);

        Company result = company;
        if(isUpdated) {
            // retrieve updated data from database
            result = classUnderTest.getRow(conn, companyUpdated.getCompanyName());
        } else {
            
            System.out.println("            :: Something went wrong when updating the database");
        }
        
        assertFalse("           :: Retrieved data was not updated",
                                company.getBudget() == result.getBudget());
       
    } // End of Method :: testUpdate()
    
    
    
    
    /**
     * Test of displayAllRows() method, of class CompanyManager.
     */
    @Test
    public void test_E_CompanyManager_getAllRows() {
        System.out.println("Testing :: CompanyManager.getAllRows()");
        
        // setting up test by creating two companies and inserting them in the database
        Company company1 = new Company(COMPANY_NAME + "_one", BUDGET);
        Company company2 = new Company(COMPANY_NAME + "_Two", BUDGET);
        classUnderTest.insert(conn, company1);
        classUnderTest.insert(conn, company2);

        // retrieve the two inserted companies from the database
        ArrayList<Company> rows = new ArrayList<>(classUnderTest.getAllRows(conn));
     
        int expResult   = 2;
        int result      = rows.size();

        assertTrue("        :: Retrieved data is not as expected", expResult == result);
        
    }
} // End of Class :: CompanyManagerTest
