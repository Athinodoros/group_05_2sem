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
public class UserAuthentication {
    
    private UserInfo userInfo; // primary key (userID)
    private String uname;
    private String password;
    private String email;

    
    public UserAuthentication() {}
    
    public UserAuthentication(UserInfo userInfo, String uname, String password, String email) {
        this.userInfo = userInfo;
        this.uname = uname;
        this.password = password;
        this.email = email;
    }
    public UserAuthentication(UserAuthentication ua) {
        this.userInfo = ua.userInfo;
        this.uname = ua.uname;
        this.password = ua.password;
        this.email = ua.email;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "UserAutentication{" + "userInfo=" + userInfo + ", uname=" + uname + ", password=" + password + ", email=" + email + '}';
    }
    
    
}
