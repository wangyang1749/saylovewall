package com.yiaban.test;

import cn.yiban.util.HTTPSimple;

public class finuser {
	public static void main(String[] args) {
		String url1="https://openapi.yiban.cn/user/me";
		String url2= "https://openapi.yiban.cn/user/other";
		
		String findUser = url2;
		findUser+="?access_token=";
		findUser+="7c0676860f96de69b11f3435458f3b13cd174b3c";
		findUser+="&yb_userid=";
		findUser+="12927286";
		findUser+="&school_name=";
		findUser+="商洛学院";
		String json = HTTPSimple.GET(findUser);
		System.out.print(json);
	}
}
