/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package layer3.dataSource.mapper;

import java.sql.*;
import layer2.domain.bean.User;
import layer3.dataSource.DBConnector;
import layer3.dataSource.utility.Convert;

/**
 *
 * @author bo
 */
public class UserManager {
    
     public boolean insert(Connection conn, User bean) { 

        int rowsInserted = 0;
        
         String sql1 = "INSERT into users (userid, uname, password, email, country, urole, company) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        // This code is taken from Henrik's DataSourceLayerDemo :: Class OrderMapper.jave
        //-------------------------------------------------------------------------------
        String sql2
                = "select projectSequence.nextval " //= "select orderseq.nextval  "
                + "from dual";                      // <- this (dual) is a dummy table, and it is needed
                                                    // because of the select statemant (oracle data-base only)
        //--------------------------------------------------------------------------------
        ResultSet keys = null;
        try (
                //PreparedStatement stmt = conn.prepareStatement(sql1, Statement.RETURN_GENERATED_KEYS);
                PreparedStatement stmt = conn.prepareStatement(sql1 );
                // Henrik's code (needed to update primary key (oracle data-base only)->
                PreparedStatement stmt1 = conn.prepareStatement( sql2 );
                ) {

            //== (Henrik's code) get unique key
            //--------------------------------
            keys = stmt1.executeQuery();
            if (keys.next()) {
                bean.setUserID(keys.getInt(1)); // <- get the key from dummy table (dual)
            }
            else {
                // ToDo - 
            }
            //--------------------------------
     
            //== insert tuple
            stmt.setInt     (1, bean.getUserID());
            stmt.setString  (2, bean.getName());
            stmt.setString  (3, bean.getPassword());
            stmt.setString  (4, bean.getEmail());
            stmt.setString  (5, bean.getCountry());
            stmt.setString  (6, bean.getRole());
            stmt.setString  (7, bean.getCompany().getCompanyName());        
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
    
    
    public User getRow(Connection conn, int userid) { 

        String sql = "SELECT * FROM users WHERE userid = ?";
        ResultSet rs = null;

        try ( PreparedStatement stmt = conn.prepareStatement(sql); ) {
            
            stmt.setInt(1, userid);
            rs = stmt.executeQuery();

            if (rs.next()) {
                User bean = new User();
                System.out.println(bean.toString());
                bean.setUserID(userid);
                bean.setName(rs.getNString("uname"));
                bean.setPassword(rs.getNString("password"));
                bean.setEmail(rs.getNString("email"));
                bean.setCountry(rs.getNString("country"));
                bean.setRole(rs.getNString("urole"));
                bean.getCompany().setCompanyName(rs.getNString("company"));
               
                
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
    
} // End of Class :: UserManager
