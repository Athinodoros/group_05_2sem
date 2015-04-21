/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package layer3.dataSource.mapper;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collection;
import layer2.domain.bean.UserInfo;
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
public class UserInfoManagerTest {
    
    private static Connection conn;
    
    private final UserInfoManager userInfoManager = new UserInfoManager();
    
    // USER_ID will be set in the call to insetRow()
    private final int USER_ID = 0; 
    private final String FIRST_NAME = "firstNameTest";
    private final String LAST_NAME = "lastNameTest";
    private final String COUNTRY = "Test";
    private final String U_ROLE = "roleTest";
    
    private final UserInfo userInfo = new UserInfo(USER_ID, FIRST_NAME, LAST_NAME, COUNTRY, U_ROLE );
    
    
    
    
    public UserInfoManagerTest() {}
    
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
    public void tearDown() {
    }

    
    
    
    /**
     * Test of insert method, of class UserInfoManager.
     */
    @Test
    public void test_A_UserInfoManager_Insert() {
        System.out.println("Testing :: UserInfoManager.insert()");
        
        boolean result = userInfoManager.insert(conn, userInfo);
        
        assertTrue(result);
        
    }
    
    /**
     * Test of delete method, of class UserInfoManager.
     */
    @Test
    public void test_B_UserInfoManager_Delete() {
        System.out.println("Testing :: UserInfoManager.delete()");

        //  Making sure I first insert all the necessary rows,
        //  before I try to delete something
        boolean status1 = userInfoManager.insert(conn, userInfo);
        
        boolean result = false;
        if(status1) {
            result = userInfoManager.delete(conn, userInfo.getUserID());
        }
        
        assertTrue(result);
        
    }
    
    
     /**
     * Test of getRow method, of class UserInfoManager.
     */
    @Test
     public void test_C_UserInfoManager_GetRow() {
        System.out.println("Testing :: UserInfoManager.getRow()");
        
        //  Making sure I first insert all the necessary rows,
        //  before I try to get something out
        boolean status1 = userInfoManager.insert(conn, userInfo);
        
        UserInfo result = null;
        if( status1) {
            result = userInfoManager.getRow(conn, userInfo.getUserID());
        }
        
        assertNotNull(result);
    } // End of method :: testgetRow
    
    
     /**
     * Test of update method, of class UserInfoManager.
     */
    @Test
   public void test_D_UserInfoManager_Update() {
        System.out.println("Testing :: UserInfoManager.Update()");

        //  Making sure I first insert all the necessary rows,
        //  before I try to update something
        boolean status1 = userInfoManager.insert(conn, userInfo);
        
        //  Create a new user with the same userid as 'user',
        //  but with a new user name
        UserInfo userInfoUpdated = new UserInfo(userInfo);
        userInfoUpdated.setFirstname(FIRST_NAME + "_Update");
        
        // Update the new user's data in the database
        boolean status2   = userInfoManager.update(conn, userInfoUpdated);
        
        UserInfo result = userInfo;
        if( status1 & status2) {
           result = userInfoManager.getRow(conn, userInfoUpdated.getUserID());
        }
        
        assertFalse( userInfo.getFirstname().equals(result.getFirstname()) );
    } // End of Method :: testUpdate()
     
     
    
    /**
     * Test of getAllRows method, of class UserInfoManager.
     */
    @Test
   public void test_E_UserInfoManager_getAllRows() {
        System.out.println("Testing :: UserInfoManager.getAllRows()");
        
        UserInfo user1      = new UserInfo(userInfo);
        UserInfo user2      = new UserInfo(userInfo);
        boolean status1 = userInfoManager.insert(conn, user1);
        boolean status2 = userInfoManager.insert(conn, user2);

        ArrayList<UserInfo> rows = new ArrayList();
        
        if( status1 & status2) {
            //  Retrieve the two inserted companies from the database
            rows = new ArrayList<>( userInfoManager.getAllRows(conn) );
        }
        
        int expResult   = 2;
        int result      = rows.size();

        assertTrue(expResult == result);
    }
    
}
