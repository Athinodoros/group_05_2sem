/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package layer2.domain.bean;

import java.io.File;

/**
 *
 * @author Athinodoros
 */
public class POE {
    
    private Project project;
    private String filePath;

    
    // Constructors ->
    public POE() {}
    
    public POE(Project project, String filePath) {
        this.project = project;
        this.filePath = filePath;
    }

    public POE(POE p) {
        this.project = p.project;
        this.filePath = p.filePath;
    }

    // Getters and setters -> 
    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
    
    // ->
    @Override
    public String toString() {
        return "POE{" + "project=" + project + ", filePath=" + filePath + '}';
    }

    
} // End of Class
