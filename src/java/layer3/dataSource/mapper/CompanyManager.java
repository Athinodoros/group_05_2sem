/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package layer3.dataSource.mapper;

import java.sql.*;
import layer2.domain.bean.Company;
import layer3.dataSource.DBConnector;

/**
 *
 * @author bo
 */
public class CompanyManager {
    
     public boolean insert(Connection conn, Company bean) { 

        int rowsInserted = 0;
        
         String sql1 = "INSERT into companies (companyname, budget) "
                + "VALUES (?, ?)";
        
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

            
//            //== (Henrik's code) get unique key
//            //--------------------------------
//            keys = stmt1.executeQuery();
//            if (keys.next()) {
//                bean.setUserID(keys.getInt(1)); // <- get the key from dummy table (dual)
//            }
//            else {
//                // ToDo - 
//            }
            //--------------------------------
     
            //== insert tuple
            stmt.setString  (1, bean.getCompanyName());
            stmt.setInt     (2, bean.getBudget());       
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
    
    
}
