package com.movie.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DBConnectivity {
	
	
	/*
	 * .getConnection() is a factory method, which creates an object of the Connection implemented class.
	 * 
	 * So whenever we require connection object we will simply call this method.
	 */
	public static Connection getConnection() {
		
		
		Connection con=null;
		
			Context initContext = null;
			try {
				initContext = new InitialContext();
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    Context envContext = null;
			try {
				envContext = (Context)initContext.lookup("java:comp/env");
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    DataSource ds = null;
			try {
				ds = (DataSource)envContext.lookup("jdbc/Test");
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    try {
				con = ds.getConnection();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    
			
		
		
		return con;
	}

}
