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
    
}
