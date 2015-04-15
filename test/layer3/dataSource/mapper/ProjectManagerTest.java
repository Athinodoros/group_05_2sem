/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package layer3.dataSource.mapper;

import java.sql.Connection;
import java.util.Calendar;
import static java.util.Calendar.MONTH;
import java.util.Date;
import java.util.Random;
import layer2.domain.bean.Company;
import layer2.domain.bean.Project;
import layer2.domain.bean.User;
import layer2.domain.interfaces.NamingConv;
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

    private ProjectManager projectManager;
    private UserManager userManager;
    private CompanyManager companyManager;
    
    private Project project;
    private User author;
    private Company dummyCompany;
    private final Date startDate;
    private final Date endDate;
    

    public ProjectManagerTest() {
        Calendar cal = Calendar.getInstance();
        Date today = cal.getTime();
        startDate = today;
        
        cal.add(MONTH, 1);
        Date oneMonthLater = cal.getTime();
        endDate = oneMonthLater;
        
        dummyCompany = new Company("dummy_" + new Random().nextInt(), 42);
        author = new User(0, "Bob", "PassWordTest", "test@sth.whatever", "Testlandia", NamingConv.RESELLER, dummyCompany);

        project = new Project(0, author, ">>> Title test <<<", startDate, endDate, ">>> Stage test <<<", 42, false, ">>> This is a test <<<");
        
        projectManager = new ProjectManager();
        userManager = new UserManager();
        companyManager = new CompanyManager();
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
        System.out.println("Testing :: ProjectManager.insert()");

        //making sure I insert all the necessary rows in the other tables first
        boolean status1 = companyManager.insert(conn, dummyCompany);
        boolean status2 = userManager.insert(conn, author);
        
        boolean result = false;
        
        if (status1 && status2) {
            result = projectManager.insert(conn, project);
        }
        
        assertTrue(result);

    } // End of method :: testInsert()

    
    @Test
    public void testGetRow() {
        System.out.println("Testing :: ProjectManager.getRow()");
        
        //making sure I first insert all the necessary rows, before I try to get something out
        boolean status1 = companyManager.insert(conn, dummyCompany);
        boolean status2 = userManager.insert(conn, author);
        boolean status3 = projectManager.insert(conn, project);
        
        Project result = null;
        if (status1 && status2 && status3) {
            result = projectManager.getRow(conn, project.getProjectID());
        }
        if (result != null) {
//            System.out.println("\n\nExpected");
//            System.out.println(project.toString());
//            System.out.println("\n\nResult");
//            System.out.println(result.toString());
            assertTrue(result.toString().equals(project.toString()));
        }
        
    } // End of method :: testGetRow()
    
    
    @Test
    public void testDelete(){
        System.out.println("Testing :: ProjectManager.delete()");
        
        //making sure I first insert all the necessary rows, before I try to delete something
        boolean status1 = companyManager.insert(conn, dummyCompany);
        boolean status2 = userManager.insert(conn, author);
        boolean status3 = projectManager.insert(conn, project);
        
        boolean result = false;
        if (status1 && status2 && status3) {
            result = projectManager.delete(conn, project.getProjectID());
        }
        
        assertTrue(result);
    } // End of method :: testDelete()
    
    
    @Test
    public void testUpdate() {
        System.out.println("Testing :: ProjectManager.update()");
        
        //making sure I first insert all the necessary rows, before I try to update something
        boolean status1 = companyManager.insert(conn, dummyCompany);
        boolean status2 = userManager.insert(conn, author);
        boolean status3 = projectManager.insert(conn, project);
        
        project.setStage("different stage");
        project.setBudget(43);
        project.setPOE(true);
        project.setComments("new comments");
        
        boolean status4 = projectManager.update(conn, project);
        
        Project updated = null;
        if (status1 && status2 && status3 && status4) {
            updated = projectManager.getRow(conn, project.getProjectID());
        }
        
        if (updated != null) {
            System.out.println("\n\nExpected");
            System.out.println(project.toString());
            System.out.println("\n\nResult");
            System.out.println(updated.toString());
            assertTrue(project.toString().equals(updated.toString()));
        }
    } // End of method :: testUpdate()
} // End of class :: 
