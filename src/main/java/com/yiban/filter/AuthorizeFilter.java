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

import com.yiban.util.AppUtil;

public class AuthorizeFilter implements Filter{

	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)req;
		HttpServletResponse response = (HttpServletResponse)resp;
		if(request.getSession().getAttribute("user")!=null){
			chain.doFilter(request, response);
		}else{
			response.sendRedirect(AppUtil.REDIRECT_URL);
		}
		
		//System.out.print("测试Filter");
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}

}
