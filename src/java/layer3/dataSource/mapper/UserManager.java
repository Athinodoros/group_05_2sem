/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package layer3.dataSource.mapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import layer2.domain.bean.Reseller;
import layer2.domain.bean.User;
import layer2.domain.interfaces.NamingConv;
import layer3.dataSource.DBConnector;
import layer3.dataSource.utility.Convert;

/**
 *
 * @author bo
 */
public class UserManager {
    
    public Collection getAllRows(Connection conn) { 

        Collection<User> rows = new ArrayList();
        
        String sql = "SELECT userid, uname, password, email, country, urole, company FROM users";
//        String sql = "SELECT * FROM users";
        
        try (   Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery( sql );
            ) {

            while (rs.next()) {
                CompanyManager cm = new CompanyManager();
                User bean = new User();
              
                bean.setUserID(rs.getInt("userid"));
                bean.setUserName(rs.getNString("uname"));
                bean.setPassWord(rs.getNString("password"));
                bean.seteMail(rs.getNString("email"));
                bean.setCountry(rs.getNString("country"));
                bean.setRole(rs.getNString("urole"));
                
                
                rows.add(bean);
////                Reseller company = new Reseller();
//                User user = new User();
//                
//                
//                
////                company.setCompanyName(rs.getString("companyname"));
////                company.setBudget(rs.getInt("budget"));
////                rows.add(company);
                
                
                
                
                                                                                        
               
            }
            
        } catch (SQLException e) {
            DBConnector.processException(e);
        }
        return rows;
    } // End of method :: gitAllRows()
    
    
    
    
    
    
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
            stmt.setString  (1, bean.getFirstName());
            stmt.setString  (2,  bean.getLastName());
            stmt.setString  (3, bean.geteMail());
            stmt.setString  (4, bean.getUserName());
            stmt.setString  (5, bean.getPassWord());
            stmt.setString  (6, bean.getCountry());
            stmt.setString  (7, bean.getRole());//.getCompanyName());    
            //stmt.
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
                CompanyManager cm = new CompanyManager();
                User bean = new User();
                
                bean.setUserID(userid);
                bean.setUserName(rs.getNString("uname"));
                bean.setPassWord(rs.getNString("password"));
                bean.seteMail(rs.getNString("email"));
                bean.setCountry(rs.getNString("country"));
                bean.setRole(rs.getNString("urole"));
               // bean.setk( cm.getRow(conn, rs.getNString("company")) );
                
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
     
     
    
    public boolean update(Connection conn, User bean) { 

        String sql
                = "UPDATE users SET "
                + "uname = ?, "
                + "password = ?, "
                + "email = ?, "
                + "country = ?, "
                + "urole = ? "
                // + "company = ? "
                + "WHERE userid = ?";
        
        
        
        try ( PreparedStatement stmt = conn.prepareStatement(sql); ) {

            CompanyManager cm = new CompanyManager();
            
            //stmt.setString(1, bean.getLastName());
            //stmt.setString(2, bean.getPassword());
            //stmt.setString(3, bean.getEmail());
            stmt.setString(4, bean.getCountry());
            stmt.setString(5, bean.getRole());
            //stmt.setString(6, bean.getCompany().getCompanyName());
            stmt.setInt(6, bean.getUserID());
            
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
    
    public boolean deleteAllRows(Connection conn, String confirm) { 
        
        if( confirm.equalsIgnoreCase("yes") ) {
            
        
            String sql = "DELETE FROM users";

            try ( PreparedStatement stmt = conn.prepareStatement(sql); ) {

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
        }
        else {
            return false;
        }
    } // End of method :: DeleteAllRows() 
    
    
} // End of Class :: UserManager
