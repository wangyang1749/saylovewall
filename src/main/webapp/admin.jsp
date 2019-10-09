<%@page import="com.yiban.model.Admin"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>管理员界面</title>
<style type="text/css">
	*{margin: 0；padding:0}
	#admin_form{
		background: #c1bdbd;
	    margin: 204px auto;
	    width: 300px;
		
	}
</style>
</head>
<body>
	<div id="admin_form">
			<div class="username"><span>用户名:</span><input type="text" id="username"></div>
			<div class="password"><span>密   码:</span><input type="password" id="password"></div>
			<div class="submit"><button id="submit">提交</button></div>
			<div id="error"></div>
	</div>	
	
	<script type="text/javascript" src="js/jquery-3.3.1.js"></script>
	<script type="text/javascript">
	$("#submit").click(function(){
		var username=document.getElementById("username");
		var password=document.getElementById("password");
		if(username.value==""){
			$("#error").html("用户名不能为空!!!");
			return;
		}
		if(password.value==""){
			$("#error").html("密码不能为空!!!");
			return;
		}
		 $.ajax({
		        url: "vadmin",
		        type: "post",
		        data: {
		        	action:"loading",
		           	username:username.value,
		           	password:password.value
		        },
		        success: function (data ,textStatus, jqXHR)
		        {	
		        	if(data=="success"){
		        		alert("登陆成功");
		        		window.location.href="admin/index.jsp";
		        	}else{
		        		$("#error").html(data);
		        	}
		        }
		     });
		
	});	
		
	</script>
</body>


</html>