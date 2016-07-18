package com.buptsse.spm.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import com.buptsse.spm.service.IUserService;

public class DwrUtil {

	@Resource
	private IUserService userService;
	
	/**
	 * 
	 * @param userName 用户名
	 * @return boolean 判定用户名是否为数字，是返回true，否则false
	 */
	public boolean isNumeric(String userName){
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(userName);
		if(isNum.matches()){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 
	 * @param userName 用户名
	 * @param passwWord 密码
	 * @return String 状态消息，但是并没有什么用！
	 */
	public String loginCheck(String userName,String passwWord){
		//需要修改
		System.out.println("此处写用户名密码校验的方法，通过返回1，失败返回失败信息");
		System.out.println("userName: " + userName + ", passWord: " + passwWord);
		
		if (StringUtils.isBlank(userName) || StringUtils.isBlank(passwWord)){
			//ServletActionContext.getRequest().setAttribute("loginMsg", "账号或密码未输入！");
			return "账号或密码未输入！";
		}
		try
		{
			if(userService.findUser(userName,passwWord) == null){
				//ServletActionContext.getRequest().setAttribute("loginMsg", "对不起，该用户不存在或密码输入错误！");
				return "对不起，该用户不存在或密码输入错误！";
			}else{
				//ServletActionContext.getRequest().setAttribute("loginMsg", "登入成功！");
				return "1";
			}
		} catch(Exception e){
			e.printStackTrace();
		}
		
		return "1";
	}
	
	/**
	 * 
	 * @param userName 用户名
	 * @return String 检测用户名输入是否有效：10位数字，返回相应字符串
	 */
	public String extenceCheck(String userName){
		System.out.println("开始检验用户名是否存在");
		try{
			if(StringUtils.isBlank(userName) ){
				System.out.println("用户名不可为空，应为10位");
				return "用户名不可为空";
			}else if(!isNumeric(userName) || userName.length() != 10){
				System.out.println("用户名应为10位学号");
				return "用户名应为10位数字";
			}else{
				if(userService.findUser(userName) != null){
					System.out.println("用户已存在，请重新输入");
					return "extence";
				}else{
					System.out.println("用户不存在");
					return "unExtence";
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return "success";
	}
	
	public String registerCheck(String registerUserName, String registerPassWord, String registerPassWord1){
		
		if (StringUtils.isBlank(registerUserName) || StringUtils.isBlank(registerPassWord) || StringUtils.isBlank(registerPassWord1)){
			System.out.println("用户名或密码为空！");
			return "error";
		}else if(!isNumeric(registerUserName) || registerUserName.length() != 10){
			return "error";
		}else{
			try {
				if(registerPassWord.equals(registerPassWord1)){
					System.out.println("两次密码输入相同");
					return "success";
				}else{
					System.out.println("两次输入的密码不一致，请重新输入！");
					return "error";
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "error";
	}
	
}
