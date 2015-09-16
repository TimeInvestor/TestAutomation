package com.timeinvestor.testng;

import org.apache.logging.log4j.ThreadContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * <TODO: Pls add class description here>
 * 
 * @author lisheng
 * @Since Sep 15, 2015
 */
public class TestMDCWithTestNG2 extends TestBase {
    
    public TestMDCWithTestNG2() {
        super(TestMDCWithTestNG2.class);
    }
    
    @DataProvider(name = "test1", parallel = true)
    private Object[][] createTestData1() {
        return new Object[][] { { "TC1", new Integer(36) }, { "TC2", new Integer(37) }, { "TC3", new Integer(38) },
                { "TC4", new Integer(39) }, { "TC5", new Integer(40) }, { "TC6", new Integer(41) } };
    }
    
    @DataProvider(name = "test2", parallel = true)
    private Object[][] createTestData2() {
        return new Object[][] { { "TC7", new Integer(36) }, { "TC8", new Integer(37) }, { "TC9", new Integer(38) },
                { "TC10", new Integer(39) }, { "TC11", new Integer(40) }, { "TC12", new Integer(41) } };
    }
    
    @BeforeMethod
    private void beforeEachTestCase(Object[] testData) {
        ThreadContext.put("testCaseId", (String) testData[0]);
        ThreadContext.put("testClass", TestMDCWithTestNG2.class.getSimpleName());
        
        logger.info("Running test case: " + testData[0] + " - " + testData[1]);
    }
    
    @AfterMethod
    private void afterEachTestCase(Object[] testData) {
        logger.info("Finishing test case: " + testData[0] + " - " + testData[1]);
        
        /* Clean current thread context to prevent memory leak */
        ThreadContext.clearAll();
    }
    
    @Test(dataProvider = "test1")
    private void testMethod1(String testCaseId, Integer age) {
        logger.info("doTest1(testCaseId={}, age={})", testCaseId, age);
        TestHelper.prettyPrint(logger, "TestMDCWithTestNG 2 - testMethod1 - prettyPrint - testCaseId: " + testCaseId);
    }
    
    @Test(dataProvider = "test2")
    private void testMethod2(String testCaseId, Integer age) {
        logger.info("doTest2(testCaseId={}, age={})", testCaseId, age);

        if(testCaseId.equalsIgnoreCase("TC10")) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        
        TestHelper.prettyPrint(logger, "TestMDCWithTestNG 2 - testMethod2 - prettyPrint - testCaseId: " + testCaseId);
    }
    
    
}
