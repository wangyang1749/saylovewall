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
            <h1 class="mydetails_title">关于我们与功能介绍</h1>
            <a href="javascript:history.go(-1);" class="return"><i>返回</i></a>
        </div>
    </header>
    <div id="navpoaition"></div>
    <div style="font-size:14px;margin: 10px;" id="abc">
   	<h1>功能介绍</h1>
   	<p>
   		1、在本应用中你可以向好友表白（可以匿名）,发送表白内容后，对方可以选择接受或者拒绝你的告白，发送的表白内容将会在主页显示。<br>
   		2、对表白内容，我们提供4中排序方式。每个用户可以选择点赞，或者取消点赞，以及对页面浏览量的显示。<br>
   		3、从主页可以进入表白内容的详细界面，并且可以评论。<br>
   		4、你也可以发布动态。<br>
   		
   		注意：如果表白对方未授权本应用，主页里不显示表白对象，一旦对方授权将显示<br>
   		
   	</p>
   		<h1>关于我们</h1>
   	<p>
   		1、本应用由商洛学院易班发展中心开发<br>
   		2、开发人员：王阳<br>
   		3、由于时间原因目前有些功能暂未添加。更多有趣的功能，尽情期待！！<br>
   		4、如果你有什么好的建议，请发送你的建议的到我们的QQ邮箱：174974895@qq.com,我们会及时处理，并且感谢您的反馈。
   		
   	</p>
    </div>
</div>
</body>
<script src="js/jquery-3.3.1.js"></script>

</html>