/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package layer3.dataSource.mapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import layer2.domain.bean.UserAuthentication;
import layer3.dataSource.DBConnector;

/**
 *
 * @author bo
 */
public class UserAutenticationManager {
    
    
     public Collection getAllRows(Connection conn) { 

        Collection<UserAuthentication> rows = new ArrayList();
        
        String sql = "SELECT userID, uname, password, email FROM userAthentication";
//        String sql = "SELECT * FROM userAthentication";
        
        try (   Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery( sql );
            ) {
            
            UserInfoManager uim = new UserInfoManager();    
            while (rs.next()) {

                UserAuthentication bean = new UserAuthentication();
                
                bean.setUserInfo(uim.getRow(conn, rs.getInt("userID")));
                bean.setUname(rs.getString("uname"));
                bean.setPassword(rs.getString("password"));
                bean.setEmail(rs.getString("email"));
                
                rows.add(bean);
                
            }
            
        } catch (SQLException e) {
            DBConnector.processException(e);
        }
        return rows;
    } // End of method :: gitAllRows()
    
    
    
    public boolean insert(Connection conn, UserAuthentication bean) { 

        int rowsInserted = 0;
        
         String sql1 = "INSERT into userAthentication (userID, uname, password, email) "
                + "VALUES (?, ?, ?, ?)";
       
        ResultSet keys = null;
        try ( PreparedStatement stmt = conn.prepareStatement(sql1 );) {

//            //== insert tuple
            stmt.setInt(1, bean.getUserInfo().getUserID());
            stmt.setString(2, bean.getUname());
            stmt.setString(3, bean.getPassword());
            stmt.setString(4, bean.getEmail());
            
            rowsInserted  = stmt.executeUpdate();
        } catch (SQLException e) {
            DBConnector.processException(e);
            return false;
        } finally {
            if (keys != null) {
                try {
                    keys.close();
                } catch (SQLException e) {
                    DBConnector.processException(e);
                }
            }
        }
        return rowsInserted == 1;
    } // End of method :: insert()
    
     
     
      public UserAuthentication getRow(Connection conn, String uname) { 

        String sql = "SELECT * FROM userAthentication WHERE uname = ?";
        ResultSet rs = null;

        try ( PreparedStatement stmt = conn.prepareStatement(sql); ) {
            
            stmt.setString(1, uname);
            rs = stmt.executeQuery();
            
            if (rs.next()) {
                UserInfoManager uim = new UserInfoManager();
                UserAuthentication bean = new UserAuthentication();
                
                bean.setUname(uname);
                bean.setUserInfo(uim.getRow(conn, rs.getInt("userID")));
                bean.setPassword(rs.getString("password"));
                bean.setEmail(rs.getString("email"));
               
                
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
     
     
    
    
    public boolean update(Connection conn, UserAuthentication bean) { 

        String sql
                = "UPDATE userAthentication SET "
                + "userID = ?, "
                + "password = ?, "
                + "email = ? "
                + "WHERE uname = ?";
        
        
        
        try ( PreparedStatement stmt = conn.prepareStatement(sql); ) {

            UserInfoManager uim = new UserInfoManager();
            
            stmt.setInt(1, bean.getUserInfo().getUserID());
            stmt.setString(2, bean.getPassword());
            stmt.setString(3, bean.getEmail());
            stmt.setString(4, bean.getUname());
            
        
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
    
    
    
     public boolean delete(Connection conn, String uname) { 
        
        String sql = "DELETE FROM userAthentication WHERE uname = ?";
        
        try ( PreparedStatement stmt = conn.prepareStatement(sql); ) {
        
            stmt.setString(1, uname);
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
     
     
    
    public int deleteAllRows(Connection conn, String confirm){
        
        if( confirm.equalsIgnoreCase("yes")) {
            
            String sql = "DELETE FROM userAthentication";
            
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
    
    
} // End of class UserAuthentication