package com.yiban.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yiban.dao.DaoFactory;
import com.yiban.dao.UserLikeDao;

public class UserLikeServlet extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out = resp.getWriter();
		int loveId,userId;
		if(req.getParameter("loveid")!=null) {
			loveId=Integer.parseInt(req.getParameter("loveid"));
		}else {return;}
		if(req.getParameter("userid")!=null) {
			userId=Integer.parseInt(req.getParameter("userid"));
		}else {return;}
		UserLikeDao uld = DaoFactory.getUserLikeDao();
		int count = uld.support(userId, loveId);
		out.write("{count:"+count+"}");
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req,resp);
	}
}
