
<%@page import="net.sf.json.processors.JsonBeanProcessor"%>
<%@page import="net.sf.json.JSONObject"%>
<%@page import="net.sf.json.JSONArray"%>
<%@page import="cn.yiban.open.common.Friend"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>朋友列表</title>
    <meta name="viewport" content="width=device-width initial-scale=1 user-scalable=no">
    <link rel="stylesheet" href="css/index.css">
	<script src="js/common.js"></script>
</head>
<body>

<%
		
		String accessToken = (String)session.getAttribute("access_token");
		
%>
<header>
    <div class="header">
        <h1 class="mydetails_title">选择你需要表白的人</h1>
        <a href="javascript:history.go(-1);" class="return"><i>返回</i></a>
        <a href="javascript:;" class="friend_love_submit" id="love_user">提交</a>
    </div>
</header>
<div id="navpoaition"></div>
<div id="friend_list">

    <div class="friend_select">
        <div class="friend_img"><img src="img/02.png"></div>
        <input type="search" placeholder="搜素">
        <button class="friend_love">搜索</button>
    </div>
    <ul class="friend_ul">
   
    </ul>
    <button id="next"></button>
    
</div>
<script type="text/javascript" src="js/jquery-3.3.1.js"></script>
<script type="text/javascript">
	var num;
	var onepage=15;
	var nowpage=1;
	getFriends(nowpage);
	function getFriends(nowpage){
		$.get("/saylovewall/as?action=getfriends&accesstoken=<%=accessToken%>&pagefriend="+nowpage,getlist);
		function getlist(data,a){
			
			var jsonf = JSON.parse(data);
			//console.info(jsonf);
			var getinfo = jsonf.info;
			var friends =getinfo.list;
			num=getinfo.num;
			
			console.info(friends);
			var node="";
			for(var i=0;i<friends.length;i++){
				node+="<li >"
			            +"<dl>"
			            +"<dd class='friend_radio'><input id='user' type='radio'  name='radios'  value="+friends[i].yb_userid+"|"+friends[i].yb_userhead+"></dd>"
			            +"<dd class='friend_img'><img src="+friends[i].yb_userhead+"></dd>"
			            +"<dd class='friend_name'>["+(i+1)+"]用户名:"+friends[i].yb_username+"</span></dd>"
			            +"</dl>"
			            +"</li>";
			}
			$(".friend_ul").html(node);
			fn2();
			addEvent();
		}
	}
	
	
	$("#love_user").click(function(){
		window.location.href="submit.jsp?userid="+userid+"&userhead="+userhead; 
	});
	var userid;
	var userhead;
	function addEvent(){
		var radios=document.getElementsByName("radios"); 
	    for(var i=0; i<radios.length; i ++){
	        radios[i].addEventListener("click",fn1);
	    }
	}
    function fn1() {
    	var str=this.value;
    	//alert(str);
    	var strs =str.split("|");
    	userid=strs[0];
    	userhead=strs[1];
    }
    function fn2(){
    	var page= Math.ceil(num/onepage);
 		var node="";
    	for(var i=0;i<page;i++){
    		node+=" <button id='next' onclick='getFriends("+(i+1)+")'>第["+(i+1)+"]页</button>";
    	}
    	$("#next").html(node);
    }
	 
</script>
</body>
</html>