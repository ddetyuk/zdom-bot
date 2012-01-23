package com.ddetyuk.actions;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.ddetyuk.actions.models.Session;
import com.ddetyuk.actions.models.User;
import com.ddetyuk.actions.models.UserInfo;
import com.ddetyuk.connection.AmfAction;
import com.exadel.flamingo.flex.messaging.amf.io.AMF3Deserializer;
import com.exadel.flamingo.flex.messaging.amf.io.AMF3Serializer;
import com.exadel.flamingo.flex.messaging.util.StringUtil;

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

	private static Logger logger = Logger.getLogger(AuthAction.class.toString());

	protected Integer result;
	protected String error;
	protected Session session;
	protected UserInfo userInfo;
	protected User user;
	
	public AuthAction() {

	}
	
	public Session getSession(){
		return session;
	}
	
	public UserInfo getUserInfo(){
		return userInfo;
	}
	
	public void setUser(User user){
		this.user = user;
	}

	@Override
	public int getRequestData(OutputStream os) {
		AMF3Serializer amf = new AMF3Serializer(os);
		try {
			FileInputStream in = new FileInputStream("26_Request.txt");
			int nextChar;
			while ((nextChar = in.read()) != -1) {
				amf.write(nextChar);
			}
			amf.flush();
			logger.log(Level.SEVERE, "Request data:"+ amf.toString());
		} catch (IOException e) {
			logger.log(Level.SEVERE, e.getMessage());
		}

		return amf.size();
	}

	@Override
	public void setResponceData(InputStream in) {
		try {
			// FileInputStream in = new FileInputStream("26_Request.txt");

			AMF3Deserializer amf = new AMF3Deserializer(in);

			@SuppressWarnings("unchecked")
			HashMap<String, String> responce = (HashMap<String, String>) amf.readObject();

			if (null != responce.get("error")) {
				error = responce.get("error");
			}
			if (null != responce.get("result")) {
				error = responce.get("result");
			}
			if (null != responce.get("common_info")) {
				logger.log(Level.SEVERE, responce.get("common_info"));
				//userInfo = new UserInfo((HashMap<String, String>)responce.get("common_info"));
			}
			if (null != responce.get("session")) {
				logger.log(Level.SEVERE, responce.get("session"));
				//session = new Session((HashMap<String, String>)responce.get("session"));
			}
			logger.log(Level.SEVERE, "Responce data:" + StringUtil.toString(responce));
		} catch (IOException e) {
			logger.log(Level.SEVERE, e.toString());
		}
	}
}
