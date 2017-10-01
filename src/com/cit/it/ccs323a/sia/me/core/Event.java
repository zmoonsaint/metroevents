package com.cit.it.ccs323a.sia.me.core;

public class Event {

	private int eventCode;
	private String eventName;
	private String eventDate;
	private String eventOrganizer;
	private String eventLocation;

	public int getEventCode() {
		return eventCode;
	}
	
	public void setEventCode(int eventCode) {
		this.eventCode = eventCode;
	}

	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	
	public String getEventDate() {
		return eventDate;
	}
	
	public void setEventDate(String eventDate) {
		this.eventDate = eventDate;
	}

	public String getEventOrganizer() {
		return eventOrganizer;
	}

	public void setEventOrganizer(String eventOrganizer) {
		this.eventOrganizer = eventOrganizer;
	}

	public String getEventLocation() {
		return eventLocation;
	}

	public void setEventLocation(String eventLocation) {
		this.eventLocation = eventLocation;
	}
	
	//create
	//search
	//update
	//delete
	
	
}
