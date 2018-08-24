package com.yiban.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BaseServlet extends HttpServlet {

	/**
	 * mvc的设计模式
	 * 默认调用service方法，该方法又默认调用他的父类方法
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("method");
		//如果是以redirect开头则重定向
		String redirect = "redirect:";
		try {
			//根据参数获取当前对象需要执行的函数
			Method m = this.getClass().getMethod(name, HttpServletRequest.class,HttpServletResponse.class);
			//使用当前对象执行该函数
			String path= (String)m.invoke(this, req,resp);
			//根据返回值跳转到相应的jsp页面
			if(path==null){
				return;
			}else if(path.startsWith(redirect)){
				resp.sendRedirect(path.substring(redirect.length()));
			}else{
				req.getRequestDispatcher("WEB-INF/"+path).forward(req, resp);
			}
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
