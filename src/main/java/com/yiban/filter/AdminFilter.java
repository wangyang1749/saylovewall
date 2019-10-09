package com.yiban.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminFilter implements Filter{

	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)req;
		HttpServletResponse response = (HttpServletResponse)resp;
	//	System.out.println("filter");
		Object obj = request.getSession().getAttribute("admin");
		if(obj!=null) {
		
			System.out.println("---"+obj);
			
		}else {
			//System.out.println("你没有登陆！");
			response.sendRedirect("admin.jsp");
			return;
		}
		chain.doFilter(req, resp);
		
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}

}
