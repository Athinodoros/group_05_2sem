/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package layer3.dataSource.mapper;

import java.sql.Connection;
import java.util.Date;
import layer2.domain.bean.Project;
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
public class ProjectManagerTest {
    
    private static Connection conn;
    
    public ProjectManagerTest() {
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
     * Test of insert method, of class ProjectManager.
     */
    @Test
    public void testInsert() {
        System.out.println("insert");
    
        
        
        final int       projectID   = 0;
        final int       author      = 0;
        final String    title       = ">>> Title test <<<";
        final Date      startDate   = new Date();
        final Date      endDate     = new Date();
        final String    stage       = ">>> Stage test <<<";
        final int       budget      = 0;
        final boolean   hasPOE      = false;
        final String    comments    = ">>> This is a test <<<";
    
        
        Project bean = new Project(
            projectID,
            author,
            title,
            startDate,
            endDate,
            stage,
            budget,
            hasPOE,
            comments
        );
        
        ProjectManager instance = new ProjectManager();
        
        boolean expResult = true;
        boolean result = instance.insert(conn, bean);
        assertEquals(expResult, result);
       
    } // End of method :: testInsert()
    
} // End of class :: 
