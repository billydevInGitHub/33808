package com.billydev.blib.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "Event_Info")
public class Event_Info {



	public void setEventIid(long eventIid) {
		this.eventId = eventIid;
	}

	public String getDtAppname() {
		return dtAppname;
	}

	public void setDtAppname(String dtAppname) {
		this.dtAppname = dtAppname;
	}

	public Timestamp getNextScheduledTime() {
		return nextScheduledTime;
	}

	public void setNextScheduledTime(Timestamp nextScheduledTime) {
		this.nextScheduledTime = nextScheduledTime;
	}

	public Timestamp getEventCreateTime() {
		return eventCreateTime;
	}

	public void setEventCreateTime(Timestamp eventCreateTime) {
		this.eventCreateTime = eventCreateTime;
	}

	long eventId;
	String dtAppname;
	Timestamp nextScheduledTime;
	String eventCreatorUserId;
	String schedule;
	String state;
	String userParameters; 
	Timestamp eventCreateTime;
	String description;
	String eventName;

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public String getSchedule() {
		return schedule;
	}


	public void setSchedule(String schedule) {
		this.schedule = schedule;
	}


	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}


	public String getUserParameters() {
		return userParameters;
	}


	public void setUserParameters(String userParameters) {
		this.userParameters = userParameters;
	}






	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}



	public String getEventCreatorUserId() {
		return eventCreatorUserId;
	}


	public void setEventCreatorUserId(String eventCreatorUserId) {
		this.eventCreatorUserId = eventCreatorUserId;
	}


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long getEventId() {
		return eventId;
	}

	public void setEventId(long eventId) {
		this.eventId = eventId;
	}

	public Event_Info() {
		
	}
	

	

}
