package com.yiban.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yiban.dao.DaoFactory;
import com.yiban.dao.LoveLinkDao;
import com.yiban.model.Admin;

import net.sf.json.JSONArray;

public class AdminServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action=req.getParameter("action");
		if("loading".equalsIgnoreCase(action)) {
			loading(req,resp);
		}else if("getlovelinklist".equalsIgnoreCase(action)) {
			getLoveLinkList(req,resp);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	private void loading(HttpServletRequest req, HttpServletResponse resp) throws IOException {
resp.setContentType("text/html;charset=utf-8");
		
		PrintWriter out = resp.getWriter();
		String userName,passWord;
		if(req.getParameter("username")!=null&&req.getParameter("password")!=null) {
			if(req.getParameter("username").equals("yiban")) {
				userName=req.getParameter("username");
			}else {
				out.write("用户名错误");
				return;
			}
			if(req.getParameter("password").equals("123456")) {
				passWord = req.getParameter("password");
			}else {
				out.write("密码错误");
				return;
			}
			Admin admin = new Admin();
			admin.setUserName(userName);
			admin.setPassWord(passWord);
			req.getSession().setAttribute("admin", admin);
			out.write("success");
		}
	}
	private void getLoveLinkList(HttpServletRequest req,HttpServletResponse resp)throws ServletException, IOException  {
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out = resp.getWriter();
		int size;
		String sort;
		if(req.getParameter("size")!=null) {
			size =Integer.parseInt(req.getParameter("size"));
		}else {return;}
		if(req.getParameter("sort")!=null){
			sort=req.getParameter("sort");
		}else{
			sort="";
		}
		LoveLinkDao lld = DaoFactory.getLoveLinkDao();
		int count;
		int page=lld.getLoveLinkCount();
		if(page%LoveLinkDao.PAGESIZE==0){
			count=page/LoveLinkDao.PAGESIZE;
		}else{
			count=page/LoveLinkDao.PAGESIZE+1;
		}
		
		
		JSONArray jsonlove = JSONArray.fromObject(lld.adminLoveLinkList(sort,size));
		out.write("["+jsonlove.toString()+",[{'count':"+count+"}]]");
	}
}
