/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test.Suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author bo
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
    
    layer3.dataSource.DBConnectorTest.class,
    layer3.dataSource.mapper.PartnerManagerTest.class,
    layer3.dataSource.mapper.UserInfoManagerTest.class,
    layer3.dataSource.mapper.UserAuthenticationManagerTest.class,
    layer3.dataSource.mapper.ProjectManagerTest.class,
    layer3.dataSource.mapper.commentManagerTest.class,
    layer3.dataSource.mapper.POEManagerTest.class,
    layer3.dataSource.mapper.BudgetManagerTest.class
    
})
public class TestSuite {
    
}
