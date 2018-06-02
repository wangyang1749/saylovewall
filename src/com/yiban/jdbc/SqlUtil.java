package com.yiban.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class SqlUtil {
	public static int updataSql(String sql[],Object args[]){
		Connection con = null;
		PreparedStatement ps=null;
		int result=0;
		try {
			con = JDBCConnection.getInstance().getConnection();
			System.out.println("新建一个con的连接");
			for(int i=0;i<sql.length;i++){
				ps = con.prepareStatement(sql[i]);
				for(int j=0;j<args.length;j++){
					if(args[j] instanceof String){
						ps.setString(j+1, (String)args[j]);
						System.out.println(j+1+"-String-"+(String)args[j]);
					}else if(args[j] instanceof Integer){
						ps.setInt(j+1, (Integer)args[j]);
						System.out.println(j+1+"-int-"+(Integer)args[j]);
					}
				}
				result += ps.executeUpdate();
			}
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				if(ps!=null)ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if(con!=null)con.close();
				System.out.println("con关闭");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return  0;
	}
	public static ResultSet QuerySqlByCon(Connection con,String sql,int arg) throws SQLException{
		PreparedStatement ps =null;
		ps = con.prepareStatement(sql);
		ps.setInt(1, arg);
		ResultSet rs = ps.executeQuery();
		return rs;
	}
	
	public static ArrayList<ResultSet> QuerySql(String sql[],Object args[]){
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		ArrayList<ResultSet> sqlList = new ArrayList<ResultSet>();
		
		try {
			con = JDBCConnection.getInstance().getConnection();
			System.out.println("新建一个con的连接");
			for(int i=0;i<sql.length;i++){
				ps = con.prepareStatement(sql[i]);
				for(int j=0;j<args.length;j++){
					if(args[j] instanceof String){
						ps.setString(j+1, (String)args[j]);
						System.out.println(j+1+"-String-"+(String)args[j]);
					}else if(args[j] instanceof Integer){
						ps.setInt(j+1, (Integer)args[j]);
						System.out.println(j+1+"-int-"+(Integer)args[j]);
					}
				}
				rs = ps.executeQuery();
				while(rs.next()){
					rs.getString("");
					rs.getInt("");
					
				}
				sqlList.add(rs);
			}
			return sqlList;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				if(rs!=null)rs.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			try {
				if(ps!=null)ps.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			try {
				if(con!=null)con.close();
				System.out.println("con关闭");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return null;
	}
	
}
