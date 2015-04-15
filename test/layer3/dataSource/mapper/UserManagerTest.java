/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package layer3.dataSource.mapper;

import java.sql.Connection;
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
public class UserManagerTest {
    
    private static Connection conn;
    
    private Random random = new Random();
    
    UserManager             userInstance        = new UserManager();
    CompanyManager          companyInstance     = new CompanyManager();
    
    private final String    COMPANY_NAME        = "CompanyNameTest_"+ random.nextInt(100_000_000);
    private final int       BUDGET              = 600;
    
    private final int       USER_ID             = 0;
    private final String    USER_NAME           = "UserNameTest";           
    private final String    PASSWORD            = "PassWordTest";
    private final String    EMAIL               = "email@Test";
    private final String    COUNTRY             = "CountryTest";
    private final String    ROLE                = "RoleTest";
    private final Company   company             = new Company(COMPANY_NAME, BUDGET);
    
    private final int       USER_ID_UPDATED     = USER_ID;
    private final String    USER_NAME_UPDATED   = USER_NAME + "Updated";        
    private final String    PASSWORD_UPDATED    = PASSWORD  + "Updated";
    private final String    EMAIL_UPDATED       = EMAIL     + "Updated";
    private final String    COUNTRY_UPDATED     = COUNTRY   + "Updated";
    private final String    ROLE_UPDATED        = ROLE      + "Updated";
    private final Company   company_updated     = company;
    
    
    
//    private final User user;
//    private final User userUpdated;
    private final User user = new User(
                                                USER_ID, USER_NAME, PASSWORD,
                                                EMAIL, COUNTRY, ROLE, company);
    private final User userUpdated= new User(
                                                USER_ID_UPDATED, USER_NAME_UPDATED,
                                                PASSWORD_UPDATED, EMAIL_UPDATED,
                                                COUNTRY_UPDATED, ROLE_UPDATED,
                                                company_updated);
    
    boolean isCompanyInserted = false;
    
    public UserManagerTest() {} // End of constructor
    
    
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
    
        isCompanyInserted  = companyInstance.insert(conn, company);
        
        if( isCompanyInserted ) {
            System.out.println("Setup       :: Company created");
        }
        else {
            System.out.println("setup       :: ! Something whent wrong when creating a company");
        }
    } // End of method :: setup()
    
    @After
    public void tearDown() {
        
        if( isCompanyInserted ) {
            boolean isCompanyDeleted = companyInstance.delete(conn, company.getCompanyName());
            
            if( isCompanyDeleted ) {
                System.out.println("TearDown    :: Company deleted");
            }
            else {
                System.out.println("TearDown    :: ! Company was not deleted");
            }
        }
    } // End of method :: tearDown()

    
    
    // Tests starts here ->
    // -------------------------------------------------------------------------
    
    
    
    /**
     * Test of insert() method, of class UserManager.
     */
    @Test
    public void test_A_UserManager_Insert() {
        System.out.println("    Testing :: UserManager.insert()");
        
        boolean result = false;
        if( isCompanyInserted ) result = insertRow(user);      
        
        assertTrue("            :: Row not inserted", result);
        
        // Clean up
        deleteRow(user);
    } // End of method :: testInert()
    
    
    
    /**
     * Test of delete() method, of class UserManager.
     */
    @Test
    public void test_B_UserManager_Delete() {
        System.out.println("    Testing :: UserManager.delete()");

        // Seting up Test :: By inserting a user (Row)
        // ---------------------------------------------------------------------
        boolean isRowInserted = false;
        
        if( isCompanyInserted ) {
            
            isRowInserted = insertRow(user);
            if( isRowInserted ) {
                System.out.println("            :: A user (Row) is inserted");
            }
            else {
                System.out.println("            :: ! User (Row) was not inserted");
            }
        } 
        // ---------------------------------------------------------------------
        
        boolean result = deleteRow(user);
        assertTrue("            :: ! Row not deleted", result);
    } // End of method :: testDelete()
    
    
    /**
     * Test of getRow() method, of class UserManager.
     */
    @Test
    public void test_C_UserManager_GetRow() {
        System.out.println("    Testing :: UserManager.getRow()");
        
        // Seting up Test :: By inserting a user (Row)
        // ---------------------------------------------------------------------
        boolean isRowInserted = false;
        
        if( isCompanyInserted ) {
            
            isRowInserted = insertRow(user);
            if( isRowInserted ) {
                System.out.println("            :: A user (Row) is inserted");
            }
            else {
                System.out.println("            :: ! User (Row) was not inserted");
            }
        } 
        // ---------------------------------------------------------------------
        
        User expResult   = user;
        User result      = userInstance.getRow(conn, user.getUserID());
        
        assertTrue(
                "            :: ! Retrieved data is not as expected",
                expResult.toString().equals(result.toString()));
        
        // Cleaning up
        deleteRow(user);
    } // End of method :: testgetRow
    
    
     @Test
    public void test_D_UserManager_Update() {
        System.out.println("    Testing :: UserManager.Update()");

         // Seting up Test :: By inserting a user (Row)
        // ---------------------------------------------------------------------
        boolean isRowInserted = false;
        
        if( isCompanyInserted ) {
            
            isRowInserted = insertRow(user);
            if( isRowInserted ) {
                System.out.println("            :: A user (Row) is inserted");
            }
            else {
                System.out.println("            :: ! User (Row) was not inserted");
            }
        } 
        // ---------------------------------------------------------------------
        userUpdated.setUserID(user.getUserID());
        
        
        User expResult   = userUpdated;
        User result      = user;

        boolean isUpdated = userInstance.update(conn, userUpdated);

        if (isUpdated) {
            
            System.out.println("            :: Row updated ... now retrieving the same row from database ");
            result = userInstance.getRow(conn, userUpdated.getUserID());
        } else {
            
            System.err.println("            :: Something went wrong when updating the database");
        } // End of if-else()
        
        assertTrue(
                "            :: ! Retrieved data is not as expected",
                expResult.toString().equals(result.toString()));
        
        // cleaning up :: by deleting a row
        deleteRow(userUpdated);
    } // End of Method :: testUpdate()
    
    
    
    private boolean insertRow(User _user) {
        
        return userInstance.insert(conn, _user);
    }
    
    private boolean deleteRow(User _user) {
        
        return userInstance.delete(conn, _user.getUserID()); 
    }
    
} // End of class :: UserManagerTest
