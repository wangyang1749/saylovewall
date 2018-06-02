package com.yiban.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class JDBCConnection {
	public static JDBCConnection instance=null;
	private String url="jdbc:mysql://localhost:3306/saylovewall?useSSL=false&characterEncoding=utf-8";
	private String user="root";
	private String password="";
	private JDBCConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	public static JDBCConnection getInstance() {
		if(instance==null) {
			instance=new JDBCConnection();
		}
		return instance;
	}
	public Connection getConnection() {
		try {
			Connection con = DriverManager.getConnection(url, user, password);
			return con;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	public static void closeConnection(Connection con) {
		try {
			if(con!=null)con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void closePreparedStatement(PreparedStatement ps) {
		try {
			if(ps!=null) ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void closeResultSet(ResultSet rs) {
		try {
			if(rs!=null)rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
	public static void main(String[] args) {
		if(JDBCConnection.getInstance().getConnection()!=null) {
			System.out.println("连接成功");
		}
		
	}
}
