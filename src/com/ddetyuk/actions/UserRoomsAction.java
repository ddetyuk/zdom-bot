package com.ddetyuk.actions;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.ddetyuk.actions.models.User;
import com.ddetyuk.actions.models.UserRoom;
import com.ddetyuk.connection.ActionException;
import com.ddetyuk.connection.AmfAction;
import com.exadel.flamingo.flex.messaging.util.StringUtil;

/**
 * 
 * @author Dmitriy Detyuk (ddetyuk@gmail.com)
 */
public class UserRoomsAction extends AmfAction {


	private static Logger logger = Logger.getLogger(UserRoomsAction.class.toString());

	protected User user;

	public void setUser(User user) {
		this.user = user;
	}
	
	@Override
	public int getRequestData(OutputStream os) {
		HashMap<String, Object> request = new HashMap<String, Object>();
		request.put("action", "user_rooms");
		request.put("session", user.getSession().toHashMap());
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
		
		if (null != responce.get("user_rooms")) {
			HashMap<String, Object> duser = (HashMap<String, Object>) responce.get("user_rooms");
			if (null != duser.get("user_rooms")) {
				Object[] userRooms = (Object[])duser.get("user_rooms");
				for (Object data : userRooms) {
					//logger.log(Level.FINE, "Responce data:" + StringUtil.toString(data));
					UserRoom room = new UserRoom((HashMap<String, Object>)data);
					//room.setUser(user);
					user.addtUserRoom(room);
				}
			}
		}

	}
	
	@Override
	public String toString() {
		return "Action User rooms";
	}
}
