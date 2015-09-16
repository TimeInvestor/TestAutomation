/**
 * 
 */
package com.timeinvestor.testinfra;

import java.io.InputStream;
import java.util.Arrays;

/**
 * This class used to store information about a KeyStore, e.g. file location,
 * password, KeyStore type.
 * 
 * @author Zheng, Lisheng
 * @version 1.0
 * @since 2014-11-06
 */
public class KeyStoreInformation {

    public static final String PKCS12 = "pkcs12";
    public static final String JKS = "jks";

    private String location;

    public String getLocation() {
	return location;
    }

    public char[] getPassword() {
	return password;
    }

    public String getKeyStoreType() {
	return keyStoreType;
    }

    private char[] password;
    private String keyStoreType;

    /**
     * @param location
     *            - The location of your keystore.
     * @param password
     *            - The password to be used to open-up your keystore
     * @param keyStoreType
     *            - The type of your keystore. Can be a
     *            {@link KeyStoreInformation#JKS} or a
     *            {@link KeyStoreInformation#PKCS12} or any other valid keystore
     *            types
     */
    public KeyStoreInformation(String location, String password,
	    String keyStoreType) {
	this.location = location;
	this.password = password.toCharArray();
	this.keyStoreType = keyStoreType;
    }

    private InputStream stream = null;

    public InputStream getStream() {
	try {
	    this.stream = FileHelper.loadFile(this.location);
	} catch (RuntimeException e) {
	    // gobble the exception and do nothing with it.
	}
	return this.stream;
    }

    @Override
    public String toString() {
	return "KeyStoreInformation [location=" + location + ", password="
		+ Arrays.toString(password) + ", keyStoreType=" + keyStoreType
		+ "]";
    }
}
