/**
 * 
 */
package com.timeinvestor.testinfra;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * This class provides helper methods for dealing with files.
 * 
 * @author Zheng, Lisheng
 * @version 1.0
 * @since 2014-11-06
 */
public class FileHelper {
    public static final Logger logger = LogManager.getLogger(FileHelper.class);

    /**
     * @param fileName
     *            - The name of the file
     * @return - An object of type {@link InputStream} that represents the
     *         stream of a file that was read from the file system.
     */
    public static InputStream loadFile(String fileName) {
	logger.entry();
	ClassLoader loader = Thread.currentThread().getContextClassLoader();
	InputStream iStream = loader.getResourceAsStream(fileName);
	if (iStream == null) {
	    try {
		iStream = new FileInputStream(fileName);
	    } catch (FileNotFoundException e) {
		// Gobbling the checked exception here and doing nothing with it
		// because we are explicitly checking if the inputstream is null
		// and then throwing a runtime exception
	    }
	}
	if (iStream == null) {
	    throw new RuntimeException("\"" + fileName
		    + "\" is not a valid resource");
	}
	logger.exit(iStream);
	return iStream;
    }
}
