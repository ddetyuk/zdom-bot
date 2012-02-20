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
import javax.persistence.OneToOne;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.google.appengine.api.datastore.Key;

/**
 * 
 * @author Dmitriy Detyuk (ddetyuk@gmail.com)
 */
@PersistenceCapable(detachable = "true", identityType = IdentityType.APPLICATION)
public class User {

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key id;
	
	@Persistent
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

	@Persistent
	private Long level = 0L;
	
	@Persistent
	private Long money = 0L;

	@Persistent
	private Long gems = 0L;
	
	@Persistent
	private Long experience = 0L;

	@Persistent
	private Double energy = 0.0;

	@Persistent
	private Double energyMax = 0.0;
	
	@Persistent
	private Double energyRestore = 0.0;
	
	@Persistent
	private Double energyUptime = 0.0;
	
	@Persistent
	private Double endurance = 0.0;
	
	@Persistent
	private Double enduranceMax = 0.0;
	
	@Persistent
	private Double enduranceRestore = 0.0;
	
	@Persistent
	private Double enduranceUptime = 0.0;
	
	@Persistent(defaultFetchGroup = "true")
	@OneToOne(mappedBy = "id", cascade = { CascadeType.ALL })
	private Session session;

	@Persistent(defaultFetchGroup = "true")
	@OneToMany(mappedBy = "id", cascade = { CascadeType.ALL })
	private List<UserRoom> userRooms;
	
	public User(String uid, String avatar, String link, String nick, String lang, String sig) {
		super();
		this.uid = uid;
		this.avatar = avatar;
		this.link = link;
		this.nick = nick;
		this.lang = lang;
		this.sig = sig;
	}
	
	public User(String uid, String avatar, String link, String nick,
			String lang, String sig, Long level, Long money, Long gems,
			Long experience, Double energy, Double energyMax,
			Double energyRestore, Double energyUptime, Double endurance,
			Double enduranceMax, Double enduranceRestore,
			Double enduranceUptime, Session session,List<UserRoom> userRooms) {
		super();
		this.uid = uid;
		this.avatar = avatar;
		this.link = link;
		this.nick = nick;
		this.lang = lang;
		this.sig = sig;
		this.level = level;
		this.money = money;
		this.gems = gems;
		this.experience = experience;
		this.energy = energy;
		this.energyMax = energyMax;
		this.energyRestore = energyRestore;
		this.energyUptime = energyUptime;
		this.endurance = endurance;
		this.enduranceMax = enduranceMax;
		this.enduranceRestore = enduranceRestore;
		this.enduranceUptime = enduranceUptime;
		this.session = session;
		this.userRooms = userRooms;
	}



	public Long getLevel() {
		return level;
	}



	public void setLevel(Long level) {
		this.level = level;
	}



	public Long getMoney() {
		return money;
	}



	public void setMoney(Long money) {
		this.money = money;
	}



	public Long getGems() {
		return gems;
	}



	public void setGems(Long gems) {
		this.gems = gems;
	}



	public Long getExperience() {
		return experience;
	}


	public void setExperience(Long experience) {
		this.experience = experience;
	}



	public Double getEnergy() {
		return energy;
	}



	public void setEnergy(Double energy) {
		this.energy = energy;
	}



	public Double getEnergyMax() {
		return energyMax;
	}



	public void setEnergyMax(Double energyMax) {
		this.energyMax = energyMax;
	}



	public Double getEnergyRestore() {
		return energyRestore;
	}



	public void setEnergyRestore(Double energyRestore) {
		this.energyRestore = energyRestore;
	}



	public Double getEnergyUptime() {
		return energyUptime;
	}



	public void setEnergyUptime(Double energyUptime) {
		this.energyUptime = energyUptime;
	}



	public Double getEndurance() {
		return endurance;
	}



	public void setEndurance(Double endurance) {
		this.endurance = endurance;
	}



	public Double getEnduranceMax() {
		return enduranceMax;
	}



	public void setEnduranceMax(Double enduranceMax) {
		this.enduranceMax = enduranceMax;
	}



	public Double getEnduranceRestore() {
		return enduranceRestore;
	}



	public void setEnduranceRestore(Double enduranceRestore) {
		this.enduranceRestore = enduranceRestore;
	}



	public Double getEnduranceUptime() {
		return enduranceUptime;
	}



	public void setEnduranceUptime(Double enduranceUptime) {
		this.enduranceUptime = enduranceUptime;
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

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("User [uid=").append(uid).append(", avatar=")
				.append(avatar).append(", link=").append(link)
				.append(", nick=").append(nick).append(", lang=").append(lang)
				.append(", sig=").append(sig).append(", level=").append(level)
				.append(", money=").append(money).append(", gems=")
				.append(gems).append(", experience=").append(experience)
				.append(", energy=").append(energy).append(", energyMax=")
				.append(energyMax).append(", energyRestore=")
				.append(energyRestore).append(", energyUptime=")
				.append(energyUptime).append(", endurance=").append(endurance)
				.append(", enduranceMax=").append(enduranceMax)
				.append(", enduranceRestore=").append(enduranceRestore)
				.append(", enduranceUptime=").append(enduranceUptime)
				.append(", session=").append(session).append("]").toString();
	}

	public List<UserRoom> getUserRooms() {
		return userRooms;
	}

	public void setUserRooms(List<UserRoom> userRooms) {
		this.userRooms = userRooms;
	}
	
	public void addtUserRoom(UserRoom userRoom) {
		if(userRooms==null){
			userRooms = new ArrayList<UserRoom>();
		}
		this.userRooms.add(userRoom);
	}

	public Key getId() {
		return id;
	}

	public void setId(Key id) {
		this.id = id;
	}

}
