/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package layer3.dataSource.mapper.utility;

import layer2.domain.bean.UserAutentication;
import layer2.domain.bean.UserInfo;
import layer3.dataSource.mapper.UserAutenticationManager;
import layer3.dataSource.mapper.UserInfoManager;

/**
 *
 * @author bo
 */
public final class TestData {
    
    public static final UserInfoManager userInfoManager = new UserInfoManager();
    public static final UserAutenticationManager userAutenticationManager = new UserAutenticationManager();
    
    // UserInfo
    // ------ 
    // USER_ID will be set in the call to insetRow()
    public static final int USER_ID = 0;
    public static final String FIRST_NAME = "firstNameTest";
    public static final String LAST_NAME = "lastNameTest";
    public static final String COUNTRY = "Test";
    public static final String U_ROLE = "roleTest";

    // UserAutentication
    // ------
    //public static final int USER_ID = 0;
    public static final String UNAME = "unameTest";
    public static final String PASSWORD = "passwordTest";
    public static final String EMAIL = "email@Test.com";
    
    
    public static final UserInfo userInfo = new UserInfo(USER_ID, FIRST_NAME, LAST_NAME, COUNTRY, U_ROLE);
    public static final UserAutentication userAutenticantion = new UserAutentication(userInfo, UNAME, PASSWORD, EMAIL);
    
    

private TestData() {
        //this prevents even the native class from 
        //calling this ctor as well :
        throw new AssertionError();
    }

}
