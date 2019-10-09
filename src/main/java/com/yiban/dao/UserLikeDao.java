package com.yiban.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import com.yiban.jdbc.JDBCConnection;
import com.yiban.model.UserLike;

public class UserLikeDao {
	
	/**
	 * 调用存储过程，判断点赞表是否存储用户，并实现点赞，实现点赞
	 */
	public int support(int userId,int loveId){
		Connection con = null;
		CallableStatement cs =null;
		ResultSet rs=null;
		int count=0;
		try {
			con = JDBCConnection.getInstance().getConnection();
			cs =  con.prepareCall("CALL suport(?,?);");
			cs.setInt(1, userId);
			cs.setInt(2, loveId);
			cs.executeQuery();
			rs=  cs.getResultSet();
			rs.next();
			//System.out.println(rs.getString("love_like"));
			count=rs.getInt("love_like");
			return count;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				if(rs!=null)rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if(cs!=null)cs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if(con!=null)con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return 0;
	}
	
	
	public static void main(String[] args) {
		System.out.println(new UserLikeDao().support(12927286,112)) ;
	}
	
	
	
	
	public int add(UserLike ul) {
		Connection con = null;
		PreparedStatement ps=null;
		String sql="INSERT INTO user_like(love_id, user_id) VALUES (?,?)";
		try {
			con = JDBCConnection.getInstance().getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, ul.getLoveId());
			ps.setInt(2, ul.getUserId());
			
			int row =ps.executeUpdate();
			return row;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}finally {
			JDBCConnection.closePreparedStatement(ps);
			JDBCConnection.closeConnection(con);
		}
	}
	/**
	 * 根据点赞的id，查找用户是否进行过点赞
	 * @param loveId
	 * @param UserId
	 * @return
	 */
	public boolean findLikeUser(int loveId,int UserId) {
		Connection con = null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		String sql="select *from user_like where love_id=?";
		try {
			con = JDBCConnection.getInstance().getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, loveId);
			rs =ps.executeQuery();
			boolean exist =false;
			while(rs.next()) {
				if(UserId==rs.getInt("user_id")) {
					exist=true;
					break;
				}
			}
			
			return exist;
		} catch (SQLException e) {
			return false;
		}finally {
			JDBCConnection.closeResultSet(rs);
			JDBCConnection.closePreparedStatement(ps);
			JDBCConnection.closeConnection(con);
		}
	}
	public int delete(int loveId,int userId) {
		Connection con = null;
		PreparedStatement ps=null;
		String sql="DELETE FROM user_like WHERE love_id= ? AND user_id = ? LIMIT 1";
		try {
			con = JDBCConnection.getInstance().getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1,loveId);
			ps.setInt(2, userId );
			int row =ps.executeUpdate();
			return row;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}finally {
			JDBCConnection.closePreparedStatement(ps);
			JDBCConnection.closeConnection(con);
		}
	}
}
