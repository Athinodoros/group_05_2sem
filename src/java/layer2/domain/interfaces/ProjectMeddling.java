/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package layer2.domain.interfaces;

import layer2.domain.bean.Project;

/**
 *
 * @author Bancho
 */
public interface ProjectMeddling {
    //this interface should be implemented by both the Controller and the DB_Facade
    
    public boolean createProject(Project project);
    
}
