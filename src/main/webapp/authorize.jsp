
<%@page import="com.yiban.dao.DaoFactory"%>
<%@page import="com.yiban.dao.UserDao"%>
<%@page import="com.yiban.model.YBUser"%>
<%@page import="cn.yiban.open.common.User"%>
<%@page import="net.sf.json.JSONObject"%>
<%@page import="com.yiban.util.AppUtil"%>
<%@page import="cn.yiban.open.FrameUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1, user-scalable=no">
<title>Insert title here</title>
</head>
<body>
	<%
	//yb_userid
	//yb_username
	//yb_usernick
	//yb_sex
	//yb_money
	//yb_exp
	//yb_userhead
	//yb_schoolid
	//yb_schoolname
	//yb_regtime
		FrameUtil util = new FrameUtil(request,response,AppUtil.APPID,AppUtil.APPSECRET,AppUtil.REDIRECT_URL);
		boolean isSuccess= util.perform();
		//判断用户是否授权成功
		if(isSuccess){
			UserDao ud = DaoFactory.getUserDao();
			User userjson = new User(util.getAccessToken());
			String json = userjson.me();
			JSONObject backUser = JSONObject.fromObject(json).getJSONObject("info");	
			session.setAttribute("access_token", util.getAccessToken());
			session.setAttribute("userid", backUser.getInt("yb_userid"));
			//如果是新用户则将用户信息存入数据库
			out.println("授权成功");
			if(ud.findId(backUser.getInt("yb_userid"))==null){
				out.println("我是新用户");
				YBUser user = new YBUser();
				user.setYbExp(backUser.getInt("yb_exp"));
				user.setYbMoney(backUser.getInt("yb_money"));
				try{
					user.setYbSchoolid(backUser.getInt("yb_schoolid"));
				}catch(Exception e){
					
				}
				
				user.setYbSchoolname(backUser.getString("yb_schoolname"));
				user.setYbSex(backUser.getString("yb_sex"));
				user.setYbUserhead(backUser.getString("yb_userhead"));
				user.setYbUserid(backUser.getInt("yb_userid"));
				user.setYbUsername(backUser.getString("yb_username"));
				user.setYbUsernick(backUser.getString("yb_usernick"));
				ud.add(user);
				session.setAttribute("user", user);
				response.sendRedirect("user/index.jsp");
			}else{
				out.println("我是老用户");
				session.setAttribute("user", ud.findId(backUser.getInt("yb_userid")));
				response.sendRedirect("user/index.jsp");
			}	
			     
		}
	
		
	%>
</body>
</html>