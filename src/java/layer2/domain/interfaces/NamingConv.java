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
    
    //roles
    public static final String ROLE = "role";
    public static final String RESELLER = "reseller";
    public static final String PARTNER = "partner";
    public static final String ADMIN = "admin";
    public static final String SUPER_USER = "superUser"; //not sure if we'll use it
    
    //attributes
    public static final String USER_NAME = "userName";
    public static final String PASSWORD = "password";
    
    //stages
    public static final String STAGE = "stage";
    public static final String PRE_APPROVED = "pre-approved";
    public static final String APPROVED = "approved";
    public static final String FINISHED = "finished";

    
    
    //Nos
    //pageloader
    public static final String MAINAREA = "mainArea";
    
   //pages
    public static final String CREATEPROJECT ="createProject";
    public static final String DASHBOARD = "dashboard";
    public static final String BUDGET = "budget";
    public static final String PROJECTLIST = "projectlist";
    public static final String CREATECOMPANY = "createCompany";
    public static final String USERFORM = "userForm";
    
    
    
    
    
    //-------------------------------------Setters and getters----------------------------------------------//

    public static String getROLE() {
        return ROLE;
    }

    public static String getRESELLER() {
        return RESELLER;
    }

    public static String getPARTNER() {
        return PARTNER;
    }

    public static String getADMIN() {
        return ADMIN;
    }

    public static String getSUPER_USER() {
        return SUPER_USER;
    }

    public static String getUSER_NAME() {
        return USER_NAME;
    }

    public static String getPASSWORD() {
        return PASSWORD;
    }

    public static String getSTAGE() {
        return STAGE;
    }

    public static String getPRE_APPROVED() {
        return PRE_APPROVED;
    }

    public static String getAPPROVED() {
        return APPROVED;
    }

    public static String getFINISHED() {
        return FINISHED;
    }

    public static String getMAINAREA() {
        return MAINAREA;
    }

    public static String getDASHBOARD() {
        return DASHBOARD;
    }

    public static String getBUDGET() {
        return BUDGET;
    }

    public static String getPROJECTLIST() {
        return PROJECTLIST;
    }
    
    
    
}
