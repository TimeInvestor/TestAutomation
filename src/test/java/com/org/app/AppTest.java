package com.org.app;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.testng.annotations.Test;

import com.org.testinfra.AbstractTest;
import com.org.testinfra.JDBCUtils;

/**
 * @author Zheng, Lisheng
 * 
 */
public class AppTest extends AbstractTest {

    public AppTest() {
	super(AppTest.class);
	logger.info(">>> Logger name: " + logger.getName());
    }

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
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

	logger.info("testMethod1 - Done");
	logger.exit();
    }

    @Test(groups = { "functest", "checkintest" })
    public void testMethod2() {
    }

    @Test(groups = { "functest" })
    public void testMethod3() {
    }
}