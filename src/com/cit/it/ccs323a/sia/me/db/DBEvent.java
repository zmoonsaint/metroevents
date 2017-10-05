package com.cit.it.ccs323a.sia.me.db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import com.cit.it.ccs323a.sia.me.core.Events;
import com.cit.it.ccs323a.sia.me.core.Request;
import com.cit.it.ccs323a.sia.me.core.User;

public class DBEvent {

	private DBEvent dbEvent;
	Connection connection;
	String sqlStatement;
	PreparedStatement stmt;
	ResultSet resultset;

	public DBEvent() {

	}

	public boolean createEvent(Events event, User user) {
		System.out.println("db createEvent(Event event)" + event.getEventCode());
		
		connection = DBAccess.getConnection();
		sqlStatement = "INSERT INTO me_event ("
				+ "requestID,"
				+ " eventCode,"
				+ " eventName,"
				+ " eventDate,"
				+ " eventOrganizer,"
				+ " eventLocation,"
				+ " eventDescription) VALUES ("
				+ "?, ?, ?, ?, ?, ?, ?)";
		Request r = new Request();
		int requestID = r.getLastRequestID() + 1;
		System.out.println(r.getLastRequestID());
		try {
			stmt = connection.prepareStatement(sqlStatement);
			stmt.setInt(1, requestID);
			stmt.setString(2, event.getEventCode());
			stmt.setString(3, event.getEventName());
			stmt.setDate(4, (Date) event.getEventDate());
			stmt.setInt(5, event.getEventOrganizer());
			stmt.setString(6, event.getEventLocation());
			stmt.setString(7, event.getEventDescription());
			System.out.println(stmt.toString());
			stmt.executeUpdate();
			
			if(user.getUserType().equals("organizer")) {
				System.out.println("User Type is Organizer. Added for approval");
				r.setUserID(user.getUserID());
				r.setRequestTypeID(2);
				r.setRequestStatusID(1);
				r.createRequest(r);
			} else {
				r.setUserID(user.getUserID());
				r.setRequestTypeID(2);
				r.setRequestStatusID(2);
				r.createRequest(r);
			}
			
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean updateEvent(Events event, User user) {
		System.out.println("db updateEvent(Event event)" + event.getEventCode());
		
		connection = DBAccess.getConnection();
		
		sqlStatement = "UPDATE me_event "
				+ "SET eventCode = ?, "
				+ "eventName = ?, "
				+ "eventDate = ?, "
				+ "eventLocation = ?, "
				+ "eventDescription = ?  "
				+ "WHERE eventID = ?";

		try {
			stmt = connection.prepareStatement(sqlStatement);
			stmt.setString(1, event.getEventCode());
			stmt.setString(2, event.getEventName());
			stmt.setDate(3, (Date) event.getEventDate());
			stmt.setString(4, event.getEventLocation());
			stmt.setString(5, event.getEventDescription());
			stmt.setInt(6, event.getEventID());
			System.out.println(stmt.toString());
			stmt.executeUpdate();

			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public boolean verifyEventIsCreated(Events event) {
		connection = DBAccess.getConnection();
		sqlStatement = "select * from me_event where eventCode = ? AND eventOrganizer = ?";

		try {
			System.out.println(connection.toString());
			stmt = connection.prepareStatement(sqlStatement);
			stmt.setString(1, event.getEventCode());
			stmt.setInt(2, event.getEventOrganizer());
			resultset = stmt.executeQuery();
			System.out.println(stmt.toString());
			if(!resultset.next()) {
				return false;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;			
	}

	public int getEventID(String eventCode) {
		
		connection = DBAccess.getConnection();
		sqlStatement = "Select * from me_event where eventCode = ?";
		
		try {
			stmt = connection.prepareStatement(sqlStatement);
			stmt.setString(1, eventCode);
			resultset = stmt.executeQuery();
			System.out.println(stmt.toString());
			while(resultset.next()) {
					return resultset.getInt("eventID");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;	
	}
	
	public void deleteEvent(String eventCode) {
		
		connection = DBAccess.getConnection();
		sqlStatement = "DELETE a.*, b.* FROM me_event a"
				+ " JOIN me_request b"
				+ " ON a.requestID = b.requestID"
				+ " AND a.eventCode = ?"
				+ " AND b.requestTypeID = 2";
		
		try {
			stmt = connection.prepareStatement(sqlStatement);
			stmt.setString(1, eventCode);
			stmt.executeUpdate();
			System.out.println(stmt.toString());

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ArrayList<Events> getAllUserEvents(User user) {
		connection = DBAccess.getConnection();
		ArrayList<Events> userEvents  = null;

		try {
			System.out.println(connection.toString());
			if(user.getUserType().equals("administrator")) {
				sqlStatement = "select * from me_event";
				stmt = connection.prepareStatement(sqlStatement);
			} else {
				sqlStatement = "select * from me_event where eventOrganizer = ?";
				stmt = connection.prepareStatement(sqlStatement);
				stmt.setInt(1, user.getUserID());
			}
			resultset = stmt.executeQuery();
			System.out.println(stmt.toString());
			userEvents = new ArrayList<>();
			while (resultset.next()) {
				Events uEvent = new Events();
				uEvent.setEventID(resultset.getInt("eventID"));
				uEvent.setEventCode(resultset.getString("eventCode"));
				uEvent.setEventName(resultset.getString("eventName"));
				uEvent.setEventDate(resultset.getDate("eventDate"));
				uEvent.setEventLocation(resultset.getString("eventLocation"));
				uEvent.setEventOrganizer(resultset.getInt("eventOrganizer"));
				uEvent.setEventDescription(resultset.getString("eventDescription"));
				uEvent.setEventDateAdd(resultset.getTimestamp("eventDateAdded"));
				
				userEvents.add(uEvent);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userEvents;			
	}
	
	public ArrayList<Events> getAllEventsByStatus(int eventStatus) {
		connection = DBAccess.getConnection();
		ArrayList<Events> userEvents  = null;

		sqlStatement = "Select eventID, eventCode, eventName, eventDate, eventLocation, eventDescription, eventOrganizer, eventDateAdded "
				+ "from me_request "
				+ "JOIN me_event ON "
				+ "me_request.requestID = me_event.requestID "
				+ "where requestStatusID = ?";

		try {
			System.out.println(connection.toString());
			stmt = connection.prepareStatement(sqlStatement);
			stmt.setInt(1, eventStatus);
			resultset = stmt.executeQuery();
			System.out.println(stmt.toString());
			userEvents = new ArrayList<>();
			while (resultset.next()) {
				Events uEvent = new Events();
				uEvent.setEventID(resultset.getInt("eventID"));
				uEvent.setEventCode(resultset.getString("eventCode"));
				uEvent.setEventName(resultset.getString("eventName"));
				uEvent.setEventDate(resultset.getDate("eventDate"));
				uEvent.setEventLocation(resultset.getString("eventLocation"));
				uEvent.setEventDescription(resultset.getString("eventDescription"));
				uEvent.setEventOrganizer(resultset.getInt("eventOrganizer"));
				uEvent.setEventDateAdd(resultset.getTimestamp("eventDateAdded"));
				userEvents.add(uEvent);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userEvents;			
	}
	
	public ArrayList<Events> getAllEventsByOrganizer(int eventOrganizer) {
		connection = DBAccess.getConnection();
		ArrayList<Events> userEvents  = null;

		sqlStatement = "Select eventID, eventCode, eventName, eventDate, eventLocation, eventDescription, eventOrganizer, eventDateAdded "
				+ "from me_request "
				+ "JOIN me_event ON "
				+ "me_request.requestID = me_event.requestID "
				+ "where eventOrganizer = ?";

		try {
			System.out.println(connection.toString());
			stmt = connection.prepareStatement(sqlStatement);
			stmt.setInt(1, eventOrganizer);
			resultset = stmt.executeQuery();
			System.out.println(stmt.toString());
			userEvents = new ArrayList<>();
			while (resultset.next()) {
				Events uEvent = new Events();
				uEvent.setEventID(resultset.getInt("eventID"));
				uEvent.setEventCode(resultset.getString("eventCode"));
				uEvent.setEventName(resultset.getString("eventName"));
				uEvent.setEventDate(resultset.getDate("eventDate"));
				uEvent.setEventLocation(resultset.getString("eventLocation"));
				uEvent.setEventDescription(resultset.getString("eventDescription"));
				uEvent.setEventOrganizer(resultset.getInt("eventOrganizer"));
				uEvent.setEventDateAdd(resultset.getTimestamp("eventDateAdded"));
				userEvents.add(uEvent);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userEvents;			
	}	
	
	public boolean verifyEventCodeExist(String eventCode) {
		System.out.println(".............................Event Code: " + eventCode);
		connection = DBAccess.getConnection();
		sqlStatement = "select * from me_event where eventCode = ?";
		
		try {
			stmt = connection.prepareStatement(sqlStatement);
			stmt.setString(1, eventCode);
			resultset = stmt.executeQuery();
			System.out.println(stmt.toString());
			while(resultset.next()) {
				if(resultset.getString("eventCode").toLowerCase().equals(eventCode.toLowerCase())) {
					return true;
				}
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;	
	}	
	
/*	private Timestamp getCurrentTimestamp() {
		java.util.Date today = new java.util.Date();
		return new java.sql.Timestamp(today.getTime());
	}*/

}
