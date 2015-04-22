/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package layer3.dataSource.mapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import layer2.domain.bean.User;
import layer2.domain.bean.UserAutentication;
import layer2.domain.bean.UserInfo;
import layer2.domain.interfaces.NamingConv;
import layer3.dataSource.DBConnector;

/**
 *
 * @author bo
 */
public class UserAutenticationManager {
    
    
     public Collection getAllRows(Connection conn) { 

        Collection<UserAutentication> rows = new ArrayList();
        
        String sql = "SELECT userID, uname, password, email FROM userAthentication";
//        String sql = "SELECT * FROM userAthentication";
        
        try (   Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery( sql );
            ) {

            while (rs.next()) {

                UserInfoManager uim = new UserInfoManager();
                UserAutentication bean = new UserAutentication();
                
                bean.setUserInfo(uim.getRow(conn, rs.getInt("userID")));
                bean.setUname(rs.getString("uname"));
                bean.setPassword(rs.getString("password"));
                bean.setEmail(rs.getString("email"));
                
                rows.add(bean);
                
                
//                bean.setUserID(rs.getInt("userid"));
//                bean.setUserName(rs.getNString("uname"));
//                bean.setPassWord(rs.getNString("password"));
//                bean.seteMail(rs.getNString("email"));
//                bean.setCountry(rs.getNString("country"));
//                bean.setRole(rs.getNString("urole"));
//                if (rs.getNString("urole").equals(NamingConv.RESELLER)) {
//                bean.setResellerID(cm.getRow(conn, rs.getInt("resellers")) );
//                }
//                
//                rows.add(bean);
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
    
    
    
    
    
    
     public boolean insert(Connection conn, UserAutentication bean) { 

        int rowsInserted = 0;
        
//         String sql1 = "INSERT into users (userid, uname, password, email, country, urole, company) "
//                + "VALUES (?, ?, ?, ?, ?, ?, ?)";
         String sql1 = "INSERT into userAthentication (userID, uname, password, email) "
                + "VALUES (?, ?, ?, ?)";
        
//        // This code is taken from Henrik's DataSourceLayerDemo :: Class OrderMapper.jave
//        //-------------------------------------------------------------------------------
//        String sql2
//                = "select projectSequence.nextval " //= "select orderseq.nextval  "
//                + "from dual";                      // <- this (dual) is a dummy table, and it is needed
//                                                    // because of the select statemant (oracle data-base only)
//        //--------------------------------------------------------------------------------
        ResultSet keys = null;
        try (
                //PreparedStatement stmt = conn.prepareStatement(sql1, Statement.RETURN_GENERATED_KEYS);
                PreparedStatement stmt = conn.prepareStatement(sql1 );
                // Henrik's code (needed to update primary key (oracle data-base only)->
//                PreparedStatement stmt1 = conn.prepareStatement( sql2 );
                ) {

//            //== (Henrik's code) get unique key
//            //--------------------------------
//            keys = stmt1.executeQuery();
//            if (keys.next()) {
//                bean.setUserID(keys.getInt(1)); // <- get the key from dummy table (dual)
//            }
//            else {
//                // ToDo - 
//            }
//            //--------------------------------
     
            
//            //== insert tuple
            stmt.setInt(1, bean.getUserInfo().getUserID());
            stmt.setString(2, bean.getUname());
            stmt.setString(3, bean.getPassword());
            stmt.setString(4, bean.getEmail());
            
            
//            stmt.setString  (1, bean.getFirstName());
//            stmt.setString  (2,  bean.getLastName());
//            stmt.setString  (3, bean.geteMail());
//            stmt.setString  (4, bean.getUserName());
//            stmt.setString  (5, bean.getPassWord());
//            stmt.setString  (6, bean.getCountry());
//            stmt.setString  (7, bean.getRole());//.getCompanyName());    
//            //stmt.
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
    
     
      public UserAutentication getRow(Connection conn, int userid) { 

        String sql = "SELECT * FROM userAthentication WHERE userID = ?";
        ResultSet rs = null;

        try ( PreparedStatement stmt = conn.prepareStatement(sql); ) {
            
            stmt.setInt(1, userid);
            rs = stmt.executeQuery();
            
            if (rs.next()) {
//                CompanyManager cm = new CompanyManager();
//                User bean = new User();
                UserInfoManager uim = new UserInfoManager();
                UserAutentication bean = new UserAutentication();
                
                bean.setUserInfo(uim.getRow(conn, rs.getInt("userID")));
                bean.setUname(rs.getString("uname"));
                bean.setUname(rs.getString("password"));
                bean.setUname(rs.getString("email"));
               
                
//                bean.setUserID(userid);
//                bean.setUserName(rs.getNString("uname"));
//                bean.setPassWord(rs.getNString("password"));
//                bean.seteMail(rs.getNString("email"));
//                bean.setCountry(rs.getNString("country"));
//                bean.setRole(rs.getNString("urole"));
//               // bean.setk( cm.getRow(conn, rs.getNString("company")) );
                
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
     
     
    
    public boolean update(Connection conn, UserAutentication bean) { 

        String sql
                = "UPDATE userAthentication SET "
                + "uname = ?, "
                + "password = ?, "
                + "email = ? "
                + "WHERE userID = ?";
        
        
        
        try ( PreparedStatement stmt = conn.prepareStatement(sql); ) {

//            CompanyManager cm = new CompanyManager();
            UserInfoManager uim = new UserInfoManager();
            
            stmt.setString(1, bean.getUname());
            stmt.setString(2, bean.getPassword());
            stmt.setString(3, bean.getEmail());
            stmt.setInt(4, bean.getUserInfo().getUserID());
            
//            stmt.setString(1, bean.getName());
//            stmt.setString(2, bean.getPassword());
//            stmt.setString(3, bean.getEmail());
//            stmt.setString(4, bean.getCountry());
//            stmt.setString(5, bean.getRole());
//            //stmt.setString(6, bean.getCompany().getCompanyName());
//            stmt.setInt(6, bean.getUserID());
            
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
        
        String sql = "DELETE FROM userAthentication WHERE userID = ?";
        
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
            
        
            String sql = "DELETE FROM userAthentication";

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
    
}