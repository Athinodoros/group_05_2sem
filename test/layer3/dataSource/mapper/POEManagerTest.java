/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package layer3.dataSource.mapper;

import java.sql.Connection;
import layer2.domain.bean.POE;
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
public class POEManagerTest {
    
    private static Connection conn;
    
    public POEManagerTest() {}
    
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

    @Test
    public void testInsert() {
        
        System.out.println("Testing :: POEManager.insert()");

        //making sure I insert all the necessary rows in the other tables first
        boolean status1 = partnerManager.insert(conn, partner);
        boolean status2 = projectManager.insert(conn, project);
        
        boolean result = false;
        
        if (status1 & status2) {
            result = poeManager.insert(conn, poe);
        }
        
        assertTrue(result);

    } // End of method :: testInsert()
    

    @Test
    public void testGetRow() {
        System.out.println("Testing :: POEManager.getRow()");
        
        //making sure I first insert all the necessary rows, before I try to get something out
        boolean status1 = partnerManager.insert(conn, partner);
        boolean status2 = projectManager.insert(conn, project);
        boolean status3 = poeManager.insert(conn, poe);
        
        POE result = null;
        
        if (status1 & status2 & status3) {
            result = poeManager.getRow(conn, poe.getProject().getProjectID());
        }
        assertNotNull(result);
    }

    @Test
    public void testUpdate() {
        System.out.println("Testing :: POEManager.update()");
        
        //making sure I first insert all the necessary rows, before I try to update something
        boolean status1 = partnerManager.insert(conn, partner);
        boolean status2 = projectManager.insert(conn, project);
        boolean status3 = poeManager.insert(conn, poe);
        
        POE poeUpdated = new POE(poe);
        //--------------------- ReDo Later---------------
        
//        poeUpdated.setFilePath(FILEPATH + "_/This is added to the file path");
        
        boolean status4 = poeManager.update(conn, poeUpdated);
        
        POE result = poe;
        if (status1 & status2 & status3 & status4) {
            result = poeManager.getRow(conn, poeUpdated.getProject().getProjectID());
        }
        
        assertFalse(poe.getFilePath().equals(result.getFilePath()));
        
    } // End of method :: testUpdate()
    

    @Test
    public void testDelete() {
        System.out.println("Testing :: POEManager.delete()");
        
        //making sure I first insert all the necessary rows, before I try to delete something
        boolean status1 = partnerManager.insert(conn, partner);
        boolean status2 = projectManager.insert(conn, project);
        boolean status3 = poeManager.insert(conn, poe);
        
        boolean result = false;
        if (status1 & status2 & status3) {
            result = poeManager.delete(conn, poe.getProject().getProjectID());
        }
        
        assertTrue(result);
    } // End of method :: testDelete()
    

    @Test
    public void testDeleteAllRows() {
        System.out.println("Testing :: poeManager.DeleteAllRows");
        //  Making sure I first insert all the necessary rows,
        //  before I try to update something
        boolean status1 = partnerManager.insert(conn, partner);
        boolean status2 = projectManager.insert(conn, project);
        boolean status3 = poeManager.insert(conn, poe);
       
        int rowsDeleted = 0;
        if(status1 & status2 & status3) {
            rowsDeleted = poeManager.deleteAllRows(conn, "yes");
        }
        assertTrue(rowsDeleted == 1);
    }
}
