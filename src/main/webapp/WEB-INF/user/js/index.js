
//弹出层定义
$("#sendcon").click(function(){
	$("#sendcon").css("color","blue");
	$("#i.iconfont.icon-biaobai").css("font-size","45px");
	
    $("#send").fadeToggle();
    event.stopPropagation();
});
document.addEventListener("click",function () {
    if($("#send").css("display")!="none"){
        $("#send").hide();
        $("#sendcon").css("color","#b9b9b9");
    }
})
$("#send").click(function () {
    event.stopPropagation();
});