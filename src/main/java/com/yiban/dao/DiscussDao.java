package com.yiban.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.yiban.jdbc.JDBCConnection;
import com.yiban.model.DiscussList;

public class DiscussDao {
	public int insertDiscuss(int loveLinkId,int UserId,String content){
		Connection con = null;
		CallableStatement cs =null;
		int count=0;
		try {
			con = JDBCConnection.getInstance().getConnection();
			cs =  con.prepareCall("CALL insert_discuss(?,?,?)");
			cs.setInt(1, loveLinkId);
			cs.setInt(2, UserId);
			cs.setString(3, content);
			count= cs.executeUpdate();
			return count;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
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
	
	public int deleteDiscuss(int id){
		Connection con = null;
		CallableStatement cs =null;
		int count=0;
		try {
			con = JDBCConnection.getInstance().getConnection();
			cs =  con.prepareCall("CALL delete_discuss(?)");
			cs.setInt(1, id);
			count= cs.executeUpdate();
			return count;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
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
	
	public ArrayList<DiscussList> findDiscuss(int loveId ,int size) {
		Connection con = null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		String sql="SELECT *FROM love_discuss,yb_user WHERE yb_user.yb_userid=love_discuss.user_id AND love_link_id =?  ORDER BY id DESC LIMIT ?";
		ArrayList<DiscussList> diss = new ArrayList<DiscussList>();
		try {
			con = JDBCConnection.getInstance().getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, loveId);
			ps.setInt(2, size);
			rs =ps.executeQuery();
			while(rs.next()) {
				DiscussList dis = new DiscussList();
				dis.setId(rs.getInt("id"));
				dis.setLoveId(rs.getInt("love_link_id"));
				dis.setUserId(rs.getInt("user_id"));
				dis.setContent(rs.getString("content"));
				dis.setUserName(rs.getString("yb_username"));
				dis.setUserHead(rs.getString("yb_userhead"));
				diss.add(dis);
			}
			return diss;
		} catch (SQLException e) {
			return null;
		}finally {
			JDBCConnection.closeResultSet(rs);
			JDBCConnection.closePreparedStatement(ps);
			JDBCConnection.closeConnection(con);
		}
	}
	/**
	 * 根据loveid查询评论数目
	 * @param id
	 * @return
	 */
	public int getLoveLinkCount(int id) {
		Connection con = null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		String sql="select count(*) as count from love_discuss WHERE love_link_id = ?";
		try {
			con = JDBCConnection.getInstance().getConnection();
			ps = con.prepareStatement(sql);
			rs =ps.executeQuery();
			rs.next();
			int count = rs.getInt("count");
			return count;
		} catch (SQLException e) {
			return 0;
		}finally {
			JDBCConnection.closeResultSet(rs);
			JDBCConnection.closePreparedStatement(ps);
			JDBCConnection.closeConnection(con);
		}
	}	
	public static void main(String[] args) {
		System.out.println(new DiscussDao().findDiscuss(130,1));
	}
}
