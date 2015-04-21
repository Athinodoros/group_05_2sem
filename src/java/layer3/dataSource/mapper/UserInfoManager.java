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
import layer2.domain.bean.UserInfo;
import layer3.dataSource.DBConnector;

/**
 *
 * @author bo
 */
public class UserInfoManager {

    
    
    public Collection getAllRows(Connection conn) {

        Collection<UserInfo> rows = new ArrayList();

        String sql = "SELECT userID, firstname, lastname, country, urole FROM userInfo";

        try (Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);) {

            while (rs.next()) {
                UserInfo userInfo = new UserInfo();

                userInfo.setUserID(rs.getInt("userID"));
                userInfo.setFirstname(rs.getString("firstname"));
                userInfo.setLastname(rs.getString("lastname"));
                userInfo.setCountry(rs.getString("country"));
                userInfo.setUrole(rs.getString("urole"));
                rows.add(userInfo);
            }

        } catch (SQLException e) {
            DBConnector.processException(e);
        }
        return rows;
    } // End of method :: gitAllRows()

    
     public boolean insert(Connection conn, UserInfo bean) { 

        int rowsInserted = 0;
        
//         String sql1 = "INSERT into users (userid, uname, password, email, country, urole, company) "
//                + "VALUES (?, ?, ?, ?, ?, ?, ?)";
         String sql1 = "INSERT into userInfo (userID, firstname, lastname, country, urole) "
                + "VALUES (?, ?, ?, ?, ?)";
        
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
            stmt.setString  (2, bean.getFirstname());
            stmt.setString  (3, bean.getLastname());
            stmt.setString  (4, bean.getCountry());
            stmt.setString  (5, bean.getUrole());
              
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
    
    
    
    
    
    public UserInfo getRow(Connection conn, int userID) {

        String sql = "SELECT * FROM userInfo WHERE userID = ?";
        ResultSet rs = null;

        try (PreparedStatement stmt = conn.prepareStatement(sql);) {

            stmt.setInt(1, userID);
            rs = stmt.executeQuery();

            if (rs.next()) {
                UserInfo bean = new UserInfo();
                
                bean.setUserID(userID);
                bean.setFirstname(rs.getString("firstname"));
                bean.setLastname(rs.getString("lastname"));
                bean.setCountry(rs.getString("country"));
                bean.setUrole(rs.getString("urole"));
                
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

    public boolean update(Connection conn, UserInfo bean) {

        String sql
                = "UPDATE userInfo SET "
                + "firstname = ?, "
                + "lastname = ?, "
                + "country = ?, "
                + "urole = ? "
                + "WHERE userID = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql);) {

            stmt.setString(1, bean.getFirstname());
            stmt.setString(2, bean.getLastname());
            stmt.setString(3, bean.getCountry());
            stmt.setString(4, bean.getUrole());
            stmt.setInt(5, bean.getUserID());

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

    
    
    
    public boolean delete(Connection conn, int userID) {

        String sql = "DELETE FROM userInfo WHERE userID = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql);) {

            stmt.setInt(1, userID);
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

    public boolean deleteAllRows(Connection conn, String confirm) {

        if (confirm.equalsIgnoreCase("yes")) {

            String sql = "DELETE FROM userInfo";

            try (PreparedStatement stmt = conn.prepareStatement(sql);) {

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
        } else {
            return false;
        }
    } // End of method :: Delete() 

}
