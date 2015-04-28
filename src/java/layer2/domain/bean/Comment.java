/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package layer2.domain.bean;

/**
 *
 * @author Athinodoros
 */
public class Comment {
    private int commentID;
    private Project project;
    private UserInfo user;
    private String comment;

    public Comment() {
    }

    public Comment(int commentID, Project project, UserInfo user, String comment) {
        this.commentID = commentID;
        this.project = project;
        this.user = user;
        this.comment = comment;
    }

    
    public int getCommentID() {
        return commentID;
    }

    public void setCommentID(int commentID) {
        this.commentID = commentID;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project projectID) {
        this.project = projectID;
    }

    public UserInfo getUser() {
        return user;
    }

    public void setUser(UserInfo userID) {
        this.user = userID;
    }

    
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
    
}
