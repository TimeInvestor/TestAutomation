package com.org.app;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.testinfra.AbstractTest;
import com.org.testinfra.JDBCUtils;
import com.org.testinfra.RestClient;

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

    /**
     * One TestNG test method.<br/>
     * It has been assigned to test groups "functest" and "checkintest".<br/>
     * It tests {@code RestClient}.
     */
    @Test(groups = { "functest", "checkintest" })
    public void testMethod2() {
	logger.entry();
	logger.info("Host Name: " + hostName);

	String url = "https://" + hostName + ":12970";

	JSONObject jsonObj = new JSONObject();
	jsonObj.put("name", "KYC");
	jsonObj.put("state", "COMPLETED");
	jsonObj.put("compliance_region", "GB");
	jsonObj.put("version", 1);

	JSONArray requestInJson = new JSONArray();
	requestInJson.put(jsonObj);
	logger.debug(">>> request In JsonArray:\n" + requestInJson);

	RestClient restClient = new RestClient(url, requestInJson, true);

	logger.info("testMethod2 - Done");
	logger.exit();
    }
}