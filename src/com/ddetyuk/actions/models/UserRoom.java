package com.ddetyuk.actions.models;

import java.util.HashMap;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

/**
 * 
 * @author Dmitriy Detyuk (ddetyuk@gmail.com)
 */
@PersistenceCapable(detachable = "true", identityType = IdentityType.APPLICATION)
public class UserRoom {

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key id;
	
	@Persistent
	private Long rid = 0L;
	
	@Persistent
	private Long lastPhenomenonStep=0L;
	
	@Persistent
	private Long count=0L;
	
	@Persistent
	private Long phenomenonId=0L;
	
	@Persistent
	private Long masterLevel=4L;
	
	@Persistent
	private Long minTime=0L;
	
	@Persistent
	private Long totalCount=0L;
	
	@Persistent
	private Long lastModeId=0L;
	
	@Persistent
	private Long nextModeId=0L;
	
	@Persistent
	private Long roomId=0L;

//	@Persistent
//	@ManyToOne(targetEntity = User.class, cascade = { CascadeType.ALL })
//	private User user;
	
	
	public UserRoom(Key id, Long rid, Long lastPhenomenonStep, Long count,
			Long phenomenonId, Long masterLevel, Long minTime, Long totalCount,
			Long lastModeId, Long nextModeId, Long roomId) {
		super();
		this.id = id;
		this.rid = rid;
		this.lastPhenomenonStep = lastPhenomenonStep;
		this.count = count;
		this.phenomenonId = phenomenonId;
		this.masterLevel = masterLevel;
		this.minTime = minTime;
		this.totalCount = totalCount;
		this.lastModeId = lastModeId;
		this.nextModeId = nextModeId;
		this.roomId = roomId;
	}
	
	public UserRoom(HashMap<String, Object> data){
		super();
		setFromHashMap(data);
	}

	public void setFromHashMap(HashMap<String, Object> data) {
		this.rid = Long.parseLong(data.get("id").toString());
		this.lastPhenomenonStep = Long.parseLong(data.get("last_phenomenon_step").toString());
		this.count  = Long.parseLong(data.get("count").toString());
		this.phenomenonId  = Long.parseLong(data.get("phenomenon_id").toString());
		this.masterLevel = Long.parseLong(data.get("master_level").toString());
		this.minTime = Long.parseLong(data.get("min_time").toString());
		this.totalCount = Long.parseLong(data.get("total_count").toString());
		this.lastModeId = Long.parseLong(data.get("last_mode_id").toString());
		this.nextModeId = Long.parseLong(data.get("next_mode_id").toString());
		this.roomId = Long.parseLong(data.get("room_id").toString());
	}
	
	public Long getRid() {
		return rid;
	}

	public void setRid(Long rid) {
		this.rid = rid;
	}

	public Long getLastPhenomenonStep() {
		return lastPhenomenonStep;
	}

	public void setLastPhenomenonStep(Long lastPhenomenonStep) {
		this.lastPhenomenonStep = lastPhenomenonStep;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

	public Long getPhenomenonId() {
		return phenomenonId;
	}

	public void setPhenomenonId(Long phenomenonId) {
		this.phenomenonId = phenomenonId;
	}

	public Long getMasterLevel() {
		return masterLevel;
	}

	public void setMasterLevel(Long masterLevel) {
		this.masterLevel = masterLevel;
	}

	public Long getMinTime() {
		return minTime;
	}

	public void setMinTime(Long minTime) {
		this.minTime = minTime;
	}

	public Long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}

	public Long getLastModeId() {
		return lastModeId;
	}

	public void setLastModeId(Long lastModeId) {
		this.lastModeId = lastModeId;
	}

	public Long getNextModeId() {
		return nextModeId;
	}

	public void setNextModeId(Long nextModeId) {
		this.nextModeId = nextModeId;
	}

	public Long getRoomId() {
		return roomId;
	}

	public void setRoomId(Long roomId) {
		this.roomId = roomId;
	}

	public Long getNeedfullEnergy(){
		if( phenomenonId>0L ){
			return 60L;
		}else{
			return 40L;
		}
	}
	
//	public User getUser() {
//		return user;
//	}
//
//	public void setUser(User user) {
//		this.user = user;
//	} 
//	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UserRoom [id=").append(id)
				.append(", lastPhenomenonStep=").append(lastPhenomenonStep)
				.append(", count=").append(count).append(", phenomenonId=")
				.append(phenomenonId).append(", masterLevel=")
				.append(masterLevel).append(", minTime=").append(minTime)
				.append(", totalCount=").append(totalCount)
				.append(", lastModeId=").append(lastModeId)
				.append(", nextModeId=").append(nextModeId).append(", roomId=")
				.append(roomId).append("]");
		return builder.toString();
	}

}
