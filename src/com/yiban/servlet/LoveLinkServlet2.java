package com.yiban.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yiban.dao.DaoFactory;
import com.yiban.dao.LoveLinkDao;
import com.yiban.dao.UserDao;
import com.yiban.model.LoveLink;
import com.yiban.model.YBUser;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class LoveLinkServlet2 extends HttpServlet{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action=req.getParameter("action");
		if("addlove".equalsIgnoreCase(action)) {
			addLove(req,resp);
		}
		
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action=req.getParameter("action");
		if("getloveinfor".equalsIgnoreCase(action)) {
			getLoveInfor(req,resp);
		}else if("getlovelinklist".equalsIgnoreCase(action)) {
			getLoveLinkList(req,resp);
		}else if("addread".equalsIgnoreCase(action)) {
			addRead(req,resp);
		}else if("findlove".equalsIgnoreCase(action)) {
			findLove(req,resp);
		}else if("attitude".equalsIgnoreCase(action)) {
			attitude(req,resp);
		}else if("deletelove".equalsIgnoreCase(action)) {
			deleteLove(req,resp);
		}else if("search".equalsIgnoreCase(action)) {
			search(req,resp);
		}else if("findlovelinkbyid".equalsIgnoreCase(action)) {
			findLoveLinkByid(req,resp);
		}
	}
	/**
	 * 表白提交
	 * @param req
	 * @param resp
	 * @throws IOException
	 */
	public void addLove(HttpServletRequest req,HttpServletResponse resp) throws IOException{
		int myId,toId,loveState;
		String sayLove,loveImg;
		if(req.getParameter("myid")!=null) {
			myId=Integer.parseInt(req.getParameter("myid"));
		}else {return;}
		if(req.getParameter("toid")!=null) {
			toId=Integer.parseInt(req.getParameter("toid"));
		}else {return;}
		if(req.getParameter("saylove")!=null) {
			sayLove=req.getParameter("saylove");
		}else{return;}
		if(req.getParameter("loveimg")!=null) {
			loveImg=req.getParameter("loveimg");
		}else{return;}
		if(req.getParameter("lovestate")!=null) {
			loveState=Integer.parseInt(req.getParameter("lovestate"));
		}else {return;}
		LoveLinkDao lld = DaoFactory.getLoveLinkDao();
		LoveLink love = new LoveLink();
		love.setMyUserId(myId);
		love.setToUserId(toId);
		love.setSayLove(sayLove);
		love.setLoveImg(loveImg);
		love.setLoveState(loveState);
		System.out.println(love);
		lld.add(love);
	}
	/**
	 * 是否有未读消息
	 * @param req
	 * @param resp
	 * @throws IOException
	 */
	public void getLoveInfor(HttpServletRequest req,HttpServletResponse resp) throws IOException{
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out = resp.getWriter();
		
		int userId;
		if(req.getParameter("userid")!=null) {
			userId =Integer.parseInt(req.getParameter("userid"));
		}else {return;}
		
		LoveLinkDao lld = DaoFactory.getLoveLinkDao();
		int unread =lld.unread(userId);
		out.write("{tolove:"+unread+"}");
	}
	/**
	 * 获得列表
	 * @param req
	 * @param resp
	 * @throws IOException
	 */
	public void getLoveLinkList(HttpServletRequest req,HttpServletResponse resp) throws IOException {
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
		JSONArray jsonlove = JSONArray.fromObject(lld.selectLoveLinkList(sort,size));
		out.write("["+jsonlove.toString()+",[{'count':"+lld.getLoveLinkCount()+"}]]");
	}
	/**
	 * 
	 * 查找单个
	 */
	public void findLoveLinkByid(HttpServletRequest req,HttpServletResponse resp) throws IOException {
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out = resp.getWriter();
		int id;
		if(req.getParameter("id")!=null) {
			id =Integer.parseInt(req.getParameter("id"));
		}else {return;}
		LoveLinkDao lld = DaoFactory.getLoveLinkDao();
		JSONArray jsonlove = JSONArray.fromObject(lld.findLoveLinkByid(id));
		out.write(jsonlove.toString());
	}
	/**
	 * 浏览量增加
	 * @param req
	 * @param resp
	 * @throws IOException
	 */
	public void addRead (HttpServletRequest req,HttpServletResponse resp) throws IOException  {
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out = resp.getWriter();
		int loveid;
		if(req.getParameter("loveid")!=null) {
			loveid =Integer.parseInt(req.getParameter("loveid"));
		}else {return;}
		LoveLinkDao lld = DaoFactory.getLoveLinkDao();
		int read =lld.addRead(loveid);
		out.write("{count:"+read+"}");
	}
	/**
	 *查看有谁想我表白
	 */
	public void findLove(HttpServletRequest req,HttpServletResponse resp) throws IOException  {
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out = resp.getWriter();
		int userId;
		String name;
		if(req.getParameter("userid")!=null) {
			userId =Integer.parseInt(req.getParameter("userid"));
		}else {return;}
		if(req.getParameter("name")!=null){
			name=req.getParameter("name");
		}else{
			return;
		}
		LoveLinkDao lld = DaoFactory.getLoveLinkDao();
		
		JSONArray jsonlove = JSONArray.fromObject(lld.findLove(name, userId));
		out.write(jsonlove.toString());
	}
	/**
	 * 点赞
	 */
	public void attitude(HttpServletRequest req,HttpServletResponse resp) throws IOException  {
		resp.setContentType("text/html;charset=utf-8");
		int loveId;
		String name;
		if(req.getParameter("loveid")!=null) {
			loveId =Integer.parseInt(req.getParameter("loveid"));
		}else {return;}
		if(req.getParameter("name")!=null){
			name=req.getParameter("name");
		}else{
			return;
		}
		LoveLinkDao lld = DaoFactory.getLoveLinkDao();
		lld.attitude(name, loveId);
	}
	/**
	 *删除表白记录
	 * @param req
	 * @param resp
	 * @throws IOException
	 */
	public void deleteLove(HttpServletRequest req,HttpServletResponse resp) throws IOException  {
		resp.setContentType("text/html;charset=utf-8");
		int loveId;
		if(req.getParameter("loveid")!=null) {
			loveId =Integer.parseInt(req.getParameter("loveid"));
		}else {return;}
		LoveLinkDao lld = DaoFactory.getLoveLinkDao();
		lld.deleteLove(loveId);
	}
	/**
	 *根据saylove搜素
	 * @param req
	 * @param resp
	 * @throws IOException
	 */
	public void search(HttpServletRequest req,HttpServletResponse resp) throws IOException  {
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out = resp.getWriter();
		String sayLove;
		if(req.getParameter("saylove")!=null){
			sayLove=req.getParameter("saylove");
		}else{
			return;
		}
		LoveLinkDao lld = DaoFactory.getLoveLinkDao();
	
		JSONArray jsonlove = JSONArray.fromObject(lld.search(sayLove));
		out.write(jsonlove.toString());
	}
}
