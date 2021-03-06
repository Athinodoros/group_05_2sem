/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package layer3.dataSource.mapper.utility;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import layer2.domain.bean.*;
import layer3.dataSource.mapper.*;

/**
 *
 * @author bo
 */
public final class TestData {

    public static final POEManager poeManager = new POEManager();
    public static final ProjectManager projectManager = new ProjectManager();
    public static final PartnerManager partnerManager = new PartnerManager();
    public static final UserInfoManager userInfoManager = new UserInfoManager();
    public static final UserAutenticationManager userAutenticationManager = new UserAutenticationManager();
    public static final CommentManager commentMananger = new CommentManager();

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
    // - public static final int USER_ID = 0;
    public static final String UNAME = "unameTest" + random.nextInt(100_000_000);
    public static final String PASSWORD = "passwordTest";
    public static final String EMAIL = "email@Test.com";

    // Project
    // ----------
    //
    private static final Calendar cal = Calendar.getInstance();
    private static final Date today = cal.getTime();

    // - public static final USER_ID = 0;
    public static final String TITLE_PROJECT = "Title_Project";
    // - public static final Partner partner;
    public static final String DESCRIPTION = "Description";
    public static final String STAGE = "Stage";
    public static final Date SDATE = today;
    public static final Date FDATE = today;
    public static final int PROJECT_BUDGET = 1000;

    // Comment
    // ------------
    //
    public static final int COMMENT_ID = 0;
    // - private static final Project project;
    // - private static final UserInfo userInfo;
    public static final String COMMENT = "This is a very small comment";

    // POE
    // ------------
    //
    // - public static final int POE_ID = 0;
    public static final String FILEPATH = "filePath";

    // Make some beans
    // ------------
    //
    public static final Partner partner = new Partner(COMPANY_NAME, COMPANY_ID);
    public static final UserInfo userInfo = new UserInfo(USER_ID, FIRST_NAME, LAST_NAME, COUNTRY, PARTNER, U_ROLE);
    public static final UserAuthentication userAutenticantion = new UserAuthentication(userInfo, UNAME, PASSWORD, EMAIL);
    public static final Project project = new Project(USER_ID, partner, UNAME, FIRST_NAME, UNAME, today, today, COMPANY_ID);
    public static final Comment comment = new Comment(COMMENT_ID, project, userInfo, COMMENT);
    public static final int poeid = random.nextInt(100_000_000);
    public static final POE poe = new POE(USER_ID, project, COUNTRY, U_ROLE, UNAME);

    private TestData() {
        //this prevents even the native class from 
        //calling this ctor as well :
        throw new AssertionError();
    }

}
