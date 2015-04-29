/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package layer2.domain;

import java.util.Collection;
import layer2.domain.bean.Partner;
import layer2.domain.bean.Project;
import layer2.domain.bean.UserInfo;
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
    public boolean createPartner(Partner partner){
        return dbf.createPartner(partner);
    
    }
    
    public boolean editProject(Project project) {
        return dbf.editProject(project);
    }
    
    
    public Collection getAllProjects(){
        return dbf.getAllProjects();
    }
    
    
    //dummy log-in methods start here
    public UserInfo getAdmin(){
        return dbf.getAdmin();
    }
    
    public UserInfo getReseller(){
        return dbf.getReseller();
    }
    //dummy log-in methods end here
    
    
    public String getFileDirec(int projectid){
        Project project = dbf.getProject(projectid);
        String companyName = project.getPartner().getCompanyName();
        return companyName + projectid;
    
    }
    
    public Collection<Partner> getAllPartners(){
      return dbf.getAllPartners();
      
        
    }
}
