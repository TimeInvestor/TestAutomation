/**
 * 
 */
package com.org.testinfra;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

/**
 * @author Zheng, Lisheng
 * 
 */
public abstract class AbstractTest {
    protected static Logger logger;

    public AbstractTest(final Class<?> clazz) {
	logger = LogManager.getLogger(clazz);
    }

    protected static String hostName;

    @BeforeSuite
    @Parameters("hostName")
    public void setup(String hostName) {
	AbstractTest.hostName = hostName;
    }

}
