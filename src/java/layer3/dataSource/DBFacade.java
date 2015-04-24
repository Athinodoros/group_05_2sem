/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package layer3.dataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import layer2.domain.bean.Project;
import layer2.domain.bean.User;
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
    
    
    
    public boolean createProject(Project newProject) {
        return projectManager.insert(conn, newProject);
    }
    public String getName(int projectid){
    
    return projectManager.getName(conn, projectid);
    } 
    
//    public boolean editProject(Project project) {
//        return projectManager.update(conn, project);
//    }
    
    //dummy methods start here
    public User getAdmin(){
        return userManager.getRow(conn, 83);
    }
    
    public User getReseller(){
        return userManager.getRow(conn, 84);
    }
    
//    public ArrayList<Project> getAllProjects(){
//    
//        return projectManager.getAllRows(conn);
//    }
    //dummy methods end here
    
} // End of Class :: DBFacade
