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

<div id="table"> 

</div>
<div id="page"></div>
</body>
<script type="text/javascript" src="../js/jquery-3.3.1.js"></script>
<script>

	$("#exit").click(function(){
		
		window.location.href="../admin.jsp";
	});
	var nowpage=1;
	getData(nowpage);
	function getData(num){
		nowpage=num;
		$.ajax({
	        url: "/saylovewall/vadmin",
	        type: "get",
	        data: {
	        	action:"getlovelinklist",
	        	size:num,
	        	sort:""
	        },
	        success: getlist
	     });
		function getlist(data){
			var jsondata = eval(data);
			var loves = jsondata[0];
			pagesize=jsondata[1][0].count;//表白的数量
			console.info(jsondata);//所有的json数据
			var node="";
			for(var i=0;i<loves.length;i++){
				node+="<img src='"+loves[i].myhead+"'>"+loves[i].myName+"--"+loves[i].mySchool+"--"+loves[i].userid;
				node+="<img src='"+loves[i].tohead+"'>"+loves[i].toName+"--"+loves[i].toSchool;
				node+=loves[i].sayLove+"--点赞"+loves[i].like+"--浏览量"+loves[i].read;
				node+=	"<button onclick='deleteLove("+loves[i].id+")'>删除</button>";
				node+="<hr>";
			}
			$("#table").html(node);
			getPage();
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
		    	getData(nowpage);
		    }
		});
	}
	function getPage(){
		var node="";
		for(var i=0;i<pagesize;i++){
			node+="<button onclick='getData("+(i+1)+")'>[第"+(i+1)+"页]</button>"
		}
		$("#page").html(node);
	}
	
	
</script>
</html>