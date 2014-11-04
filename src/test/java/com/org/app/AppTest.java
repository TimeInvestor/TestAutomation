package com.org.app;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.testinfra.AbstractTest;
import com.org.testinfra.JDBCUtils;

/**
 * This is a sample test class. <br/>
 * It extends {@link AbstractTest}. <br/>
 * It is driven by TestNG. <br/>
 * 
 * @author Zheng, Lisheng
 * 
 */
public class AppTest extends AbstractTest {

    /**
     * This is a constructor which provides full class name to super class
     * {@code AbstractTest} to get {@code logger} initiated.
     */
    public AppTest() {
	super(AppTest.class);
	logger.info(">>> Logger name: " + logger.getName());
    }

    /**
     * One TestNG test method.<br/>
     * It has been assigned to test groups "functest" and "checkintest".<br/>
     * It tests jdbc connection to local mysql database.
     */
    @Test(groups = { "functest", "checkintest" })
    public void testMethod1() {
	logger.entry();
	logger.info("Host Name: " + hostName);

	String sqlQuery = "SELECT * FROM Employees";
	try {
	    JDBCUtils dbUtils = new JDBCUtils("com.mysql.jdbc.Driver",
		    "jdbc:mysql://localhost/EMP", "admin", "password");
	    ResultSet resultSet = dbUtils.executQuery(sqlQuery);

	    if (resultSet.next()) {
		logger.info("*** Employee ID: " + resultSet.getString("ID"));
		logger.info("*** First Name: "
			+ resultSet.getString("FirstName"));
		logger.info("*** First Name: "
			+ resultSet.getString("LastName"));
		logger.info("*** First Name: "
			+ resultSet.getString("Date_Of_Birth"));
		logger.info("*** First Name: " + resultSet.getString("Address"));
	    }

	} catch (ClassNotFoundException | SQLException e) {
	    Assert.fail("testMethod1 is failling bcs of following issue:", e);
	}

	logger.info("testMethod1 - Done");
	logger.exit();
    }
}