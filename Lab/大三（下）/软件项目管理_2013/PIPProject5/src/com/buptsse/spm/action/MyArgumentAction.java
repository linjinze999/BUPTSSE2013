package com.buptsse.spm.action;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.buptsse.spm.dao.impl.MySqlDao;
import com.buptsse.spm.domain.MyArgument;
import com.buptsse.spm.service.ArgumentService;

public class MyArgumentAction {
	private static Logger LOG = LoggerFactory.getLogger(MyArgumentAction.class);
	private MyArgument argument;
	@Resource
	private ArgumentService argumentService;
	
	public String updateDeployArgs(){
		JSONObject jo = new JSONObject();
		try{
			if(argument==null){
				jo.put("error", "错误：未找到参数！");
			}
			int result = argumentService.updateDeployArgs(argument);
			if(result>0){
				jo.put("success", "成功：设置服务器参数成功！");
			}else if(result==-2){
				jo.put("error", "错误：参数不完整！");
			}else if(result==-1){
				jo.put("error", "错误：未找到数据库设置对象！");
			}else if(result==0){
				jo.put("error", "错误：数据库修改出错！");
			}
			ServletActionContext.getResponse().getWriter().write(jo.toString());
		}catch(Exception e){
			jo.put("error", "错误：设置时出现未知错误！");
			try {
				ServletActionContext.getResponse().getWriter().write(jo.toString());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return null;
	}
	
	public String updateDatabaseArgs(){
		JSONObject jo = new JSONObject();
		try{
			if(argument==null){
				jo.put("error", "错误：未找到参数！");
			}
			int result = argumentService.updateDatabaseArgs(argument);
			if(result>0){
				jo.put("success", "成功：设置服务器参数成功！");
			}else if(result==-2){
				jo.put("error", "错误：参数不完整！");
			}else if(result==-1){
				jo.put("error", "错误：未找到数据库设置对象！");
			}else if(result==0){
				jo.put("error", "错误：数据库修改出错！");
			}
			ServletActionContext.getResponse().getWriter().write(jo.toString());
		}catch(Exception e){
			jo.put("error", "错误：设置时出现未知错误！");
			try {
				ServletActionContext.getResponse().getWriter().write(jo.toString());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return null;
	}
	
	public String updateDocuPath(){
		JSONObject jo = new JSONObject();
		try{
			if(argument==null){
				jo.put("error", "错误：未找到参数！");
			}
			int result = argumentService.updateDocuPath(argument);
			if(result>0){
				jo.put("success", "成功：设置服务器参数成功！");
			}else if(result==-2){
				jo.put("error", "错误：参数不完整！");
			}else if(result==-1){
				jo.put("error", "错误：未找到数据库设置对象！");
			}else if(result==0){
				jo.put("error", "错误：数据库修改出错！");
			}
			ServletActionContext.getResponse().getWriter().write(jo.toString());
		}catch(Exception e){
			jo.put("error", "错误：设置时出现未知错误！");
			try {
				ServletActionContext.getResponse().getWriter().write(jo.toString());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return null;
	}
	
	public String searchArgument(){
		JSONObject jo = new JSONObject();
		try{
			MyArgument arg = argumentService.searchArgument();
			if(arg==null){
				jo.put("error", "错误：未找到参数数据！");
			}else{
				jo.put("deployip", arg.getDeployIp());
				jo.put("deployport", arg.getDeployPort());
				jo.put("deployaccount", arg.getDeployAccount());
				jo.put("deploypassword", arg.getDeployPassword());
				jo.put("dbip", arg.getDbIp());
				jo.put("dbport", arg.getDbPort());
				jo.put("dbaccount", arg.getDbAccount());
				jo.put("dbpassword", arg.getDbPassword());
				jo.put("docupath", arg.getDocuPath());
			}
			ServletActionContext.getResponse().getWriter().write(jo.toString());
		}catch(Exception e){
			jo.put("error", "错误：寻找参数时出现未知错误！");
			try {
				ServletActionContext.getResponse().getWriter().write(jo.toString());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return null;
	}
	
	public String testDeployConnect(){
		JSONObject jo = new JSONObject();
		try{
			if(argument.getDeployIp()==null || argument.getDeployIp().equals("")){
				jo.put("error", "错误：IP参数为空");
			}else{
				String urll = "";
				if(!argument.getDeployIp().startsWith("http")){
					urll="http://"+argument.getDeployIp();
				}else{
					urll=argument.getDeployIp();
				}
				if(argument.getDeployPort()!=null && !"".equals(argument.getDeployPort())){
					urll+=":"+argument.getDeployPort();
				}
				urll+="/";
				URL url = new URL(urll);
				URLConnection con = url.openConnection();
				java.io.BufferedReader in;
				in = new java.io.BufferedReader(new java.io.InputStreamReader(con.getInputStream()));
			    String s;
			    while((s = in.readLine()) != null) {
			        if (s.length() > 0) {
			            //若服务器打开，则返回成功
			        	jo.put("success", "该服务器IP地址可用！");
			        	break;
			            }
			        }
			    in.close();
			}
		}catch(Exception e){
			//e.printStackTrace();
			jo.put("error", "无法连接此服务器！");
		}
		try {
			ServletActionContext.getResponse().getWriter().write(jo.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public String testDatabaseConnect(){
		JSONObject jo = new JSONObject();
		try{
			if(argument.getDbIp()==null || argument.getDbIp().equals("")){
				jo.put("error", "错误：IP参数为空");
			}else{
				String urll = "";
				urll=argument.getDbIp();
				if(argument.getDbPort()!=null && !"".equals(argument.getDbPort())){
					urll+=":"+argument.getDbPort();
				}
				urll+="/";
				MySqlDao ms = new MySqlDao(urll,argument.getDbAccount(),argument.getDbPassword());
				if(ms.getStat()==null){
					jo.put("error", "无法连接此数据库！");
				}else{
					jo.put("success", "连接数据库成功！");
				}
			}
		}catch(Exception e){
			//e.printStackTrace();
			jo.put("error", "无法连接此数据库！");
		}
		try {
			ServletActionContext.getResponse().getWriter().write(jo.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	public MyArgument getArgument() {
		return argument;
	}
	public void setArgument(MyArgument argument) {
		this.argument = argument;
	}

	public static Logger getLOG() {
		return LOG;
	}

	public static void setLOG(Logger lOG) {
		LOG = lOG;
	}

	public ArgumentService getArgumentService() {
		return argumentService;
	}

	public void setArgumentService(ArgumentService argumentService) {
		this.argumentService = argumentService;
	}
}
