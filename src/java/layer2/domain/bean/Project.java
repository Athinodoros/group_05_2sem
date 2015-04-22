/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package layer2.domain.bean;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import oracle.sql.DATE;

/**
 *
 * @author Bancho
 */
public class Project {
    private String mydate="yyyy-mm-dd";
    private int projectID;
    private String companyName;
    private String title;
    private String description;
    private String stage;
    private Date sdate ;
    private Date fdate;
    private int projectBudget;
    private boolean hasPOE;
    private String comments;
    
    //private d
    //private User user;
    public Project() {
        
    }

    public Project(int projectID, String companyName, String title, String description, String stage, Date sdate, Date fdate, int projectBudget, boolean hasPOE, String comments) {
        this.projectID = projectID;
        this.companyName = companyName;
        this.title = title;
        this.description = description;
        this.stage = stage;
        this.sdate = sdate;
        this.fdate = fdate;
        this.projectBudget = projectBudget;
        this.hasPOE = hasPOE;
        this.comments = comments;
        
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

    public void setSdate(String sdate) throws ParseException{
        DateFormat df = new SimpleDateFormat(mydate);
        this.sdate = df.parse("yyy-mm-dd");
    }

    public Date getFdate() {
        return fdate;
    }

    public void setFdate(String fdate) throws ParseException{
        DateFormat df = new SimpleDateFormat(mydate);
        this.fdate = df.parse(fdate);
    }

    public int getProjectBudget() {
        return projectBudget;
    }

    public void setProjectBudget(int projectBudget) {
        this.projectBudget = projectBudget;
    }

    public boolean isHasPOE() {
        return hasPOE;
    }

    public void setHasPOE(boolean hasPOE) {
        this.hasPOE = hasPOE;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

}
