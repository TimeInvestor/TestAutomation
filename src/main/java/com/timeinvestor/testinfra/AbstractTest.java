package com.timeinvestor.testinfra;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

/**
 * This is the base class that every new test class should extend.
 * <p>
 * It provides {@code hostName} to subclass. It also provides {@code logger} for
 * logging usage.
 * 
 * @author Zheng, Lisheng
 * @version 1.0
 * @since 2014-11-01
 */
public abstract class AbstractTest {
    /**
     * The Log4j 2 {@code Logger}
     */
    protected static Logger logger;

    /**
     * A constructor that takes subclass class name and use it to initialize
     * {@code logger}.
     * 
     * @param clazz
     * 
     * @see LogManager
     */
    public AbstractTest(final Class<?> clazz) {
	logger = LogManager.getLogger(clazz);
    }

    /**
     * The hostname of a testing stage/server
     */
    protected static String hostName;

    /**
     * This method is annotated with {@code @BeforeSuite} from TestNG. Thus, It
     * will always run first before any test class/method. <br/>
     * It is also annotated with {@code @Parameters} from TestNG. With this
     * annotation TestNG will provide {@code hostName} to the method from TestNG
     * template. <br/>
     * By doing these, we make sure {@code hostName} is set up when individual
     * test method is triggered.
     * 
     * @param hostName
     */
    @BeforeSuite
    @Parameters("hostName")
    public void setup(String hostName) {
	AbstractTest.hostName = hostName;
    }

}
