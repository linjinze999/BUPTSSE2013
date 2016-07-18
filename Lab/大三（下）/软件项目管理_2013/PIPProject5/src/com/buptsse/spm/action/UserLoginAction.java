package com.buptsse.spm.action;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.buptsse.spm.domain.UserAll;
import com.buptsse.spm.service.UserAllService;
import com.opensymphony.xwork2.ActionContext;

/**
 * @author Jinze Lin
 * @date 2016年04月13日 上午10:03
 * @description 实现登录页面逻辑
 * @modify
 * @modifyDate
 */

public class UserLoginAction {
	private static Logger LOG = LoggerFactory.getLogger(UserLoginAction.class);
	private UserAll userAll;		//用户
	private String saveUser;		//保存用户
	private String inputCheckCode;	//验证码输入值
	private String checkCode;		//验证码
	@Resource
	private UserAllService userAllService;
	
	public String login()
	{
		Map session = (Map) ActionContext.getContext().getSession();
		/**
		 * 记住用户
		 **/
		session.put("login_account", userAll.getAccountUserAll());
		if(saveUser!=null){
			session.put("login_password", userAll.getPasswordUserAll());
		}else{
			session.put("login_password", "");
		}
		/**
		 * 验证验证码
		 **/
		
		if(inputCheckCode==null || inputCheckCode.equals("验证码")){
			ServletActionContext.getRequest().setAttribute("login_error", "错误：请输入验证码！");
			//try{
			//	PrintWriter outjs = ServletActionContext.getResponse().getWriter();
			//	outjs.print("<script>$(function(){alert('11111');})</script>");
			//}catch(Exception e){}
			
			return "error";
		}else if(inputCheckCode.compareToIgnoreCase(checkCode)!=0){
			ServletActionContext.getRequest().setAttribute("login_error", "错误：验证码错误！");
			return "error";
		}
		
		LOG.error("userID:" + userAll.getIdUserAll());
		try
		{
			/**
			 * 查询用户
			 **/
			UserAll tempuser = new UserAll();
			tempuser = userAllService.findUserAll(userAll.getAccountUserAll(),userAll.getPasswordUserAll());
			if(tempuser == null){
				ServletActionContext.getRequest().setAttribute("login_error", "错误：用户名不存在！");
				return "error";
			}else if(tempuser.getIdUserAll()<=0){
				ServletActionContext.getRequest().setAttribute("login_error", "错误：密码错误！");
				return "error";
			}else{
				session.put("userId", tempuser.getIdUserAll());
				session.put("userAccount", tempuser.getAccountUserAll());
				session.put("userName", tempuser.getNameUserAll());
				session.put("userRole", tempuser.getRoleUserAll());
				//session.put("user", tempuser);
				
				
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				try{
					Calendar calendar = tempuser.getLogintimeUserAll();
					if(calendar!=null){
						String str = dateFormat.format(calendar.getTime());
						session.put("userLogintime", str);
					}else{
						session.put("userLogintime", "无");
					}
				}catch(Exception e){
					session.put("userLogintime", "无");
				}
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(new Date()); 
				tempuser.setLogintimeUserAll(calendar);
				tempuser.setLoginipUserAll(ServletActionContext.getRequest().getRemoteAddr());
				userAllService.updateUserAll(tempuser);
				return "success";
			}
		} catch(Exception e){
			e.printStackTrace();
			ServletActionContext.getRequest().setAttribute("login_error", "错误：无法连接数据库！");
		}
		LOG.error("开始保存数据");
		return "error";
	}
	public String touristLogin()
	{
		Map session = (Map) ActionContext.getContext().getSession();
		try
		{
			session.put("userId", 0);
			session.put("userAccount", 0);
			session.put("userName", "游客");
			session.put("userRole", "游客");
			return "success";
		} catch(Exception e){
			e.printStackTrace();
			ServletActionContext.getRequest().setAttribute("login_error", "错误：无法连接数据库！");
		}
		LOG.error("开始保存数据");
		return "error";
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
	
	
	
	public UserAll getUserAll() {
		return userAll;
	}
	public void setUserAll(UserAll userAll) {
		this.userAll = userAll;
	}
	public String getSaveUser(){
		return saveUser;
	}
	public void setSaveUser(String saveUser){
		this.saveUser = saveUser;
	}
	public String getInputCheckCode(){
		return inputCheckCode;
	}
	public void setInputCheckCode(String inputCheckCode){
		this.inputCheckCode = inputCheckCode;
	}
	public String getCheckCode(){
		return checkCode;
	}
	public void setCheckCode(String checkCode){
		this.checkCode = checkCode;
	}
	public UserAllService getUserAllService() {
		return userAllService;
	}
	public void setUserAllService(UserAllService userAllService) {
		this.userAllService = userAllService;
	}
}
