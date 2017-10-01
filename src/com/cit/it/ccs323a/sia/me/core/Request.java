package com.cit.it.ccs323a.sia.me.core;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import com.cit.it.ccs323a.sia.me.db.DBRequest;

public class Request {
	private int requestID;
	private int requestTypeID;
	private int userID;
	private int requestStatusID;
	private Date requestDate;
	private Date requestStatusDate;
	private DBRequest dbRequest = new DBRequest();
	
	public int getRequestID() {
		return requestID;
	}
	public void setRequestID(int requestID) {
		this.requestID = requestID;
	}
	public int getRequestTypeID() {
		return requestTypeID;
	}
	public void setRequestTypeID(int requestType) {
		this.requestTypeID = requestType;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public int getRequestStatusID() {
		return requestStatusID;
	}
	public void setRequestStatusID(int requestStatusID) {
		this.requestStatusID = requestStatusID;
	}
	public Date getRequestDate() {
		return requestDate;
	}
	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}
	public Date getRequestStatusDate() {
		return requestStatusDate;
	}
	public void setRequestStatusDate(Date requestStatusDate) {
		this.requestStatusDate = requestStatusDate;
	}
	public boolean createRequest(Request request) throws SQLException {
		return dbRequest.createRequest(request);
	}
	
	public boolean verifyRequestIsCreated(Request request) {
		return dbRequest.verifyRequestIsCreated(request);
	}
	
	public ArrayList<Request> getAllUserRequests(User user) {
		return dbRequest.getAllUserRequests(user);
	}

}
