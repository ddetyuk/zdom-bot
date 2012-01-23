package com.ddetyuk.connection;

import com.ddetyuk.connection.Action;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.GZIPInputStream;

public class Connector {

	private static Logger logger = Logger.getLogger(Connector.class.toString());

	public Connector() {

	}

	public void execute(Action action) {
		
		try {

			URL url = new URL(action.getUrl());

			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod(action.getMathod());
			connection.setDoOutput(true);
			
			// prepare headers
			HashMap<String, String> headers = action.getHeaders();
			for (String key : headers.keySet()) {
				connection.setRequestProperty(key, headers.get(key));
			}

			// push data
			action.getRequestData(connection.getOutputStream());
			//connection.setFixedLengthStreamingMode(length);

			
			if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
				if(connection.getContentEncoding().equals("gzip")){
					logger.log(Level.SEVERE, "ContentEncoding: gzip");
					action.setResponceData(new GZIPInputStream (connection.getInputStream()));
				}else{
					logger.log(Level.SEVERE, "ContentEncoding: other");
					action.setResponceData(connection.getInputStream());
				}
			} else {
				logger.log(Level.SEVERE, "" + connection.getResponseCode());
				logger.log(Level.SEVERE, connection.getResponseMessage());
			}
			connection.disconnect();
		} catch (MalformedURLException e1) {
			logger.log(Level.SEVERE,e1.getMessage());
		} catch (IOException e) {
			logger.log(Level.SEVERE,e.getMessage());
		}
	}
}