/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package layer2.domain.bean;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;

/**
 *
 * @author Athinodoros
 */
public class POE {
    
    private int POEID;
    private Project project;
    private String fileName;
    private String prefix;
    private File file ;
    private InputStream inStream;
    private FileInputStream fileIn;
    private FileOutputStream  fileOut;

    //new costructors ->

    public POE() {
    }

    public POE(int POEID, Project project, String fileName, String prefix, File file, InputStream inStream, FileInputStream fileIn, FileOutputStream fileOut) {
        this.POEID = POEID;
        this.project = project;
        this.fileName = fileName;
        this.prefix = prefix;
        this.file = file;
        this.inStream = inStream;
        this.fileIn = fileIn;
        this.fileOut = fileOut;
    }
    
    public POE(POE p){
        this.POEID = p.POEID;
        this.project = p.project;
        this.fileName = p.fileName;
        this.prefix = p.prefix;
        this.fileIn = p.fileIn;
        this.fileOut = p.fileOut;
        this.inStream = p.inStream;
        
    }

    // Getters and setters -> 

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public InputStream getInStream() {
        return inStream;
    }

    public void setInStream(InputStream inStream) {
        this.inStream = inStream;
    }
    
    public FileOutputStream getFileOut() throws FileNotFoundException{
        
        return new FileOutputStream(this.file);
    }

    public void setFileOut(FileOutputStream fileOut) {
        this.fileOut = fileOut;
    }

    public FileInputStream getFileIn() throws FileNotFoundException{
        return new FileInputStream(this.file);
    }

    public void setFileIn(FileInputStream fileIn) {
        this.fileIn = fileIn;
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
        String[] pr = filename.split(".");
        
        this.prefix = pr[1];
    }
    
} // End of Class
