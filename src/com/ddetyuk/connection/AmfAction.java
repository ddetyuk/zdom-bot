package com.ddetyuk.connection;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.exadel.flamingo.flex.messaging.amf.io.AMF3Deserializer;
import com.exadel.flamingo.flex.messaging.amf.io.AMF3Serializer;

/**
 * 
 * @author Dmitriy Detyuk (ddetyuk@gmail.com)
 */
public abstract class AmfAction implements Action {

	private static Logger logger = Logger.getLogger(AmfAction.class.toString());
	
	protected static String APP_SECRET = "bSv9ms2ZGU";
	protected static String APP_ID = "1995682";

	protected String error ="";
	protected Integer result;
	
	public AmfAction() {
		headers.put("Host", "n1-mhouse-vk.ilikegames.ru");
		headers.put("User-Agent","Mozilla/5.0 (Windows NT 6.1; rv:9.0.1) Gecko/20100101 Firefox/9.0.1 FirePHP/0.6");
		headers.put("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		headers.put("Accept-Language", "ru-ru,ru;q=0.8,en-us;q=0.5,en;q=0.3");
		headers.put("Accept-Encoding", "gzip, deflate");
		headers.put("Accept-Charset", "windows-1251,utf-8;q=0.7,*;q=0.7");
		headers.put("Connection", "keep-alive");
		headers.put("x-insight", "activate");
		headers.put("Referer","http://cs304205.vkontakte.ru/[[IMPORT]]/cs304904.vkontakte.ru/u104048413/c4469458f209d6.zip?1288194647");
		headers.put("Content-type", "application/x-www-form-urlencoded");
	}

	@Override
	public HashMap<String, String> getHeaders() {
		return headers;
	}

	@Override
	public String getMathod() {
		return "POST";
	}

	@Override
	public String getUrl() {
		return "http://n1-mhouse-vk.ilikegames.ru/request.php";
	}

	public String signer(String userid) {
		String data = new String(APP_ID + "_" + userid + "_" + APP_SECRET);
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			byte hash[] = md5.digest(data.getBytes());
			Formatter formatter = new Formatter();
			for (byte b : hash) {
				formatter.format("%02x", b);
			}
			return formatter.toString();
		} catch (NoSuchAlgorithmException e) {
			 logger.log(Level.SEVERE, e.getMessage());
		}
		return "";
	}

	public void serialize(OutputStream os, Object obj) {

		try {
			ByteArrayOutputStream nos = new ByteArrayOutputStream();
			AMF3Serializer amf = new AMF3Serializer(nos);
			amf.writeObject(obj);
			amf.close();

			// maga amf hack, replaced object to array (/n issue)
			byte[] bytes = nos.toByteArray();
			for (int i = 0; i < bytes.length; i++) {
				if (bytes[i] == 0x0A && bytes[i + 1] == 0x0B) {
					os.write(0x09);
					i++;
				} else {
					if (bytes[i] == 0x0A && bytes[i + 1] == 0x01) {
						os.write(0x09);
					} else {
						os.write(bytes[i]);
					}
				}
			}
		} catch (IOException e) {
			 logger.log(Level.SEVERE, e.getMessage());
		}
	}

	@SuppressWarnings("unchecked")
	public Object unserialize(InputStream in) {
		 Map<Object, Object> obj = new HashMap<Object, Object>();
		try {
			AMF3Deserializer amf = new AMF3Deserializer(in);
			obj = (Map<Object, Object>)amf.readObject();
		} catch (IOException e) {
			 logger.log(Level.SEVERE, e.getMessage());
		}
		return obj;
	}
	
	@Override
	public boolean isError() {
		return !error.isEmpty();
	}
	
	@Override
	public String getError() {
		return error;
	}
}
