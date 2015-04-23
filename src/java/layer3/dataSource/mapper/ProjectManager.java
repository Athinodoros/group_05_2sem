/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package layer3.dataSource.mapper;

import java.sql.*;
import java.util.ArrayList;
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

        String sql1 = "INSERT into project (projectid, companyName, title,description, stage, sdate, fdate, budget) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

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
            stmt.setString(2, bean.getCompanyName());
            stmt.setString(3, bean.getTitle());
            stmt.setString(4, bean.getDescription());
            stmt.setString(5, bean.getStage());
            stmt.setDate(6, Convert.date2SqlDate(bean.getSdate()));
            stmt.setDate(7, Convert.date2SqlDate(bean.getFdate()));
            stmt.setInt(8, bean.getProjectBudget());            
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
    
    
//    public Project getRow(Connection conn, int projectid){
//        
//        String sql = "SELECT * FROM project WHERE projectid = ?";
//        ResultSet rs = null;
//        Project bean = new Project();
//        
//        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
//            stmt.setInt(1, projectid);
//            rs = stmt.executeQuery();
//            
//            if (rs.next()) {
//                bean.setProjectID(rs.getInt("projectid"));
//                bean.setTitle(rs.getNString("title"));
//                //bean.setFdate();
//                //bean.setEndDate(Convert.sqlDate2Date(rs.getDate("enddate")));
//                bean.setStage(rs.getNString("stage"));
//                bean.setProjectBudget(rs.getInt("budget"));
//                bean.setCompanyName(rs.getNString("companyName"));
//                bean.setDescription(rs.getNString("description"));
    
    
//    
//                
//                int authorID = rs.getInt("authorid");
//                UserManager userManager = new UserManager();
//                User author = userManager.getRow(conn, authorID);
//                bean.setAuthor(author);
//            }
//            
//            return bean;
//            
//        } catch (SQLException e) {
//            DBConnector.processException(e);
//            return null;
//        } finally {
//            if (rs != null) {
//                try {
//                    rs.close();
//                } catch (SQLException ex) {
//                    DBConnector.processException(ex);
//                }
//            }
//        }
//    } // End of method :: getRow()
//    
//    
//    public boolean delete(Connection conn, int projectid){
//        String sql = "DELETE FROM project WHERE projectid = ?";
//        
//        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
//            stmt.setInt(1, projectid);
//            
//            int affected = stmt.executeUpdate();
//            if (affected == 1) {
//                return true;
//            }
//            else{
//                return false;
//            }
//        } catch (SQLException e) {
//            DBConnector.processException(e);
//            return false;
//        }
//    } // End of method :: delete()
//    
//    
//    public boolean update(Connection conn, Project bean){
//        String sql = "UPDATE project SET companyName = ? , title = ? , description = ? , stage = ? , fdate = ?, sdate = ?, projectBudget = ?  WHERE projectid = ?";
//        
//        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
//            
//            stmt.setString(1, bean.getCompanyName());
//            stmt.setString(2, bean.getTitle());
//            stmt.setString(3, bean.getDescription());
//            stmt.setString(4, bean.getStage());
//            stmt.setDate(5, Convert.date2SqlDate(bean.getSdate()));
//            stmt.setDate(6, Convert.date2SqlDate(bean.getFdate()));
//            stmt.setInt(7, bean.getProjectBudget()); 
//            
//            int affected = stmt.executeUpdate();
//            if (affected == 1) {
//                return true;
//            }
//            else{
//                return false;
//            }
//        } catch (SQLException e) {
//            DBConnector.processException(e);
//            return false;
//        }
//    }
    public ArrayList<Project> getAllRows(Connection conn){
        
        String sql = "SELECT * FROM project";
        ResultSet rs = null;
        
        ArrayList<Project> allproj = new ArrayList<Project>();
        
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            //stmt.setInt(1, projectid);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                Project bean = new Project();
                bean.setProjectID(rs.getInt("projectid"));
                bean.setTitle(rs.getNString("title"));
                //bean.setFdate();
                //bean.setEndDate(Convert.sqlDate2Date(rs.getDate("enddate")));
                bean.setStage(rs.getNString("stage"));
                bean.setProjectBudget(rs.getInt("budget"));
                bean.setCompanyName(rs.getNString("companyName"));
                bean.setDescription(rs.getNString("description"));
                
                int authorID = rs.getInt("authorid");
                UserManager userManager = new UserManager();
                User author = userManager.getRow(conn, authorID);
                //bean.setAuthor(author);
                allproj.add(bean);
            }
            return allproj;
            
            
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
    }// End of method :: update()
} // End of Class :: ProjectManager+

