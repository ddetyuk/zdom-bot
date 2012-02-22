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
public class Top100FriendsAction extends AmfAction {


	private static Logger logger = Logger.getLogger(Top100FriendsAction.class.toString());

	protected User user;

	public void setUser(User user) {
		this.user = user;
	}
	
	@Override
	public int getRequestData(OutputStream os) {

		HashMap<String, Object> request = new HashMap<String, Object>();
		request.put("action", "top100_friends");
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
		
		if (null != responce.get("top100_friends")) {
			HashMap<String, Object> udata = (HashMap<String, Object>) responce.get("top100_friends");
			if (null != udata.get("top100")) {
				Object[] data = (Object[]) udata.get("top100");
				logger.log(Level.INFO, "data:" + StringUtil.toString(data));
				//[{set_cnt=363, weekly=0, exp=519552, set_c2=1, set_c1=192, reg_time=1314993586, user_id=5336999, rating=1, type_id=set}, {set_cnt=249, weekly=0, exp=367835, set_c2=1, set_c1=167, reg_time=1299532348, user_id=1843937, rating=2, type_id=set}, {set_cnt=128, weekly=0, exp=203158, set_c2=0, set_c1=38, reg_time=1306999215, user_id=3634596, rating=3, type_id=set}, {set_cnt=49, weekly=0, exp=80125, set_c2=0, set_c1=37, reg_time=1314365007, user_id=5163863, rating=4, type_id=set}, {set_cnt=23, weekly=0, exp=39752, set_c2=0, set_c1=14, reg_time=1300833780, user_id=2176459, rating=5, type_id=set}, {set_cnt=14, weekly=0, exp=41466, set_c2=0, set_c1=6, reg_time=1310311138, user_id=4386456, rating=6, type_id=set}, {set_cnt=5, weekly=0, exp=7484, set_c2=0, set_c1=0, reg_time=1310846878, user_id=4490396, rating=7, type_id=set}, {set_cnt=1, weekly=0, exp=2448, set_c2=0, set_c1=1, reg_time=1320690988, user_id=6303758, rating=8, type_id=set}, {set_cnt=0, weekly=0, exp=351, set_c2=0, set_c1=0, reg_time=1312133408, user_id=4759694, rating=9, type_id=set}, {set_cnt=0, weekly=0, exp=263, set_c2=0, set_c1=0, reg_time=1301235070, user_id=2364685, rating=10, type_id=set}, {set_cnt=0, weekly=0, exp=152, set_c2=0, set_c1=0, reg_time=1293442108, user_id=617093, rating=11, type_id=set}, {set_cnt=0, weekly=0, exp=140, set_c2=0, set_c1=0, reg_time=1315251677, user_id=5383355, rating=12, type_id=set}, {set_cnt=0, weekly=0, exp=0, set_c2=0, set_c1=0, reg_time=1302637130, user_id=2782408, rating=13, type_id=set}, {set_cnt=0, weekly=0, exp=0, set_c2=0, set_c1=0, reg_time=1301253627, user_id=2388187, rating=14, type_id=set}, {set_cnt=19, weekly=1, exp=46139, set_c2=1, set_c1=3, reg_time=1314993586, user_id=5336999, rating=1, type_id=set}, {set_cnt=14, weekly=1, exp=29375, set_c2=0, set_c1=6, reg_time=1306999215, user_id=3634596, rating=2, type_id=set}, {set_cnt=5, weekly=1, exp=11752, set_c2=0, set_c1=0, reg_time=1310311138, user_id=4386456, rating=3, type_id=set}, {set_cnt=0, weekly=1, exp=1535, set_c2=0, set_c1=0, reg_time=1314365007, user_id=5163863, rating=4, type_id=set}, {set_cnt=0, weekly=1, exp=455, set_c2=0, set_c1=0, reg_time=1300833780, user_id=2176459, rating=5, type_id=set}, {set_cnt=0, weekly=1, exp=105, set_c2=0, set_c1=0, reg_time=1320690988, user_id=6303758, rating=6, type_id=set}, {weekly=0, exp=519552, reg_time=1314993586, user_id=5336999, rating=1, type_id=exp}, {weekly=0, exp=367835, reg_time=1299532348, user_id=1843937, rating=2, type_id=exp}, {weekly=0, exp=203158, reg_time=1306999215, user_id=3634596, rating=3, type_id=exp}, {weekly=0, exp=80125, reg_time=1314365007, user_id=5163863, rating=4, type_id=exp}, {weekly=0, exp=41466, reg_time=1310311138, user_id=4386456, rating=5, type_id=exp}, {weekly=0, exp=39752, reg_time=1300833780, user_id=2176459, rating=6, type_id=exp}, {weekly=0, exp=7484, reg_time=1310846878, user_id=4490396, rating=7, type_id=exp}, {weekly=0, exp=2448, reg_time=1320690988, user_id=6303758, rating=8, type_id=exp}, {weekly=0, exp=351, reg_time=1312133408, user_id=4759694, rating=9, type_id=exp}, {weekly=0, exp=263, reg_time=1301235070, user_id=2364685, rating=10, type_id=exp}, {weekly=0, exp=152, reg_time=1293442108, user_id=617093, rating=11, type_id=exp}, {weekly=0, exp=140, reg_time=1315251677, user_id=5383355, rating=12, type_id=exp}, {weekly=0, exp=0, reg_time=1302637130, user_id=2782408, rating=13, type_id=exp}, {weekly=0, exp=0, reg_time=1301253627, user_id=2388187, rating=14, type_id=exp}, {weekly=1, exp=46139, reg_time=1314993586, user_id=5336999, rating=1, type_id=exp}, {weekly=1, exp=29375, reg_time=1306999215, user_id=3634596, rating=2, type_id=exp}, {weekly=1, exp=11752, reg_time=1310311138, user_id=4386456, rating=3, type_id=exp}, {weekly=1, exp=1535, reg_time=1314365007, user_id=5163863, rating=4, type_id=exp}, {weekly=1, exp=455, reg_time=1300833780, user_id=2176459, rating=5, type_id=exp}, {weekly=1, exp=105, reg_time=1320690988, user_id=6303758, rating=6, type_id=exp}, {weekly=0, reg_time=1314993586, user_id=5336999, rating=1, quest_cnt=367, type_id=quest}, {weekly=0, reg_time=1306999215, user_id=3634596, rating=2, quest_cnt=264, type_id=quest}, {weekly=0, reg_time=1299532348, user_id=1843937, rating=3, quest_cnt=189, type_id=quest}, {weekly=0, reg_time=1314365007, user_id=5163863, rating=4, quest_cnt=143, type_id=quest}, {weekly=0, reg_time=1310311138, user_id=4386456, rating=5, quest_cnt=139, type_id=quest}, {weekly=0, reg_time=1300833780, user_id=2176459, rating=6, quest_cnt=100, type_id=quest}, {weekly=0, reg_time=1310846878, user_id=4490396, rating=7, quest_cnt=42, type_id=quest}, {weekly=0, reg_time=1320690988, user_id=6303758, rating=8, quest_cnt=9, type_id=quest}, {weekly=0, reg_time=1312133408, user_id=4759694, rating=9, quest_cnt=2, type_id=quest}, {weekly=0, reg_time=1301235070, user_id=2364685, rating=10, quest_cnt=2, type_id=quest}, {weekly=0, reg_time=1315251677, user_id=5383355, rating=11, quest_cnt=1, type_id=quest}, {weekly=0, reg_time=1293442108, user_id=617093, rating=12, quest_cnt=1, type_id=quest}, {weekly=0, reg_time=1302637130, user_id=2782408, rating=13, quest_cnt=0, type_id=quest}, {weekly=0, reg_time=1301253627, user_id=2388187, rating=14, quest_cnt=0, type_id=quest}, {weekly=1, reg_time=1310311138, user_id=4386456, rating=1, quest_cnt=25, type_id=quest}, {weekly=1, reg_time=1306999215, user_id=3634596, rating=2, quest_cnt=14, type_id=quest}, {weekly=1, reg_time=1314993586, user_id=5336999, rating=3, quest_cnt=12, type_id=quest}, {weekly=1, reg_time=1314365007, user_id=5163863, rating=4, quest_cnt=3, type_id=quest}, {weekly=1, reg_time=1300833780, user_id=2176459, rating=5, quest_cnt=1, type_id=quest}, {weekly=1, reg_time=1320690988, user_id=6303758, rating=6, quest_cnt=0, type_id=quest}, {weekly=0, reg_time=1314993586, monster_cnt=2283, user_id=5336999, rating=1, type_id=monster}, {weekly=0, reg_time=1306999215, monster_cnt=1041, user_id=3634596, rating=2, type_id=monster}, {weekly=0, reg_time=1314365007, monster_cnt=144, user_id=5163863, rating=3, type_id=monster}, {weekly=0, reg_time=1310311138, monster_cnt=108, user_id=4386456, rating=4, type_id=monster}, {weekly=0, reg_time=1300833780, monster_cnt=28, user_id=2176459, rating=5, type_id=monster}, {weekly=0, reg_time=1310846878, monster_cnt=3, user_id=4490396, rating=6, type_id=monster}, {weekly=0, reg_time=1320690988, monster_cnt=1, user_id=6303758, rating=7, type_id=monster}, {weekly=0, reg_time=1315251677, monster_cnt=0, user_id=5383355, rating=8, type_id=monster}, {weekly=0, reg_time=1312133408, monster_cnt=0, user_id=4759694, rating=9, type_id=monster}, {weekly=0, reg_time=1302637130, monster_cnt=0, user_id=2782408, rating=10, type_id=monster}, {weekly=0, reg_time=1301253627, monster_cnt=0, user_id=2388187, rating=11, type_id=monster}, {weekly=0, reg_time=1301235070, monster_cnt=0, user_id=2364685, rating=12, type_id=monster}, {weekly=0, reg_time=1299532348, monster_cnt=0, user_id=1843937, rating=13, type_id=monster}, {weekly=0, reg_time=1293442108, monster_cnt=0, user_id=617093, rating=14, type_id=monster}, {weekly=1, reg_time=1314993586, monster_cnt=299, user_id=5336999, rating=1, type_id=monster}, {weekly=1, reg_time=1306999215, monster_cnt=202, user_id=3634596, rating=2, type_id=monster}, {weekly=1, reg_time=1310311138, monster_cnt=100, user_id=4386456, rating=3, type_id=monster}, {weekly=1, reg_time=1314365007, monster_cnt=4, user_id=5163863, rating=4, type_id=monster}, {weekly=1, reg_time=1320690988, monster_cnt=0, user_id=6303758, rating=5, type_id=monster}, {weekly=1, reg_time=1300833780, monster_cnt=0, user_id=2176459, rating=6, type_id=monster}]
			}
		}
	}
	
	@Override
	public String toString() {
		return "Action Top 100 Friends";
	}
}
