package com.yiban.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.yiban.open.common.Friend;

public class AppServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action=req.getParameter("action");
		if("getfriends".equalsIgnoreCase(action)) {
			getFriends(req,resp);
		}
	}

	public  void getFriends(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		System.out.println("ddd");
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out = resp.getWriter();
		
		int pageFriend;
		String accessToken;
		if(req.getParameter("accesstoken")!=null) {
			accessToken =req.getParameter("accesstoken");
		}else {return;}
		if(req.getParameter("pagefriend")!=null) {
			pageFriend = Integer.parseInt(req.getParameter("pagefriend"));
		}else {
			pageFriend=1;
		}
		Friend friend = new Friend(accessToken);
		String jsonlist = friend.list(pageFriend,15);
		out.write(jsonlist);
	}
}
