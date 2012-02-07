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
public class LoginAction extends AmfAction {


	private static Logger logger = Logger.getLogger(LoginAction.class.toString());

	protected Integer result;
	protected String error;
	protected User user;

	public LoginAction() {

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

		HashMap<String, Object> from = new HashMap<String, Object>();
		from.put("nick", user.getNick());
		from.put("lang", user.getLang());

		HashMap<String, Object> request = new HashMap<String, Object>();
		request.put("auth_relogin", 0);
		request.put("uid", user.getUid());
		request.put("link", user.getLink());
		request.put("sig", signer(user.getUid()));
		request.put("avatar", user.getAvatar());
		request.put("form", from);
		request.put("action", "login");
		request.put("session", new int[0]);
		//request.put("app_friends",user.getApp_friends().toArray());
		request.put("app_friends",new Object[0]);

		serialize(os, request);

		return 0;
	}

	@SuppressWarnings("unchecked")
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

		if (null != responce.get("common_info")) {
			logger.log(Level.SEVERE,StringUtil.toString(responce.get("common_info")));
			user.setUserInfo(new UserInfo((HashMap<String, String>)responce.get("common_info")));
		}
		if (null != responce.get("session")) {
			logger.log(Level.SEVERE,StringUtil.toString(responce.get("session")));
			user.setSession(new Session((HashMap<String, String>)responce.get("session")));
		}
		logger.log(Level.WARNING, "Responce data:" + StringUtil.toString(responce));

	}
}
