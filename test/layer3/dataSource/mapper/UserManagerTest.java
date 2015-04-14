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
    
    UserManager     userInstance        = new UserManager();
    CompanyManager  companyInstance     = new CompanyManager();
    
    private final String    COMPANY_NAME                = "CompanyNameTest_"+ random.nextInt(100_000_000);
    private final int       BUDGET                      = 600;
    
    private final int       USER_ID                     = 0;
    private final String    USER_NAME                   = "UserNameTest";           
    private final String    PASSWORD                    = "PassWordTest";
    private final String    EMAIL                       = "email@Test";
    private final String    COUNTRY                     = "CountryTest";
    private final String    ROLE                        = "RoleTest";
//    private final Company   company;
    private final Company   company = new Company(COMPANY_NAME, BUDGET);
    
    private final int       USER_ID_UPDATED             = USER_ID;
    private final String    USER_NAME_UPDATED           = USER_NAME + "Updated";        
    private final String    PASSWORD_UPDATED            = PASSWORD  + "Updated";
    private final String    EMAIL_UPDATED               = EMAIL     + "Updated";
    private final String    COUNTRY_UPDATED             = COUNTRY   + "Updated";
    private final String    ROLE_UPDATED                = ROLE      + "Updated";
//    private final Company   company_updated;
    private final Company   company_updated = company;
    
    
    
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
    
    public UserManagerTest() {
//        company                     = new Company(COMPANY_NAME, BUDGET);
//        company_updated             = company;
        
//        user                        = new User(
//                                                USER_ID, USER_NAME, PASSWORD,
//                                                EMAIL, COUNTRY, ROLE, company);
        
//        userUpdated                 = new User(
//                                                USER_ID_UPDATED, USER_NAME_UPDATED,
//                                                PASSWORD_UPDATED, EMAIL_UPDATED,
//                                                COUNTRY_UPDATED, ROLE_UPDATED,
//                                                company_updated);
        
        
    } // End of constructor
    
    
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
    public void setUp() {}
    
    @After
    public void tearDown() {}

    /**
     * Test of insert method, of class UserManager.
     */
    @Test
    public void testInsert() {
        System.out.println("Testing :: UserManager.insert()");
        
        boolean isInserted  = companyInstance.insert(conn, company);
        
        if( ! isInserted ) System.err.println("        :: Something whent wrong when creating a company");
        
        boolean expResult   = true;
        boolean result      = insertRow();      
        
        assertEquals("        :: Row not inserted", expResult, result);
        
        deleteRow();
    } // End of method :: testInert()
    
    
    /**
     * Test of getRow method, of class UserManager.
     */
    @Test
    public void testGetRow() {
        System.out.println("Testing :: UserManager.getRow()");
        
        boolean isInserted  = companyInstance.insert(conn, company);
        
        if( ! isInserted ) System.err.println("        :: Something whent wrong when creating a company");
        
        insertRow();

        System.out.println(user.toString());
        
        User expResult   = user;
        User result      = userInstance.getRow(conn, user.getUserID());
        
        result.toString();
        
        assertTrue(
                "        :: Retrieved data is not as expected",
                expResult.toString().equals(result.toString()));
    } // End of method :: testgetRow
    
    
    @Test
    public void test_D_Delete() {
        System.out.println("Testing :: UserManager.delete()");

        boolean isInserted  = companyInstance.insert(conn, company);
        if( ! isInserted ) System.err.println("        :: Something whent wrong when creating a company");
        
        // Seting up :: by inserting a row
        insertRow();
        
        boolean result = deleteRow();

        assertTrue("        :: Row not deleted", result);
    } // End of method :: testDelete()
    
    
    
    private boolean insertRow() {
        
        return userInstance.insert(conn, user);
    }
    
    private boolean deleteRow() {
        
        return userInstance.delete(conn, user.getUserID()); 
    }
    
} // End of class :: UserManagerTest
