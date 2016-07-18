package com.buptsse.spm.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

/**
 * 客户端浏览器缓存过滤器：<br>
 * 用于配置是否启用客户端浏览器缓存
 */
public class ClientNoCacheFilter implements Filter {

	private FilterConfig filterConfig;
	private boolean clientCache = false;// 是否启用客户端浏览器缓存：false为不启用客户端缓存，true为启用客户端缓存

	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
		String tempFE = filterConfig.getInitParameter("clientCache");
		if ("true".equals(tempFE)) {
			clientCache = true;
		}
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterChain) throws IOException, ServletException {

		boolean flag = false;// 缓存标志：若为false不缓存，若为true则缓存。默认值为false
		String pnc = request.getParameter("CNC");// 提交参数：如果提交参数中包含有CNC参数，则不缓存
		Object anc = request.getAttribute("CNC");// Attr参数：如果Attr参数中包含有CNC参数，则不缓存
		if (pnc != null || anc != null) {
			flag = true;
		}

		if (flag || !clientCache) {
			((HttpServletResponse) response).setHeader("Cache-Control",
					"no-cache");
			((HttpServletResponse) response).setHeader("Pragma", "no-cache");
			((HttpServletResponse) response).setDateHeader("Expires", -1);
		}

		filterChain.doFilter(request, response);
	}

	public void destroy() {
	}

	public FilterConfig getFilterConfig() {
		return filterConfig;
	}

	public void setFilterConfig(FilterConfig filterConfig) {
		this.filterConfig = filterConfig;
	}

	public boolean isClientCache() {
		return clientCache;
	}

	public void setClientCache(boolean clientCache) {
		this.clientCache = clientCache;
	}

}
