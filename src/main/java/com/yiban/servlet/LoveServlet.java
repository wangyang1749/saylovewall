package com.yiban.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yiban.dao.DaoFactory;
import com.yiban.dao.ILoveLinkDao;
import com.yiban.dao.MysqlDaoFactory;
import com.yiban.model.YBUser;

/**
 * Servlet implementation class LoveServlet
 */
@WebServlet("/love")
public class LoveServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
    
	private  ILoveLinkDao love = MysqlDaoFactory.getInstance().getLoveLinkDao();
    public String list(HttpServletRequest req,HttpServletResponse resp){
//    	YBUser yu = (YBUser)req.getSession().getAttribute("user");
//    	int userId=yu.getYbUserid();
//    	req.setAttribute("userId", userId);
//    	
    	
    	return "user/list.jsp";
    }


}
