/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package layer3.dataSource.mapper;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import layer2.domain.bean.Project;
import layer2.domain.bean.User;
import layer3.dataSource.DBConnector;
import layer3.dataSource.utility.Convert;

/**
 *
 * @author bo
 */
public class ProjectManager {

    public boolean insert(Connection conn, Project bean) {

        int rowsInserted = 0;

        String sql1 = "INSERT into projects (projectid, authorid, title, startdate, enddate, stage, budget, poe, comments) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        // This code is taken from Henrik's DataSourceLayerDemo :: Class OrderMapper.jave
        //-------------------------------------------------------------------------------
        String sql2
                = "select projectSequence.nextval " //= "select orderseq.nextval  "
                + "from dual";                      // <- this (dual) is a dummy table, and it is needed
        // because of the select statemant (oracle data-base only)
        //--------------------------------------------------------------------------------

        ResultSet keys = null;
        try (
                PreparedStatement stmt = conn.prepareStatement(sql1, Statement.RETURN_GENERATED_KEYS);
                PreparedStatement stmt1 = conn.prepareStatement(sql2);) {

            //== (Henrik's code) get unique key
            //--------------------------------
            keys = stmt1.executeQuery();
            if (keys.next()) {
                bean.setProjectID(keys.getInt(1)); // <- get the key from dummy table (dual)
            } else {
                // ToDo - 
            }
            //--------------------------------

            
            //== insert tuple
            stmt.setInt(1, bean.getProjectID());
            stmt.setInt(2, bean.getAuthor().getUserID());
            stmt.setString(3, bean.getTitle());
            stmt.setDate(4, Convert.date2SqlDate(bean.getStartDate()));
            stmt.setDate(5, Convert.date2SqlDate(bean.getEndDate()));
            stmt.setString(6, bean.getStage());
            stmt.setInt(7, bean.getBudget());
            stmt.setString(8, Convert.Boolean2String(bean.hasPOE()));
            stmt.setString(9, bean.getComments());
            rowsInserted = stmt.executeUpdate();

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
    
    
    public Project getRow(Connection conn, int projectid){
        
        String sql = "SELECT * FROM projects WHERE projectid = ?";
        ResultSet rs = null;
        Project bean = new Project();
        
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, projectid);
            rs = stmt.executeQuery();
            
            if (rs.next()) {
                bean.setProjectID(rs.getInt("projectid"));
                bean.setTitle(rs.getNString("title"));
                bean.setStartDate(Convert.sqlDate2Date(rs.getDate("startdate")));
                bean.setEndDate(Convert.sqlDate2Date(rs.getDate("enddate")));
                bean.setStage(rs.getNString("stage"));
                bean.setBudget(rs.getInt("budget"));
                bean.setPOE(Convert.String2Boolean(rs.getNString("poe")));
                bean.setComments(rs.getNString("comments"));
                
                int authorID = rs.getInt("authorid");
                UserManager userManager = new UserManager();
                User author = userManager.getRow(conn, authorID);
                bean.setAuthor(author);
            }
            
            return bean;
            
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
    } // End of method :: getRow()
} // End of Class :: ProjectManager
