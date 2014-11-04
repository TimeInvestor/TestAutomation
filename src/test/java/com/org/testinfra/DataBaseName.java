/**
 * 
 */
package com.org.testinfra;

/**
 * Enum represents database names
 * 
 * @author Zheng, Lisheng
 * @version 1.0
 * @since 2014-11-01
 */
public enum DataBaseName {
    // Sample DB Names
    EMP("EMP"), PRD("PRD"), CUSTOMER("CUSTOMER");

    /**
     * Database name
     */
    String dbName;

    /**
     * This is a constructor
     * 
     * @param dbName
     */
    private DataBaseName(String dbName) {
	this.dbName = dbName;
    }

    /**
     * @return String the database name
     */
    public String getDbName() {
	return this.dbName;
    }
}
