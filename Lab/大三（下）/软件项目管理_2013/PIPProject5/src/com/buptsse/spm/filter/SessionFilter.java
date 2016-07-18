package com.buptsse.spm.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.buptsse.spm.domain.User;



/**
 * session校验<br>
 * 
 * 
 */
public class SessionFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		checkSession((HttpServletRequest)request,(HttpServletResponse)response);		
		chain.doFilter(request, response);
		
	}

	
	/** 校验SESSION是否有效，判断session里面是否有user */
	private void checkSession(HttpServletRequest request,
			HttpServletResponse response) {
		// 如果Session失效，跳回登录页面
		HttpSession session =  request.getSession();
		try {
			if (session == null) {
					System.out.println("*****校验到session失效*****");
					response.sendRedirect("/SPM_Project");
			}else{
				User user = (User)session.getAttribute("user");
				if(user==null){
					System.out.println("*****校验到用户未登录*****");
					response.sendRedirect("/SPM_Project/jsp/relogin.jsp");
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
	
	
	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}






}