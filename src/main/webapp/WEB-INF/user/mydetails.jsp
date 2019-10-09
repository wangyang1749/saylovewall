<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width initial-scale=1 user-scalable=no">

    <title>我的详细信息</title>
    <link rel="stylesheet" href="css/index.css">
    <script src="js/common.js"></script>
    <style>

    </style>
</head>
<body>
    <div id="wrap">
        <header>
            <div class="header">
                <h1 class="mydetails_title">我的详情</h1>
                <a href="javascript:history.go(-1);" class="return"><i>返回</i></a>
            </div>
        </header>
        <div id="navpoaition"></div>
        <div id="details_content">
            <div class="dc_head">
               <div class="dc_img"> <img src="img/01.jpg"></div>
                <div class="dc_text">
                    <span>username</span>
                </div>
            </div>
            <div class="dc_list">
                <ul class="dc_input_list">
                    <li><span>用户名：</span><input type="text"></li>
                    <li><span>用户名：</span><input type="text"></li>
                    <li><span>用户名：</span><input type="text"></li>
                    <li><span>用户名：</span><input type="text"></li>
                    <li><span>用户名：</span><input type="text"></li>
                </ul>
            </div>
            <button class="sub_but">点击提交修改</button>
        </div>
    </div>
</body>
</html>