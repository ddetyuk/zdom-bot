package com.ddetyuk.actions;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.ddetyuk.actions.models.User;
import com.ddetyuk.connection.ActionException;
import com.ddetyuk.connection.AmfAction;
import com.exadel.flamingo.flex.messaging.util.StringUtil;

/**
 * 
 * @author Dmitriy Detyuk (ddetyuk@gmail.com)
 */
public class FriendHelpAction extends AmfAction {


	private static Logger logger = Logger.getLogger(FriendHelpAction.class.toString());

	protected User user;
	
	protected User otheruser;

	public void setUser(User user) {
		this.user = user;
	}
	
	public void setOtherUser(User user) {
		this.otheruser = user;
	}
	
	@Override
	public int getRequestData(OutputStream os) {
		Object[] helps = new Object[0];
//		for (Object object : helps) {
//			HashMap<String, Object> data  = new HashMap<String, Object>();
//			//data.put("room_id", value);
//			data.put("help_type", 2);
//			//helps[] = data;
//		}
		
		HashMap<String, Object> from = new HashMap<String, Object>();
		from.put("friend_id", otheruser.getUid());
		from.put("helps", helps);
		
		
		HashMap<String, Object> request = new HashMap<String, Object>();
		request.put("action", "friend_helps");
		request.put("session", user.getSession().toHashMap());
		request.put("form", from);

		serialize(os, request);

		return 0;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void setResponceData(InputStream in) throws ActionException {
		
		HashMap<String, Object> responce = (HashMap<String, Object>)unserialize(in);
		
		logger.log(Level.FINE, "Responce data:" + StringUtil.toString(responce));

		if (null != responce.get("error")) {
			throw new ActionException((String) responce.get("error"));
		}
		

	}
	
	@Override
	public String toString() {
		return "Action Other user";
	}
}
