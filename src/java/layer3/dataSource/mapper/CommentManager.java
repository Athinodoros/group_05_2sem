/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package layer3.dataSource.mapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import layer2.domain.bean.Comment;
import layer3.dataSource.DBConnector;

/**
 *
 * @author Athinodoros
 */
public class CommentManager {

    public boolean insert(Connection conn, Comment bean) {
        
        int rowsInserted = 0;
        String sql1 = "INSERT into comments (commentID, projectID, userID, comments) VALUES (?,?,?,?)";
        // This code is taken from Henrik's DataSourceLayerDemo :: Class OrderMapper.jave
        //-------------------------------------------------------------------------------
        String sql2
                = "select commentSequence.nextval " //= "select orderseq.nextval  "
                + "from dual";                      // <- this (dual) is a dummy table, and it is needed
        // because of the select statemant (oracle data-base only)
        //--------------------------------------------------------------------------------
        ResultSet keys = null;
        try (
                PreparedStatement prep1 = conn.prepareStatement(sql1);
                PreparedStatement prep2 = conn.prepareStatement(sql2);) {
            //== (Henrik's code) get unique key
            //--------------------------------
            keys = prep2.executeQuery();
            if (keys.next()) {
                bean.setCommentID(keys.getInt(1)); // <- get the key from dummy table (dual)
            } else {
                // ToDo - 
            }
            //--------------------------------
            prep1.setInt(1, bean.getCommentID());
            prep1.setInt(2, bean.getProject().getProjectID());
            prep1.setInt(3, bean.getUser().getUserID());
            prep1.setString(4, bean.getComment());

            rowsInserted = prep1.executeUpdate();

        } catch (SQLException e) {
            DBConnector.processException(e);
            return false;
        } finally {
            if (keys != null) {
                try {
                    keys.close();
                } catch (SQLException e) {
                    DBConnector.processException(e);
                }
            }
        }
        return rowsInserted == 1;
    } //end of method ->Insert

    public Comment getRow(Connection conn, int commentID) {
        
        String sql1 = "SELECT * FROM comments WHERE commentID = ? ";
        ResultSet rs = null;
        
        try (PreparedStatement prep = conn.prepareStatement(sql1);) {
            
            prep.setInt(1, commentID);
            rs = prep.executeQuery();
            
            if (rs.next()) {
                Comment bean = new Comment();
                
                UserInfoManager um = new UserInfoManager();
                ProjectManager pm = new ProjectManager();

                bean.setCommentID(commentID);
                bean.setProject(pm.getRow(conn, rs.getInt("projectID")));
                bean.setUser(um.getRow(conn, rs.getInt("userID")));
                bean.setComment("comments");

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
    }//end of method -> getrow

    public Collection<Comment> getAllRows(Connection conn, int projectID) {
        
        Collection<Comment> rows = new ArrayList();
        
        String sql = "SELECT * FROM comments WHERE projectID = ? ";
        ResultSet rs = null;

        try (PreparedStatement prep = conn.prepareStatement(sql);) {
            
            prep.setInt(1, projectID);
            rs = prep.executeQuery();
            
            while (rs.next()) {
                Comment bean = new Comment();
                ProjectManager pm = new ProjectManager();
                UserInfoManager um = new UserInfoManager();
                bean.setCommentID(rs.getInt("commentID"));
                bean.setProject(pm.getRow(conn, rs.getInt("projectID")));
                bean.setUser(um.getRow(conn, rs.getInt("userID")));
                bean.setComment(rs.getString("comments"));
                rows.add(bean);
            }
            return rows;
        } catch (SQLException e) {
            DBConnector.processException(e);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    DBConnector.processException(e);
                }
            }
        }
        return rows;
    }
    
    
    public int deleteAllRows(Connection conn,String confirm){
         if( confirm.equalsIgnoreCase("yes")) {
            
            String sql = "DELETE FROM comments";
            
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                return stmt.executeUpdate();
            } catch (SQLException e) {
                DBConnector.processException(e);
                return -1;
            }
        }else{
            return -1;
        }
    }
    
    //public boolean update(Connection conn , Comment bean){}
}
