/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package layer3.dataSource;

/**
 *
 * @author bo
 */
public class OracleTest {
    
    public static final String USERNAME = "cphas293";
    public static final String PASSWORD = "cphas293";

    // This is the path to the Oracle database:
    public static final String URL = "jdbc:oracle:thin:@datdb.cphbusiness.dk:1521:dat";

    // The driver is not needed anymore since jave 7.
    // ... But since the programm doesn't work without the driver! I included it.
    public static final String DRIVER = "oracle.jdbc.driver.OracleDriver";

   // PRIVATE //
    /**
     * The caller references the constants using OracleTest.USERNAME,
     * and so on. Thus, the caller should be prevented from constructing objects
     * of this class, by declaring this private constructor.
     */
    private OracleTest() {
    //this prevents even the native class from 
        //calling this ctor as well :
        throw new AssertionError();
    }
}
