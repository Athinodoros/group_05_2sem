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
public class UserManagerTest {
    
    private static Connection conn;
    
    private Random random = new Random();
    
    UserManager             userManager         = new UserManager();
    CompanyManager          companyManager      = new CompanyManager();
    
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
    
        Delete.database(conn, "yes");   
    }
    
    @After
    public void tearDown() {}

    
    
    /**
     * Test of insert() method, of class UserManager.
     */
    @Test
    public void test_A_UserManager_Insert() {
        System.out.println("Testing :: UserManager.insert()");
        
        //  Making sure I insert all the necessary rows in the other tables first
        boolean status1  = companyManager.insert(conn, company);
        
        boolean result = false;
        if(status1) {
            result = userManager.insert(conn, user);
        }
        
        assertTrue(result);
    } // End of method :: testInert()
    
    
    
    /**
     * Test of delete() method, of class UserManager.
     */
    @Test
    public void test_B_UserManager_Delete() {
        System.out.println("Testing :: UserManager.delete()");

        //  Making sure I first insert all the necessary rows,
        //  before I try to delete something
        boolean status1 = companyManager.insert(conn, company);
        boolean status2 = userManager.insert(conn, user);
        
        boolean result = false;
        if(status1 & status2) {
            result = userManager.delete(conn, user.getUserID());
        }
        
        assertTrue(result);
    } // End of method :: testDelete()
    
    
    /**
     * Test of getRow() method, of class UserManager.
     */
    @Test
    public void test_C_UserManager_GetRow() {
        System.out.println("Testing :: UserManager.getRow()");
        
        //  Making sure I first insert all the necessary rows,
        //  before I try to get something out
        boolean status1 = companyManager.insert(conn, company);
        boolean status2 = userManager.insert(conn, user);
        
        User result = null;
        if( status1 & status2 ) {
            result = userManager.getRow(conn, user.getUserID());
        }
        
        assertNotNull(result);
    } // End of method :: testgetRow
    
    
    
    /**
     * Test of update() method, of class UserManager.
     */
    @Test
    public void test_D_UserManager_Update() {
        System.out.println("Testing :: UserManager.Update()");

        //  Making sure I first insert all the necessary rows,
        //  before I try to update something
        boolean status1 = companyManager.insert(conn, company);
        boolean status2 = userManager.insert(conn, user);
        
        //  Create a new user with the same userid as 'user',
        //  but with a new user name
        User userUpdated = new User(user);
        userUpdated.setName(USER_NAME + "_Update");
        
        // Update the new user's data in the database
        boolean status3   = userManager.update(conn, userUpdated);
        
        User result = user;
        if( status1 & status2 & status3 ) {
           result = userManager.getRow(conn, userUpdated.getUserID());
        }
        
        assertFalse( user.getName().equals(result.getName()) );
    } // End of Method :: testUpdate()
    
    
    
    /**
     * Test of displayAllRows() method, of class CompanyManager.
     */
    @Test
    public void test_E_CompanyManager_getAllRows() {
        System.out.println("Testing :: CompanyManager.getAllRows()");
        
        //  Making sure I first insert all the necessary rows,
        //  before I try to get something out
        boolean status1 = companyManager.insert(conn, company);
        
        User user1      = new User(user);
        User user2      = new User(user);
        boolean status2 = userManager.insert(conn, user1);
        boolean status3 = userManager.insert(conn, user2);

        ArrayList<Company> rows = new ArrayList();
        
        if( status1 & status2 & status3 ) {
            //  Retrieve the two inserted companies from the database
            rows = new ArrayList<>( userManager.getAllRows(conn) );
        }
        
        int expResult   = 2;
        int result      = rows.size();

        assertTrue(expResult == result);
    }
} // End of class :: UserManagerTest
