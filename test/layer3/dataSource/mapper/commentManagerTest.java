/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package layer3.dataSource.mapper;

import java.sql.Connection;
import java.util.ArrayList;
import layer2.domain.bean.Comment;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Athinodoros
 */
public class commentManagerTest {
    
    public commentManagerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testInsert() {
        System.out.println("insert");
        Connection conn = null;
        Comment bean = null;
        commentManager instance = new commentManager();
        boolean expResult = false;
        boolean result = instance.insert(conn, bean);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetRow() {
        System.out.println("getRow");
        Connection conn = null;
        int commentID = 0;
        commentManager instance = new commentManager();
        Comment expResult = null;
        Comment result = instance.getRow(conn, commentID);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
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
