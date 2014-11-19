package com.org.testinfra;

import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyStore;

import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.glassfish.jersey.apache.connector.ApacheClientProperties;
import org.glassfish.jersey.apache.connector.ApacheConnectorProvider;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.filter.LoggingFilter;
import org.json.JSONArray;

/**
 * RestClient based on Jerey client
 * 
 * @author Zheng, Lisheng
 * @version 1.0
 * @since 2014-11-05
 */
public class RestClient {
    private static final Logger logger = LogManager.getLogger(RestClient.class);

    Client client;
    URL endPointURL;

    /**
     * Constructor
     */
    public RestClient(String url, JSONArray request, boolean printDetails) {
	try {
	    endPointURL = new URL(url);
	} catch (MalformedURLException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

	KeyStoreInformation keyStoreInfo = new KeyStoreInformation(
		"src/main/resources/certificate/protected.jks", "passwordstg2",
		KeyStoreInformation.JKS);
	Client client = this.getClient(keyStoreInfo, true);

	if (printDetails) {
	    client.register(new LoggingFilter(java.util.logging.Logger
		    .getLogger(RestClient.class.getName()), true));
	}

	WebTarget webTarget = client.target(endPointURL.toString());
	webTarget = webTarget.path("v1").path("compliance").path("levels")
		.queryParam("customer_identifier", "1286402640416238550")
		.queryParam("customer_type", "ACCOUNT");

	Response response = webTarget
		.request()
		.header("Accept", "application/json")
		.header("Content-Type", "application/json")
		.put(Entity.entity(request.toString(),
			MediaType.APPLICATION_JSON));

	logger.info(">>> Status: " + response.getStatus());
	logger.info(">>> Response: " + response.readEntity(String.class));
	logger.info(">>> Headers: " + response.getHeaders());
    }

    /**
     * Get operation
     * 
     * @param clazz
     * @return
     */
    public <T extends Object> T get(Class<T> responseClass) {
	// TODO
	return null;
    }

    /**
     * Post operation
     * 
     * @param clazz
     * @param input
     * @return
     */
    public <T extends Object> T post(Class<T> responseClass, String input) {
	// TODO

	return null;
    }

    /**
     * Put operation
     * 
     * @param clazz
     * @param input
     * @return
     */
    public <T extends Object> T put(Class<T> responseClass, String input) {
	if ("https".equals(endPointURL.getProtocol())) {
	    // this.setUpHttps(keyStoreInfo);
	}

	// TODO

	return null;
    }

    private Client getClient(KeyStoreInformation keyStoreInfo, boolean isHttps) {
	// logger.entry();

	final PoolingHttpClientConnectionManager connectionManager;

	if (isHttps) {
	    try {
		KeyManager[] km = this.getKeyManager(keyStoreInfo);
		TrustManager[] tm = new TrustManager[] { new InsecureX509TrustManager() };
		SSLContext ctx = SSLContext.getInstance("TLSv1");
		ctx.init(km, tm, null);
		SSLConnectionSocketFactory socketFactory = new SSLConnectionSocketFactory(
			ctx,
			SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);

		Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder
			.<ConnectionSocketFactory> create()
			.register("https", socketFactory).build();

		connectionManager = new PoolingHttpClientConnectionManager(
			socketFactoryRegistry);
	    } catch (Exception e) {// NOSONAR
		// Intentionally catching all exceptions here.
		throw new RuntimeException(e);
	    }
	} else {
	    connectionManager = new PoolingHttpClientConnectionManager();
	}

	ClientConfig config = new ClientConfig();
	config.property(ApacheClientProperties.CONNECTION_MANAGER,
		connectionManager);
	config.connectorProvider(new ApacheConnectorProvider());

	ClientBuilder builder = ClientBuilder.newBuilder();
	client = builder.withConfig(config).build();

	// logger.exit(client);
	return client;
    }

    private KeyManager[] getKeyManager(KeyStoreInformation keyStoreInfo) {
	// logger.entry(keyStoreInfo);
	KeyManager[] km = null;
	if (keyStoreInfo == null) {
	    // logger.exit(null);
	    return km;
	}
	try {
	    KeyManagerFactory kmf = KeyManagerFactory
		    .getInstance(KeyManagerFactory.getDefaultAlgorithm());
	    KeyStore keyStore = KeyStore.getInstance(keyStoreInfo
		    .getKeyStoreType());
	    keyStore.load(keyStoreInfo.getStream(), keyStoreInfo.getPassword());
	    kmf.init(keyStore, keyStoreInfo.getPassword());
	    km = kmf.getKeyManagers();
	} catch (Exception e) {
	    // logger.error("Unable to retrieve Key Managers.");
	}
	// logger.exit(km);
	return km;
    }
}
