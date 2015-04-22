/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package layer2.domain.bean;

import java.io.File;

/**
 *
 * @author Athinodoros
 */
public class POE {
    private String filePath;
    private String fileName;
    private File file;

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }
    private int userID;
    private int projectID;

    public POE(String filePath, String fileName, int userID, int projectID) {
        this.filePath = filePath;
        this.fileName = fileName;
        this.userID = userID;
        this.projectID = projectID;
    }

    public POE() {
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getProjectID() {
        return projectID;
    }

    public void setProjectID(int projectID) {
        this.projectID = projectID;
    }
    
    
}
