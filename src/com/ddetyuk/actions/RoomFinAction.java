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
public class RoomFinAction extends AmfAction {


	private static Logger logger = Logger.getLogger(RoomFinAction.class.toString());

	protected User user;
	protected UserRoom room;

	public void setUser(User user) {
		this.user = user;
	}
	
	public void setRoom(UserRoom room) {
		this.room = room;
	}
	
	@Override
	public int getRequestData(OutputStream os) {

		HashMap<String, Object> from = new HashMap<String, Object>();
		from.put("room_id", room.getRoomId());
		from.put("mode_id", room.getNextModeId());
		from.put("missing_items",0);
		from.put("click_energy",3);
		from.put("click_money",60);
		from.put("click_exp",48);
		from.put("used_items",new int[0]);
		from.put("exec_time", room.getMinTime());
		from.put("sq_click_money",0);
		
		HashMap<String, Object> request = new HashMap<String, Object>();
		request.put("action", "room_fin");
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
		if (null != responce.get("room_fin")) {
			HashMap<String, Object> action = (HashMap<String, Object>) responce.get("room_fin");
			if (null != action.get("energy")) {
				user.setEnergy(Double.parseDouble(action.get("energy").toString()));
			}
			if (null != action.get("room")) {
				room.setFromHashMap((HashMap<String, Object>) action.get("room"));
			}
		}

	}
	
	@Override
	public String toString() {
		return "Action Room Fin";
	}
}
