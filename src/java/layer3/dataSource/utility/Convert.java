/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package layer3.dataSource.utility;

import java.util.Calendar;

/**
 *
 * @author bo
 */
public class Convert {
    
    
    public static java.sql.Date date2SqlDate(java.util.Date date) {
    
        return new java.sql.Date(date.getTime());
    }

    public static java.util.Date sqlDate2Date(java.sql.Date date) {
        
        return new java.util.Date();
    }
    
    
    public static String boolean2String(boolean bool) {
        
        if(bool == true)
            return "y";
        else
            return "n";
    } // End of method()
    
    public static boolean string2Boolean(String string) {
        
       if( string.equalsIgnoreCase("y") )
           return true;
       else
           return false;
    }  
} // End of Class :: Convert
