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
public class RoomStartAction extends AmfAction {


	private static Logger logger = Logger.getLogger(RoomStartAction.class.toString());

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
		
		HashMap<String, Object> request = new HashMap<String, Object>();
		request.put("action", "room_start");
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
		//room_start
		//WARNING: Responce data:{common_info={result=1, time=1.329708971E9, bank_version=47, money_game=8422, money_real=11}, room_start={energy_uptime=1329708961, energy=133}}
		

	}
	
	@Override
	public String toString() {
		return "Action Room Start";
	}
}
