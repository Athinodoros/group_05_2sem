/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package layer2.domain.bean;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

/**
 *
 * @author Athinodoros
 */
public class POE {
    
    private int POEID;
    private long size;
    private Project project;
    private String fileName;
    private String prefix;
    private InputStream inStream;
    private OutputStream  fileOut;

    //new costructors ->

    public POE() {
    }

    public POE(int POEID, Project project, String fileName, String prefix, String th) {
        this.POEID = POEID;
        this.project = project;
        this.fileName = fileName;
        this.prefix = prefix;
        
    }
    
    public POE(POE p){
        this.POEID = p.POEID;
        this.project = p.project;
        this.fileName = p.fileName;
        this.prefix = p.prefix;
        this.fileOut = p.fileOut;
        this.inStream = p.inStream;
        
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public OutputStream getFileOut() {
        return fileOut;
    }

    public void setFileOut(OutputStream fileOut) {
        this.fileOut = fileOut;
    }

    // Getters and setters -> 

    public InputStream getInStream() {
        return inStream;
    }

    public void setInStream(InputStream inStream) {
        this.inStream = inStream;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public String getFilePath() {
        return fileName;
    }

    public void setFilePath(String filePath) {
        this.fileName = filePath;
    }
    
    // ->

    @Override
    public String toString() {
        return super.toString(); //To change body of generated methods, choose Tools | Templates.
    }

    public int getPOEID() {
        return POEID;
    }

    public void setPOEID(int POEID) {
        this.POEID = POEID;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    
    public String getPrefix() {
        return prefix;
    }
    
    public void setPrefix(String filename) {        
        this.prefix = filename;
    }
    
} // End of Class
