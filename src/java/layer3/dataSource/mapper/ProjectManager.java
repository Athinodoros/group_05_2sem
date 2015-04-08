/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package layer3.dataSource.mapper;

import java.sql.*;
import layer2.domain.bean.Project;
import layer3.dataSource.DBConnector;

/**
 *
 * @author bo
 */
public class ProjectManager {
    
    public boolean insert(Connection conn, Project bean) { 

        int rowsInserted = 0;  
        // This code is taken from Henrik's DataSourceLayerDemo :: Class OrderMapper.jave
        //-------------------------------------------------------------------------------
        String sql1
                = "select adminSeq.nextval "//= "select orderseq.nextval  "
                + "from dual";              // <- this (dual) is a dummy table, and it is needed
                                            // because of the select statemant (oracle data-base only)
        //--------------------------------------------------------------------------------
        
        String sql = "INSERT into projects (projectid, authorid, title, startdate, enddate, stage, budget, poe, comments) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        ResultSet keys = null;
        try (
                PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                
                // Henrik's code (needed to update primary key (oracle data-base only)->
                PreparedStatement stmt1 = conn.prepareStatement( sql1 );
                ) {

            
            //== (Henrik's code) get unique key
            //--------------------------------
            keys = stmt1.executeQuery();
            if (keys.next()) {
                bean.setProjectID(keys.getInt(1)); // <- get the key from dummy table (dual)
            }
            else {
                // ToDo - 
            }
            //--------------------------------
// String sql = "INSERT into projects (projectid, authorid, title, "
//         + "startdate, enddate, stage, budget, poe, comments) "          
            //== insert tuple
            stmt.setInt     (1, bean.getProjectID());
            stmt.setInt     (2, bean.getAuthorID());
            stmt.setString  (3, bean.getTitle());
            stmt.setDate    (4, convertJavaDateToSqlDate( bean.getStartDate()));
            stmt.setDate    (5, convertJavaDateToSqlDate( bean.getEndDate()));
            stmt.setString  (6, bean.getStage());
            stmt.setInt     (7, bean.getBudget());
            stmt.setString  (8, bean.getPOE());
            stmt.setString  (9, bean.getComments());
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
    
    public java.sql.Date convertJavaDateToSqlDate(java.util.Date date) {
    return new java.sql.Date(date.getTime());
}
    
    
} // End of Class :: ProjectManager
