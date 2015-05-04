/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package layer3.dataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import layer2.domain.bean.POE;
import layer2.domain.bean.Partner;
import layer2.domain.bean.Project;
import layer2.domain.bean.UserAuthentication;
import layer2.domain.bean.UserInfo;
import layer3.dataSource.mapper.POEManager;
import layer3.dataSource.mapper.PartnerManager;
import layer3.dataSource.mapper.ProjectManager;
import layer3.dataSource.mapper.UserAutenticationManager;
import layer3.dataSource.mapper.UserInfoManager;

/**
 *
 * @author bo
 */
public class DBFacade {

    private Connection conn;
    
    private ProjectManager projectManager;
    private UserInfoManager userInfoManager;
    private UserAutenticationManager userAthMan;
    private PartnerManager partnerManager;
    private POEManager poeManager;


// Singleton Start --> 
    private DBFacade() {

        DBConnector.getInstance().setDBType(DBType.ORACLE_THIN);
        conn = DBConnector.getInstance().getConnection();

        partnerManager = new PartnerManager();
        projectManager = new ProjectManager();
        userInfoManager = new UserInfoManager();
        userAthMan = new UserAutenticationManager();
        poeManager = new POEManager();
    }

    public static DBFacade getInstance() {
        return DBFacadeHolder.INSTANCE;
    }

    private static class DBFacadeHolder {
        private static final DBFacade INSTANCE = new DBFacade();
    }
// <-- End of Singleton
    
    private Connection getConnection(){
        try {
            if (conn.isClosed()) {
                conn = DBConnector.getInstance().getConnection();
            }
        } catch (SQLException ex) {
            DBConnector.processException(ex);
        }
        return conn;
    }
    
    public void closeConnection() {
        DBConnector.getInstance().close();
    }
    
    public boolean createProject(Project newProject) {
        return projectManager.insert(getConnection(), newProject);
    }

    public Project getProject(int projectid) {
        return projectManager.getRow(getConnection(), projectid);
    }

    public boolean createPartner(Partner partner) {
        return partnerManager.insert(getConnection(), partner);
    }

    public Collection getAllProjects() {
        return projectManager.getAllRows(getConnection());
    }

    public boolean editProject(Project project) {
        return projectManager.update(getConnection(), project);
    }

    public Collection<Partner> getAllPartners() {
        return partnerManager.getAllRows(getConnection());
    }

    public boolean createPOE(POE poe) {
        return poeManager.insert(getConnection(), poe);
    }

    public boolean createUserInfo(UserInfo ui) {
        return userInfoManager.insert(getConnection(), ui);
    }

    public UserInfo getUserInfo(int userid) {
        return userInfoManager.getRow(getConnection(), userid);
    }

    public UserAuthentication getUserAuthentication(String username) {
        return userAthMan.getRow(getConnection(), username);
    }

    public boolean createUserAth(UserAuthentication ua) {
        return userAthMan.insert(getConnection(), ua);
    }

} // End of Class :: DBFacade
