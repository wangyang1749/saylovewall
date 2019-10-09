/**
 * Created by wangyang on 2018/5/27.
 */
var width = document.documentElement.clientWidth/3;
var styleN=document.createElement("style");
styleN.innerHTML="html{font-size:"+ width+"px !important}";
document.head.appendChild(styleN);