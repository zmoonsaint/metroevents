package com.cit.it.ccs323a.sia.me.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Statement;

public class DBAccess {
	
    private static DBAccess instance = new DBAccess();
    public static final String URL = "jdbc:mysql://localhost:3306/metroevents_final";
    public static final String USER = "me_events";
    public static final String PASSWORD = "0rdbV0S82NBBYeKf";
    public static final String DRIVER_CLASS = "com.mysql.jdbc.Driver"; 
    
    //private constructor
    private DBAccess() {
        try {
            //Step 2: Load MySQL Java driver
            Class.forName(DRIVER_CLASS);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }    
        
    private Connection createConnection() {
    	 
        Connection connection = null;
        try {
            //Step 3: Establish Java MySQL connection
//        	System.out.println(URL);
//        	System.out.println(USER);
//        	System.out.println(PASSWORD);
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.out.println("ERROR: Unable to Connect to Database.");
        }
        return connection;
    }   
     
    public static Connection getConnection() {
        return instance.createConnection();
    }
    
}
