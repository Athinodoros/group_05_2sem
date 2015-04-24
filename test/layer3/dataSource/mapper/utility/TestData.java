/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package layer3.dataSource.mapper.utility;

import java.util.Random;
import layer2.domain.bean.Partner;
import layer2.domain.bean.UserAuthentication;
import layer2.domain.bean.UserInfo;
import layer3.dataSource.mapper.PartnerManager;
import layer3.dataSource.mapper.UserAutenticationManager;
import layer3.dataSource.mapper.UserInfoManager;

/**
 *
 * @author bo
 */
public final class TestData {
    
    public static final PartnerManager partnerManager = new PartnerManager();
    public static final UserInfoManager userInfoManager = new UserInfoManager();
    public static final UserAutenticationManager userAutenticationManager = new UserAutenticationManager();
    
    
    // Company
    // ------ 
    // 
    private static final Random random = new Random();
    public static final String COMPANY_NAME = "CompanyNameTest_" + random.nextInt(100_000_000);
    public static final int COMPANY_ID = 0; // this value is probably not needed

    
    // UserInfo
    // ------ 
    // USER_ID will be set in the call to insetRow()
    public static final int USER_ID = 0;
    public static final String FIRST_NAME = "firstNameTest";
    public static final String LAST_NAME = "lastNameTest";
    public static final String COUNTRY = "Test";
    public static final Partner PARTNER = new Partner(COMPANY_NAME, COMPANY_ID);
    public static final String U_ROLE = "roleTest";

    // UserAutentication
    // ------
    //public static final int USER_ID = 0;
    public static final String UNAME = "unameTest";
    public static final String PASSWORD = "passwordTest";
    public static final String EMAIL = "email@Test.com";
    
    // Make some beans
    public static final Partner partner = new Partner(COMPANY_NAME, COMPANY_ID);
    public static final UserInfo userInfo = new UserInfo(USER_ID, FIRST_NAME, LAST_NAME, COUNTRY, PARTNER, U_ROLE);
    public static final UserAuthentication userAutenticantion = new UserAuthentication(userInfo, UNAME, PASSWORD, EMAIL);
    
    

private TestData() {
        //this prevents even the native class from 
        //calling this ctor as well :
        throw new AssertionError();
    }

}
