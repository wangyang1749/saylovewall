package com.yiaban.test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.yiban.jdbc.SqlUtil;
import com.yiban.model.YBUser;

public class TextSqlUtil {
	public static void main(String[] args) {
//		String sql[]={"INSERT INTO `saylovewall`.`love_link`"
//				+ "(`my_user_id`, `to_user_id`, `say_love`, `love_img`)"
//				+ " VALUES (?,?,?,?)"};
//		Object arg[]={12927286,1479,"lovelovelove","123"};
//		SqlUtil.updataSql(sql,arg);
		String sql[]={};
		Object arg[]={};
		ArrayList<ResultSet> rsList = SqlUtil.QuerySql(sql, arg);
		
	}
	
}
