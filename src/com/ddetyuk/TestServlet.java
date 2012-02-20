package com.ddetyuk;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ddetyuk.connection.AmfAction;
import com.exadel.flamingo.flex.messaging.amf.io.AMF3Deserializer;
import com.exadel.flamingo.flex.messaging.util.StringUtil;


/**
 * 
 * @author Dmitriy Detyuk (ddetyuk@gmail.com)
 */
@SuppressWarnings("serial")
public class TestServlet extends HttpServlet {
	
	private static Logger logger = Logger.getLogger(AmfAction.class.toString());

	@SuppressWarnings("unchecked")
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		FileInputStream in = new FileInputStream("room_fin");
		Map<Object, Object> obj = new HashMap<Object, Object>();
		try {
			AMF3Deserializer amf = new AMF3Deserializer(in);
			obj = (Map<Object, Object>) amf.readObject();
		} catch (Exception e) {
			logger.log(Level.INFO, "Responce data:" + e.getMessage());
		}
		logger.log(Level.INFO, "data:" + StringUtil.toString(obj));
		resp.setContentType("text/javascript");
		resp.getWriter().println(StringUtil.toString(obj));
	}
}
