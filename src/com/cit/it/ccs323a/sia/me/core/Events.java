package com.cit.it.ccs323a.sia.me.core;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import com.cit.it.ccs323a.sia.me.db.DBEvent;
import com.cit.it.ccs323a.sia.me.ui.NotificationOrg;

public class Events {

	private int eventID;
	private String eventCode;
	private String eventName;
	private Date eventDate;
	private int eventOrganizer; //userID
	private String eventLocation;
	private String eventDescription;
	private Timestamp eventDateAdd;
	public DBEvent dbEvent = new DBEvent();

	public String getEventCode() {
		return eventCode;
	}
	
	public void setEventCode(String eventCode) {
		this.eventCode = eventCode;
	}

	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	
	public Date getEventDate() {
		return eventDate;
	}
	
	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}

	public int getEventOrganizer() {
		return eventOrganizer;
	}

	public void setEventOrganizer(int eventOrganizer) {
		this.eventOrganizer = eventOrganizer;
	}

	public String getEventLocation() {
		return eventLocation;
	}

	public void setEventLocation(String eventLocation) {
		this.eventLocation = eventLocation;
	}
	
	public Timestamp getEventDateAdd() {
		return eventDateAdd;
	}

	public void setEventDateAdd(Timestamp eventDateAdd) {
		this.eventDateAdd = eventDateAdd;
	}
	
	public String getEventDescription() {
		return eventDescription;
	}

	public void setEventDescription(String eventDescription) {
		this.eventDescription = eventDescription;
	}
	
	public int getEventID() {
		return eventID;
	}

	public void setEventID(int eventID) {
		this.eventID = eventID;
	}
	
	
	public int getEventID(String eventCode) {
		return dbEvent.getEventID(eventCode);
	}
	//create
	public boolean createEvent(Events event, User user) {
		return dbEvent.createEvent(event, user);
	}
	
	public boolean verifyEventCodeExist(String eventCode) {
		return dbEvent.verifyEventCodeExist(eventCode);
	}
	
	public ArrayList<Events> getAllUserEvents(User user) {
		return dbEvent.getAllUserEvents(user);
	}
	public ArrayList<Events> getAllEventsByStatus(int eventStatus) {
		return dbEvent.getAllEventsByStatus(eventStatus);
	}
	
	public ArrayList<Events> getAllEventsByOrganizer(int eventOrganizer) {
		return dbEvent.getAllEventsByOrganizer(eventOrganizer);
	}
	//search
	//update
	public boolean updateEvent(Events event, User user) {
		return dbEvent.updateEvent(event, user);
	}
	//delete
	public void deleteEvent(String eventCode) {
		dbEvent.deleteEvent(eventCode);
	}
	
	@SuppressWarnings("deprecation")
	public static void main(String [] args) {


           SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm");
            java.util.Date parsed;
			try {
				parsed = format.parse("2011-02-10 24:23");
				 java.sql.Date sql = new java.sql.Date(parsed.getTime());
					Events e = new Events();
					e.setEventCode("test134");
					e.setEventName("test event");
					e.setEventLocation("test event location");
					e.setEventOrganizer(5);
					e.setEventDate(sql);
					
					Request requests = new Request();
					requests.setUserID(5);
					requests.setEventCode("test134");
					requests.setRequestTypeID(2);
					requests.setRequestStatusID(1);
					requests.createRequest(requests);
					
					System.out.println(requests.getRequestTypeID());
					
				
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
           

			
/*			Request request = new Request();
			request.setUserID(5);
			request.setRequestTypeID(2);
			request.setRequestStatusID(1);
			request.setEventCode("test129");
			
			request.joinEvent(request);*/
		
	/*		Request requests = new Request();
			requests.setUserID(5);
			requests.setRequestTypeID(3);
			requests.setRequestStatusID(1);
			try {
				requests.createRequest(requests);
				
				System.out.println(requests.getUserID());
				System.out.println(requests.getRequestTypeID());
				System.out.println(requests.getRequestStatusID());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			*/
			User user1 = new User();
			user1.setUserID(5);
			user1.setUserType("user");
			NotificationOrg no = new NotificationOrg(user1);
			no.setVisible(true);

	}




	
}
