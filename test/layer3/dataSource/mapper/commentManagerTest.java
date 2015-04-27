/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package layer3.dataSource.mapper;

import java.sql.Connection;
import java.util.ArrayList;
import layer2.domain.bean.Comment;
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
 * @author Athinodoros
 */
public class commentManagerTest {

    private static Connection conn;

    public commentManagerTest() {
    }

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
        System.out.println("insert");
        
        boolean status1 = commentMan.insert(conn, com1) ;
        
        assertTrue(status1);
    }

    @Test
    public void testGetRow() {
        Comment cm = commentMan.getRow(conn, com1.getCommentID());
        assertEquals(partner, cm);
    }

    @Test
    public void testGetProjectComments() {
        System.out.println("getProjectComments");
        Connection conn = null;
        int projectID = 0;
        commentManager instance = new commentManager();
        ArrayList<Comment> expResult = null;
        ArrayList<Comment> result = instance.getProjectComments(conn, projectID);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

}
