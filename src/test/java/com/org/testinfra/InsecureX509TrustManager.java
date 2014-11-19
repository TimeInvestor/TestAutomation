/**
 * 
 */
package com.org.testinfra;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.X509TrustManager;

/**
 * A completely insecure (yet very easy to use) x509 trust manager. This manager
 * trusts all servers and all clients, regardless of credentials or lack
 * thereof.
 * 
 * @author Zheng, Lisheng
 * @version 1.0
 * @since 2014-11-06
 */
public class InsecureX509TrustManager implements X509TrustManager {

    public void checkClientTrusted(X509Certificate[] certs, String authType)
	    throws CertificateException {
    }

    public void checkServerTrusted(X509Certificate[] certs, String authType)
	    throws CertificateException {
    }

    public X509Certificate[] getAcceptedIssuers() {
	return new X509Certificate[0];
    }

}
