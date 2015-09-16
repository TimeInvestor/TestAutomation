package com.timeinvestor.testinfra;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.message.MessageFactory;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;

/**
 * A Client enable SOAP testing
 * 
 * @author Zheng, Lisheng
 * @version 1.0
 * @since 2014-11-05
 */
public class SOAPClient {
    private static final Logger logger = LogManager.getLogger(SOAPClient.class);
    
    private void testSOAP() {
        try {
            // Create SOAP Connection
            SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
            SOAPConnection soapConnection = soapConnectionFactory.createConnection();
            
            // Send SOAP Message to SOAP Server
            String url = "http://ws.cdyne.com/emailverify/Emailvernotestemail.asmx";
            SOAPMessage soapResponse = soapConnection.call(createSOAPRequest(), url);
            
            // print SOAP Response
            System.out.print("Response SOAP Message:");
            soapResponse.writeTo(System.out);
            
            soapConnection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private SOAPMessage createSOAPRequest() throws Exception {
        //TODO
        
        return null;
    }
    
}
