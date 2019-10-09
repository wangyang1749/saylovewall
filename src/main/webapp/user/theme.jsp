<%@page import="com.yiban.model.YBUser"%>
<%@page import="com.yiban.dao.LoveLinkDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1, user-scalable=no">
<title>详细信息</title>
 	<link rel="stylesheet" href="css/index.css">
	<script src="js/common.js"></script>
	<link rel="stylesheet" href="css/iconfont.css">
 <style type="text/css">

 </style>
</head>
<body>
<%
    	int id;
    	if(request.getParameter("id")!=null){
    		id=Integer.parseInt(request.getParameter("id"));
    	}else{return ;}
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
            <h1 class="mydetails_title">详细信息</h1>
            <a href="javascript:history.go(-1);" class="return"><i>返回</i></a>
        </div>
       
    </header>
    <div id="navpoaition"></div>
    <div style="font-size:14px" id="abc">
		     
	</div>
	<hr>
	<div id="def">
	
	</div>
	<div id="f_message"></div>
	<div  id="zfo"></div>
	<div style="font-size:14px"  id="dis_foot">
		<input type="text"  id="user_discuss">
		<button id="sub_dis">评论</button>
	</div>
</div>    
  
<script src="js/jquery-3.3.1.js"></script>
<script type="text/javascript">
	var pagesize;
	var onesize=3;//每次增加的数据获取的数据
	var nowsize=5;//当前的数据
	addRead();
	function addRead(){
		$.get("/saylovewall/lls?action=addread&loveid=<%=id%>",getlist);
		
		function getlist(data,a){
			//alert(data);
		}
	}
	
	getLove();
	function getLove(){
		$.ajax({
	        url:"/saylovewall/lls",
	        type: "get",
	        data: {
	        	action:"findlovelinkbyid",
	        	id:<%=id %>
	        },
	        success: getL
	     });
		function getL(data){
			var love =eval(data);
			 pagesize=love[0].loveDiscuss;
			//console.info(love);
			//console.info("----"+pagesize);
			var str="";
			if(love[0].img!=""){
				str+="<div id='abc_head'><div class='abc_my'><div><img src='"+love[0].myhead+"'></div><span>"+love[0].mySchool+"</span><span>"+love[0].myName+"</span></div>";
			}else{
				str+="<div id='def_head'><div class='def_my'><div><img style='width:50px' src='"+love[0].myhead+"'><span>"+love[0].mySchool+"</span><span>"+love[0].myName+"</span></div></div>";
			}
			
			if(!love[0].toName==""){
				str+="<i id='lovelove'class='iconfont icon-tubiaozhizuomoban'></i><div class='abc_to'><div><img src='"+love[0].tohead+"'></div><span>"+love[0].toSchool+"</span><span>"+love[0].toName+"</span></div>";
			}
			str+="</div><p>";
			if(love[0].img!=""){
				str+="<img clas='abc_big' src="+love[0].img+"'..' style='width:100%'>";
			}
		
			str+="<span>"+love[0].sayLove+"</span></p>";
			$("#abc").html(str);
			getDiscuss(nowsize);
		}
	}
	$(window).scroll(function(){
		var scrollTop = $(this).scrollTop();
		var scrollHeight=$(document).height();
		var windowHeight = $(this).height();
		if(scrollTop+windowHeight == scrollHeight){
			if(pagesize<=nowsize){
				$("#f_message").html("无数据了");
			}else if(pagesize-nowsize>onesize){
				nowsize = nowsize+onesize;
				getDiscuss(nowsize);
				$("#f_message").html("正在加载!");
			}else{
				nowsize = pagesize;
				$("#f_message").html("正在加载!");
				getDiscuss(nowsize);
				
			}
		}
	});

	function getDiscuss(nowsize){
		$.ajax({
	        url: "/saylovewall/ds",
	        type: "get",
	        data: {
	        	action:"finddiscuss",
	        	loveid:<%=id %>,
	        	size:nowsize
	        },
	        success: getD
	     });
		function getD(data){
			var discuss =eval(data);
			console.info(discuss);
			var node="";
			for(var i=0;i<discuss.length;i++){
				node+="<div id='discuss'><img src="+discuss[i].userHead+"><span>"+discuss[i].userName+"</span>"
				+"<p>"+discuss[i].content;
				if(discuss[i].userId==<%=user.getYbUserid()%>){
					node+="<button onclick='deleteDis("+discuss[i].id+")'>删除</button><p/>" ;
				}
				node+="</div>";
				
			}
			$("#def").html(node);
		}
	}
	$("#sub_dis").click(function(){
		$.ajax({
	        url: "/saylovewall/ds",
	        type: "post",
	        data: {
	        	action:"insertdiscuss",
	        	loveid:<%=id %>,
	        	userid:<%=user.getYbUserid()%>,
	        	content:$("#user_discuss").val()
	        },
	        success: function(){
	        	getDiscuss(nowsize);
	        	$("#user_discuss").val("");
	        }
	     });
	});
	function deleteDis(in_id){
		if(!confirm("确定删除?")){
 			return;
 		}
		$.ajax({
	        url: "/saylovewall/ds",
	        type: "post",
	        data: {
	        	action:"deletediscuss",
	        	id:in_id
	        },
	        success: function(){
	        	getDiscuss(nowsize);
	        }
	     });	
	}
</script>
</body>
</html>