package com.ddetyuk.connection;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.GZIPInputStream;


/**
 * 
 * @author Dmitriy Detyuk (ddetyuk@gmail.com)
 */
public class Connector {

	private static Logger logger = Logger.getLogger(Connector.class.toString());

	public Connector() {

	}

	public void execute(Action action) throws ActionException {
		logger.log(Level.INFO,"Execute action: " + action.toString() );
		try {

			URL url = new URL(action.getUrl());

			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			connection.setRequestMethod(action.getMathod());
			connection.setDoOutput(true);

			// prepare headers
			HashMap<String, String> headers = action.getHeaders();
			for (String key : headers.keySet()) {
				connection.setRequestProperty(key, headers.get(key));
			}

			// push data
			//connection.setFixedLengthStreamingMode(390);
			//ByteArrayOutputStream baos = new ByteArrayOutputStream();
			//action.getRequestData(baos);
			//connection.getOutputStream().write(baos.toByteArray());
			//connection.setFixedLengthStreamingMode(length);
			
			action.getRequestData(connection.getOutputStream());

			if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
				if (connection.getContentEncoding().equals("gzip")) {
					logger.log(Level.FINE, "ContentEncoding: gzip");
					action.setResponceData(new GZIPInputStream(connection
							.getInputStream()));
				} else {
					logger.log(Level.FINE, "ContentEncoding: other");
					action.setResponceData(connection.getInputStream());
				}
			} else {
				logger.log(Level.SEVERE, "" + connection.getResponseCode());
				logger.log(Level.SEVERE, connection.getResponseMessage());
			}
			connection.disconnect();
		} catch (MalformedURLException e1) {
			logger.log(Level.SEVERE, e1.getMessage());
		} catch (IOException e) {
			logger.log(Level.SEVERE, e.getMessage());
		}
	}
}
