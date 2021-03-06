/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package layer3.dataSource.mapper;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collection;
import layer2.domain.bean.UserAuthentication;
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

// Import TestData
import static layer3.dataSource.mapper.utility.TestData.*;
/**
 *
 * @author bo
 */
public class UserAuthenticationManagerTest {
    
    private static Connection conn;

    
    public UserAuthenticationManagerTest() {}
    
    
    
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
     * Test of insert method, of class UserAutenticationManager.
     */
   @Test
    public void test_A_UserAutenticationManager_Insert() {
        System.out.println("Testing :: UserAutenticationManager.insert()");
        
        //  Making sure I insert all the necessary rows in the other tables first
        boolean status1 = partnerManager.insert(conn, partner);
        boolean status2 = userInfoManager.insert(conn, userInfo);
        
        boolean result = false;
        if(status1 & status2) {
            result = userAutenticationManager.insert(conn, userAutenticantion);
        }
        
        assertTrue(result);
    } // End of method :: testInert()
    
    
    /**
     * Test of delete method, of class UserAutenticationManager.
     */
    @Test
    public void test_B_UserAutenticationManager_Delete() {
        System.out.println("Testing :: UserAutenticationManager.delete()");

        //  Making sure I first insert all the necessary rows,
        //  before I try to delete something
        boolean status1 = partnerManager.insert(conn, partner);
        boolean status2 = userInfoManager.insert(conn, userInfo);
        boolean status3 = userAutenticationManager.insert(conn, userAutenticantion);
        
        boolean result = false;
        if(status1 & status2 & status3) {
            result = userAutenticationManager.delete(conn, userAutenticantion.getUname());
        }
        
        assertTrue(result);
    } // End of method :: testDelete()
    
     /**
     * Test of getRow method, of class UserAutenticationManager.
     */
    @Test
    public void test_C_UserAutenticationManager_GetRow() {
        System.out.println("Testing :: UserAutenticationManager.getRow()");
        
        //  Making sure I first insert all the necessary rows,
        //  before I try to get something out
        boolean status1 = partnerManager.insert(conn, partner);
        boolean status2 = userInfoManager.insert(conn, userInfo);
        boolean status3 = userAutenticationManager.insert(conn, userAutenticantion);
        
        UserAuthentication result = null;
        if( status1 & status2 & status3 ) {
            result = userAutenticationManager.getRow(conn, userAutenticantion.getUname());
        }
        
        assertNotNull(result);
    } // End of method :: testgetRow
    
    
    
     /**
     * Test of update method, of class UserAutenticationManager.
     */
    @Test
    public void test_D_UserAutenticationManager_Update() {
        System.out.println("Testing :: UserAutenticationManager.Update()");

        //  Making sure I first insert all the necessary rows,
        //  before I try to update something
        boolean status1 = partnerManager.insert(conn, partner);
        boolean status2 = userInfoManager.insert(conn, userInfo);
        boolean status3 = userAutenticationManager.insert(conn, userAutenticantion);
        
        // Change password
        UserAuthentication userAuthenciationUpdate = new UserAuthentication(userAutenticantion);
        userAuthenciationUpdate.setPassword(PASSWORD + "_Update");
        // Update the user's data in the database
        boolean status4 = userAutenticationManager.update(conn, userAuthenciationUpdate);
        
        UserAuthentication result = userAutenticantion;
        if( status1 & status2 & status3 & status4) {
            result = userAutenticationManager.getRow(conn, userAuthenciationUpdate.getUname());
        }
        
        assertFalse(userAutenticantion.getPassword().equals(result.getPassword()));
    } // End of Method :: testUpdate()
    
    
    /**
     * Test of getAllRows method, of class UserAutenticationManager.
     */
    @Test
    public void test_E_UserAuthenticationManager_getAllRows() {
        System.out.println("Testing :: CompanyManager.getAllRows()");
        
        //  Making sure I first insert all the necessary rows,
        //  before I try to get something out
        boolean status1 = partnerManager.insert(conn, partner);
        boolean status2 = userInfoManager.insert(conn, userInfo);
        
        UserAuthentication user1 = new UserAuthentication(userAutenticantion);
        UserAuthentication user2 = new UserAuthentication(userAutenticantion);
        
        // create one user with TWO different usernames (unames)
        user1.setUname(UNAME + "_ONE");
        user2.setUname(UNAME + "_TWO");
        
        boolean status3 = userAutenticationManager.insert(conn, user1);
        boolean status4 = userAutenticationManager.insert(conn, user2);

        ArrayList<UserAuthentication> rows = new ArrayList();
       
        if( status1 & status2 & status3 & status4) {
            //  Retrieve the two inserted companies from the database
            rows = new ArrayList<>( userAutenticationManager.getAllRows(conn) );
        }
        
        int expResult   = 2;
        int result      = rows.size();

        assertTrue(expResult == result);
    }

    
    @Test
    public void test_F_UserAuthenticationManager_testDeleteAllRows(){
        
        //  Making sure I first insert all the necessary rows,
        //  before I try to update something
        boolean status1 = partnerManager.insert(conn, partner);
        boolean status2 = userInfoManager.insert(conn, userInfo);
        boolean status3 = userAutenticationManager.insert(conn, userAutenticantion);
        
        
        int rowsDeleted = 0;
        if(status1 & status2 & status3) {
            
            rowsDeleted = userAutenticationManager.deleteAllRows(conn, "yes");
        }
        assertTrue(rowsDeleted == 1);
    }
    
} // End of Class
