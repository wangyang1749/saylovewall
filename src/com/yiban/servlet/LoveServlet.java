package com.yiban.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yiban.model.YBUser;

/**
 * Servlet implementation class LoveServlet
 */
@WebServlet("/love")
public class LoveServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
      
    public String list(HttpServletRequest req,HttpServletResponse resp){
    	YBUser yu = (YBUser)req.getSession().getAttribute("user");
    	int userId=yu.getYbUserid();
    	req.setAttribute("userId", userId);
    	return "user/index.jsp";
    }


}
