/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package layer3.dataSource;

import java.sql.Connection;
import java.sql.SQLException;
import layer3.dataSource.mapper.CompanyManager;
import layer3.dataSource.mapper.ProjectManager;
import layer3.dataSource.mapper.UserManager;
//import layer2.domain.bean.Admin;
//import layer3.dataSource.mapper.AdminManager;

/**
 *
 * @author bo
 */
public class DBFacade {
    
    //private AdminManager adminManager;
    private CompanyManager  companyManager;
    private ProjectManager  projectManager;
    private UserManager     userManager;
    
    private Connection conn;
    
// Singleton Start --> 
    
    private DBFacade() {
        
        DBConnector.getInstance().setDBType(DBType.ORACLE_THIN);
        conn = DBConnector.getInstance().getConnection();
        
        //adminManager = new AdminManager();
        companyManager  = new CompanyManager();
        projectManager  = new ProjectManager();
        userManager     = new UserManager();
    }
    
    public static DBFacade getInstance() {
        return DBFacadeHolder.INSTANCE;
    }
    
    private static class DBFacadeHolder {

        private static final DBFacade INSTANCE = new DBFacade();
    }

// <-- End of Singleton
    
    
    
    // Do some work here ->
    
//    public void displayAllRows() {
//        
//        adminManager.displayAllRows(conn);
//    }
//    
//     public Admin getRow(int adminId) {
//        
//        return adminManager.getRow(conn, adminId);
//    }
//    
//     public boolean delete(int adminId) {
//        
//        return adminManager.delete(conn, adminId);
//    }
//    
//    public boolean insert(Admin bean) {
//        
//        return adminManager.insert(conn, bean);
//    }
//    
//    public boolean update(Admin bean) {
//        
//        return adminManager.update(conn, bean);
//    }
    
} // End of Class :: DBFacade
