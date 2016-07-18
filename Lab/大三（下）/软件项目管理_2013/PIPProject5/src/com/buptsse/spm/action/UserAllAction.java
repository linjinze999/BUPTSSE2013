package com.buptsse.spm.action;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.buptsse.spm.domain.Team;
import com.buptsse.spm.domain.UserAll;
import com.buptsse.spm.service.TeamService;
import com.buptsse.spm.service.UserAllService;
import com.opensymphony.xwork2.ActionContext;

public class UserAllAction {
	private static Logger LOG = LoggerFactory.getLogger(UserLoginAction.class);
	private UserAll userAll;		//用户
	private List<Integer> deleteUserList;//用于删除批量用户
	private String logintime;
	private String oldPassword;
	@Resource
	private UserAllService userAllService;
	@Resource
	private TeamService teamService;
	
	public String searchUserAll()
	{
		
		try
		{
			/**
			 * 查询用户
			 **/
			List<UserAll> tempuser = new ArrayList<UserAll>();
			tempuser = userAllService.searchUserAllByUser(userAll);
			//Calendar calendar = Calendar.getInstance();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
			JSONArray json = new JSONArray();
			for(int i=0;i<tempuser.size();i++){
				JSONObject jo = new JSONObject();
				jo.put("Id", tempuser.get(i).getIdUserAll());
				jo.put("Account", tempuser.get(i).getAccountUserAll());
				jo.put("Password", tempuser.get(i).getPasswordUserAll());
				jo.put("Role", tempuser.get(i).getRoleUserAll());
				jo.put("Name", tempuser.get(i).getNameUserAll());
				jo.put("Sex", tempuser.get(i).getSexUserAll());
				jo.put("State", tempuser.get(i).getStateUserAll());
				jo.put("Idcard", tempuser.get(i).getIdcardUserAll());
				jo.put("Phone1", tempuser.get(i).getPhone1UserAll());
				jo.put("Phone2", tempuser.get(i).getPhone2UserAll());
				jo.put("Qq", tempuser.get(i).getQqUserAll());
				jo.put("Email", tempuser.get(i).getEmailUserAll());
				jo.put("Address", tempuser.get(i).getAddressUserAll());
				Calendar calendar = tempuser.get(i).getLogintimeUserAll();
				String str="";
				if(calendar!=null){
					str = dateFormat.format(calendar.getTime());
				}
				jo.put("Logintime", str);
				jo.put("Loginip", tempuser.get(i).getLoginipUserAll());
				jo.put("Img", tempuser.get(i).getPhotoUserAll());
				jo.put("College", tempuser.get(i).getCollegeUserAll());
				jo.put("Major", tempuser.get(i).getMajorUserAll());
				jo.put("Grade", tempuser.get(i).getGradeUserAll());
				json.add(jo);
			}
			ServletActionContext.getResponse().getWriter().write(json.toString());
		} catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	public String addUser()
	{
		String str="";
		try
		{
			UserAll tempuser = new UserAll();
			tempuser = userAllService.findUserAllByAccount(userAll.getAccountUserAll());
			if(tempuser == null){
				boolean result = userAllService.insertUserAll(userAll);
				if(result){
					str="成功：添加用户成功！";
				}else{
					str="失败：添加失败，请检查数据是否正确或完全！";
				}
			}else{
				str="失败：该账号已存在！";
			}
			String message = JSONObject.toJSONString(str);
			ServletActionContext.getResponse().getWriter().write(message);
		} catch(Exception e){
			e.printStackTrace();
			str="失败：添加失败，请检查数据是否正确或完全！";
			String message = JSONObject.toJSONString(str);
			try {
				ServletActionContext.getResponse().getWriter().write(message);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return null;
	}
	
	public String updateUser()
	{
		String str="";
		try
		{
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar calendar = Calendar.getInstance();
			if(logintime!=null && !"".equals(logintime)){
				Date date = dateFormat.parse(logintime); 
				calendar.setTime(date);
				userAll.setLogintimeUserAll(calendar);
			}
			UserAll ua = userAllService.findUserAllById(userAll.getIdUserAll());
			if(ua.getTeamidUserAll()!=null)
				userAll.setTeamidUserAll(ua.getTeamidUserAll());
			if(ua.getScoreUserAll()!=null){
				userAll.setScoreUserAll(ua.getScoreUserAll());
			}
			boolean result = userAllService.updateUserAll(userAll);
			if(result){
				str="成功：修改用户成功！";
			}else{
				str="失败：修改失败，请检查数据是否正确或完全！";
			}

			String message = JSONObject.toJSONString(str);
			ServletActionContext.getResponse().getWriter().write(message);
		} catch(Exception e){
			e.printStackTrace();
			str="失败：修改失败，请检查数据是否正确或完全！";
			String message = JSONObject.toJSONString(str);
			try {
				ServletActionContext.getResponse().getWriter().write(message);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return null;
	}
	
	public String deleteUser()
	{
		String str="";
		try
		{
			boolean result = userAllService.deleteUserAll(userAll);
			if(result){
				str="成功：删除用户成功！";
			}else{
				str="失败：删除失败，请检查数据是否正确！";
			}

			String message = JSONObject.toJSONString(str);
			ServletActionContext.getResponse().getWriter().write(message);
		} catch(Exception e){
			e.printStackTrace();
			str="失败：删除失败，请检查数据是否正确！";
			String message = JSONObject.toJSONString(str);
			try {
				ServletActionContext.getResponse().getWriter().write(message);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return null;
	}
	
	public String deleteUserList()
	{
		String str="";
		try
		{
			boolean result = true;
			for(int i=0;i<deleteUserList.size();i++){
				UserAll dl = new UserAll();
				dl.setIdUserAll(deleteUserList.get(i));
				if(!userAllService.deleteUserAll(dl)){
					result = false;
				}
			}
			if(result){
				str="成功：批量删除用户成功！";
			}else{
				str="失败：删除过程中遇到错误！";
			}

			String message = JSONObject.toJSONString(str);
			ServletActionContext.getResponse().getWriter().write(message);
		} catch(Exception e){
			e.printStackTrace();
			str="失败：删除过程中遇到错误！";
			String message = JSONObject.toJSONString(str);
			try {
				ServletActionContext.getResponse().getWriter().write(message);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return null;
	}
	
	public String changePassword(){
		String str="";
		try
		{
			UserAll tempuser = new UserAll();
			tempuser = userAllService.findUserAllByAccount(userAll.getAccountUserAll());
			if(tempuser == null){
				str="失败：找不到用户！";
			}else if(!tempuser.getPasswordUserAll().equals(oldPassword)){
				str="失败：旧密码不正确！";
			}else{
				tempuser.setPasswordUserAll(userAll.getPasswordUserAll());
				boolean result = userAllService.updateUserAll(tempuser);
				if(result){
					str="成功：修改密码成功！";
				}else{
					str="失败：修改密码失败！请检查数据网络。";
				}
			}
			String message = JSONObject.toJSONString(str);
			ServletActionContext.getResponse().getWriter().write(message);
		} catch(Exception e){
			e.printStackTrace();
			str="失败：修改密码失败！请检查数据网络。";
			String message = JSONObject.toJSONString(str);
			try {
				ServletActionContext.getResponse().getWriter().write(message);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return null;
	}
	
	public String searchStudentLogin()
	{
		
		try
		{
			List<UserAll> tempuser = new ArrayList<UserAll>();
			tempuser = userAllService.searchUserAllByUser(userAll);
			//Calendar calendar = Calendar.getInstance();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
			JSONArray json = new JSONArray();
			for(int i=0;i<tempuser.size();i++){
				JSONObject jo = new JSONObject();
				jo.put("Id", tempuser.get(i).getIdUserAll());
				jo.put("Name", tempuser.get(i).getNameUserAll());
				jo.put("Sex", tempuser.get(i).getSexUserAll());
				jo.put("State", tempuser.get(i).getStateUserAll());
				if(tempuser.get(i).getTeamidUserAll()!=null && tempuser.get(i).getTeamidUserAll()>0){
					List<Team> t=teamService.searchAllTeam();
					for(int j=0;j<t.size();j++){
						if(t.get(j).getId()==tempuser.get(i).getTeamidUserAll()){
							jo.put("Team", t.get(j).getName());
							break;
						}
					}
				}else{
					jo.put("Team", "未分配");
				}
				Calendar calendar = tempuser.get(i).getLogintimeUserAll();
				String str="";
				if(calendar!=null){
					str = dateFormat.format(calendar.getTime());
				}else{
					str = "未登录";
				}
				jo.put("Logintime", str);
				if(tempuser.get(i).getLoginipUserAll()==null || "".equals(tempuser.get(i).getLoginipUserAll())){
					jo.put("Loginip", "未登录");
				}else{
					jo.put("Loginip", tempuser.get(i).getLoginipUserAll());
				}
				jo.put("College", tempuser.get(i).getCollegeUserAll());
				jo.put("Major", tempuser.get(i).getMajorUserAll());
				jo.put("Grade", tempuser.get(i).getGradeUserAll());
				if(tempuser.get(i).getScoreUserAll()!=null && tempuser.get(i).getScoreUserAll()>0){
					jo.put("Score", tempuser.get(i).getScoreUserAll());
				}else{
					jo.put("Score", "");
				}
				json.add(jo);
			}
			ServletActionContext.getResponse().getWriter().write(json.toString());
		} catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	public String updateStudentScore()
	{
		String str="";
		try
		{
			int result = userAllService.updateUserAllScore(userAll);
			if(result == -2){
				str="失败：学生成绩格式错误！";
			}else if(result == -1){
				str="失败：找不到学生！";
			}else if(result == 0){
				str="失败：更新失败！请检查数据和网络！";
			}else{
				str="成功：更新成功！等待页面刷新！";
			}
			String message = JSONObject.toJSONString(str);
			ServletActionContext.getResponse().getWriter().write(message);
		} catch(Exception e){
			e.printStackTrace();
			str="失败：更新成绩失败！请检查数据网络。";
			String message = JSONObject.toJSONString(str);
			try {
				ServletActionContext.getResponse().getWriter().write(message);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return null;
	}
	
	public UserAll getUserAll() {
		return userAll;
	}
	public void setUserAll(UserAll userAll) {
		this.userAll = userAll;
	}
	public List<Integer> getDeleteUserList(){
		return deleteUserList;
	}
	public void serDeleteUserList(List<Integer> deleteUserList){
		this.deleteUserList = deleteUserList;
	}
	public String getLogintime(){
		return logintime;
	}
	public void setLogintime(String logintime){
		this.logintime = logintime;
	}
	public String getOldPassword(){
		return oldPassword;
	}
	public void setOldPassword(String oldPassword){
		this.oldPassword = oldPassword;
	}
	
}
