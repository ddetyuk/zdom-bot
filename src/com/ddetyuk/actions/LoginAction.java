package com.ddetyuk.actions;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.ddetyuk.actions.models.Session;
import com.ddetyuk.actions.models.User;
import com.ddetyuk.connection.ActionException;
import com.ddetyuk.connection.AmfAction;
import com.exadel.flamingo.flex.messaging.util.StringUtil;

/**
 * 
 * @author Dmitriy Detyuk (ddetyuk@gmail.com)
 */
public class LoginAction extends AmfAction {


	private static Logger logger = Logger.getLogger(LoginAction.class.toString());

	protected User user;

	public LoginAction() {

	}

	public void setUser(User user) {
		this.user = user;
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

		serialize(os, request);

		return 0;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void setResponceData(InputStream in) throws ActionException {
		
		Map<String, Object> responce = (Map<String, Object>)unserialize(in);

		logger.log(Level.FINE, "Responce data:" + StringUtil.toString(responce));
		
		if (null != responce.get("error")) {
			throw new ActionException((String) responce.get("error"));
		}
		if (null != responce.get("session")) {
			logger.log(Level.FINE,StringUtil.toString(responce.get("session")));
			user.setSession(new Session((HashMap<String, Object>)responce.get("session")));
		}
		

	}
	
	@Override
	public String toString() {
		return "Action Login";
	}
}
