/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package layer3.dataSource.mapper;

import java.sql.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import layer2.domain.bean.Partner;
import layer2.domain.bean.Project;
import layer3.dataSource.DBConnector;
import layer3.dataSource.utility.Convert;
import oracle.sql.DATE;

/**
 *
 * @author bo
 */
public class ProjectManager {

    public boolean insert(Connection conn, Project bean) {

        int rowsInserted = 0;


String sql1 = "INSERT into project (projectID, companyName, title, description, stage, sdate, fdate, projectBudget) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
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
            stmt.setString(2, bean.getPartner().getCompanyName());
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
       
    
    public Project getRow(Connection conn, int projectid) {
        
        String sql = "SELECT * FROM project WHERE projectID = ?";
        ResultSet rs = null;
        Project bean = new Project();
        
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, projectid);
            rs = stmt.executeQuery();
            
            if (rs.next()) {
                Partner parter = new Partner();
                PartnerManager pm = new PartnerManager();
                
                bean.setProjectID(projectid);
                bean.setPartner(pm.getRow(conn, rs.getString("companyName")));
                bean.setTitle(rs.getString("title"));
                bean.setDescription(rs.getString("description"));
                bean.setStage(rs.getString("stage"));
                bean.setSdate(rs.getDate("sdate"));
                bean.setFdate(rs.getDate("fdate"));
                bean.setProjectBudget(rs.getInt("projectBudget"));
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
    
    
    public boolean delete(Connection conn, int projectid){
        String sql = "DELETE FROM project WHERE projectID = ?";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, projectid);
            
            int affected = stmt.executeUpdate();
            if (affected == 1) {
                return true;
            }
            else{
                return false;
            }
        } catch (SQLException e) {
            DBConnector.processException(e);
            return false;
        }
    } // End of method :: delete()
    
    
    public boolean update(Connection conn, Project bean){
        
        String sql = "UPDATE project SET companyName = ? , title = ? , description = ? , stage = ? , sdate = ?, fdate = ?, projectBudget = ?  WHERE projectID = ?";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, bean.getPartner().getCompanyName());
            stmt.setString(2, bean.getTitle());
            stmt.setString(3, bean.getDescription());
            stmt.setString(4, bean.getStage());
            stmt.setDate(5, Convert.date2SqlDate(bean.getSdate()));
            stmt.setDate(6, Convert.date2SqlDate(bean.getFdate()));
            stmt.setInt(7, bean.getProjectBudget()); 
            stmt.setInt(8, bean.getProjectID());
            
            int affected = stmt.executeUpdate();
            if (affected == 1) {
                return true;
            }
            else{
                return false;
            }
        } catch (SQLException e) {
            DBConnector.processException(e);
            return false;
        }
    }
   
    
    
    public Collection getAllRows(Connection conn) { 

        Collection<Project> rows = new ArrayList();
        

        String sql = "SELECT * FROM project";
        
        try (   Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery( sql );
            ) {

            while (rs.next()) {
//                CompanyManager cm = new CompanyManager();
//                User bean = new User();
                PartnerManager pm = new PartnerManager();
                Project bean = new Project();
                
                bean.setProjectID(rs.getInt("projectID"));
                bean.setPartner(pm.getRow(conn, rs.getString("companyName")));
                bean.setTitle(rs.getString("title"));
                bean.setDescription(rs.getString("description"));
                bean.setStage(rs.getString("stage"));
                bean.setSdate(rs.getDate("sdate"));
                bean.setFdate(rs.getDate("fdate"));
                bean.setProjectBudget(rs.getInt("projectBudget"));
                
                rows.add(bean);
            }
            
        } catch (SQLException e) {
            DBConnector.processException(e);
        }
        return rows;
    } // End of method :: getAllRows()
    
    
    public int deleteAllRows(Connection conn, String confirm){
        
        if( confirm.equalsIgnoreCase("yes")) {
            
            String sql = "DELETE FROM project";
            
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
    
} // End of Class :: ProjectManager+

