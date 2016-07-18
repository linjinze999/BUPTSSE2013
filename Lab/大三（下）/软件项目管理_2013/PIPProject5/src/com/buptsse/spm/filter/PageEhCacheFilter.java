package com.buptsse.spm.filter;

import java.util.Enumeration;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.ehcache.CacheException;
import net.sf.ehcache.constructs.blocking.LockTimeoutException;
import net.sf.ehcache.constructs.web.AlreadyCommittedException;
import net.sf.ehcache.constructs.web.AlreadyGzippedException;
import net.sf.ehcache.constructs.web.filter.FilterNonReentrantException;
import net.sf.ehcache.constructs.web.filter.SimplePageCachingFilter;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 * 服务端页面缓存过滤器：<br>
 * 利用服务端缓存机制，使那些访问频繁的动态页面可以直接快速响应给客户端，降低数据库等资源的访问压力。<br>
 * 参考地址：http://www.cnblogs.com/hoojo/archive/2012/07/12/2587556.html
 */
public class PageEhCacheFilter extends SimplePageCachingFilter {

	private final static Logger log = Logger.getLogger(PageEhCacheFilter.class);

	private final static String FILTER_URL_PATTERNS = "patterns";
	private static String[] cacheURLs = null;

	/**
	 * 初始化
	 */
	private void init() throws CacheException {
		String patterns = filterConfig.getInitParameter(FILTER_URL_PATTERNS);
		if (patterns != null) {
			cacheURLs = StringUtils.split(patterns.trim(), ",");
		}
	}

	@Override
	protected void doFilter(final HttpServletRequest request,
			final HttpServletResponse response, final FilterChain chain)
			throws AlreadyGzippedException, AlreadyCommittedException,
			FilterNonReentrantException, LockTimeoutException, Exception {
		if (cacheURLs == null) {
			init();
		}

		String url = request.getRequestURI();

		boolean flag = false;// 缓存标志：若为false不缓存，若为true则缓存。默认值为false
		String pnc = request.getParameter("SNC");// 提交参数：如果提交参数中包含有SNC参数，则不缓存
		Object anc = request.getAttribute("SNC");// Attr参数：如果Attr参数中包含有SNC参数，则不缓存
		if (pnc == null && anc == null) {
			// 判断是否匹配配置的缓存URL规则
			if (cacheURLs != null && cacheURLs.length > 0) {
				for (String cacheURL : cacheURLs) {
					if (url.contains(cacheURL.trim())) {
						flag = true;
						break;
					}
				}
			}
		}

		// 如果包含我们要缓存的url，就缓存该页面，否则执行正常的页面转向
		if (flag) {// 缓存
			String query = request.getQueryString();
			if (query != null) {
				if (url.indexOf("?") != -1) {
					query = "&" + query;
				} else {
					query = "?" + query;
				}
			}
			log.info("当前请求被缓存：" + url + query);
			super.doFilter(request, response, chain);// 调用Ehcache进行缓存
		} else {// 不缓存
			chain.doFilter(request, response);
		}

	}

	/**
	 * 兼容ie6/7的gzip压缩
	 * 
	 * @see net.sf.ehcache.constructs.web.filter.Filter#acceptsGzipEncoding(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	protected boolean acceptsGzipEncoding(HttpServletRequest request) {
		boolean ie6 = this.headerContains(request, "User-Agent", "MSIE 6.0");// 判断是否为IE6
		boolean ie7 = this.headerContains(request, "User-Agent", "MSIE 7.0");// 判断是否为IE7
		return this.acceptsEncoding(request, "gzip") || ie6 || ie7;
	}

	private boolean headerContains(final HttpServletRequest request,
			final String header, final String value) {
		this.logRequestHeaders(request);
		final Enumeration<String> accepted = request.getHeaders(header);
		while (accepted.hasMoreElements()) {
			final String headerValue = (String) accepted.nextElement();
			if (headerValue.indexOf(value) != -1) {
				return true;
			}
		}
		return false;
	}

}