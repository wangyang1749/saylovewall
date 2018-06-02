<%@page import="com.yiban.model.YBUser"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>我要表白</title>
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
int userid;
if(request.getParameter("userid")!=null){
		userid = Integer.parseInt(request.getParameter("userid"));
}else{userid=000;}
String userhead="img/02.png";
if(request.getParameter("userhead")!=null){
	userhead =request.getParameter("userhead");
}
%>
<header>
    <div class="header">
        <h1 class="mydetails_title">向好友表白</h1>
        <a href="javascript:history.go(-1);" class="return"><i>返回</i></a>
    </div>
</header>
<div id="up_msg" class=""></div>
    <div id="navpoaition"></div>
    <div id="love_list">
    		
      		<div id="file"> 
            	<form  enctype="multipart/form-data" id="myform">
            		<input type="file" name="file01" onchange="imgPreview(this)" >
            	</form>
            </div>
        <div class="love_list_con">
            <div class="love_list_head">
                <div class="love_list_my_love  head">
                    <img src="<%=user.getYbUserhead()%>">
                </div>
                <div class="love_list_to_love head">
                    <a href="myfriends.jsp"> <img src="<%= userhead%>"></a>
                </div>
            </div>
            <div class="love_list_content">
                <textarea placeholder="想要说的话......" rows="10" id="saylove"></textarea>
            </div>
          
              <div class="no_radio">
            	显示我的信息：<input type="radio" name="no"    checked="true" value="0">
            	不显示我的信息：<input type="radio"name="no" value="2">
        	  </div>
            <button class="sub_but" id="sub_info">点击提交</button>
            <div id="error"></div>
        </div>

    </div>
    <script type="text/javascript" src="js/jquery-3.3.1.js"></script>
<script type="text/javascript">
	var state=0;
	var img="";
	getRadio();
	function getRadio(){
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
	}
	
	
	$(function(){
		$("#sub_info").click(sublove);
	 	function sublove(){
	 		if(<%=userid%>==000){
	 			$("#error").html("请选择saylove的人！！！");
	 			return;
	 		}
	 		if($("#saylove").val()==""){
	 			$("#error").html("请输入表白内容！！！");
	 			return;
	 		}
	 		
	 		if(!confirm("确定向他/她表白?")){
	 			return;
	 		}
			$.post("/saylovewall/lls",{
				action:"addlove",
				myid:<%=user.getYbUserid()%>,
				toid:<%=userid%>,
				saylove:$("#saylove").val(),
				loveimg:img,
				lovestate:state
				},
				function(data,status){
					if(status=="success"){
						alert("恭喜你发出一条表白,等待对方回复！！");
						window.location.href="index.jsp";
					}
				});
		}
	});
	
	
	function imgPreview(obj){
		/*var reader = new FileReader();
		
		reader.onload = function(e) {
            //获取图片dom
            var img = document.getElementById("preview");
            //图片路径设置为读取的图片
            img.src = reader.result;
            //alert(reader.result);
        };
        var file = obj.files[0];
        reader.readAsDataURL(file);*/
        confirm();
        function confirm() {
    		var formData = new FormData($("#myform")[0]);
    		$.ajax({
    	        type : 'post',
    	        url : '../Upload',
    	        data : formData,
    	        contentType: false, 
    	        processData: false,
    	        success : function(data){
    	        	//alert(data); 
    	        	img="images/"+data;
    	        	$(".love_list_head").css("background","url('../images/"+data+"')");
    	        	$("#up_msg").addClass("upload");
    	        	$("#up_msg").html("图片上传成功");
    	        	setTimeout(function(){
    	        			$("#up_msg").html("");
    	        			$("#up_msg").removeClass("upload");
    	        	},1500);
    	        }
    	    });
    	}
	}
	
</script>
</body>
</html>