/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package layer3.dataSource.mapper;

import java.sql.Connection;
import java.util.*;
import layer2.domain.bean.Project;
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
public class ProjectManagerTest {

    private static Connection conn;

    public ProjectManagerTest() {}

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
     * Test of insert method, of class ProjectManager.
     */
    @Test
    public void testInsert() {
        System.out.println("Testing :: ProjectManager.insert()");

        //making sure I insert all the necessary rows in the other tables first
        boolean status1 = partnerManager.insert(conn, partner);
        
        boolean result = false;
        
        if (status1) {
            result = projectManager.insert(conn, project);
        }
        
        assertTrue(result);

    } // End of method :: testInsert()

    
    @Test
    public void testGetRow() {
        System.out.println("Testing :: ProjectManager.getRow()");
        
        //making sure I first insert all the necessary rows, before I try to get something out
        boolean status1 = partnerManager.insert(conn, partner);
        boolean status2 = projectManager.insert(conn, project);
        
        Project result = null;
        
        if (status1 & status2) {
            result = projectManager.getRow(conn, project.getProjectID());
        }
        assertNotNull(result);
        
    } // End of method :: testGetRow()
    
    
    @Test
    public void testDelete(){
        System.out.println("Testing :: ProjectManager.delete()");
        
        //making sure I first insert all the necessary rows, before I try to delete something
        boolean status1 = partnerManager.insert(conn, partner);
        boolean status2 = projectManager.insert(conn, project);
        
        boolean result = false;
        if (status1 & status2) {
            result = projectManager.delete(conn, project.getProjectID());
        }
        
        assertTrue(result);
    } // End of method :: testDelete()
    
    
    @Test
    public void testUpdate() {
        System.out.println("Testing :: ProjectManager.update()");
        
        //making sure I first insert all the necessary rows, before I try to update something
        boolean status1 = partnerManager.insert(conn, partner);
        boolean status2 = projectManager.insert(conn, project);
        
        Project projectUpdated = new Project(project);
        projectUpdated.setDescription(DESCRIPTION + "_Updated");
        
        boolean status3 = projectManager.update(conn, projectUpdated);
        
        Project result = project;
        if (status1 & status2 & status3) {
            result = projectManager.getRow(conn, projectUpdated.getProjectID());
        }
        
        assertFalse(project.getDescription().equals(result.getDescription()));
        
    } // End of method :: testUpdate()
    
    
    /**
     * Test of getAllRows method, of class ProjectManager.
     */
    @Test
    public void test_E_ProjectManager_getAllRows() {
        System.out.println("Testing :: ProjectManager.getAllRows()");

        //  Making sure I first insert all the necessary rows,
        //  before I try to get something out
        boolean status1 = partnerManager.insert(conn, partner);
       
        Project project1 = new Project(project);
        Project project2 = new Project(project);

        boolean status2 = projectManager.insert(conn, project1);
        boolean status3 = projectManager.insert(conn, project2);
        
        ArrayList<Project> rows = new ArrayList();

        if (status1 & status2 & status3) {
            //  Retrieve the two inserted companies from the database
            rows = new ArrayList<>(projectManager.getAllRows(conn));
        }

        int expResult = 2;
        int result = rows.size();

        assertTrue(expResult == result);
    }
    
    @Test
    public void test_F_UserInfoManager_DeleteAllRows() {
        System.out.println("Testing :: ProjectManager.DeleteAllRows");
        //  Making sure I first insert all the necessary rows,
        //  before I try to update something
        boolean status1 = partnerManager.insert(conn, partner);
        boolean status2 = projectManager.insert(conn, project);
       
        int rowsDeleted = 0;
        if(status1 & status2) {
            rowsDeleted = projectManager.deleteAllRows(conn, "yes");
        }
        assertTrue(rowsDeleted == 1);
    }
    
    
} // End of class :: 
