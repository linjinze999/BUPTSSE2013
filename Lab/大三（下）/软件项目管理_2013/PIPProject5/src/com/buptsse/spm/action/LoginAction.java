package com.buptsse.spm.action;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.buptsse.spm.domain.User;
import com.buptsse.spm.service.IUserService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @author xinyu han
 * @date 2015年10月21日 上午9:22:50
 * @description 实现登录页面逻辑
 * @modify	yifei Xue
 * @modifyDate 2015年10月24日 上午11:30:50
 */

public class LoginAction extends ActionSupport
{
	private static Logger LOG = LoggerFactory.getLogger(LoginAction.class);
	private User user;
	
	@Resource
	private IUserService userService;
	/**
	 * 
	 * @description 实现登入功能 
	 */
	public String login()
	{
		LOG.error("username:" + user.getUserName());
		
		try
		{
			User tempuser = new User();
			tempuser = userService.findUser(user.getUserName(),user.getPassword());
			
			Map session = (Map) ActionContext.getContext().getSession();
			session.put("user", tempuser);
			return SUCCESS;			

			
		} catch(Exception e){
			e.printStackTrace();
		}
		LOG.error("开始保存数据");
		return SUCCESS;
	}
	
	public String logout()
	{
		System.out.println("*********进入登出功能**********");
		try
		{
			Map session = (Map) ActionContext.getContext().getSession();
			session.clear();
			return "success";			
		} catch(Exception e){
			e.printStackTrace();
		}
		return "success";
	}	
	
	
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public IUserService getUserService() {
		return userService;
	}
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}
}
