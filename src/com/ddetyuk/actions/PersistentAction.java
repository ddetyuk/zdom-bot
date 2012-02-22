package com.ddetyuk.actions;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;

import com.ddetyuk.actions.models.User;
import com.ddetyuk.connection.ActionException;
import com.ddetyuk.connection.AmfAction;

/**
 * 
 * @author Dmitriy Detyuk (ddetyuk@gmail.com)
 */
public class PersistentAction extends AmfAction {

	protected User user;

	public void setUser(User user) {
		this.user = user;
	}
	
	@Override
	public int getRequestData(OutputStream os) {

		HashMap<String, Object> request = new HashMap<String, Object>();
		request.put("action", "friends_online_update");
		request.put("session", user.getSession().toHashMap());
		
		serialize(os, request);

		return 0;
	}


	@Override
	public void setResponceData(InputStream in) throws ActionException {
		
//		HashMap<String, Object> responce = (HashMap<String, Object>)unserialize(in);
//
//		logger.log(Level.FINE, "Responce data:" + StringUtil.toString(responce));
//		
//		if (null != responce.get("error")) {
//			throw new ActionException((String) responce.get("error"));
//		}
	}
	
	@Override
	public String toString() {
		return "Action Persistent";
	}
}
