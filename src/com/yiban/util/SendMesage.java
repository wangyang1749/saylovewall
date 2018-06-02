package com.yiban.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;


import cn.yiban.util.HTTPSimple;

public class SendMesage {
	public static void sendMesage(String access_token,String to_yb_uid,String content,String template){
		List<NameValuePair> param = new ArrayList<NameValuePair>();
		param.add(new BasicNameValuePair("access_token",access_token));
		param.add(new BasicNameValuePair("to_yb_uid",to_yb_uid));
		param.add(new BasicNameValuePair("content",content));
		param.add(new BasicNameValuePair("template",template));
		HTTPSimple.POST(ApiUtil.MSG, param);
	}
}
