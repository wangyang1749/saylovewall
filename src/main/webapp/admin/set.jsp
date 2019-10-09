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
<div id="up_msg" class=""></div>
	<div id="file">
		<form enctype="multipart/form-data" id="myform">
			<input type="file" name="file01" onchange="imgPreview(this)">
		</form>
	</div>
	<div style="width: 200px; height: 200px;">
		<img id="amdinImg" src="../common/index.png" style="width: 100%">
	</div>

</body>
<script type="text/javascript" src="../js/jquery-3.3.1.js"></script>
<script type="text/javascript">
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
	        url : '../adminupload',
	        data : formData,
	        contentType: false, 
	        processData: false,
	        success : function(data){
	        	//alert(data); 
	        	img="images/"+data;
	        	$("#amdinImg").attr("src", "../common/"+data);
	        	//$("#up_msg").addClass("upload");
	        	$("#up_msg").html("图片上传成功");
	        	setTimeout(function(){
	        			$("#up_msg").html("");
	        			//$("#up_msg").removeClass("upload");
	        	},1500);
	        }
	    });
	}
}

</script>
</html>