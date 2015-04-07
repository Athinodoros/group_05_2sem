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
    
    private String userID;
    private String name;
    private String password;
    private String email;
    private String country;
    private String role;
    private Company company;

    public User(String userID, String name, String password, String email, String country, String role, Company company) {
        this.userID = userID;
        this.name = name;
        this.password = password;
        this.email = email;
        this.country = country;
        this.role = role;
        this.company = company;
    }

    public String getUserID() {
        return userID;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getCountry() {
        return country;
    }

    public String getRole() {
        return role;
    }

    public Company getCompany() {
        return company;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
    
    
    
}
