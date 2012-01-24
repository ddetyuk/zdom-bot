package com.ddetyuk.actions.models;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 
 * @author Dmitriy Detyuk (ddetyuk@gmail.com)
 */
@PersistenceCapable(detachable = "true", identityType = IdentityType.APPLICATION)
public class User {

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private String uid = "";

	@Persistent
	private String avatar = "";

	@Persistent
	private String link = "";

	@Persistent
	private String nick = "";

	@Persistent
	private String lang = "";

	@Persistent
	private String sig = "";

	@Persistent(defaultFetchGroup = "false")
	@OneToMany(mappedBy = "id", cascade = { CascadeType.ALL })
	private List<UserFriend> app_friends = new ArrayList<UserFriend>();

	@Persistent(defaultFetchGroup = "false")
	@OneToMany(mappedBy = "id", cascade = { CascadeType.ALL })
	private List<Session> sessions = new ArrayList<Session>();

	@Persistent(defaultFetchGroup = "false")
	@OneToMany(mappedBy = "id", cascade = { CascadeType.ALL })
	private List<UserInfo> userInfo = new ArrayList<UserInfo>();

	public User(String uid, String avatar, String link, String nick,
			String lang, String sig, List<UserFriend> app_friends,
			List<Session> sessions, List<UserInfo> userInfo) {
		super();
		this.uid = uid;
		this.avatar = avatar;
		this.link = link;
		this.nick = nick;
		this.lang = lang;
		this.sig = sig;
		this.app_friends = app_friends;
		this.sessions = sessions;
		this.userInfo = userInfo;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public String getSig() {
		return sig;
	}

	public void setSig(String sig) {
		this.sig = sig;
	}

	public List<UserFriend> getApp_friends() {
		return app_friends;
	}

	public void setApp_friends(List<UserFriend> app_friends) {
		this.app_friends = app_friends;
	}

	public List<Session> getSessions() {
		return sessions;
	}

	public void setSessions(List<Session> sessions) {
		this.sessions = sessions;
	}

	public List<UserInfo> getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(List<UserInfo> userInfo) {
		this.userInfo = userInfo;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("uid", uid)
				.append("avatar", avatar).append("link", link)
				.append("nick", nick).append("lang", lang).append("sig", sig)
				.append("app_friends", app_friends).toString();
	}

}
