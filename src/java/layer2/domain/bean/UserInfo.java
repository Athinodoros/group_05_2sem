/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package layer2.domain.bean;

/**
 *
 * @author bo
 */
public class UserInfo {

    private int     userID;     // primary key
    private String  firstname;
    private String  lastname;
    private String  country;
    private String  urole;      //

    public UserInfo() {
    }

    public UserInfo(int userID, String firstname, String lastname, String country, String urole) {
        this.userID = userID;
        this.firstname = firstname;
        this.lastname = lastname;
        this.country = country;
        this.urole = urole;
    }

    public UserInfo(UserInfo u) {
        this.userID = u.userID;
        this.firstname = u.firstname;
        this.lastname = u.lastname;
        this.country = u.country;
        this.urole = u.urole;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getUrole() {
        return urole;
    }

    public void setUrole(String urole) {
        this.urole = urole;
    }

    @Override
    public String toString() {
        return "UserInfo{" + "userID=" + userID + ", firstname=" + firstname + ", lastname=" + lastname + ", country=" + country + ", urole=" + urole + '}';
    }

}
