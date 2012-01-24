package com.ddetyuk.connection;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;

/**
 * 
 * @author Dmitriy Detyuk (ddetyuk@gmail.com)
 */
public interface Action {
	
	public String method = "";
	public HashMap<String, String> headers = new HashMap<String, String>();
	public String url = "";

	public HashMap<String, String> getHeaders();
	
	public String getMathod();
	
	public String getUrl();
	
	public int getRequestData(OutputStream os);
	
	public void setResponceData(InputStream is);

}
