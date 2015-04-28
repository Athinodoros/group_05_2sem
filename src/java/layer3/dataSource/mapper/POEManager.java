/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package layer3.dataSource.mapper;

import java.sql.*;
import layer2.domain.bean.POE;
import layer3.dataSource.DBConnector;

/**
 *
 * @author bo
 */
public class POEManager {
    
    
    public boolean insert(Connection conn, POE bean) { 
        
        int rowsInserted = 0;
         
        String sql = "INSERT into POE (projectID, filePath) VALUES (?, ?)";
         
        try ( PreparedStatement stmt = conn.prepareStatement( sql ); ) {
 
            ProjectManager pm = new ProjectManager();
            
            //== insert tuplet
            stmt.setInt     (1, bean.getProject().getProjectID() );
            stmt.setString  (2, bean.getFilePath());
              
            rowsInserted  = stmt.executeUpdate(); 

        } catch (SQLException e) {
            DBConnector.processException(e);
            return false;
        }
        
        return rowsInserted == 1;
    } // End of method :: insert()
    
    
    public POE getRow(Connection conn, int projectID) {

        String sql = "SELECT * FROM POE WHERE projectID = ?";
        ResultSet rs = null;

        try (PreparedStatement stmt = conn.prepareStatement(sql);) {

            stmt.setInt(1, projectID);
            rs = stmt.executeQuery();

            if (rs.next()) {
                
                ProjectManager pm = new ProjectManager();
                POE bean = new POE();
                
                bean.setProject( pm.getRow(conn, rs.getInt("projectID")));
                bean.setFilePath(rs.getString("filePath"));
                
                return bean;
                
            } else {
                return null;
            }

        } catch (SQLException e) {
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
    
    
    public boolean update(Connection conn, POE bean) {

        String sql = "UPDATE POE SET filePath = ? WHERE projectID = ?";

        try ( PreparedStatement stmt = conn.prepareStatement(sql); ) {

            stmt.setString  (1, bean.getFilePath());
            stmt.setInt     (2, bean.getProject().getProjectID());

            int affected = stmt.executeUpdate();
            if (affected == 1) {
                return true;
            } else {
                return false;
            }

        } catch (SQLException e) {
            DBConnector.processException(e);
            return false;
        }
    } // End of method :: update()
    
    
    public boolean delete(Connection conn, int projectID) {

        String sql = "DELETE FROM POE WHERE projectID = ?";

        try ( PreparedStatement stmt = conn.prepareStatement(sql); ) {

            stmt.setInt(1, projectID);
            int effected = stmt.executeUpdate();

            if (effected == 1) {
                return true;
            } else {
                return false;
            }

        } catch (SQLException e) {
            DBConnector.processException(e);
            return false;
        }
    } // End of method :: Delete() 
    
    
    public int deleteAllRows(Connection conn, String confirm){
        
        if( confirm.equalsIgnoreCase("yes")) {
            
            String sql = "DELETE FROM POE";
            
            try ( PreparedStatement stmt = conn.prepareStatement(sql); ) {
                
                return stmt.executeUpdate();
            } catch (SQLException e) {
                
                DBConnector.processException(e);
                return -1;
            }
        }else{
            return -1;
        }
    } // End of method :: deleteAllRows
    
} // End of Class
