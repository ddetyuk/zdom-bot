package com.ddetyuk.actions.models;

import java.util.HashMap;
import java.util.logging.Logger;

import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import org.apache.commons.lang.builder.ToStringBuilder;

@PersistenceCapable(detachable = "true", identityType = IdentityType.APPLICATION)
public class User {

	private static Logger logger = Logger.getLogger(User.class.toString());

	@PrimaryKey
	@Persistent
	private String us_sid = "";

	@Persistent
	private String us_crc = "";

	@Persistent
	private String us_uid = "";

	@Persistent
	private String us_key = "";

	public User(String us_sid, String us_crc, String us_uid, String us_key) {
		this.us_sid = us_sid;
		this.us_crc = us_crc;
		this.us_uid = us_uid;
		this.us_key = us_key;
	}

	public User(HashMap<String, String> data) {
		us_sid = data.get("us_sid");
		us_crc = data.get("us_crc");
		us_uid = data.get("us_uid");
		us_key = data.get("us_key");
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("us_sid", us_sid)
				.append("us_crc", us_crc).append("us_uid", us_uid)
				.append("us_key", us_key).toString();
	}

}
