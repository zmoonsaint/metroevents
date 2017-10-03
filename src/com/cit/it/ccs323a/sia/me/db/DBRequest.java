package com.cit.it.ccs323a.sia.me.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

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
		System.out.println("db createRequest(Request request)" + request.getUserID());
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
			stmt.setInt(3,  1);
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

		sqlStatement = "select * from me_request where userID = ? AND requestTypeID IN (1,2,3)";
		
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
	
	public int getLastRequestID() {
		
		
		connection = DBAccess.getConnection();
		sqlStatement = "SELECT * from me_event ORDER by requestID DESC LIMIT 1";

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
	
	private Timestamp getCurrentTimestamp() {
		java.util.Date today = new java.util.Date();
		return new java.sql.Timestamp(today.getTime());
	}
	
}
