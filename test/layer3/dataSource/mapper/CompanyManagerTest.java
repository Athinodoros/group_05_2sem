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
    
    CompanyManager instance = new CompanyManager();

    private final String COMPANY_NAME           = "CompanyNameTest";
    private final String COMPANY_NAME_UPDATED   = COMPANY_NAME;

    private final int BUDGET            = 600;
    private final int BUDGET_UPDATED    = 700;

    private final Company company           = new Company(COMPANY_NAME, BUDGET);
    private final Company companyUpdated    = new Company(COMPANY_NAME_UPDATED, BUDGET_UPDATED);

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
        System.out.println("Testing :: CompanyManager.insert()");

        boolean expResult   = true;
        boolean result      = instance.insert(conn, company);

        assertEquals("        :: Row not inserted", expResult, result);
    } // End of method :: testInsert()

    /**
     * Test of getRow method, of class CompanyManager.
     */
    @Test
    public void testGetRow() {
        System.out.println("Testing :: CompanyManager.getRow()");

        Company expResult   = company;
        Company result      = instance.getRow(conn, company.getCompanyName());

        assertTrue(
                "        :: Retrieved data is not as expected",
                expResult.toString().equals(result.toString()));
    } // End of method :: testgetRow

    /**
     * Test of update method, of class CompanyManager.
     */
    @Test
    public void testUpdate() {
        System.out.println("Testing :: CompanyManager.Update()");

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
    } // End of Method :: testUpdate()

    /**
     * Test of update method, of class CompanyManager.
     */
    @Test
    public void testDelete() {
        System.out.println("Testing :: CompanyManager.delete()");

        boolean result = instance.delete(conn, companyUpdated.getCompanyName());

        assertTrue("        :: Row not deleted", result);

    } // End of method :: testDelete()
} // End of Class :: CompanyManagerTest
