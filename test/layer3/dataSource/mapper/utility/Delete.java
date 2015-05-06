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
    private static final CommentManager commentManager = new CommentManager();
    private static final POEManager poeManager = new POEManager();
    
    private Delete() {}
    
    
    public static int database(Connection conn, String confirm ) {
        
        // Delete all data in the database (should be inserted in drop order)
        
        int affected = 0;
        
        affected =+ poeManager.deleteAllRows(conn, confirm);
        affected =+ commentManager.deleteAllRows(conn, confirm);
        affected =+ projectManager.deleteAllRows(conn, confirm);
        affected =+ budgetManager.deleteAllRows(conn, confirm);
        affected =+ userAutenticationManager.deleteAllRows(conn, confirm);
        affected =+ userInforManager.deleteAllRows(conn, confirm);
        affected =+ partnerManager.deleteAllRows(conn, confirm);
        
        return affected;
    }
    
} // End of class :: Delete
