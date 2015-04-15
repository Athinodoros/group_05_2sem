/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package layer2.domain;

import layer2.domain.bean.Project;
import layer2.domain.bean.User;
import layer3.dataSource.DBFacade;

/**
 *
 * @author Bancho
 */
public class Controller {
    
    private DBFacade dbf;
    
    public Controller() {
        dbf = DBFacade.getInstance();
    }
    
    
    public boolean createProject(Project newProject, User creator){
        return dbf.createProject(newProject, creator);
    }
    
    public boolean editProject(Project project) {
        return dbf.editProject(project);
    }
    
    //dummy methods start here
    public User getAdmin(){
        return dbf.getAdmin();
    }
    
    public User getReseller(){
        return dbf.getReseller();
    }
    //dummy methods end here
    
}
