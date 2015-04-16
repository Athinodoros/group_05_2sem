/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package layer3.dataSource.mapper.utility;

import java.sql.Connection;
import layer3.dataSource.mapper.CompanyManager;
import layer3.dataSource.mapper.ProjectManager;
import layer3.dataSource.mapper.UserManager;

/**
 *
 * @author bo
 */
public class Delete {
    
    private static final CompanyManager companyManager  = new CompanyManager();
    private static final UserManager    userManager     = new UserManager();
    private static final ProjectManager projectManager  = new ProjectManager();
    
    
    private Delete() {}
    
    
    public static void database(Connection conn, String confirm ) {
        
        // Delete all data in the database
        
        //projectManager.deleteAllRows(conn, confirm);
        userManager.deleteAllRows(conn, confirm);
        companyManager.deleteAllRows(conn, confirm);
    }
    
    
    
} // End of class :: Delete
