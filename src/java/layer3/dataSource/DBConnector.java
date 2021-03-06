/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package layer3.dataSource;


import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {

    
    private DBType dbType = DBType.ORACLE_THIN;

    private Connection conn = null;
    
// Singleton Start --> 
    private static DBConnector instance = null;

    private DBConnector() {
    }

    public static DBConnector getInstance() {
        if (instance == null) {
            instance = new DBConnector();
        }
        return instance;
    }
    
     public Connection getConnection() {
        if (conn == null) {
            if( openConnection() ) {
                System.out.println("Connection opened");
                return conn;
            } else {
                return null;
            }
        }
        return conn;
    }
 // <-- End of Singleton

    public void setDBType(DBType dbType) {
        this.dbType = dbType;
    }

    private boolean openConnection() {
        try {
            switch (dbType) {

                case ORACLE_THIN:
                    Class.forName(Oracle.DRIVER);
                    conn = DriverManager.getConnection(Oracle.URL, Oracle.USERNAME, Oracle.PASSWORD);
                    return true;
                    
                case ORACLE_THIN_TEST_DATABASE:
                    Class.forName(Oracle.DRIVER);
                    conn = DriverManager.getConnection(OracleTest.URL, OracleTest.USERNAME, OracleTest.PASSWORD);
                    return true;
                    
                case SOME_OTHER_DATA_BASE:
                    // Class.forName(SOME_OTHER_DATA_BASE_DRIVER);
                    // return DriverManager.getConnection(SOME_OTHER_DATA_BASE, USERNAME, PASSWORD);
                    return true;

                default:
                    return false;
            }
        } catch (ClassNotFoundException | SQLException e ) {
            System.err.println(e);
            return false;
        }

    }

    public void close() {
        System.out.println("Closing connection");
        try {
            conn.close();
            conn = null;
        } catch (Exception e) {
        } // End of try-catch
    } // End of close()
    
    
    
    public static void processException(SQLException e) {
        
        StringBuilder sb = new StringBuilder();
        
        sb.append("One or more Error(s) or Exception(s) are thrown form the Data Base :: ");
        sb.append("\n\n");
        
        sb.append("Error message    :: ");  sb.append( e.getMessage() );    sb.append("\n");
        sb.append("Error code       :: ");  sb.append( e.getErrorCode() );  sb.append("\n");
        sb.append("SQL state        :: ");  sb.append( e.getSQLState() );   sb.append("\n");
        
        System.err.println(sb);
    } // End of processException()
    

} // End of Class:: ConnectionManager
