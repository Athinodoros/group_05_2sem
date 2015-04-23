/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package layer3.dataSource.mapper;

import layer2.domain.bean.Budget;
import layer3.dataSource.DBConnector;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import java.sql.*;
import layer3.dataSource.DBType;
import static org.junit.Assert.*;
import org.junit.BeforeClass;

/**
 *
 * @author Bancho
 */
public class BudgetManagerTest {
    
    private static Connection conn;
    private BudgetManager budgetManager;
    
    public BudgetManagerTest() {
        budgetManager = new BudgetManager();
    }
    
    
    @BeforeClass
    public static void setUpClass() {
        DBConnector.getInstance().setDBType(DBType.ORACLE_THIN_TEST_DATABASE);
        conn = DBConnector.getInstance().getConnection();
    }
    
    
    @Before
    public void setUp() {
        budgetManager.deleteAllRows(conn, true);
    }
    
    
    @AfterClass
    public static void tearDownClass() {
        DBConnector.getInstance().close();
    }

    
    //Test methods below
    @Test
    public void testInsert(){
        Budget budget = new Budget(1, 2015, 300000);
        boolean result = budgetManager.insert(conn, budget);
        assertTrue(result);
    }
    
    @Test
    public void testGetRow(){
        Budget budget = new Budget(1, 2015, 300000);
        budgetManager.insert(conn, budget);
        Budget result = budgetManager.getRow(conn, budget.getQuarter(), budget.getQyear());
        assertEquals(budget.toString(), result.toString());
    }
    
    @Test
    public void testUpdate(){
        Budget budget = new Budget(1, 2015, 300000);
        budgetManager.insert(conn, budget);
        budget.setQbudget(400000);
        budgetManager.update(conn, budget);
        Budget result = budgetManager.getRow(conn, budget.getQuarter(), budget.getQyear());
        assertEquals(budget.toString(), result.toString());
    }
    
    @Test
    public void testDelete(){
        Budget budget = new Budget(1, 2015, 300000);
        budgetManager.insert(conn, budget);
        boolean result = budgetManager.delete(conn, budget.getQuarter(), budget.getQyear());
        assertTrue(result);
    }
    
    @Test
    public void testDeleteAllRows(){
        Budget budget = new Budget(1, 2015, 300000);
        budgetManager.insert(conn, budget);
        
        Budget budget2 = new Budget(2, 2015, 400000);
        budgetManager.insert(conn, budget2);
        
        Budget budget3 = new Budget(3, 2015, 500000);
        budgetManager.insert(conn, budget3);
        
        int rowsDeleted = budgetManager.deleteAllRows(conn, true);
        
        assertTrue(rowsDeleted == 3);
    }
}
