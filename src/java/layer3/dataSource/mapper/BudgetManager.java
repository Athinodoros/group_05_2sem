/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package layer3.dataSource.mapper;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import layer2.domain.bean.Budget;
import layer3.dataSource.DBConnector;

/**
 *
 * @author Bancho
 */
public class BudgetManager {
    
    public boolean insert(Connection conn, Budget bean){
        String sql = "INSERT INTO budget (quarter, qyear, qbudget) VALUES (? , ? , ?)";
        int rowsInserted = 0;
        
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, bean.getQuarter());
            stmt.setInt(2, bean.getQyear());
            stmt.setInt(3, bean.getQbudget());
            rowsInserted = stmt.executeUpdate();
            
            return rowsInserted == 1;
        } catch (SQLException e) {
            DBConnector.processException(e);
            return false;
        }
    }
    
    
    public boolean update(Connection conn, Budget bean){
        String sql = "UPDATE budget SET qbudget = ? WHERE quarter = ? AND qyear = ?";
        int rowsAffected = 0;
        
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, bean.getQbudget());
            stmt.setInt(2, bean.getQuarter());
            stmt.setInt(3, bean.getQyear());
            rowsAffected = stmt.executeUpdate();
            
            return rowsAffected == 1;
        } catch (SQLException e) {
            DBConnector.processException(e);
            return false;
        }
    }
    
    
    public Budget getRow(Connection conn, int quarter, int qyear){
        String sql = "SELECT qbudget FROM budget WHERE quarter = ? AND qyear = ?";
        ResultSet rs = null;
        Budget budget = new Budget();
        budget.setQuarter(quarter);
        budget.setQyear(qyear);
        
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, quarter);
            stmt.setInt(2, qyear);
            rs = stmt.executeQuery();
            
            if (rs.next()) {
                budget.setQbudget(rs.getInt("qbudget"));
            }
            
            return budget;
            
        } catch (SQLException e) {
            DBConnector.processException(e);
            return null;
            
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    DBConnector.processException(ex);
                }
            }
        }
    }
    
}
