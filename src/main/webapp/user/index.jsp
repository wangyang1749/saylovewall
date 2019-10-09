<%@page import="com.yiban.model.YBUser"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width initial-scale=1 user-scalable=no">
	<title>SayLoveWall</title>
	<link rel="stylesheet" href="css/index.css">
	 <link rel="stylesheet" href="css/iconfont.css">
	<script src="js/common.js"></script>
	<style>a.index_icon { color: red!important ;}</style>
</head>

<body>
<%
	YBUser yu = (YBUser)session.getAttribute("user");
	int userId=yu.getYbUserid();
%>
	<div id="wrap">
		<!--头部-->
		<header>
			<div class="header">
				<a href="index.jsp">
					<h1 class="htitle">商洛学院表白墙</h1>
				</a>
			</div>
			
		</header>
		<!--  <input id="textsearch"" type="search" placeholder="搜素">
		       <button id="search" onclick="search()">搜索</button>
		       <div id="searchs"></div>       --> 
		<!--轮播图-->
		<div id="ztop"></div>
		<div id="slider" style="font-size: 0px;">
			<img src="../common/index.png"/>
			<!--  <dl>
				<dd></dd>
				<!--<dd><img src="img/j-banner02.jpg"></dd>-->
				<!--<dd><img src="img/j-banner03.jpg"></dd>-->
				<!--<dd><img src="img/j-banner02.jpg"></dd>-->
			</dl>
		</div>
		<!--动态列表-->

		<div id="index_list">
			<div id="sort">
			
				<button onclick="sortId()" class="sort_but" id="but01">默认</button>
				<button onclick="sortTime()" id="but02">时间</button>
				<button onclick="sortRead()" id="but03">浏览量</button>
				<button onclick="sortLike()" id="but04">点赞数</button>
				
			</div>
			
			 <div id="bb"></div>
			<ul id="aa"></ul>
			<div id="f_message"></div>
		</div>
		
		<div id="nofoot" style="height:54px"></div>
		
		
		<!-- 底部导航 -->
		<jsp:include page="inc/footer.jsp"></jsp:include>

	</div>
</body>

	
<script src="js/jquery-3.3.1.js"></script>
<script src="js/index.js"></script>
<script type="text/javascript">

	var sortBut=document.getElementById("sort");
	var buts =  sortBut.getElementsByTagName("button");
	for(var i=0;i<buts.length;i++){
		buts[i].addEventListener("click",fn1);
	}
	function fn1(){
		for(var i=0;i<buts.length;i++){
			buts[i].className="";
		}
		this.className="sort_but";
	}



isNotMessage();
function isNotMessage(){
	$.get("/saylovewall/lls?action=getloveinfor&userid=<%=session.getAttribute("userid")%>",
		function(data,status){
		 var row =eval(data);
		 if(row>0){
			 $(".pic").addClass("message");
			 $(".pic").html(row);
		}
	});
}

var pagesize;
var onesize=5;//每次增加的数据获取的数据

//当滚动条滚动到底部时触发此函数 
$(window).scroll(function(){
	var scrollTop = $(this).scrollTop();
	var scrollHeight=$(document).height();
	var windowHeight = $(this).height();
	if(scrollTop+windowHeight == scrollHeight){
		if(pagesize<=nowsize){
			$("#f_message").html("无数据了");
		}else if(pagesize-nowsize>onesize){
			nowsize = nowsize+onesize;
			getData(nowsize,nowSort);
			$("#f_message").html("正在加载!");
		}else{
			nowsize = pagesize;
			$("#f_message").html("正在加载!");
			getData(nowsize,nowSort);
			
		}
	}
});
var nowsize=4;//当前的数据
var nowSort="id";//默认排序
getData(nowsize,nowSort);//第一次默认取5条数据
function getData(num,nsort){
	$.ajax({
        url: "/saylovewall/lls",
        type: "get",
        data: {
        	action:"getlovelinklist",
        	size:num,
        	sort:nsort
        },
        success: getlist
     });
}	
function getlist(data,a){
	var jsondata = eval(data);
	var loves = jsondata[0];
	pagesize=jsondata[1][0].count;//表白的数量
	console.info(loves);//所有的json数据
	var node="";
	for(var i=0;i<loves.length;i++){
		node+=" <li>"
               +"<div class='panel'>"
           		+"<div class='panel_body'>"
               	+"<div class='ph'>"
                   +"<a href='javascript:;'>"
                   +"<dl>"
                   +"<dd><img src='"+loves[i].myhead+"'></dd>"
                   +"<dd class='ph_name'><span>"+loves[i].myName+"</span><br><span>"+loves[i].time+"</span></dd>"
                   +"<dd s style='clear: both'></dd>"
                   +"</dl>"
                   +"</a>"
                   +"</div>"
                   +"<div class='pb'>"
                   +"<a href='theme.jsp?id="+loves[i].id+"'>"
                   +"<p>"+loves[i].sayLove;
                   if(loves[i].img!=""){
                   	node+="<img src="+loves[i].img+"'..' style='width: 100%'/>"
                   }
                   
                   
                   node+="</p>"
                   +"</a>"
                   +"</div>"
                   +"<div class='pf'>"
                   +"<dl>"
                   +"<dd ><a onclick='getLikeCount("+loves[i].userid+","+loves[i].id+")'><i class='iconfont icon-dianzan'></i><span id='like"+loves[i].id+"'  class='read'>"+loves[i].like+"</span></a></dd>"
                   +"<dd class='pf_pos'><i class='iconfont icon-liulanliang'></i><span class='read'>"+loves[i].read+"</span></dd>"
                   +"</dl>"
                   +"</div>"
                   +"</div>"
                   +"</div>"
                   +"</li>";
	}
	$("#aa").html(node);
}

//点赞
function getLikeCount(userid,id){
	$.get("/saylovewall/uls?loveid="+id+"&userid=<%=userId%>",getlist);
	function getlist(data){
		$("#like"+id).html(""+eval(data));
	}
}

//一下都是排序
function sortId(){
	//alert("id");
	nowSort="id";
	getData(nowsize,nowSort);
}
function sortTime(){
	//alert("time");
	nowSort="time";
	getData(nowsize,nowSort);
}
function sortRead(){
	//alert("read");
	nowSort="read";
	getData(nowsize,nowSort);
}
function sortLike(){
	//alert("like");
	nowSort="like";
	getData(nowsize,nowSort);
}
/*搜索功能暂未开通
$("#textsearch").focus(function(){
	console.info("被点击");
	$("#searchs").css("display","block");
	search()
});
$("#textsearch").blur(function(){
	$("#searchs").css("display","none");
	$("#searchs").html("");
});
function search(){
	console.info("---"+$("#search").val());
	$.ajax({
        url: "/saylovewall/lls",
        type: "get",
        data: {
        	action:"search",
        	saylove:$("#search").val()
        },
        success: getSearch
     });
	function getSearch(data){
		var list=eval(data);
		console.info(list);
		var node="";
		for(var i=0;i<list.length;i++){
			node+=list[i].sayLove+"<br>";
		}
		
		$("#searchs").html(node);
	}
}
*/
</script>

</html>
