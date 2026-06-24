package com.demo;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
//@SelectPackages("com.demo")
@SelectClasses({
        G1TestingApplicationTests.class,
        RepositoryTestsSuite.class,
        ControllerTestsSuite.class,
        SeleniumTestsSuite.class
})
public class AllTestsSuite {
}
