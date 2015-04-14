/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package layer3.dataSource.mapper;

import java.sql.*;
import layer2.domain.bean.Company;
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

        String sql1 = "SELECT * FROM users WHERE userid = ?";
        String sql2 = "SELECT * FROM companies WHERE companyname = ?";
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = conn.prepareStatement(sql1);
            stmt.setInt(1, userid);
            rs = stmt.executeQuery();
            
            User bean = new User();
            String companyName = "";

            if (rs.next()) {
                bean.setUserID(userid);
                bean.setName(rs.getNString("uname"));
                bean.setPassword(rs.getNString("password"));
                bean.setEmail(rs.getNString("email"));
                bean.setCountry(rs.getNString("country"));
                bean.setRole(rs.getNString("urole"));
                companyName = rs.getNString("company");
            }
            
            stmt = conn.prepareStatement(sql2);
            stmt.setString(1, companyName);
            rs = stmt.executeQuery();
            
            Company company = new Company();
            
            if (rs.next()) {
                company.setCompanyName(rs.getNString("companyname"));
                company.setBudget(rs.getInt("budget"));
            }
            
            bean.setCompany(company);
            
            return bean;

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
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    DBConnector.processException(e);
                }
            }
        }
    } // End of method :: getRow() 
    
    public boolean delete(Connection conn, int userid) { 
        
        String sql = "DELETE FROM users WHERE userid = ?";
        
        try ( PreparedStatement stmt = conn.prepareStatement(sql); ) {
        
            stmt.setInt(1, userid);
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
    
} // End of Class :: UserManager
