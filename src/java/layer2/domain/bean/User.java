/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package layer2.domain.bean;

/**
 *
 * @author Bancho
 */
public class User {
    
  private String firstName;
  private String lastName;
  private String eMail;
  private String userName;
  private String passWord;
  private String country;
  private String role;
  private String companyName;
  private int userID;
  

    public User() {
    }

    public User(String firstName, String lastName, String eMail, String userName, String passWord, String country, String role, String companyName, int userID) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.eMail = eMail;
        this.userName = userName;
        this.passWord = passWord;
        this.country = country;
        this.role = role;
        this.companyName = companyName;
        this.userID = userID;
    }
    
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    
    
}
