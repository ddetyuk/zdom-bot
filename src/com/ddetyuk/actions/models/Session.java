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
	private Key id;

	@Persistent
	@ManyToOne
	private String us_uid = "";

	@Persistent
	private String us_sid = "";

	@Persistent
	private String us_crc = "";

	@Persistent
	private Integer us_key = 0;

	public Session(String us_sid, String us_crc, String us_uid, Integer us_key) {
		super();
		this.id = KeyFactory.createKey(Session.class.getSimpleName(),
				us_uid);
		this.us_sid = us_sid;
		this.us_crc = us_crc;
		this.us_uid = us_uid;
		this.us_key = us_key;
	}

	public Session(HashMap<String, Object> data) {
		us_sid = (String) data.get("us_sid");
		us_crc = (String) data.get("us_crc");
		us_uid = (String) data.get("us_uid");
		us_key = (Integer) data.get("us_key");
	}
	
	public HashMap<String, Object> toHashMap(){
		HashMap<String, Object> data = new HashMap<String, Object>();
		data.put("us_sid",us_sid);
		data.put("us_crc",us_crc);
		data.put("us_uid",us_uid);
		data.put("us_key",us_key);
		return data;
	}
	
	@Override
	public String toString() {
		return new ToStringBuilder(this).append("us_sid", us_sid)
				.append("us_crc", us_crc).append("us_uid", us_uid)
				.append("us_key", us_key).toString();
	}

	public Key getId() {
		return id;
	}

	public void setId(Key id) {
		this.id = id;
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

	public Integer getUs_key() {
		return us_key;
	}

	public void setUs_key(Integer us_key) {
		this.us_key = us_key;
	}

}
