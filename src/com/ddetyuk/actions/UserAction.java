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
public class UserAction extends AmfAction {


	private static Logger logger = Logger.getLogger(UserAction.class.toString());

	protected User user;

	public void setUser(User user) {
		this.user = user;
	}
	
	@Override
	public int getRequestData(OutputStream os) {

		HashMap<String, Object> request = new HashMap<String, Object>();
		request.put("action", "user");
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
		
		if (null != responce.get("user")) {
			HashMap<String, Object> udata = (HashMap<String, Object>) responce.get("user");
			if (null != udata.get("user")) {
				HashMap<String, Object> data = (HashMap<String, Object>) udata.get("user");
				
				logger.log(Level.FINE, "data:" + StringUtil.toString(data));
				
				user.setLevel(Long.parseLong(data.get("level").toString()));
				user.setMoney(Long.parseLong(data.get("money").toString()));
				user.setExperience(Long.parseLong(data.get("EXP").toString()));
				user.setGems(Long.parseLong(data.get("money_real").toString()));
				
				user.setEnergy(Double.parseDouble(data.get("ENERGY").toString()));
				user.setEnergyMax(Double.parseDouble(data.get("ENERGY_MAX").toString()));
				user.setEnergyRestore(Double.parseDouble(data.get("ENERGY_RESTORE_T").toString()));
				user.setEnergyUptime(Double.parseDouble(data.get("ENERGY_UPTIME").toString()));
				
				user.setEndurance(Double.parseDouble(data.get("ENDURANCE").toString()));
				user.setEnduranceMax(Double.parseDouble(data.get("ENDURANCE_MAX").toString()));
				user.setEnduranceRestore(Double.parseDouble(data.get("ENDURANCE_RESTORE_T").toString()));
				user.setEnduranceUptime(Double.parseDouble(data.get("ENDURANCE_UPTIME").toString()));
			}
		}
	}
	
	@Override
	public String toString() {
		return "Action User";
	}
}
