/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package layer3.dataSource.mapper;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Random;
import layer2.domain.bean.Company;
import layer2.domain.bean.User;
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
public class UserManagerTest {
    
    private static Connection conn;
    
    private Random random = new Random();
    
    UserManager             classUnderTest      = new UserManager();
    CompanyManager          companyInstance     = new CompanyManager();
    
    private final String    COMPANY_NAME        = "CompanyNameTest_"+ random.nextInt(100_000_000);
    private final int       BUDGET              = 600;
    
    private final int       USER_ID             = 0; // this value will be set in the call to insetRow()
    private final String    USER_NAME           = "UserNameTest";           
    private final String    PASSWORD            = "PassWordTest";
    private final String    EMAIL               = "email@Test";
    private final String    COUNTRY             = "CountryTest";
    private final String    ROLE                = "RoleTest";
    private final Company   company             = new Company(COMPANY_NAME, BUDGET);
    
    
    private final User user = new User(
                                                USER_ID, USER_NAME, PASSWORD,
                                                EMAIL, COUNTRY, ROLE, company);

    
    public UserManagerTest() {}
    
    
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
    
        // Delete all data in the database
        classUnderTest.deleteAllRows(conn, "yes");
        boolean isCompanyDeleted = companyInstance.deleteAllRows(conn, "yes");
        
        // Insert a company (row) in database
        boolean isCompanyInserted  = companyInstance.insert(conn, company);
        
        if( ! (isCompanyDeleted & isCompanyInserted ) ) {
       
            System.out.println("Test setup   :: ! Something whent wrong");
        }
    } // End of method :: setup()
    
    @After
    public void tearDown() {}

    
    
    /**
     * Test of insert() method, of class UserManager.
     */
    @Test
    public void test_A_UserManager_Insert() {
        System.out.println("    Testing :: UserManager.insert()");
        
        boolean result = classUnderTest.insert(conn, user);

        assertTrue("        :: Row not inserted", result);
    } // End of method :: testInert()
    
    
    
    /**
     * Test of delete() method, of class UserManager.
     */
    @Test
    public void test_B_UserManager_Delete() {
        System.out.println("    Testing :: UserManager.delete()");

        // Setting up Test by inserting a user (Row)  
        boolean isRowInserted = classUnderTest.insert(conn, user);
        if( ! isRowInserted ) {
            System.out.println("            :: ! User (Row) was not inserted");
        }
        
        // Delete user (row)
        boolean result = classUnderTest.delete(conn, user.getUserID());

        assertTrue("        :: Row not deleted", result);
        
    } // End of method :: testDelete()
    
    
    /**
     * Test of getRow() method, of class UserManager.
     */
    @Test
    public void test_C_UserManager_GetRow() {
        System.out.println("    Testing :: UserManager.getRow()");
        
        // Setting up Test by inserting a user (Row)  
        boolean isRowInserted = classUnderTest.insert(conn, user);
        if( ! isRowInserted ) {
            System.out.println("            :: ! User (Row) was not inserted");
        }
        // Get user (row)
        User result = classUnderTest.getRow(conn, user.getUserID());

        assertNotNull("        :: Retrieved data is not as expected", result);
       
    } // End of method :: testgetRow
    
    
    
    /**
     * Test of update() method, of class UserManager.
     */
    @Test
    public void test_D_UserManager_Update() {
        System.out.println("    Testing :: UserManager.Update()");

        // Setting up Test by inserting a user (Row)  
        boolean isRowInserted = classUnderTest.insert(conn, user);
        if( ! isRowInserted ) {
            System.out.println("            :: ! User (Row) was not inserted");
        }
        
        // Create new usser and update user name
        User userUpdated = new User(user);
        userUpdated.setName(USER_NAME + "_Update");

        // Update User data in the database
        boolean isUpdated   = classUnderTest.update(conn, userUpdated);

        User result = user;
        if(isUpdated) {
            // retrieve updated data from database
            result = classUnderTest.getRow(conn, userUpdated.getUserID());
        } else {
            
            System.out.println("            :: Something went wrong when updating the database");
        }
        
        assertFalse("           :: Retrieved data was not updated",
                                user.getName().equals(result.getName()));
       
    } // End of Method :: testUpdate()
    
    
    
    /**
     * Test of displayAllRows() method, of class CompanyManager.
     */
    @Test
    public void test_E_CompanyManager_getAllRows() {
        System.out.println("Testing :: CompanyManager.getAllRows()");
        
        // setting up test by creating two companies and inserting them in the database
        User user1 = new User(user);
        User user2 = new User(user);
        classUnderTest.insert(conn, user1);
        classUnderTest.insert(conn, user2);

        // retrieve the two inserted companies from the database
        ArrayList<Company> rows = new ArrayList<>(classUnderTest.getAllRows(conn));
     
        int expResult   = 2;
        int result      = rows.size();

        assertTrue("        :: Retrieved data is not as expected", expResult == result);
        
    }
} // End of class :: UserManagerTest
