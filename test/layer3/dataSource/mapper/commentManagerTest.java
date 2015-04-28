/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package layer3.dataSource.mapper;

import java.sql.Connection;
import java.util.ArrayList;
import layer2.domain.bean.Comment;
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
        System.out.println("Testing :: CommentManager.insert()");
        
        //making sure I insert all the necessary rows in the other tables first
        boolean status1 = partnerManager.insert(conn, partner);
        boolean status2 = projectManager.insert(conn, project);
        
        boolean result = false;
        
        if (status1 & status2) {
             result = commentMananger.insert(conn, comment);
        }
        
        assertTrue(result);
    }

    @Test
    public void testGetRow() {
        System.out.println("Testing :: CommentManager.getRow()");
        
        //making sure I first insert all the necessary rows, before I try to get something out
        boolean status1 = partnerManager.insert(conn, partner);
        boolean status2 = projectManager.insert(conn, project);
        boolean status3 = commentMananger.insert(conn, comment);
        
        Comment result = null;
       
        if (status1 & status2 & status3) {
            result = commentMananger.getRow(conn, comment.getCommentID());
        }
        assertNotNull(result);
    }

    @Test
    public void testGetAllProjectComments() {
        System.out.println("Testing :: CommentManager.getAllrojectComments()");
        
        //  Making sure I first insert all the necessary rows,
        //  before I try to get something out
        boolean status1 = partnerManager.insert(conn, partner);
        boolean status2 = projectManager.insert(conn, project);
                
        Comment comment1 = new Comment(comment);
        Comment comment2 = new Comment(comment);
        
        comment2.setComment(COMMENT + "_Plus something added");
        
        
        // Ceate two comments for the some project
        boolean status3 = commentMananger.insert(conn, comment1);
        boolean status4 = commentMananger.insert(conn, comment2);
        
        ArrayList<Comment> rows = new ArrayList();

        if (status1 & status2 & status3) {
            //  Retrieve the two inserted companies from the database
            rows = new ArrayList<>( commentMananger.getAllProjectComments(conn, comment1.getProject().getProjectID()) );
        }

        
        int expResult = 2;
        int result = rows.size();

        assertTrue(expResult == result);
        
        
        
    }

}
