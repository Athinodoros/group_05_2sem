/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package layer3.dataSource.mapper;

import java.sql.*;
import layer2.domain.bean.Project;
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
                
                // Henrik's code (needed to update primary key (oracle data-base only)->
                PreparedStatement stmt1 = conn.prepareStatement( sql2 );
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
     
            //== insert tuple
            stmt.setInt     (1, bean.getProjectID());
            stmt.setNull    (2, bean.getAuthor());
            stmt.setString  (3, bean.getTitle());
            stmt.setDate    (4, Convert.date2SqlDate(bean.getStartDate()));
            stmt.setDate    (5, Convert.date2SqlDate(bean.getEndDate()));
            stmt.setString  (6, bean.getStage());
            stmt.setInt     (7, bean.getBudget());
            stmt.setString  (8, Convert.Boolean2String(bean.hasPOE()));
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
} // End of Class :: ProjectManager
