package com.yiban.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yiban.dao.DaoFactory;
import com.yiban.dao.DiscussDao;
import com.yiban.dao.LoveLinkDao;

import net.sf.json.JSONArray;

public class DiscussServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action=req.getParameter("action");
		if("insertdiscuss".equalsIgnoreCase(action)) {
			insertDiscuss(req,resp);
		}else if("deletediscuss".equalsIgnoreCase(action)) {
			deleteDiscuss(req,resp);
		}else if("finddiscuss".equalsIgnoreCase(action)) {
			findDiscuss(req,resp);
		}	
	
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	
	
	public void findDiscuss(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out = resp.getWriter();
		int loveId,size;
		if(req.getParameter("loveid")!=null){
			loveId= Integer.parseInt(req.getParameter("loveid"));
		}else{return;}
		if(req.getParameter("size")!=null){
			size= Integer.parseInt(req.getParameter("size"));
		}else{return;}
		DiscussDao dd = DaoFactory.getDiscussDao();
		JSONArray jsonlove = JSONArray.fromObject(dd.findDiscuss(loveId,size));
		out.write(jsonlove.toString());
	}
	public void deleteDiscuss(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
		int id;
		if(req.getParameter("id")!=null){
			id= Integer.parseInt(req.getParameter("id"));
		}else{return;}
		DiscussDao dd = DaoFactory.getDiscussDao();
		dd.deleteDiscuss(id);
	}
	public  void insertDiscuss(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		int loveId,userId;
		String content;
		if(req.getParameter("loveid")!=null){
			loveId= Integer.parseInt(req.getParameter("loveid"));
		}else{return;}
		
		if(req.getParameter("userid")!=null){
			userId= Integer.parseInt(req.getParameter("userid"));
		}else{return;}
		if(req.getParameter("content")!=null){
			content= req.getParameter("content");
		}else{return;}
		
		DiscussDao dd = DaoFactory.getDiscussDao();
		dd.insertDiscuss(loveId, userId, content);
	}
}
