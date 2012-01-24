package com.ddetyuk.actions;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.ddetyuk.actions.models.User;
import com.ddetyuk.connection.AmfAction;
import com.exadel.flamingo.flex.messaging.amf.io.AMF3Deserializer;
import com.exadel.flamingo.flex.messaging.amf.io.AMF3Serializer;
import com.exadel.flamingo.flex.messaging.util.StringUtil;

/**
 * 
 * @author Dmitriy Detyuk (ddetyuk@gmail.com)
 */
public class AuthAction extends AmfAction {

	/*
	 * {auth_relogin=0, uid=5806946, form={nick=Дмитрий Детюк, lang=ru},
	 * session=[Ljava.lang.Object;@d22462,
	 * app_friends=[Ljava.lang.Object;@52d1a4,
	 * link=http://vkontakte.ru/id5806946, action=login,
	 * avatar=http://cs304405.vkontakte.ru/u5806946/b_44446400.jpg,
	 * sig=c4cab838653ea54d776e425e296ff5e5}
	 * 
	 * {common_info={result=1, time=1.327283868E9, bank_version=28,
	 * money_game=46788, money_real=11},
	 * session={us_crc=494849bf03931495e98051ff6f7c6138, us_key=57,
	 * us_uid=2176459, us_sid=3b8a2a8829b36dbc6670385d3be14906},
	 * login={monsters_update={update=[Ljava.lang.Object;@40ece0,
	 * del=[Ljava.lang.Object;@7f8922, monsters=[Ljava.lang.Object;@1041876,
	 * add=[Ljava.lang.Object;@1e1ec86}}}
	 */

	private static Logger logger = Logger
			.getLogger(AuthAction.class.toString());

	protected Integer result;
	protected String error;
	protected User user;

	public AuthAction() {
		user = new User("5806946","http://cs304405.vkontakte.ru/u5806946/b_44446400.jpg","http://vkontakte.ru/id5806946","Дмитрий Детюк","ru","c4cab838653ea54d776e425e296ff5e5",null,null,null);
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public int getRequestData(OutputStream os) {
		
		AMF3Serializer amf = new AMF3Serializer(os);
		
		
		Object[] friends = new Object[14];
		friends[0]="3376832";
		
		friends[1]="4497913";
		friends[2]="5982299";
		friends[3]="6033591";
		friends[4]="6660943";
		friends[5]="8738217";
		friends[6]="9316462";
		friends[7]="9459033";
		friends[8]="16064853";
		friends[9]="17775761";
		friends[10]="32575984";
		friends[11]="73447443";
		friends[12]="98481428";
		friends[13]="148349370";
		
		HashMap<String, String> from = new HashMap<String, String>();
		from.put("lang", user.getLang());
		from.put("nick", user.getNick());

		HashMap<String, Object> request = new HashMap<String, Object>();
		request.put("auth_relogin", "0");
		request.put("link", user.getLink());
		request.put("sig", user.getSig());
		request.put("avatar", user.getAvatar());
		request.put("app_friends",friends);
		request.put("form", from);
		request.put("action", "login");
		request.put("session", new Object[0]);
		//request.put("app_friends",user.getApp_friends().toArray());
		request.put("uid", user.getUid());
		
		
		
		try {
			amf.writeObject(request);
			amf.flush();
		} catch (IOException e) {
			logger.log(Level.SEVERE, e.getMessage());
		}
		
//		 try {
//		 FileInputStream in = new FileInputStream("h");
//		 int nextChar;
//		 while ((nextChar = in.read()) != -1) {
//			 os.write(nextChar);
//		 }
//		 os.flush();
//
//		 } catch (IOException e) {
//		 logger.log(Level.SEVERE, e.getMessage());
//		 }

		return amf.size();
	}

	@Override
	public void setResponceData(InputStream in) {
		try {
			 //FileInputStream in = new FileInputStream("h");

			AMF3Deserializer amf = new AMF3Deserializer(in);

			@SuppressWarnings("unchecked")
			HashMap<String, Object> responce = (HashMap<String, Object>) amf
					.readObject();

			if (null != responce.get("error")) {
				error = (String)responce.get("error");
			}
			if (null != responce.get("result")) {
				result = (Integer)responce.get("result");
			}
			if (null != responce.get("common_info")) {
				logger.log(Level.SEVERE, (String)responce.get("common_info"));
				// userInfo = new UserInfo((HashMap<String,
				// String>)responce.get("common_info"));
			}
			if (null != responce.get("session")) {
				logger.log(Level.SEVERE, (String)responce.get("session"));
				// session = new Session((HashMap<String,
				// String>)responce.get("session"));
			}
			logger.log(Level.SEVERE,
					"Responce data:" + StringUtil.toString(responce));
		} catch (IOException e) {
			logger.log(Level.SEVERE, e.toString());
		}
	}
}
