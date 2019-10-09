<%@ page import="com.yiban.dao.UserDao" %>
<%@ page import="com.yiban.dao.DaoFactory" %><%--
  Created by IntelliJ IDEA.
  User: wangyang
  Date: 2019/10/9
  Time: 17:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    UserDao ud = DaoFactory.getUserDao();
    session.setAttribute("user", ud.findId(5201314));
    response.sendRedirect("user/index.jsp");
%>
</body>
</html>
