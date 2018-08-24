<%@page import="com.yiban.model.YBUser"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>发布动态</title>
    <meta name="viewport" content="width=device-width initial-scale=1 user-scalable=no">
    <link rel="stylesheet" href="css/index.css">
	<script src="js/common.js"></script>
</head>
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
    <header>
        <div class="header">
            <h1 class="mydetails_title">发布动态</h1>
            <a href="javascript:history.go(-1);" class="return"><i>返回</i></a>
        </div>
    </header>
    <div id="navpoaition"></div>
    <div id="no_name">
        <div class="no_h">
            <textarea class="no_text" placeholder="想要说的话......" rows="10" id="saylove"></textarea>
        </div>
        <div class="no_radio">
            显示我的信息：<input type="radio" name="no"  checked="true" value="0">
            不显示我的信息：<input type="radio"name="no" value="-2">

        </div>
        <div class="no_f"> <button id="sub2" class="sub_but">点击提交</button></div>
        

    </div>
</div>
<script type="text/javascript" src="js/jquery-3.3.1.js"></script>
<script type="text/javascript">
	var state=0;
	var radios=document.getElementsByName("no"); 
	for(var i=0; i<radios.length; i ++){
	    radios[i].addEventListener("click",fn1);
	}
	function fn1(){
		state=this.value;
		if(state==0){
			alert("向对方展示你的信息");
		}else{
			alert("向对不方展示你的信息");
		}
	}
	$(function(){
		$("#sub2").click(function(){
			insertdata();
		});
	});
	function insertdata(){
		if(!confirm("确定发布?")){
 			return;
 		}
		$.post("/saylovewall/lls",
		{action:"addlove",myid:<%=user.getYbUserid()%>,toid:520520520,saylove:$("#saylove").val(),loveimg:"",lovestate:state},
		function(data,status){
			if(status=="success"){
				window.location.href="index.jsp";
			}
		});
	}
	
</script>
</body>
</html>