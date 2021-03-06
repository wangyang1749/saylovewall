package com.yiban.dao;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yiban.jdbc.JDBCConnection;
import com.yiban.model.LoveLink;
import com.yiban.model.LoveList;
import com.yiban.model.Page;
import com.yiban.model.Pager;
import com.yiban.model.YBUser;

public class LoveLinkDao implements ILoveLinkDao{
	public static final int PAGESIZE=10;
	
	
	//利用反射读取数据库中的数据
	private void getObject(Object o,ResultSet rs,String start) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		Method[] m = o.getClass().getDeclaredMethods();
		for(int i=0;i<m.length;i++){
			String name = m[i].getName();
			if(name.startsWith("set")){
				String type = m[i].getParameterTypes()[0].getName();
				//System.out.println(type);
				//System.out.println(start+name.substring(3));
				try {
					if(type.equals("int")){
						m[i].invoke(o, rs.getInt(start+name.substring(3)));
					}else if(type.equals("java.lang.String")){
						m[i].invoke(o, rs.getString(start+name.substring(3)));
					}
				} catch (SQLException e) {
					continue;
					//e.printStackTrace();
				}
				
			}
		}
	}
	
	/**
	 * 从数据库中取得A向B的表白记录
	 *
	 */
	public Page<LoveLink> list(int currentRecord){
		Connection con = null;
		PreparedStatement ps=null;
		ResultSet rs = null;
		String sql=	"select love_link.`id`,love_link.`my_user_id` as myuserid,love_link.`to_user_id` as touserid,love_link.`say_love` as saylove,  " 
					+"love_link.`love_img` as loveimg,love_link.`love_state` as lovestate,love_link.`read_state` as readState,love_link.`love_like` as lovelike, "
					+"love_link.`love_time` as loveTime ,love_link.`love_read` as loveRead ,"
					+ "y1.`yb_userid` AS ybuserid,y1.`yb_username` AS ybusername ,"
					+ "y2.`yb_userid` as toybuserid,y2.`yb_username` as toybusername ,"
					+ "y3.`yb_userid` as disybuserid,y3.`yb_username` as disybusername "
					+" from love_link " 
					+"left join yb_user As y1 " 
					+"on love_link.`my_user_id`=y1.`yb_userid` " 
					+"left join yb_user as y2 " 
					+"on love_link.`to_user_id`=y2.`yb_userid` " 
					+"left join love_discuss " 
					+"on love_link.`id`=love_discuss.`love_link_id` " 
					+"left join yb_user As y3 " 
					+"on love_discuss.`user_id`=y3.`yb_userid`";
		String sql2 ="select count(*)as count from love_link";
		Page<LoveLink> pages = new Page<LoveLink>();
		try {
			con=JDBCConnection.getInstance().getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			List<LoveLink> list = new ArrayList<LoveLink>();
			while(rs.next()){
				LoveLink lovelink  = new LoveLink();
				this.getObject(lovelink, rs,"");
				YBUser myInfo = new YBUser();
				this.getObject(myInfo, rs,"");
				YBUser toInfo = new YBUser();
				this.getObject(toInfo, rs,"to");
				YBUser disInfo = new YBUser();
				this.getObject(disInfo, rs,"dis");
				lovelink.setSelf(myInfo);
				lovelink.setTo(toInfo);
				lovelink.setDis(disInfo);
				list.add(lovelink);
			}
			pages.setList(list);
			rs.close();
			ps.close();
			ps = con.prepareStatement(sql2);
			rs = ps.executeQuery();
			rs.next();
			int totalRecord = rs.getInt("count");
			pages.setTotalRecord(totalRecord);
			pages.setCurrentRecord(currentRecord);
			
			
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			JDBCConnection.closeResultSet(rs);
			JDBCConnection.closePreparedStatement(ps);
			JDBCConnection.closeConnection(con);
		}
		return pages;
	}
	
	public static void main(String[] args) {
		Page<LoveLink> pages = new LoveLinkDao().list(5);
		System.out.println(pages.getList());
		System.out.println(pages.getTotalRecord());
	}
	/*以上为新添加的方法*/
	
	/**
	 * 发起表白
	 *@return int
	 */
	public int add(LoveLink love) {
		Connection con = null;
		PreparedStatement ps=null;
		String sql="INSERT INTO love_link(my_user_id, to_user_id, say_love, love_img,love_state) VALUES (?,?,?,?,?)";
		try {
			con = JDBCConnection.getInstance().getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, love.getMyUserId());
			ps.setInt(2, love.getToUserId());
			ps.setString(3, love.getSayLove());
			ps.setString(4, love.getLoveImg());
			ps.setInt(5, love.getLoveState());
			int row =ps.executeUpdate();
			//TODO
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
	 * 浏览量的增加
	 */
	public int addRead(int loveId) {
		Connection con = null;
		CallableStatement cs=null;
		ResultSet rs =null;
		int read = 0;
		try {
			con = JDBCConnection.getInstance().getConnection();
			cs = con.prepareCall("CALL raise_read(?)");
			cs.setInt(1, loveId);
			rs = cs.executeQuery();
			rs.next();
			read = rs.getInt("love_read");
			return read;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
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
	}

	
	
	/**
	 *查找一条记录 
	 */
	public LoveList findLoveLinkByid(int id) {
		Connection con = null;
		PreparedStatement ps=null;
		ResultSet rs=null;

		String sql="SELECT love_link.love_dis,love_link.id,love_link.my_user_id,love_link.to_user_id,love_link.say_love,love_link.love_img,love_link.love_state,love_link.read_state,love_link.love_read,love_link.love_like,love_link.love_time,y1.yb_username,y1.yb_schoolname,y1.yb_userhead,y2.yb_username AS to_username,y2.yb_schoolname AS to_schoolname,y2.yb_userhead AS to_userhead FROM love_link LEFT OUTER JOIN yb_user AS y1 ON love_link.my_user_id=y1.yb_userid LEFT OUTER JOIN  yb_user AS y2 ON love_link.to_user_id=y2.yb_userid where id=?";
		try {
			con = JDBCConnection.getInstance().getConnection();
			//System.out.println("创建一个连接");
			ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			rs =ps.executeQuery();
			rs.next();
			LoveList love = new LoveList();
			if(rs.getInt("love_state")==2||rs.getInt("love_state")==-2) {
				//当love的状态为2或者-2时，我是匿名的
				love.setToName(rs.getString("to_username"));
				love.setTohead(rs.getString("to_userhead"));
				love.setToSchool(rs.getString("to_schoolname"));
				love.setMyName(URLDecoder.decode("匿名", "UTF-8"));
				love.setMyhead("img/01.png");
				love.setMySchool(URLDecoder.decode("匿名", "UTF-8"));
			}else {
				love.setToName(rs.getString("to_username"));
				love.setTohead(rs.getString("to_userhead"));
				love.setToSchool(rs.getString("to_schoolname"));
				love.setMyName(rs.getString("yb_username"));
				love.setMyhead(rs.getString("yb_userhead"));
				love.setMySchool(rs.getString("yb_schoolname"));
			}
			love.setImg(rs.getString("love_img"));
			love.setSayLove(rs.getString("say_love"));
			love.setLike(rs.getInt("love_like"));
			love.setRead(rs.getInt("love_read"));
			love.setTime(rs.getString("love_time"));
			love.setId(rs.getInt("id"));
			love.setUserid(rs.getInt("my_user_id"));
			love.setLoveState(rs.getInt("love_state"));
			love.setLoveDiscuss(rs.getInt("love_dis"));
			
			return love;
		} catch (SQLException e) {
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null){
					rs.close();
					//System.out.println("rs关闭");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if(ps!=null){
					ps.close();
					//System.out.println("ps关闭");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if(con!=null){
					con.close();
					//System.out.println("con关闭");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}	
	
//	public static void main(String[] args) {
//		System.out.println(new LoveLinkDao().findLoveLinkByid(130));
//	}
	
	/**
	 * admin的列表
	 * @param name
	 * @param page
	 * @return 
	 */
	public ArrayList<LoveList> adminLoveLinkList(String name,int page) {
		ArrayList<LoveList> loves =new ArrayList<LoveList>();
		Connection con = null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		String sqlname;
		switch (name) {
		case "id":
			sqlname = "id";
			break;
		case "time":
			sqlname = "love_time";
			break;
		case "read":
			sqlname="love_read";
			break;
		case "like":
			sqlname = "love_like";
			break;
		default:
			sqlname = "id";
			break;
		}
		String sql="SELECT love_link.id,love_link.my_user_id,love_link.to_user_id,love_link.say_love,love_link.love_img,love_link.love_state,love_link.read_state,love_link.love_read,love_link.love_like,love_link.love_time,y1.yb_username,y1.yb_schoolname,y1.yb_userhead,y2.yb_username AS to_username,y2.yb_schoolname AS to_schoolname,y2.yb_userhead AS to_userhead FROM love_link LEFT OUTER JOIN yb_user AS y1 ON love_link.my_user_id=y1.yb_userid LEFT OUTER JOIN  yb_user AS y2 ON love_link.to_user_id=y2.yb_userid ORDER BY "+sqlname+" DESC LIMIT ?,?";
		try {
			con = JDBCConnection.getInstance().getConnection();
			//System.out.println("创建一个连接");
			ps = con.prepareStatement(sql);
			ps.setInt(1, (page-1)*PAGESIZE);//计算起始页
			ps.setInt(2, PAGESIZE);
			rs =ps.executeQuery();
			while(rs.next()) {
				LoveList love = new LoveList();
				love.setToName(rs.getString("to_username"));
				love.setTohead(rs.getString("to_userhead"));
				love.setToSchool(rs.getString("to_schoolname"));
				love.setMyName(rs.getString("yb_username"));
				love.setMyhead(rs.getString("yb_userhead"));
				love.setMySchool(rs.getString("yb_schoolname"));
				love.setImg(rs.getString("love_img"));
				love.setSayLove(rs.getString("say_love"));
				love.setLike(rs.getInt("love_like"));
				love.setRead(rs.getInt("love_read"));
				love.setTime(rs.getString("love_time"));
				love.setId(rs.getInt("id"));
				love.setUserid(rs.getInt("my_user_id"));
				love.setLoveState(rs.getInt("love_state"));
				loves.add(love);
			}
			//TODO ceshi
			ps = con.prepareStatement("");
			
			return loves;
		} catch (SQLException e) {
			return null;
		}finally {
			try {
				if(rs!=null){
					rs.close();
					//System.out.println("rs关闭");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if(ps!=null){
					ps.close();
					//System.out.println("ps关闭");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if(con!=null){
					con.close();
					//System.out.println("con关闭");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}	
	
	
	
	/**
	 * 返回表白和用户的列表
	 * @param name
	 * @param page
	 * @return 
	 */
	public ArrayList<LoveList> selectLoveLinkList(String name,int page) {
		ArrayList<LoveList> loves =new ArrayList<LoveList>();
		Connection con = null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		String sqlname;
		switch (name) {
		case "id":
			sqlname = "id";
			break;
		case "time":
			sqlname = "love_time";
			break;
		case "read":
			sqlname="love_read";
			break;
		case "like":
			sqlname = "love_like";
			break;
		default:
			sqlname = "id";
			break;
		}
		String sql="SELECT love_link.id,love_link.my_user_id,love_link.to_user_id,love_link.say_love,love_link.love_img,love_link.love_state,love_link.read_state,love_link.love_read,love_link.love_like,love_link.love_time,y1.yb_username,y1.yb_schoolname,y1.yb_userhead,y2.yb_username AS to_username,y2.yb_schoolname AS to_schoolname,y2.yb_userhead AS to_userhead FROM love_link LEFT OUTER JOIN yb_user AS y1 ON love_link.my_user_id=y1.yb_userid LEFT OUTER JOIN  yb_user AS y2 ON love_link.to_user_id=y2.yb_userid ORDER BY "+sqlname+" DESC LIMIT ?";
		try {
			con = JDBCConnection.getInstance().getConnection();
			//System.out.println("创建一个连接");
			ps = con.prepareStatement(sql);
			
			ps.setInt(1, page);
			rs =ps.executeQuery();
			while(rs.next()) {
				LoveList love = new LoveList();
				if(rs.getInt("love_state")==2||rs.getInt("love_state")==-2) {
					//当love的状态为2或者-2时，我是匿名的
					love.setToName(rs.getString("to_username"));
					love.setTohead(rs.getString("to_userhead"));
					love.setToSchool(rs.getString("to_schoolname"));
					love.setMyName(URLDecoder.decode("匿名", "UTF-8"));
					love.setMyhead("img/01.png");
					love.setMySchool(URLDecoder.decode("匿名", "UTF-8"));
				}else {
					love.setToName(rs.getString("to_username"));
					love.setTohead(rs.getString("to_userhead"));
					love.setToSchool(rs.getString("to_schoolname"));
					love.setMyName(rs.getString("yb_username"));
					love.setMyhead(rs.getString("yb_userhead"));
					love.setMySchool(rs.getString("yb_schoolname"));
				}
				love.setImg(rs.getString("love_img"));
				love.setSayLove(rs.getString("say_love"));
				love.setLike(rs.getInt("love_like"));
				love.setRead(rs.getInt("love_read"));
				love.setTime(rs.getString("love_time"));
				love.setId(rs.getInt("id"));
				love.setUserid(rs.getInt("my_user_id"));
				love.setLoveState(rs.getInt("love_state"));
				loves.add(love);
			}
			return loves;
		} catch (SQLException e) {
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null){
					rs.close();
					//System.out.println("rs关闭");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if(ps!=null){
					ps.close();
					//System.out.println("ps关闭");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if(con!=null){
					con.close();
					//System.out.println("con关闭");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}	
	
	/**
	 * 展示lovelink列表
	 */
	public  ArrayList<LoveLink>  getLoveLinkList(int page) {
		Connection con = null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		String sql="select *from love_link  LIMIT ?";
		try {
			con = JDBCConnection.getInstance().getConnection();
			ps = con.prepareStatement(sql);
			//ps.setInt(1, (page-1)*PAGESIZE);//计算起始页
		//	ps.setInt(2, PAGESIZE);
			ps.setInt(1, page);
			rs =ps.executeQuery();
			ArrayList<LoveLink> loves =new ArrayList<LoveLink>();
			boolean b;
			int i=0;
			while(b =  rs.next()) {
				System.out.println(b+"--"+i++);
				LoveLink love = new LoveLink();
				love.setId(rs.getInt("id"));
				love.setMyUserId(rs.getInt("my_user_id"));
				love.setToUserId(rs.getInt("to_user_id"));
				love.setSayLove(rs.getString("say_love"));
				love.setLoveImg(rs.getString("love_img"));
				love.setLoveState(rs.getInt("love_state"));
				love.setReadState(rs.getInt("read_state"));
				love.setLoveRead(rs.getInt("love_read"));
				love.setLoveLike(rs.getInt("love_like"));
				love.setLoveTime(rs.getString("love_time"));
				loves.add(love);
			}
			
			return loves;
		} catch (SQLException e) {
			return null;
		}finally {
			JDBCConnection.closeResultSet(rs);
			JDBCConnection.closePreparedStatement(ps);
			JDBCConnection.closeConnection(con);
		}
	}	
	
	/**
	 * 查询表白总数
	 */
	
	public int getLoveLinkCount() {
		Connection con = null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		String sql="select count(*) as count from love_link";
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
	/**
	 * 查询未读消息
	 */
	public int unread(int userId){
		Connection con = null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		String sql="SELECT COUNT(*) AS unread FROM love_link WHERE to_user_id =? AND read_state=0";
		try {
			con = JDBCConnection.getInstance().getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, userId);
			rs =ps.executeQuery();
			rs.next();
			int unread = rs.getInt("unread");
			return unread;
		} catch (SQLException e) {
			return 0;
		}finally {
			JDBCConnection.closeResultSet(rs);
			JDBCConnection.closePreparedStatement(ps);
			JDBCConnection.closeConnection(con);
		}
	}
	public int calcelUnread(){
		Connection con = null;
		PreparedStatement ps=null;
		String sql="SELECT COUNT(*) AS unread FROM love_link WHERE to_user_id =?";
		try {
			con = JDBCConnection.getInstance().getConnection();
			ps = con.prepareStatement(sql);
			int row =ps.executeUpdate();
			return row;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JDBCConnection.closePreparedStatement(ps);
			JDBCConnection.closeConnection(con);
		}
		return 0;
		
	}
	/**
	 * 根据我的id看有谁向我表白(存储过程)
	 */
	public ArrayList<LoveList> findLove(String name,int userId){
		Connection con=null;
		CallableStatement cs =null;
		ResultSet rs= null;
		ArrayList<LoveList> loves =new ArrayList<LoveList>();
		try {
			con=JDBCConnection.getInstance().getConnection();
			cs =con.prepareCall("CALL find_love(?,?)");
			cs.setString(1, name);
			cs.setInt(2, userId);
			rs = cs.executeQuery();
			while(rs.next()){
				LoveList love = new LoveList();
				love.setToName(rs.getString("to_username"));
				love.setTohead(rs.getString("to_userhead"));
				love.setToSchool(rs.getString("to_schoolname"));
				love.setMyName(rs.getString("yb_username"));
				love.setMyhead(rs.getString("yb_userhead"));
				love.setMySchool(rs.getString("yb_schoolname"));
				love.setImg(rs.getString("love_img"));
				love.setSayLove(rs.getString("say_love"));
				love.setLike(rs.getInt("love_like"));
				love.setRead(rs.getInt("love_read"));
				love.setTime(rs.getString("love_time"));
				love.setId(rs.getInt("id"));
				love.setUserid(rs.getInt("my_user_id"));
				love.setLoveState(rs.getInt("love_state"));
				loves.add(love);
			}
			return loves;
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
		
		return null;
	}
//	public static void main(String[] args) {
//		System.out.println(new LoveLinkDao().findLove("my", 12927286));
//	}
	
	
	/**
	 * 根据我的id看有谁向我表白
	 */
	public ArrayList<LoveLink> findId(String idName,int myId) {
		Connection con = null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		String sqlName;
		switch (idName) {
		case "my":
			sqlName="my_user_id";
			break;
		case"to":
			sqlName="to_user_id";
			break;
		default:
			sqlName="my_user_id";
		}
		
		String sql="select *from love_link where "+sqlName+" =?";
		try {
			con = JDBCConnection.getInstance().getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, myId);
			rs =ps.executeQuery();
			ArrayList<LoveLink> loves =new ArrayList<LoveLink>();
			while(rs.next()) {
				LoveLink love = new LoveLink();
				love.setId(rs.getInt("id"));
				love.setMyUserId(rs.getInt("my_user_id"));
				love.setToUserId(rs.getInt("to_user_id"));
				love.setSayLove(rs.getString("say_love"));
				love.setLoveImg(rs.getString("love_img"));
				love.setLoveState(rs.getInt("love_state"));
				love.setReadState(rs.getInt("read_state"));
				loves.add(love);
			}
			
			return loves;
		} catch (SQLException e) {
			return null;
		}finally {
			JDBCConnection.closeResultSet(rs);
			JDBCConnection.closePreparedStatement(ps);
			JDBCConnection.closeConnection(con);
		}
	}	
	/**
	 * 
	 */
	public int attitude(String name,int loveId){
		Connection con = null;
		CallableStatement cs =null;
		int count=0;
		try {
			con = JDBCConnection.getInstance().getConnection();
			cs =  con.prepareCall("CALL attitude(?,?)");
			cs.setString(1, name);
			cs.setInt(2, loveId);
			count =cs.executeUpdate();
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
	
	/**
	 * 
	 */
	
	public int deleteLove(int id) {
		Connection con = null;
		PreparedStatement ps=null;
		String sql="DELETE FROM `saylovewall`.`love_link` WHERE `id` = ?";
		try {
			con = JDBCConnection.getInstance().getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, id);
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
	public ArrayList<LoveLink> search(String sayLove){
		Connection con = null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		String sql="SELECT *FROM love_link WHERE say_love LIKE '%"+sayLove+"%'";
		try {
			con = JDBCConnection.getInstance().getConnection();
			ps = con.prepareStatement(sql);
			rs =ps.executeQuery();
			ArrayList<LoveLink> loves =new ArrayList<LoveLink>();
			while(rs.next()) {
				LoveLink love = new LoveLink();
				love.setId(rs.getInt("id"));
				love.setMyUserId(rs.getInt("my_user_id"));
				love.setToUserId(rs.getInt("to_user_id"));
				love.setSayLove(rs.getString("say_love"));
				love.setLoveImg(rs.getString("love_img"));
				love.setLoveState(rs.getInt("love_state"));
				love.setReadState(rs.getInt("read_state"));
				loves.add(love);
			}
			
			return loves;
		} catch (SQLException e) {
			return null;
		}finally {
			JDBCConnection.closeResultSet(rs);
			JDBCConnection.closePreparedStatement(ps);
			JDBCConnection.closeConnection(con);
		}
	}
	

}
