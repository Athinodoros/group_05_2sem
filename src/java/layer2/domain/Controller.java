/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package layer2.domain;

import java.util.ArrayList;
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
    
    
    public boolean createProject(Project newProject){
        return dbf.createProject(newProject);
    }
    
//    public boolean editProject(Project project) {
//        return dbf.editProject(project);
//    }
    
    //dummy methods start here
    public User getAdmin(){
        return dbf.getAdmin();
    }
    
    public User getReseller(){
        return dbf.getReseller();
    }
    
//    public ArrayList<Project> getAllProjects(){
//        return dbf.getAllProjects();
//    }
    //dummy methods end here
    
}
