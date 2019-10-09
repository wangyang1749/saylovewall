<%@page import="com.yiban.model.YBUser"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width initial-scale=1 user-scalable=no">
    <title>SayLoveWall</title>
</head>
<link rel="stylesheet" href="css/index.css">
 <link rel="stylesheet" href="css/iconfont.css">
<script src="js/common.js"></script>
<style>a.my_iocn { color: red !important;}</style>
<body>
<%
YBUser user;
if(session.getAttribute("user")==null){
	return;
}else{
	user = (YBUser)session.getAttribute("user");
}
%>
<div id="wrap">
    <!--我的头部-->
    <div id="user">
        <div class="user_img">
            <img src="<%=user.getYbUserhead()%>">
        </div>
        <div class="user_text">
            <div class="user_name"><%=user.getYbUsername() %></div>
            <div class="user_desc"><%=user.getYbSchoolname() %></div>
        </div>
    </div>
    <div id="user_date">
        <dl>
            <dd><a href="mylove.jsp"><i ></i><span>我的表白</span><b></b></a> </dd>
            <dd id="cancel"><a href="tolove.jsp"><i></i><span>给我表白</span><span class="pic"></span></a> </dd>
            <dd><a href="mydetails.jsp"><i></i><span>设置</span><b></b></a> </dd>
              <dd><a href="about.jsp"><i></i><span>关于我们与功能介绍</span><b></b></a> </dd>
        </dl>
    </div>
    <!--导航-->
   <jsp:include page="inc/footer.jsp"></jsp:include>
</div>
<script src="js/jquery-3.3.1.js"></script>
<script type="text/javascript">
$(document).ready(function(){
		$.get("lls?action=getloveinfor&userid=<%=session.getAttribute("userid") %>",
		function(data,status){
			 var row =eval(data);
			 if(row>0){
				 $(".pic").addClass("message");
				 $(".pic").html(row);
				 $("#cancel")[0].addEventListener("click",fn1);
			 }
			 function fn1(){
					
			 }
		});
		
});
</script>

<script src="js/index.js"></script>
</body>
</html>