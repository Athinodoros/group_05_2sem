/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package layer2.domain.bean;

import java.util.Date;

/**
 *
 * @author Bancho
 */
public class Project {
 int   projectID;
String companyName;
 String title;
String description;
String stage;
Date sdate;
Date fdate;
int projectBudget;

    public Project(int projectID, String companyName, String title, String description, String stage, Date sdate, Date fdate, int projectBudget) {
        this.projectID = projectID;
        this.companyName = companyName;
        this.title = title;
        this.description = description;
        this.stage = stage;
        this.sdate = sdate;
        this.fdate = fdate;
        this.projectBudget = projectBudget;
    }

    public int getProjectID() {
        return projectID;
    }

    public void setProjectID(int projectID) {
        this.projectID = projectID;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public Date getSdate() {
        return sdate;
    }

    public void setSdate(Date sdate) {
        this.sdate = sdate;
    }

    public Date getFdate() {
        return fdate;
    }

    public void setFdate(Date fdate) {
        this.fdate = fdate;
    }

    public int getProjectBudget() {
        return projectBudget;
    }

    public void setProjectBudget(int projectBudget) {
        this.projectBudget = projectBudget;
    }

    @Override
    public String toString() {
        return "Project{" + "projectID=" + projectID + ", companyName=" + companyName + ", title=" + title + ", description=" + description + ", stage=" + stage + ", sdate=" + sdate + ", fdate=" + fdate + ", projectBudget=" + projectBudget + '}';
    }
    
    
    
}
