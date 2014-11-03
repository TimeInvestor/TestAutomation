/**
 * 
 */
package com.org.testinfra;

/**
 * @author Zheng, Lisheng
 * 
 */
public enum DataBaseName {
    // Sample DB Names
    EMP("EMP"), PRD("PRD"), CUSTOMER("CUSTOMER");

    String dbName;

    private DataBaseName(String dbName) {
	this.dbName = dbName;
    }

    public String getDbName() {
	return this.dbName;
    }
}
