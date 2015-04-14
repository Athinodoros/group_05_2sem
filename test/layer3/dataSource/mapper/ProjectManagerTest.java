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
            System.out.println("Expected");
            System.out.println(project.toString());
            System.out.println("Result");
            System.out.println(result.toString());
            assertTrue(result.toString().equals(project.toString()));
        }
        
    } // End of method :: testGetRow()
} // End of class :: 
