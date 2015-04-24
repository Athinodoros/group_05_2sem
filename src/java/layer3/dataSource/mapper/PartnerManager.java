/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package layer3.dataSource.mapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import layer2.domain.bean.Partner;
import layer3.dataSource.DBConnector;

/**
 *
 * @author bo
 */
public class PartnerManager {
    
     public Collection getAllRows(Connection conn) { 

        Collection<Partner> rows = new ArrayList();
        
        String sql = "SELECT companyName, companyID FROM partner";
        
        try (   Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery( sql );
            ) {

            while (rs.next()) {
                Partner partner = new Partner();
                
                partner.setCompanyName(rs.getString("companyName"));
                partner.setCompanyID(rs.getInt("companyID"));
                
                rows.add(partner);
            }
            
        } catch (SQLException e) {
            DBConnector.processException(e);
        }
        return rows;
    } // End of method :: gitAllRows()
    
    
    
     public boolean insert(Connection conn, Partner bean) { 

        int rowsInserted = 0;
        
         String sql1 = "INSERT into partner (companyName, companyID) " + "VALUES (?, ?)";
 
        try ( PreparedStatement stmt = conn.prepareStatement(sql1); ) {
     
            //== insert tuple
            stmt.setString  (1, bean.getCompanyName());
            stmt.setInt     (2, bean.getCompanyID());       
            rowsInserted  = stmt.executeUpdate();
            

        } catch (SQLException e) {
            DBConnector.processException(e);
            return false;
        }
        return rowsInserted == 1;
    } // End of method :: insert()
    
    
     
     public Partner getRow(Connection conn, String companyName) { 

        String sql = "SELECT * FROM partner WHERE companyName = ?";
        ResultSet rs = null;

        try ( PreparedStatement stmt = conn.prepareStatement(sql); ) {
            
            stmt.setString(1, companyName);
            rs = stmt.executeQuery();

            if (rs.next()) {
                Partner bean = new Partner();
                bean.setCompanyName(companyName);
                bean.setCompanyID(rs.getInt("companyID"));
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
     
     
    public boolean update(Connection conn, Partner bean) { 

        String sql
                = "UPDATE partner SET "
                + "companyID = ? "
                + "WHERE companyName = ?";
        
        try ( PreparedStatement stmt = conn.prepareStatement(sql); ) {

            stmt.setInt     (1, bean.getCompanyID());
            stmt.setString  (2, bean.getCompanyName());

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
     
    public boolean delete(Connection conn, String companyName) { 
        
        String sql = "DELETE FROM partner WHERE companyName = ?";
        
        try ( PreparedStatement stmt = conn.prepareStatement(sql); ) {
        
            stmt.setString(1, companyName);
            int effected = stmt.executeUpdate();

            if(effected == 1) {
                return true;
            } else {
                return false;
            }
       
        } catch (SQLException e) {
            DBConnector.processException(e);
            return false;
        }
    } // End of method :: Delete() 
    
//    public boolean deleteAllRows(Connection conn, String confirm) { 
//     
//        if( confirm.equalsIgnoreCase("yes")) {
//            
//            String sql = "DELETE FROM partner";
//
//            try ( PreparedStatement stmt = conn.prepareStatement(sql); ) {
//
//                int effected = stmt.executeUpdate();
//
//                if(effected == 1) {
//                    return true;
//                } else {
//                    return false;
//                }
//
//            } catch (SQLException e) {
//                DBConnector.processException(e);
//                return false;
//            }
//        }
//        else {
//            return false;
//        }
//    } // End of method :: Delete() 
    
    
    
    public int deleteAllRows(Connection conn, String confirm){
        
        if( confirm.equalsIgnoreCase("yes")) {
            
            String sql = "DELETE FROM partner";
            
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                return stmt.executeUpdate();
            } catch (SQLException e) {
                DBConnector.processException(e);
                return -1;
            }
        }else{
            return -1;
        }
    }
    
    
}
