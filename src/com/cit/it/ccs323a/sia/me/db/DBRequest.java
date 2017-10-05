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

public class DBRequest {
	
	private DBRequest dbRequest;
	Connection connection;
	String sqlStatement;
	PreparedStatement stmt;
	ResultSet resultset;
	
	public DBRequest() {
		
	}
	
	public boolean createRequest(Request request) {
		System.out.println("db createRequest(Request request )" + request.getUserID());
		connection = DBAccess.getConnection();
		sqlStatement = "INSERT INTO me_request ("
			    + " requestID,"
			    + " userID,"
			    + " requestTypeID,"
			    //+ " requestDate,"
			    + " requestStatusID ) VALUES ("
			    + "null, ?, ?, ?)";
		try {
			stmt = connection.prepareStatement(sqlStatement);
			stmt.setInt(1, request.getUserID());
			stmt.setInt(2, request.getRequestTypeID());
			//stmt.setTimestamp(3, getCurrentTimestamp());
			stmt.setInt(3,  request.getRequestStatusID());
			System.out.println(stmt.toString());
			stmt.executeUpdate();	
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean joinEvent(Request request) {
		System.out.println("db createRequest(Request request)" + request.getUserID());
		connection = DBAccess.getConnection();
		sqlStatement = "INSERT INTO me_joinevent ("
			    + " requestID,"
			    + " userID,"
			    + " eventCode ) VALUES ("
			    + "?, ?, ?)";
		

		try {
			stmt = connection.prepareStatement(sqlStatement);
			stmt.setInt(1, request.getRequestID());
			stmt.setInt(2, request.getUserID());
			stmt.setString(3, request.getEventCode());
			System.out.println(stmt.toString());
			stmt.executeUpdate();		
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public boolean verifyRequestIsCreated(Request request) {
		connection = DBAccess.getConnection();
		sqlStatement = "select * from me_request where userID = ? AND requestTypeID = ?";
		
		try {
			System.out.println(connection.toString());
			stmt = connection.prepareStatement(sqlStatement);
			stmt.setInt(1, request.getUserID());
			stmt.setInt(2, request.getRequestTypeID());
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
	
	
	public ArrayList<Request> getAllUserRequests(User user) {
		connection = DBAccess.getConnection();
		ArrayList<Request> userRequests  = null;

		sqlStatement = "select * from me_request where userID = ?";
		
		try {
			System.out.println(connection.toString());
			stmt = connection.prepareStatement(sqlStatement);
			stmt.setInt(1, user.getUserID());
			resultset = stmt.executeQuery();
			System.out.println(stmt.toString());
			userRequests = new ArrayList<>();
			while (resultset.next()) {
				Request uRequest = new Request();
				uRequest.setUserID(user.getUserID());
				uRequest.setRequestID(resultset.getInt("requestID"));
				uRequest.setRequestTypeID(resultset.getInt("requestTypeID"));
				uRequest.setRequestDate(resultset.getTimestamp("requestDate"));
				uRequest.setRequestStatusID(resultset.getInt("requestStatusID"));
				uRequest.setRequestStatusDate(resultset.getTimestamp("requestStatusDate"));
				userRequests.add(uRequest);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userRequests;			
	}
	
	public ArrayList<Request> getAllUserRequests() {
		connection = DBAccess.getConnection();
		ArrayList<Request> userRequests  = null;

		sqlStatement = "select * from me_request";
		
		try {
			System.out.println(connection.toString());
			stmt = connection.prepareStatement(sqlStatement);
			resultset = stmt.executeQuery();
			System.out.println(stmt.toString());
			userRequests = new ArrayList<>();
			while (resultset.next()) {
				Request uRequest = new Request();
				uRequest.setUserID(resultset.getInt("userID"));
				uRequest.setRequestID(resultset.getInt("requestID"));
				uRequest.setRequestTypeID(resultset.getInt("requestTypeID"));
				uRequest.setRequestDate(resultset.getTimestamp("requestDate"));
				uRequest.setRequestStatusID(resultset.getInt("requestStatusID"));
				uRequest.setRequestStatusDate(resultset.getTimestamp("requestStatusDate"));
				userRequests.add(uRequest);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userRequests;			
	}	
	
	public ArrayList<Request> getAllUserRequestsByStatus(int statusID) {
		connection = DBAccess.getConnection();
		ArrayList<Request> userRequests  = null;

		sqlStatement = "select * from me_request where requestStatusID = ?";
		
		try {
			System.out.println(connection.toString());
			stmt = connection.prepareStatement(sqlStatement);
			stmt.setInt(1, statusID);
			resultset = stmt.executeQuery();
			System.out.println(stmt.toString());
			userRequests = new ArrayList<>();
			while (resultset.next()) {
				Request uRequest = new Request();
				uRequest.setUserID(resultset.getInt("userID"));
				uRequest.setRequestID(resultset.getInt("requestID"));
				uRequest.setRequestTypeID(resultset.getInt("requestTypeID"));
				uRequest.setRequestDate(resultset.getTimestamp("requestDate"));
				uRequest.setRequestStatusID(resultset.getInt("requestStatusID"));
				uRequest.setRequestStatusDate(resultset.getTimestamp("requestStatusDate"));
				userRequests.add(uRequest);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userRequests;			
	}
	
	public ArrayList<Request> getAllUserRequestsForOrganizer(int userID) {
		connection = DBAccess.getConnection();
		ArrayList<Request> userRequests = new ArrayList<>();
		Request uRequest = new Request();

		sqlStatement = "SELECT DISTINCT me_request.requestID, me_request.requestTypeID, "
				+ "me_request.requestStatusID, me_request.requestDate, "
				+ "me_request.requestStatusDate FROM me_event "
				+ " JOIN me_request "
				+ "ON me_request.requestID = me_event.requestID "
				+ "where eventOrganizer = ?";
		
		try {
			System.out.println(connection.toString());
			stmt = connection.prepareStatement(sqlStatement);
			stmt.setInt(1, userID);
			resultset = stmt.executeQuery();
			System.out.println(stmt.toString());
			while (resultset.next()) {
				System.out.print(resultset.getInt("requestID"));

				uRequest.setUserID(userID);
				uRequest.setRequestID(resultset.getInt("requestID"));
				uRequest.setRequestTypeID(resultset.getInt("requestTypeID"));
				uRequest.setRequestDate(resultset.getTimestamp("requestDate"));
				uRequest.setRequestStatusID(resultset.getInt("requestStatusID"));
				uRequest.setRequestStatusDate(resultset.getTimestamp("requestStatusDate"));
				userRequests.add(uRequest);
			}
			
			sqlStatement = "SELECT * FROM me_joinevent JOIN me_event "
					+   "ON me_joinevent.eventCode = me_event.eventCode AND me_event.eventOrganizer = ? "
					+ " JOIN me_request " + 
					" ON me_request.requestID = me_joinevent.requestID";
			
			stmt = connection.prepareStatement(sqlStatement);
			stmt.setInt(1, userID);
			resultset = stmt.executeQuery();
			System.out.println(stmt.toString());

			while (resultset.next()) {
				System.out.print(resultset.getInt("requestID"));
				uRequest.setUserID(userID);
				uRequest.setRequestID(resultset.getInt("requestID"));
				uRequest.setRequestTypeID(resultset.getInt("requestTypeID"));
				uRequest.setRequestDate(resultset.getTimestamp("requestDate"));
				uRequest.setRequestStatusID(resultset.getInt("requestStatusID"));
				uRequest.setRequestStatusDate(resultset.getTimestamp("requestStatusDate"));
				userRequests.add(uRequest);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userRequests;			
	}
	
	
	public int getLastRequestID() {
		
		
		connection = DBAccess.getConnection();
		sqlStatement = "SELECT requestID from me_request ORDER by requestID DESC LIMIT 1";

		try {
			System.out.println(connection.toString());
			stmt = connection.prepareStatement(sqlStatement);
			resultset = stmt.executeQuery();
			System.out.println(stmt.toString());
			if(resultset.next()) {
				return resultset.getInt("requestID");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	
	public boolean processRequest(int requestID, int requestStatus) {
		System.out.println("db processRequest(Event requestID)" + requestID + ", requestStatus " + requestStatus);
		
		connection = DBAccess.getConnection();
			
		sqlStatement = "UPDATE me_request "
				+ "SET requestStatusID = ? "
				+ "WHERE requestID = ?";

		try {

				
			stmt = connection.prepareStatement(sqlStatement);
			stmt.setInt(1, requestStatus);
			stmt.setInt(2, requestID);
			System.out.println(stmt.toString());
			stmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	
	
	private Timestamp getCurrentTimestamp() {
		java.util.Date today = new java.util.Date();
		return new java.sql.Timestamp(today.getTime());
	}
	
}
