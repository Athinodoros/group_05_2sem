/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package layer3.dataSource.mapper;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;
import layer2.domain.bean.Partner;
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
public class PartnerManagerTest {
    
    private static Connection conn;

    
    public PartnerManagerTest() {}
    
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
     * Test of insert method, of class PartnerManager.
     */
    @Test
    public void test_A_Partner_Insert() {
        System.out.println("Testing :: PartnerManager.insert()");
        
        boolean result = partnerManager.insert(conn, partner);
        
        assertTrue(result);
    }
    
    /**
     * Test of delete method, of class PartnerManager.
     */
    @Test
    public void test_B_PartnerManager_Delete() {
        System.out.println("Testing :: PartnerManager.delete()");

        //  Making sure I first insert all the necessary rows,
        //  before I try to delete something
        boolean status1 = partnerManager.insert(conn, partner);
      
        boolean result = false;

        if (status1) {
            result = partnerManager.delete(conn, partner.getCompanyName());
        }

        assertTrue(result);
    }
    
    
    /**
     * Test of getRow method, of class PartnerManager.
     */
     @Test
    public void test_C_PartnerManager_GetRow() {
        System.out.println("Testing :: PartnerManager.getRow()");

        //  Making sure I first insert all the necessary rows,
        //  before I try to get something out
        boolean status1 = partnerManager.insert(conn, partner);

        Partner result = null;
        if (status1) {
            result = partnerManager.getRow(conn, partner.getCompanyName());
        }

        assertNotNull(result);
    } // End of method :: testgetRow
    
    
    /**
     * Test of update method, of class PartnerManager.
     */
    @Test
    public void test_D_ParnerManager_Update() {
        System.out.println("Testing :: PartnerManager.Update()");

        //  Making sure I first insert all the necessary rows,
        //  before I try to update something
        boolean status1 = partnerManager.insert(conn, partner);
        
        //  Create a new company with the same name as 'company',
        //  but with a new budget
        Partner partnerUpdated = new Partner(partner);
        partnerUpdated.setCompanyID(COMPANY_ID + 100);

        // Update company data in the database
        boolean status2 = partnerManager.update(conn, partnerUpdated);

        Partner result = partner;
        if( status1 & status2 ) {
            // retrieve updated data from database
            result = partnerManager.getRow(conn, partnerUpdated.getCompanyName());
        }
        
        assertFalse(partner.getCompanyID() == result.getCompanyID());
    } // End of Method :: testUpdate()
    
    
    /**
     * Test of getAllRows method, of class PartnerManager.
     */
    @Test
    public void test_E_PartnerManager_getAllRows() {
        System.out.println("Testing :: PartnerManager.getAllRows()");
        
        // setting up test by creating two companies and inserting them in the database
        Partner partner1    = new Partner(COMPANY_NAME + "_one", COMPANY_ID);
        Partner partner2    = new Partner(COMPANY_NAME + "_Two", COMPANY_ID);
        boolean status1     = partnerManager.insert(conn, partner1);
        boolean status2     = partnerManager.insert(conn, partner2);

        ArrayList<Partner> rows = new ArrayList<>();
        
        if( status1 & status2 ) {
            // retrieve the two inserted companies from the database
            rows = new ArrayList<>(partnerManager.getAllRows(conn));
        }
        
        int expResult   = 2;
        int result      = rows.size();

        assertTrue(expResult == result);
    }

    @Test
    public void test_F_UserManager_DeleteAllRows() {
        System.out.println("Testing :: UserManager.DeleteAllRows");
        //  Making sure I first insert all the necessary rows,
        //  before I try to update something
        boolean status1 = partnerManager.insert(conn, partner);
       
        int rowsDeleted = 0;
        if(status1) {
            rowsDeleted = partnerManager.deleteAllRows(conn, "yes");
        }
        assertTrue(rowsDeleted == 1);
    }
    

}
