/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package layer3.dataSource.mapper;

import java.sql.Connection;
import java.util.*;
import layer2.domain.bean.Reseller;
import layer3.dataSource.DBConnector;
import layer3.dataSource.DBType;
import layer3.dataSource.mapper.utility.Delete;
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
    
    CompanyManager companyManager = new CompanyManager();
    
    Random random = new Random();

    private final String COMPANY_NAME           = "CompanyNameTest_" + random.nextInt(100_000_000);
    private final int BUDGET                    = 600;

    private final Reseller company               = new Reseller(COMPANY_NAME, BUDGET);

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
        
        Delete.database(conn, "yes");
    }

    @After
    public void tearDown() {}

    
    
    
    /**
     * Test of insert method, of class CompanyManager.
     */
    @Test
    public void test_A_CompanyManager_Insert() {
        System.out.println("Testing :: CompanyManager.insert()");
        
        boolean result = companyManager.insert(conn, company);

        assertTrue(result);
    } // End of method :: testInsert()

    
    
    /**
     * Test of update method, of class CompanyManager.
     */
    @Test
    public void test_B_CompanyManager_Delete() {
        System.out.println("Testing :: CompanyManager.delete()");

        //  Making sure I first insert all the necessary rows,
        //  before I try to delete something
        boolean status = companyManager.insert(conn, company);
        
        boolean result = false;
        if( status ) {
            result = companyManager.delete(conn, company.getCompanyName());
        }
        
        assertTrue(result);
    } // End of method :: testDelete()
    
    
    
    /**
     * Test of getRow method, of class CompanyManager.
     */
    @Test
    public void test_C_CompanyManager_GetRow() {
        System.out.println("Testing :: CompanyManager.getRow()");
        
        //  Making sure I first insert all the necessary rows,
        //  before I try to get something out
        boolean status = companyManager.insert(conn, company);
        
        Reseller result = null;
        if( status ) {
            result = companyManager.getRow(conn, company.getCompanyName());
        }
        
        assertNotNull(result);
    } // End of method :: testgetRow

    
    
    
     /**
     * Test of update method, of class CompanyManager.
     */
    @Test
    public void test_D_CompanyManager_Update() {
        System.out.println("Testing :: CompanyManager.Update()");

        //  Making sure I first insert all the necessary rows,
        //  before I try to update something
        boolean status1 = companyManager.insert(conn, company);
        
        //  Create a new company with the same name as 'company',
        //  but with a new budget
        Reseller companyUpdated = new Reseller(company);
        companyUpdated.setBudget(BUDGET + 100);

        // Update company data in the database
        boolean status2   = companyManager.update(conn, companyUpdated);

        Reseller result = company;
        if( status1 & status2 ) {
            // retrieve updated data from database
            result = companyManager.getRow(conn, companyUpdated.getCompanyName());
        }
        
        assertFalse(company.getBudget() == result.getBudget());
    } // End of Method :: testUpdate()
    
    
    
    
    /**
     * Test of displayAllRows() method, of class CompanyManager.
     */
    @Test
    public void test_E_CompanyManager_getAllRows() {
        System.out.println("Testing :: CompanyManager.getAllRows()");
        
        // setting up test by creating two companies and inserting them in the database
        Reseller company1    = new Reseller(COMPANY_NAME + "_one", BUDGET);
        Reseller company2    = new Reseller(COMPANY_NAME + "_Two", BUDGET);
        boolean status1     = companyManager.insert(conn, company1);
        boolean status2     = companyManager.insert(conn, company2);

        ArrayList<Reseller> rows = new ArrayList<>();
        
        if( status1 & status2 ) {
            // retrieve the two inserted companies from the database
            rows = new ArrayList<>(companyManager.getAllRows(conn));
        }
        
        int expResult   = 2;
        int result      = rows.size();

        assertTrue(expResult == result);
    }
} // End of Class :: CompanyManagerTest
