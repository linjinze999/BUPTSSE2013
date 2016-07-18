/**
 * 
 */
package com.buptsse.spm.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.buptsse.spm.domain.Code;
import com.buptsse.spm.domain.Course;
import com.buptsse.spm.domain.User;
import com.buptsse.spm.service.ICodeService;
import com.buptsse.spm.service.IUserService;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @author Xue Yifei
 * @date 2015年10月17日 下午3:53:50
 * @description
 * @modify
 * @modifyDate 2015年10月24日 下午11:53:50
 */
public class RegisterAction extends ActionSupport {
	private static Logger LOG = LoggerFactory.getLogger(RegisterAction.class);
	private User user;
	@Resource
	private IUserService userService;
	@Resource
	private ICodeService codeService;	

	protected String userid="";
	protected String userName="";
	protected String position="";


	/**
	 * 
	 * @discription 实现注册功能
	 */
	public String register() {
		String msg="";
		LOG.error("username:" + user.getUserName());
		if (user == null){
			LOG.error("USER对象为空！");
		}
		if (StringUtils.isBlank(user.getUserName()) || StringUtils.isBlank(user.getPassword())){
			msg = "用户名或密码未输入,请输入用户名或密码！";
		}else{
			LOG.error("开始保存数据");
			if(user.getPassword().equals(user.getPassword1())){
				user.setUserId(user.getUserName());
				user.setId(user.getUserName());
				user.setPosition("3");
				userService.addUser(user);
				msg = "恭喜您，注册成功！";
				LOG.error("保存数据");
				//ServletActionContext.getRequest().setAttribute("registerMsg", "注册成功！");
			}else{
				msg = "对不起，两次输入的密码不一致，请重新输入！";
			}
		}
		try {
			ServletActionContext.getResponse().getWriter().write(msg);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	
	
	
	/**
	 * 查询所有的用户信息
	 * @return
	 */
	public String listUser() {
		
		int page=Integer.parseInt(ServletActionContext.getRequest().getParameter("page"));
		int rows=Integer.parseInt(ServletActionContext.getRequest().getParameter("rows"));

		Map paramMap = new HashMap();
		paramMap.put("userid", userid);
		paramMap.put("userName", userName);
		paramMap.put("position", position);

		List<User> list = userService.findPage(paramMap,page, rows);
		
		for(User user:list){
			Code code =  codeService.findCodeName("position", user.getPosition());
			String codeName =code.getCodeName();
			user.setPosition(codeName);
		}
		
		
		//查询总条数,不带任何参数
		Long total = userService.count(paramMap);
		
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", total);
		
		String str=JSONObject.toJSONString(map);
		System.out.println("后台输出的json为："+str);
		try {
			ServletActionContext.getResponse().getWriter().write(str);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
			
	}	
	

	
	
	/**
	 * 用户删除方法
	 * @return
	 */
	public String deleteUser() {
		
		boolean result = false;
		String str = "";
		String[] ids  = ServletActionContext.getRequest().getParameterValues("ids[]");  
		
		for (int i = 0; i < ids.length; i++) { 
			//将已确认的删除
			try {
				result = userService.deleteUser(ids[i]);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				result = false;
			}
			
			if(result){
				str = "删除成功！";
			}else{
				str = "删除失败，请联系管理员！";
			}

		}

		String message = JSONObject.toJSONString(str);
		try {
			ServletActionContext.getResponse().getWriter().write(message);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
		
		return null;
		
	}	
	
	
	/**
	 * 增加用户方法
	 * @return
	 */
	public String insertUser() {
		
		//初始值
		user.setId(user.getUserId());
		
		boolean result = false;
		Map<String, Object> map=new HashMap<String, Object>();
		
		try {
			result = userService.addUser(user);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result = false;
		}
		
		if(result){
			map.put("code", "1");
			map.put("message", "添加成功！");
		}else{
			map.put("code", "2");
			map.put("message", "添加失败，请联系管理员！");
		}


		String str = JSONObject.toJSONString(map);
		try {
			ServletActionContext.getResponse().getWriter().write(str);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
		return null;
	}	
	
	
	
	
	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}
	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}
	
	
	public IUserService getUserService() {
		return userService;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}	
	public ICodeService getCodeService() {
		return codeService;
	}

	public void setCodeService(ICodeService codeService) {
		this.codeService = codeService;
	}	
	
}
