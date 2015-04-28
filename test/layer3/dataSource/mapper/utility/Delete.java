/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package layer3.dataSource.mapper.utility;

import java.sql.Connection;
import layer2.domain.bean.UserAuthentication;
import layer3.dataSource.mapper.*;
//import layer3.dataSource.mapper.CompanyManager;
//import layer3.dataSource.mapper.ProjectManager;
//import layer3.dataSource.mapper.UserManager;

/**
 *
 * @author bo
 */
public class Delete {
    
    private static final PartnerManager partnerManager = new PartnerManager();
    private static final UserInfoManager userInforManager = new UserInfoManager();
    private static final UserAutenticationManager userAutenticationManager = new UserAutenticationManager();
    private static final BudgetManager budgetManager = new BudgetManager();
    private static final ProjectManager projectManager = new ProjectManager();
    private static final commentManager commentManager = new commentManager();
    
    private Delete() {}
    
    
    public static void database(Connection conn, String confirm ) {
        
        // Delete all data in the database (should be inserted in drop order)
        
        projectManager.deleteAllRows(conn, confirm);
        budgetManager.deleteAllRows(conn, confirm);
        userAutenticationManager.deleteAllRows(conn, confirm);
        userInforManager.deleteAllRows(conn, confirm);
        partnerManager.deleteAllRows(conn, confirm);
        commentManager.deleteAllRows(conn, confirm);
    }
    
    
    
} // End of class :: Delete
