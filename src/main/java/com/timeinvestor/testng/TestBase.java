package com.timeinvestor.testng;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

/**
 * Base class for all test class
 * 
 * @author lisheng
 * @Since Sep 15, 2015
 */
public class TestBase {
    
    /**
     * The Log4j 2 {@code Logger}
     */
    protected Logger logger;
    
    /**
     * A constructor that takes subclass class name and use it to initialize {@code logger}.
     * 
     * @param clazz
     *            - Class of subclass
     * 
     * @see LogManager
     */
    public TestBase(final Class<?> clazz) {
        logger = LogManager.getLogger(clazz);
    }
    
    /**
     * The hostname of a testing stage/server
     */
    protected static String hostName;
    
    @BeforeSuite
    @Parameters("hostName")
    public void initialSetupForEntireTest(String hostName) {
        logger.info("initialSetupForEntireTest(hostName={})", hostName);
        
        /* Set host name */
        TestBase.hostName = hostName;
    }
}
