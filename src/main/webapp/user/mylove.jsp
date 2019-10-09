<%@page import="com.yiban.model.LoveList"%>
<%@page import="java.util.ArrayList"%>
<%@page import="jdk.nashorn.internal.ir.LiteralNode.ArrayLiteralNode"%>
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
            <h1 class="mydetails_title">我的表白</h1>
            <a href="javascript:history.go(-1);" class="return"><i>返回</i></a>
        </div>
    </header>
    <div id="navpoaition"></div>
    <div style="font-size:14px" id="abc">
		    
	</div>
</div>
<script src="js/jquery-3.3.1.js"></script>
<script type="text/javascript">
getData();
function getData(){
	$.ajax({
	    url: "/saylovewall/lls",
	    type: "get",
	    data: {
	    	action:"findlove",
	    	name:"my",
	    	userid:<%=user.getYbUserid()%>
	    },
	    success: getlist
	});
	function getlist(data){
		var loves = eval(data);
		//console.info(loves);
		var node="";
		for(var i=0;i<loves.length;i++){
			node+="<div>我向："+loves[i].toName+"表白</div>"+loves[i].sayLove+"<br>";
			if(loves[i].loveState==1){
				node+=	"对方同意了你的表白<button onclick='deleteLove("+loves[i].id+")'>解除关系</button><br><br>";
			}else if(loves[i].loveState==-1){
				node+=	"对方拒绝了你的表白<button onclick='deleteLove("+loves[i].id+")'>解除关系</button><br><br>";
			}else if(loves[i].toName==""){
				node+=	"<button onclick='deleteLove("+loves[i].id+")'>删除这条记录</button><br>";
			}else{
				node+="等待中。。。。<br>";
			}
			node+="<hr>";
		}
		$("#abc").html(node);
	}
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
</body>
</html>