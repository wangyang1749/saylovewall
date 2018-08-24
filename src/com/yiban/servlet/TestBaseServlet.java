package com.yiban.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TestBaseServlet
 */
@WebServlet("/tbs")
public class TestBaseServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
    
	
    public void add(HttpServletRequest req,HttpServletResponse resp) throws IOException{
    	resp.getWriter().write("hello word");
    	System.out.println("被执行");
    	//return "index.jsp";
    }

}
