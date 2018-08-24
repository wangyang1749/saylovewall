<%@page import="com.yiban.dao.UserDao"%>
<%@page import="com.yiban.model.LoveLink"%>
<%@page import="java.util.List"%>
<%@page import="com.yiban.dao.DaoFactory"%>
<%@page import="com.yiban.dao.LoveLinkDao"%>
<%@page import="com.yiban.model.YBUser"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>我的表白</title>
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
            <h1 class="mydetails_title">给我表白</h1>
            <a href="javascript:history.go(-1);" class="return"><i>返回</i></a>
        </div>
    </header>
    <div id="navpoaition"></div>
    <div style="font-size:14px" id="abc">
   
    </div>
</div>
</body>
<script src="js/jquery-3.3.1.js"></script>
<script type="text/javascript">
getData();
function getData(){
	$.ajax({
	    url: "/saylovewall/lls",
	    type: "get",
	    data: {
	    	action:"findlove",
	    	name:"m",
	    	userid:<%=user.getYbUserid()%>
	    },
	    success: getlist
	});
	function getlist(data){
		var loves = eval(data);
		//console.info(loves);
		var node="";
		for(var i=0;i<loves.length;i++){
			
			if(loves[i].loveState==2){
				node+="<div>匿名易有向你表白<div><div>说："+loves[i].sayLove+"</div>";
			}else{
				node+="<div><img style='width: 50px; border-radius: 50%;' src='"+loves[i].myhead+"'>"+loves[i].myName+"</div><div>向你表白说："+loves[i].sayLove+"</div>";
			}
			//+"---"+loves[i].myName+"<br>";
			if(loves[i].loveState==1){
				node+=	"你已经同意对方的表白<button onclick='deleteLove("+loves[i].id+")'>解除关系</button><br>";
			}else if(loves[i].loveState==-1){
				node+=	"你已经拒绝对方的表白<button onclick='deleteLove("+loves[i].id+")'>删除记录</button><br>";
			}else{
				node+="--<button onclick='agree("+loves[i].id+")'>同意</button>--<button onclick='refuse("+loves[i].id+")'>拒绝</button>--<br>";
			}
			node+="<hr>";
			
		}
		$("#abc").html(node);
	}
}

function agree(id){
	$.ajax({
	    url: "/saylovewall/lls",
	    type: "get",
	    data: {
	    	action:"attitude",
	    	name:"agree",
	    	loveid:id
	    },
	    success: function(){
	    	alert("恭喜你！");
	    	getData();
	    }
	});
}
function refuse(id){
	$.ajax({
	    url: "/saylovewall/lls",
	    type: "get",
	    data: {
	    	action:"attitude",
	    	name:"m",
	    	loveid:id
	    },
	    success: function(){
	    	alert("拒绝对方了！");
	    	getData();
	    }
	});
}
function deleteLove(id){
	if(!confirm("确定删除?")){
		return;
	}
	$.ajax({
	    url: "/saylovewall/lls",
	    type: "get",
	    data: {
	    	action:"deletelove",
	    	loveid:id
	    },
	    success: function(){
	    	getData();
	    }
	});
}
</script>	
</html>