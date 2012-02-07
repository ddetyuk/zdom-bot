package com.ddetyuk.actions;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.ddetyuk.actions.models.Session;
import com.ddetyuk.actions.models.User;
import com.ddetyuk.actions.models.UserInfo;
import com.ddetyuk.connection.AmfAction;
import com.exadel.flamingo.flex.messaging.util.StringUtil;

/**
 * 
 * @author Dmitriy Detyuk (ddetyuk@gmail.com)
 */
public class UserAction extends AmfAction {


	private static Logger logger = Logger.getLogger(UserAction.class.toString());

	protected Integer result;
	protected String error;
	protected User user;

	public UserAction() {

	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public UserInfo getUserInfo(){
		return null;
	}

	public Session getSession(){
		return null;
	}
	
	@Override
	public int getRequestData(OutputStream os) {

		HashMap<String, Object> request = new HashMap<String, Object>();
		request.put("action", "user");
		request.put("session", user.getSession().toHashMap());

		serialize(os, request);

		return 0;
	}

	@Override
	public void setResponceData(InputStream in) {
		
		HashMap<String, Object> responce = new HashMap<String, Object>();
		unserialize(in, responce);

		if (null != responce.get("error")) {
			error = (String) responce.get("error");
		}
		if (null != responce.get("result")) {
			result = (Integer) responce.get("result");
		}

		logger.log(Level.WARNING, "Responce data:" + StringUtil.toString(responce));

	}
}
