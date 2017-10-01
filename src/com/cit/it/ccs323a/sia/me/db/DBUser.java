package com.cit.it.ccs323a.sia.me.db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
			    + "null, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try {

			stmt = connection.prepareStatement(sqlStatement);
			stmt.setString(1, user.getUserFullName());
			stmt.setInt(2, user.getUserAge());
			stmt.setString(3, user.getUserBirthdate());
			stmt.setString(4, user.getUserAddress());
			stmt.setString(5, user.getUserName());
			stmt.setString(6, user.getUserEmail());
			stmt.setString(7,  user.getUserPassword());
			stmt.setString(8,  "");
			stmt.setInt(9,  Integer.valueOf(user.getUserType()));

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
		sqlStatement = "SELECT * FROM me_user WHERE userName = ?";
		
		try {
			stmt = connection.prepareStatement(sqlStatement);
			stmt.setString(1, userName);
			resultset = stmt.executeQuery();
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
	
	

}
