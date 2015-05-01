/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package layer3.dataSource.mapper;

import com.sun.xml.ws.transport.tcp.io.OutputWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.*;
import layer2.domain.bean.POE;
import layer3.dataSource.DBConnector;

/**
 *
 * @author bo
 */
public class POEManager {
    
    
    public boolean insert(Connection conn, POE bean) { 
        
        int rowsInserted = 0;
         
        String sql = "INSERT into POE (projectID, filePath) VALUES (?, ?, ?, ?, ?)";
         
        // This code is taken from Henrik's DataSourceLayerDemo :: Class OrderMapper.jave
        //-------------------------------------------------------------------------------
        String sql2
                = "select POESequence.nextval " //= "select orderseq.nextval  "
                + "from dual";                      // <- this (dual) is a dummy table, and it is needed
                                                    // because of the select statemant (oracle data-base only)
        //--------------------------------------------------------------------------------
        try ( PreparedStatement stmt = conn.prepareStatement( sql ); ) {
            PreparedStatement stmt1 = conn.prepareStatement(sql2);
            ResultSet keys;
            keys = stmt1.executeQuery();
            if (keys != null) {
                bean.setPOEID(keys.getInt(1));
            }

 
            ProjectManager pm = new ProjectManager();
            FileInputStream fis = new FileInputStream(bean.getFile());
            //== insert 
            stmt.setInt(1, bean.getPOEID()  );
            stmt.setInt(2, bean.getProject().getProjectID() );
            stmt.setString(3, bean.getPrefix());
            stmt.setString(4, bean.getFileName());
            stmt.setBinaryStream(5, fis);
            rowsInserted  = stmt.executeUpdate(); 

        } catch (SQLException e) {
            DBConnector.processException(e);
            return false;
        }catch(FileNotFoundException e){
            System.out.println(e.getStackTrace());
            
        }
        
        return rowsInserted == 1;
    } // End of method :: insert()
    
    
    public POE getRow(Connection conn, int projectID) {

        String sql = "SELECT * FROM POE WHERE projectID = ?";
        ResultSet rs = null;

        try (PreparedStatement stmt = conn.prepareStatement(sql);) {

            stmt.setInt(1, projectID);
            rs = stmt.executeQuery();

            if (rs.next()) {
                
                ProjectManager pm = new ProjectManager();
                POE bean = new POE();
                InputStream inStr = rs.getBinaryStream("fileBin");
                bean.setProject( pm.getRow(conn, rs.getInt("projectID")));
                File file = new File("C:\\"+rs.getString("fileName"));
                
                return bean;
                
            } else {
                return null;
            }

        } catch (SQLException e) {
            DBConnector.processException(e);
            return null;
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    DBConnector.processException(e);
                }
            }
        }
    } // End of method :: getRow() 
    
    
    public boolean update(Connection conn, POE bean) {

        String sql = "UPDATE POE SET fileName = ?, fileBin = ? WHERE projectID = ?, fileName=?";

        try ( PreparedStatement stmt = conn.prepareStatement(sql); ) {

            stmt.setString  (1, bean.getFileName());
            stmt.setInt     (2, bean.getProject().getProjectID());

            int affected = stmt.executeUpdate();
            if (affected == 1) {
                return true;
            } else {
                return false;
            }

        } catch (SQLException e) {
            DBConnector.processException(e);
            return false;
        }
    } // End of method :: update()
    
    
    public boolean delete(Connection conn, int projectID) {

        String sql = "DELETE FROM POE WHERE projectID = ?";

        try ( PreparedStatement stmt = conn.prepareStatement(sql); ) {

            stmt.setInt(1, projectID);
            int effected = stmt.executeUpdate();

            if (effected == 1) {
                return true;
            } else {
                return false;
            }

        } catch (SQLException e) {
            DBConnector.processException(e);
            return false;
        }
    } // End of method :: Delete() 
    
    
    public int deleteAllRows(Connection conn, String confirm){
        
        if( confirm.equalsIgnoreCase("yes")) {
            
            String sql = "DELETE FROM POE";
            
            try ( PreparedStatement stmt = conn.prepareStatement(sql); ) {
                
                return stmt.executeUpdate();
            } catch (SQLException e) {
                
                DBConnector.processException(e);
                return -1;
            }
        }else{
            return -1;
        }
    } // End of method :: deleteAllRows
    
} // End of Class
