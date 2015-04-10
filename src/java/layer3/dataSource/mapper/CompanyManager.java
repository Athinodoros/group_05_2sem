/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package layer3.dataSource.mapper;

import java.sql.*;
import layer2.domain.bean.Company;
import layer3.dataSource.DBConnector;

/**
 *
 * @author bo
 */
public class CompanyManager {
    
     public boolean insert(Connection conn, Company bean) { 

        int rowsInserted = 0;
        
         String sql1 = "INSERT into companies (companyname, budget) " + "VALUES (?, ?)";
 
        try (
                // PreparedStatement stmt = conn.prepareStatement(sql1, Statement.RETURN_GENERATED_KEYS);
                PreparedStatement stmt = conn.prepareStatement(sql1);
                ) {
     
            //== insert tuple
            stmt.setString  (1, bean.getCompanyName());
            stmt.setInt     (2, bean.getBudget());       
            rowsInserted  = stmt.executeUpdate();
            

        } catch (SQLException e) {
            DBConnector.processException(e);
            return false;
        }
        return rowsInserted == 1;
    } // End of method :: insert()
    
    
     
     public Company getRow(Connection conn, String companyName) { 

        String sql = "SELECT * FROM companies WHERE companyname = ?";
        ResultSet rs = null;

        try ( PreparedStatement stmt = conn.prepareStatement(sql); ) {
            
            stmt.setString(1, companyName);
            rs = stmt.executeQuery();

            if (rs.next()) {
                Company bean = new Company();
                bean.setCompanyName(companyName);
                bean.setBudget(rs.getInt("budget"));
                return bean;
            } else {
                return null;
            }

        } catch ( SQLException e) {
            DBConnector.processException(e);
            return null;
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    DBConnector.processException(e);
                }
            }
        }
    } // End of method :: getRow() 
     
     
     
} // End of Class :: CompanyManager