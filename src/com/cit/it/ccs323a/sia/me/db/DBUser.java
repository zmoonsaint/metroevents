package com.cit.it.ccs323a.sia.me.db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.cit.it.ccs323a.sia.me.core.Events;
import com.cit.it.ccs323a.sia.me.core.Request;
import com.cit.it.ccs323a.sia.me.core.User;
import com.mysql.jdbc.Statement;

public class DBUser {
		
	private User dbUser;
	Connection connection;
	String sqlStatement;
	PreparedStatement stmt;
	ResultSet resultset;
	
	public DBUser() {
		
	}
	
	public DBUser(User user) {
		this.dbUser = user;
	}
	
	public boolean createNewUser(User user) throws SQLException {
		
		connection = DBAccess.getConnection();
		sqlStatement = "INSERT INTO me_user ("
			    + " userID,"
			    + " userFullName,"
			    + " userAge,"
			    + " userBirthdate,"
			    + " userAddress,"
			    + " userName,"
			    + " userEmail,"
			    + " userPassword,"
			    + " userRequest,"
			    + " userTypeID ) VALUES ("
			    + "?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try {

			stmt = connection.prepareStatement(sqlStatement);
			stmt.setInt(1, user.getUserID());
			stmt.setString(2, user.getUserFullName());
			stmt.setInt(3, user.getUserAge());
			stmt.setString(4, user.getUserBirthdate());
			stmt.setString(5, user.getUserAddress());
			stmt.setString(6, user.getUserName());
			stmt.setString(7, user.getUserEmail());
			stmt.setString(8,  user.getUserPassword());
			stmt.setString(9,  "");
			stmt.setInt(10,  Integer.valueOf(user.getUserType()));

			System.out.println(stmt.toString());
			stmt.executeUpdate();
			
			if(searchUser(user.getUserName())) {
				return true;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return false;
	}
	
	public boolean searchUser(String userName) {
		connection = DBAccess.getConnection();
		sqlStatement = "select * from me_user where userName = ?";
		
		try {
			System.out.println(connection.toString());
			stmt = connection.prepareStatement(sqlStatement);
			stmt.setString(1, userName);
			resultset = stmt.executeQuery();
			System.out.println(stmt.toString());
			while(resultset.next()) {
				if(resultset.getString("userName").toLowerCase().equals(userName.toLowerCase())) {
					return true;
				}
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;	
	}
	
	public boolean verifyLoginCredentials(String userName, String password) {
	
		connection = DBAccess.getConnection();
		sqlStatement = "SELECT userName, userPassword "
				+ "FROM me_user JOIN me_request "
				+ "ON me_user.userID = me_request.userID "
				+ "WHERE requestStatusID = 2 "
				+ "AND me_request.requestTypeID IN ("+ 1 + ","
				+ 4 + "," + 5 + ")"
				+ "AND userName = ?";
		try {
			stmt = connection.prepareStatement(sqlStatement);
			stmt.setString(1, userName);
			resultset = stmt.executeQuery();
			System.out.println(stmt);
			while(resultset.next()) {
				if(resultset.getString("userName").toLowerCase().equals(userName.toLowerCase()) && resultset.getString("userPassword").equals(password)) {
					return true;
				}
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;	
	}
	
	public int getUserAccountStatus(String userName) {
		
		System.out.println("getUserAccountStatus(String userName)" + userName);
		
		connection = DBAccess.getConnection();
		sqlStatement = "SELECT requestStatusID FROM me_user "
				+ "JOIN me_request ON me_user.userID = me_request.userID "
				+ "WHERE me_user.userName = ? "
				+ "AND me_request.requestTypeID IN (1,4,5)";		
		try {
			stmt = connection.prepareStatement(sqlStatement);
			stmt.setString(1, userName);
			resultset = stmt.executeQuery();
			System.out.println(stmt.toString());
			while(resultset.next()) {
					return resultset.getInt("requestStatusID");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;	
	}
	
	
	public String getUserType(String userName) {
		
		connection = DBAccess.getConnection();
		sqlStatement = "Select me_usertype.userType from me_user LEFT JOIN me_usertype ON me_usertype.userTypeID = me_user.userTypeID where me_user.userName = ?";
		
		try {
			stmt = connection.prepareStatement(sqlStatement);
			stmt.setString(1, userName);
			resultset = stmt.executeQuery();
			System.out.println(stmt.toString());
			while(resultset.next()) {
					return resultset.getString("userType");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;	
	}
	
	public User getUserData(String userName) {
		System.out.println("getUserData(String " + userName +")");
		
		User user = new User();
		connection = DBAccess.getConnection();
		sqlStatement = "Select * from me_user LEFT JOIN me_usertype ON me_usertype.userTypeID = me_user.userTypeID where me_user.userName = ?";
		
		try {
			stmt = connection.prepareStatement(sqlStatement);
			stmt.setString(1, userName);
			resultset = stmt.executeQuery();
			while(resultset.next()) {
				user.setUserID(resultset.getInt("userID"));
				user.setUserFullName(resultset.getString("userFullName"));
				user.setUserAge(resultset.getInt("userAge"));
				user.setUserBirthdate(resultset.getString("userBirthdate"));
				user.setUserAddress(resultset.getString("userAddress"));
				user.setUserEmail(resultset.getString("userEmail"));
				user.setUserName(resultset.getString("userName"));
				user.setUserPassword(resultset.getString("userPassword"));
				user.setUserType(resultset.getString("userType"));
				return user;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;	
	}
	
	public User getUserData(int userID) {
		
		User user = new User();
		connection = DBAccess.getConnection();
		sqlStatement = "Select * from me_user LEFT JOIN me_usertype ON me_usertype.userTypeID = me_user.userTypeID where me_user.userID = ?";
		
		try {
			stmt = connection.prepareStatement(sqlStatement);
			stmt.setInt(1, userID);
			resultset = stmt.executeQuery();
			while(resultset.next()) {
				user.setUserID(resultset.getInt("userID"));
				user.setUserFullName(resultset.getString("userFullName"));
				user.setUserAge(resultset.getInt("userAge"));
				user.setUserBirthdate(resultset.getString("userBirthdate"));
				user.setUserAddress(resultset.getString("userAddress"));
				user.setUserEmail(resultset.getString("userEmail"));
				user.setUserName(resultset.getString("userName"));
				user.setUserPassword(resultset.getString("userPassword"));
				user.setUserType(resultset.getString("userType"));
				return user;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;	
	}
	
	public boolean updateUserProfile(User user) {
		
		connection = DBAccess.getConnection();
		sqlStatement = "UPDATE me_user SET userName = ?, userFullName = ?, userAge = ?, userBirthdate = ?, userAddress = ?, userEmail = ?, userPassword = ?  WHERE userID = ?";
		
		
		try {
			stmt = connection.prepareStatement(sqlStatement);
			stmt.setString(1, user.getUserName());
			stmt.setString(2, user.getUserFullName());
			stmt.setInt(3, user.getUserAge());
			stmt.setString(4, user.getUserBirthdate());
			stmt.setString(5, user.getUserAddress());
			stmt.setString(6, user.getUserEmail());
			stmt.setString(7,  user.getUserPassword());
			stmt.setInt(8,  user.getUserID());
			stmt.executeUpdate();
			System.out.println(stmt.toString());

			return true;	

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	public int getLastUserID() {
		connection = DBAccess.getConnection();
		sqlStatement = "SELECT userID from me_user ORDER by userID DESC LIMIT 1";

		try {
			System.out.println(connection.toString());
			stmt = connection.prepareStatement(sqlStatement);
			resultset = stmt.executeQuery();
			System.out.println(stmt.toString());
			if(resultset.next()) {
				return resultset.getInt("userID");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	
	public boolean setUserAccountType(String username, int requestType) {
		System.out.println("db setUserAccountType(requestType) " + requestType);
		
		connection = DBAccess.getConnection();
		sqlStatement = "UPDATE me_user "
				+ "SET userTypeID = ? "
				+ "WHERE userName = ?";

		try {
			stmt = connection.prepareStatement(sqlStatement);
			stmt.setInt(1, requestType);
			stmt.setString(2, username);
			System.out.println(stmt.toString());
			stmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	
	public boolean userJoinEvent(int userID, String eventCode) throws SQLException {
		connection = DBAccess.getConnection();
		sqlStatement = "INSERT INTO me_joinevent ("
				+ "requestID, "
			    + " userID,"
			    + " eventCode ) VALUES (?, ? , ? )";
		try {

			
			Request request = new Request();
			request.setUserID(userID);
			request.setRequestStatusID(1);
			request.setEventCode(eventCode);
			request.setRequestTypeID(3);
			request.createRequest(request);
			stmt = connection.prepareStatement(sqlStatement);
			stmt.setInt(1, request.getLastRequestID());
			stmt.setInt(2, userID);
			stmt.setString(3, eventCode);
			System.out.println(stmt.toString());
			stmt.executeUpdate();


		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean alreadyJoinEvent(int userID, String eventCode) throws SQLException {
		connection = DBAccess.getConnection();
		sqlStatement = "SELECT * from me_joinevent where eventCode = ? and userID = ?" ;

		try {
			System.out.println(connection.toString());
			stmt = connection.prepareStatement(sqlStatement);
			stmt.setString(1, eventCode);
			stmt.setInt(2, userID);
			resultset = stmt.executeQuery();
			System.out.println(stmt.toString());
			if(resultset.next()) {
				return true;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	

}
