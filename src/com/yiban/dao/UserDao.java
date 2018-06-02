package com.yiban.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yiban.jdbc.JDBCConnection;
import com.yiban.model.Pager;
import com.yiban.model.YBUser;

public class UserDao {
	//添加用户
	int pageSize=10;
	public int add(YBUser user) {
		Connection con = null;
		PreparedStatement ps=null;
		String sql="INSERT INTO yb_user(yb_userid,yb_username,yb_usernick,yb_sex,yb_money,yb_userhead,yb_schoolid,yb_schoolname,yb_exp) VALUES (?,?,?,?,?,?,?,?,?)";
		try {
			con = JDBCConnection.getInstance().getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, user.getYbUserid());
			ps.setString(2, user.getYbUsername());
			ps.setString(3, user.getYbUsernick());
			ps.setString(4, user.getYbSex());
			ps.setInt(5, user.getYbMoney());
			ps.setString(6, user.getYbUserhead());
			ps.setInt(7, user.getYbSchoolid());
			ps.setString(8, user.getYbSchoolname());
			ps.setInt(9, user.getYbExp());
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
	 * 根据用户id查找用户
	 */
	public YBUser findId(int id) {
		Connection con = null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		String sql="select *from yb_user where yb_userid = ?";
		try {
			con = JDBCConnection.getInstance().getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			rs =ps.executeQuery();
			YBUser user = new YBUser();
			rs.next();
			user.setYbExp(rs.getInt("yb_exp"));
			user.setYbMoney(rs.getInt("yb_money"));
			user.setYbSchoolid(rs.getInt("yb_schoolid"));
			user.setYbSchoolname(rs.getString("yb_schoolname"));
			user.setYbSex(rs.getString("yb_sex"));
			user.setYbUserhead(rs.getString("yb_userhead"));
			user.setYbUserid(rs.getInt("yb_userid"));
			user.setYbUsername(rs.getString("yb_username"));
			user.setYbUsernick(rs.getString("yb_usernick"));
			user.setYbTime(rs.getString("yb_time"));
			return user;
		} catch (SQLException e) {
			return null;
		}finally {
			JDBCConnection.closeResultSet(rs);
			JDBCConnection.closePreparedStatement(ps);
			JDBCConnection.closeConnection(con);
		}
		
	}
	
	public Pager<YBUser> getUserList(int page){
		Connection con = null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		Pager<YBUser> pager = new Pager<YBUser>();
		List<YBUser> list = new ArrayList<YBUser>();
		String sql1="SELECT * from yb_user ORDER BY yb_userid DESC LIMIT ?,?;";
		String sql2 = "SELECT  COUNT(*) as count from yb_user";
		try {
			con = JDBCConnection.getInstance().getConnection();
			ps = con.prepareStatement(sql1);
			ps.setInt(1, (page-1)*pageSize);
			ps.setInt(2, pageSize);
			rs =ps.executeQuery();
			
			while(rs.next()) {
				YBUser user = new YBUser();
				user.setYbExp(rs.getInt("yb_exp"));
				user.setYbMoney(rs.getInt("yb_money"));
				user.setYbSchoolid(rs.getInt("yb_schoolid"));
				user.setYbSchoolname(rs.getString("yb_schoolname"));
				user.setYbSex(rs.getString("yb_sex"));
				user.setYbUserhead(rs.getString("yb_userhead"));
				user.setYbUserid(rs.getInt("yb_userid"));
				user.setYbUsername(rs.getString("yb_username"));
				user.setYbUsernick(rs.getString("yb_usernick"));
				user.setYbTime(rs.getString("yb_time"));
				list.add(user);
			}
			ps = con.prepareStatement(sql2);
			rs= ps.executeQuery();
			rs.next();
			int count = rs.getInt("count");
			int p;
			if(count%pageSize==0) {
				p=count/pageSize;
			}else {
				p=count/pageSize+1;
			}
			
			
			
			pager.setList(list);
			pager.setPageCount(p);
			return pager;
			
			
		} catch (SQLException e) {
		
		}finally {
			JDBCConnection.closeResultSet(rs);
			JDBCConnection.closePreparedStatement(ps);
			JDBCConnection.closeConnection(con);
		}
		return null;
	}
	public static void main(String[] args) {
		System.out.println(new UserDao().getUserList(1).getList()+"--"+new UserDao().getUserList(1).getPageCount());
	}
}


