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
    layer3.dataSource.mapper.CompanyManagerTest.class,
    layer3.dataSource.mapper.UserManagerTest.class
    
})
public class TestSuite {
    
}
