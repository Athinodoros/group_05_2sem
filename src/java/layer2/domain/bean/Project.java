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
    
    private int projectID;
    private int author;
    private String title;
    private Date startDate;
    private Date endDate;
    private String stage;
    private int budget;
    private boolean hasPOE;
    private String comments;

    public Project(int projectID, int author, String title, Date startDate, Date endDate, String stage, int budget, boolean hasPOE, String comments) {
        this.projectID = projectID;
        this.author = author;
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.stage = stage;
        this.budget = budget;
        this.hasPOE = hasPOE;
        this.comments = comments;
    }

    public int getProjectID() {
        return projectID;
    }

    public int getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public String getStage() {
        return stage;
    }

    public int getBudget() {
        return budget;
    }

    public boolean hasPOE() {
        return hasPOE;
    }

    public String getComments() {
        return comments;
    }

    public void setProjectID(int projectID) {
        this.projectID = projectID;
    }

    public void setAuthor(int author) {
        this.author = author;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public void setPOE(boolean hasPOE) {
        this.hasPOE = hasPOE;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
    
    
    
}
