package com.yiban.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yiban.jdbc.JDBCConnection;
import com.yiban.model.User;

public class JDBCUtil2<T> {
	public  List<T> executeQuery(Connection con,String sql,T t,Object... param) {
		List<T> list = new ArrayList<T>();
		try {
			PreparedStatement ps =  con.prepareCall(sql);
			//循环插入sql语句
			for(int i=1;i<param.length+1;i++){
				if(param[i] instanceof Integer){
					ps.setInt(i, (int)param[i]);
				}else if(param[i] instanceof String){
					ps.setString(i, (String)param[i]);
				}
			}
			ResultSet rs = ps.executeQuery();
			//利用反射写入对象
			while(rs.next()){
				//每次在内存中创建一个对象Object obj = new User()
				@SuppressWarnings("unchecked")
				T obj = (T)t.getClass().newInstance();
				//获取所有方法
				Method[] m =  t.getClass().getDeclaredMethods();
				//循环为前面的对象赋值相当于，使用新建立的对象调用set方法
				for (int i = 0; i < m.length; i++) {
					//获取参数类型
					@SuppressWarnings("rawtypes")
					Class[] cs = m[i].getParameterTypes();
					//筛选set方法
					if(m[i].getName().startsWith("set")){
						String type =cs[0].getName();
						String name=m[i].getName();
						//筛选类型
						System.out.println(type);
						if(type.equals("int")){
							//由于反射是运行时调用，所以可以通过父类的引用调用子类的方法,相当于： Object o = new User(); o.setUsername(rs.getInt());
							m[i].invoke(obj, rs.getInt(name.substring(3).substring(0,1).toLowerCase()+name.substring(4)));
							//System.out.println("int"+rs.getInt(name.substring(3).substring(0,1).toLowerCase()+name.substring(4)));
						}else if(type.equals("java.lang.String")){
							m[i].invoke(obj, rs.getString(name.substring(3).substring(0,1).toLowerCase()+name.substring(4)));
							//System.out.println("String"+rs.getString(name.substring(3).substring(0,1).toLowerCase()+name.substring(4)));
						}else{
							Class c= Class.forName(type);
							Object o =c.newInstance();
						}
					}
				}
				System.out.println("-------"+obj);
				list.add(obj);
			}
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			
		}
		
		return list;
	}
	public static void main(String[] args) {
		String sql="select * from user";
		Connection con = JDBCConnection.getInstance().getConnection();
		JDBCUtil2<User> util = new JDBCUtil2<User>();
		//List<User> uu = (List<User>)executeQuery(con, sql, new User());
		List<User> list =util.executeQuery(con, sql, new User());
		//证明是不同的对象存储在list中
		//System.out.println(list.get(0)==list.get(1));
		for(User u: list){
			System.out.println(u.getUsername());
		}

	}
}
