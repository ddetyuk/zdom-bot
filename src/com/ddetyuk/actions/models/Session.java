package com.ddetyuk.actions.models;

import java.util.HashMap;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import javax.persistence.ManyToOne;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

@PersistenceCapable(detachable = "true", identityType = IdentityType.APPLICATION)
public class Session {

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key key;

	@Persistent
	@ManyToOne
	private String us_uid = "";

	@Persistent
	private String us_sid = "";

	@Persistent
	private String us_crc = "";

	@Persistent
	private String us_key = "";

	public Session(String us_sid, String us_crc, String us_uid, String us_key) {
		super();
		this.key = KeyFactory.createKey(UserFriend.class.getSimpleName(),
				us_uid);
		this.us_sid = us_sid;
		this.us_crc = us_crc;
		this.us_uid = us_uid;
		this.us_key = us_key;
	}

	public Session(HashMap<String, String> data) {
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

	public Key getKey() {
		return key;
	}

	public void setKey(Key key) {
		this.key = key;
	}

	public String getUs_uid() {
		return us_uid;
	}

	public void setUs_uid(String us_uid) {
		this.us_uid = us_uid;
	}

	public String getUs_sid() {
		return us_sid;
	}

	public void setUs_sid(String us_sid) {
		this.us_sid = us_sid;
	}

	public String getUs_crc() {
		return us_crc;
	}

	public void setUs_crc(String us_crc) {
		this.us_crc = us_crc;
	}

	public String getUs_key() {
		return us_key;
	}

	public void setUs_key(String us_key) {
		this.us_key = us_key;
	}

}
