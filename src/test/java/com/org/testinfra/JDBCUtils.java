/**
 * 
 */
package com.org.testinfra;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Zheng, Lisheng
 * 
 */
public class JDBCUtils {
    static final String DEFAULT_JDBC_DRIVER = "com.mysql.jdbc.Driver";

    // JDBC driver name and database URL
    private String dbDriver = null;
    private String dbURL = null;
    private DataBaseName dbName = null;
    private String dbUser = null;
    private String dbPassword = null;

    private Connection dbConnection = null;

    public JDBCUtils(String dbDriver, String dbURL, String dbUser,
	    String dbPassword) throws ClassNotFoundException, SQLException {

	this.dbDriver = dbDriver;
	this.dbURL = dbURL;
	this.dbUser = dbUser;
	this.dbPassword = dbPassword;

	this.establishConnection();
    }

    public JDBCUtils(String host, DataBaseName dbName)
	    throws ClassNotFoundException, SQLException {

	this.getHostDBConnectionValues(host);
	this.establishConnection();
    }

    public void establishConnection() throws ClassNotFoundException,
	    SQLException {

	Class.forName(this.dbDriver);
	this.dbConnection = DriverManager.getConnection(this.dbURL,
		this.dbUser, this.dbPassword);
    }

    public void closeConnection() throws SQLException {

	this.dbConnection.close();
	this.dbConnection = null;
    }

    public ResultSet executQuery(String sqlQuery) throws SQLException {
	// all or nothing execution when doing query
	this.dbConnection.setAutoCommit(true);

	// create the statement
	Statement statement = dbConnection.createStatement();

	// execute the query
	ResultSet resultSet = statement.executeQuery(sqlQuery);

	return resultSet;
    }

    private void getHostDBConnectionValues(String host) {
	// TODO get DB connection values from some file/setting store on host
	// identified by dbName
    }

    /**
     * @return the dbDriver
     */
    public String getDbDriver() {
	return dbDriver;
    }

    /**
     * @param dbDriver
     *            the dbDriver to set
     */
    public void setDbDriver(String dbDriver) {
	this.dbDriver = dbDriver;
    }

    /**
     * @return the dbURL
     */
    public String getDbURL() {
	return dbURL;
    }

    /**
     * @param dbURL
     *            the dbURL to set
     */
    public void setDbURL(String dbURL) {
	this.dbURL = dbURL;
    }

    /**
     * @return the dbName
     */
    public DataBaseName getDbName() {
	return dbName;
    }

    /**
     * @param dbName
     *            the dbName to set
     */
    public void setDbName(DataBaseName dbName) {
	this.dbName = dbName;
    }

    /**
     * @return the dbUser
     */
    public String getDbUser() {
	return dbUser;
    }

    /**
     * @param dbUser
     *            the dbUser to set
     */
    public void setDbUser(String dbUser) {
	this.dbUser = dbUser;
    }

    /**
     * @return the dbPassword
     */
    public String getDbPassword() {
	return dbPassword;
    }

    /**
     * @param dbPassword
     *            the dbPassword to set
     */
    public void setDbPassword(String dbPassword) {
	this.dbPassword = dbPassword;
    }

    /**
     * @return the defaultJdbcDriver
     */
    public static String getDefaultJdbcDriver() {
	return DEFAULT_JDBC_DRIVER;
    }

    /**
     * @return the dbConnection
     */
    public Connection getDbConnection() {
	return dbConnection;
    }

    /**
     * @param dbConnection
     *            the dbConnection to set
     */
    public void setDbConnection(Connection dbConnection) {
	this.dbConnection = dbConnection;
    }
}
