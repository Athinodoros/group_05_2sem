/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package layer2.domain.bean;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.Locale;
import oracle.sql.DATE;

/**
 *
 * @author Bancho , athinodwros
 */
public class Project {

    
    private int projectID;      // primary key
    private Partner partner;    // foreign key (companyName)
    private String title;
    private String description;
    private String stage;
    private Date sdate;
    private Date fdate;
    private int projectBudget;
//    private boolean hasPOE;
//    private String comments;


//    DateFormat format = new SimpleDateFormat("yyyy,mm,dd", Locale.ENGLISH);
//        DateFormat df = new SimpleDateFormat(mydate);
//        this.sdate = format.parse(sdate.toString());
    
//    DateFormat format = new SimpleDateFormat("yyyy,mm,dd", Locale.ENGLISH);
//        DateFormat df = new SimpleDateFormat(mydate);
//        this.fdate = format.parse(fdate.toString());

    public Project(){}
    
    public Project(Project p) {
        this.projectID = p.projectID;
        this.partner = p.partner;
        this.title = p.title;
        this.description = p.description;
        this.stage = p.stage;
        this.sdate = p.sdate;
        this.fdate = p.fdate;
        this.projectBudget = p.projectBudget;
    }

    public Project(int projectID, Partner partner, String title, String description, String stage, Date sdate, Date fdate, int projectBudget) {
        this.projectID = projectID;
        this.partner = partner;
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

    public Partner getPartner() {
        return partner;
    }

    public void setPartner(Partner partner) {
        this.partner = partner;
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
        return "Project{" + "projectID=" + projectID + ", partner=" + partner + ", title=" + title + ", description=" + description + ", stage=" + stage + ", sdate=" + sdate + ", fdate=" + fdate + ", projectBudget=" + projectBudget + '}';
    }
    
    

} // End of class
