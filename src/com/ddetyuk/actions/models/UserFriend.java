package com.ddetyuk.actions.models;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import javax.persistence.ManyToOne;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

/**
 * 
 * @author Dmitriy Detyuk (ddetyuk@gmail.com)
 */
@PersistenceCapable(detachable = "true", identityType = IdentityType.APPLICATION)
public class UserFriend {

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key key;

	@Persistent
	@ManyToOne
	private String uid;

	@Persistent
	private String fid;

	public UserFriend(String uid, String fid) {
		super();
		this.key = KeyFactory.createKey(UserFriend.class.getSimpleName(), uid);
		this.uid = uid;
		this.fid = fid;
	}

	public void setKey(Key key) {
		this.key = key;
	}

	public Key getKey() {
		return key;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getUid() {
		return uid;
	}

	public String getFid() {
		return fid;
	}

	public void setFid(String fid) {
		this.fid = fid;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("uid", uid).append("fid", fid)
				.toString();
	}

}
