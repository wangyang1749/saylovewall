<%@page import="com.yiban.model.YBUser"%>
<%@page import="com.yiban.model.Pager"%>
<%@page import="java.util.List"%>
<%@page import="com.yiban.dao.DaoFactory"%>
<%@page import="com.yiban.dao.UserDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>管理员界面</title>
<style>
	img{
		    width: 50px;
	}
</style>
</head>
<body>
<jsp:include page="inc/top.jsp"></jsp:include>
<%
		int nowpage;
		if(request.getParameter("nowpage")!=null){
			nowpage= Integer.parseInt(request.getParameter("nowpage"));
		}else{
			nowpage=1;
		}
		
		UserDao ud = DaoFactory.getUserDao();
		Pager pager = ud.getUserList(nowpage);
		List<YBUser> users = pager.getList();
		int count = pager.getPageCount();
		
%>

<div id="table"> 
<table border="1">
<tr>
<td>用戶id</td><td>用户头像</td><td>用戶名</td><td>用戶性別</td><td>用戶學校id</td><td>用户学校</td><td>	注册时间</td>
</tr>

<%
	for(YBUser user :users){
		%>
		<tr>
		<td><%=user.getYbUserid() %></td><td><img src='<%= user.getYbUserhead()%>' width=50 height=50></td><td><%=user.getYbUsername()%></td><td><%=user.getYbSex() %></td><td><%=user.getYbSchoolid() %></td><td><%=user.getYbSchoolname() %></td><td>	<%=user.getYbTime() %></td>
		</tr>
		<%
	}	
%>

</table>

<%
	for(int i=0;i<count;i++){
		%>
		<a href="userList.jsp?nowpage=<%=i+1%>">第<%=i +1%>页</a>
		<%
	}
	
%>
</div>
</html>