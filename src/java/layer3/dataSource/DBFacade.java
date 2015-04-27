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
import layer2.domain.bean.UserInfo;
import layer3.dataSource.mapper.CompanyManager;
import layer3.dataSource.mapper.ProjectManager;
import layer3.dataSource.mapper.UserInfoManager;

/**
 *
 * @author bo
 */
public class DBFacade {
    
    private CompanyManager  companyManager;
    private ProjectManager  projectManager;
    private UserInfoManager     userInfoManager;
    
    private Connection conn;
    
// Singleton Start --> 
    
    private DBFacade() {
        
        DBConnector.getInstance().setDBType(DBType.ORACLE_THIN);
        conn = DBConnector.getInstance().getConnection();
        
        companyManager  = new CompanyManager();
        projectManager  = new ProjectManager();
        userInfoManager     = new UserInfoManager();
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
    
    public Project getProject(int projectid){
        return projectManager.getRow(conn, projectid);
    }
    
    
    //dummy log-in methods start here
    
    public UserInfo getAdmin(){
        return userInfoManager.getRow(conn, 83);
    }
    
    public UserInfo getReseller(){
        return userInfoManager.getRow(conn, 84);
    }
    
    //dummy log-in methods end here
    
    
    
//    public ArrayList<Project> getAllProjects(){
//    
//        return projectManager.getAllRows(conn);
//    }
    
    
//    public boolean editProject(Project project) {
//        return projectManager.update(conn, project);
//    }
    
    
} // End of Class :: DBFacade
