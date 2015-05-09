    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package layer2.domain.interfaces;

/**
 *
 * @author Bancho
 */
public class NamingConv {

    //roles of users
    public static final String ROLE = "role";
    public static final String PARTNER = "partner";
    public static final String ADMIN = "admin";

    //log-in
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";

    //stages of projects
    public static final String STAGE = "stage";
    public static final String PENDING = "pending";
    public static final String APPROVED = "approved";
    public static final String FINISHED = "finished";
    
    //commands for Servlet switch case
    public static final String NEWCOMMENT = "newcomment";
    public static final String INCOMMENT = "newcomment";
    public static final String COMMAND = "command";
    public static final String RELOAD_MAIN = "reloadMain";
    public static final String LOG_IN = "log-in";
    public static final String LOG_OUT = "log-out";
    public static final String UPLOAD = "upload";
    public static final String CREATE_PROJECT = "createProject";
    public static final String CREATE_COMPANY = "createPartner";
    public static final String CREATE_USER = "createUser";
    
    //beans, created by beanHandlers
    public static final String NEW_PROJECT = "newProject";
    public static final String NEW_PARTNER = "newPartner";
    public static final String NEW_USER_ATH = "newUserAth";
    public static final String NEW_USER_INFO = "newUserInfo";
    
    //requset and session attributes
    public static final String PROJECT = "project";
    public static final String CONTROLLER = "controller";
    public static final String PARTNER_LIST = "partnerList";
    public static final String MAINAREA = "mainArea";
    public static final String USER = "user";
    public static final String PROJECTS = "projects";
    public static final String TYPE = "type";
    public static final String SDATE = "sdate";
    public static final String FDATE = "fdate";
    
    
    
    //----------------------------------------------------Nos--------------------------------------------------------
    //pages
    public static final String DASHBOARD = "dashboard";
    public static final String SET_BUDGET = "setBudget";
    public static final String VIEW_BUDGET = "viewBudget";
    public static final String PROJECT_OVERVIEW = "projectOverview";
    public static final String VIEW_PROJECT_DETAILS = "viewProjectDetails";
    public static final String SEE = "seeProjects";
    public static final String PENDING_PROJECTS = "viewPending";
    public static final String APPROVED_PROJECTS = "viewApproved";
    public static final String SUCCESS = "success";
    public static final String FAIL = "fail";
}
