package com.cit.it.ccs323a.sia.me.core;

import java.sql.SQLException;
import java.util.Date;

import com.cit.it.ccs323a.sia.me.db.DBUser;

public class User {
	
	private int userID;
	private String userFullName;
	private int userAge;
	private String userBirthdate;
	private String userAddress;
	private String userName;
	private String userEmail;
	private String userPassword;
	private String userRequest;
	private String userType;
	private DBUser dbUser = new DBUser();
	
	public int getUserID() {
		return userID;
	}
	public String getUserFullName() {
		return userFullName;
	}
	public void setUserFullName(String userFullName) {
		this.userFullName = userFullName;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public int getUserAge() {
		return userAge;
	}
	public void setUserAge(int userAge) {
		this.userAge = userAge;
	}
	public String getUserAddress() {
		return userAddress;
	}
	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}
	public String getUserBirthdate() {
		return userBirthdate;
	}
	public void setUserBirthdate(String userBirthdate) {
		this.userBirthdate = userBirthdate;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getUserRequest() {
		return userRequest;
	}
	public void setUserRequest(String userRequest) {
		this.userRequest = userRequest;
	}
	
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	
	public boolean registerNewUser(User user) throws SQLException {
		return dbUser.createNewUser(user);
	}
	
	public boolean searchUser(String username) {
		return dbUser.searchUser(username);
	}
	
	public boolean verifyLoginCredentials(String userName, String password) {
		return dbUser.verifyLoginCredentials(userName, password);	
	}
	
	public String getUserType(String userName) {
		String userType = "";
		if(dbUser.searchUser(userName)) {
			userType = dbUser.getUserType(userName);
		}
		return userType;
	}

	public User getUserData(String userName) {
		return dbUser.getUserData(userName);
	}
	
	public User getUserData(int userID) {
		return dbUser.getUserData(userID);
	}
	
	public boolean updateUserProfile(User user) {
		return dbUser.updateUserProfile(user);
	}

}
