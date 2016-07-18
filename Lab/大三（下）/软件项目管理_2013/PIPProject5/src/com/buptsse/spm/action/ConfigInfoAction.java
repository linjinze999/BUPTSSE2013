package com.buptsse.spm.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import com.alibaba.fastjson.JSONObject;
import com.buptsse.spm.domain.ConfigInfo;
import com.buptsse.spm.service.IConfigInfoService;
import com.buptsse.spm.service.ISelectCourseService;

import com.opensymphony.xwork2.ActionSupport;


/**
 * @author libing
 * @date 2015年12月16日 下午4:17
 * @description 实现系统配置相关功能
 * @modify
 * @modifyDate 
 */
public class ConfigInfoAction extends ActionSupport{

	private static final long serialVersionUID = 1L;
	private static Logger LOG = LoggerFactory.getLogger(ConfigInfoAction.class);
	@Resource
	private IConfigInfoService configInfoService;
	
	public List configInfoList = new ArrayList();
	
	public ConfigInfo configInfo;

	
	public String id;
	
	public String configType;
	
	public String configCode;

	public String configValue;
	
	public String configDesc;
	
	public String validate;




	/**
	 * 查询所有的配置内容
	 * @return
	 * @throws Exception
	 */
	public String findConfigInfoList() throws Exception{
		
		configInfoList = configInfoService.findAllConfigInfo();
		
		String str=JSONObject.toJSONString(configInfoList);
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
	 * 修改配置的方法
	 * @return
	 * @throws Exception
	 */
	public String editConfigInfo() throws Exception{
		String msg = "";

		
		boolean result = false;
		Map<String, Object> map=new HashMap<String, Object>();
		try {
			configInfo = new ConfigInfo();
			configInfo.setId(new Integer(id));
			configInfo.setConfigType(configType);
			configInfo.setConfigCode(configCode);
			configInfo.setConfigValue(configValue);
			configInfo.setConfigDesc(configDesc);
			configInfo.setValidate(validate);
			result = configInfoService.saveOrUpdate(configInfo);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result = false;
		}		
		
		if(result){
			map.put("code", "1");
			map.put("message", "修改成功！");
		}else{
			map.put("code", "2");
			map.put("message", "修改失败，请联系管理员！");
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
	 * 删除留言方法
	 * @return
	 * @throws Exception
	 */
	public String deleteConfigInfo() throws Exception{
		String msg = "";
		
		boolean flag = configInfoService.deleteConfigInfo(configInfo.getId());
		
		if(flag){
			msg = "删除成功";//表示保存成功
		}else{
			msg = "删除失败，请联系管理员！";//表示保存失败
		}
		String str=JSONObject.toJSONString(msg);
		try {
			ServletActionContext.getResponse().getWriter().write(str);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
		return null;
	}	
	


	
	
	public IConfigInfoService getConfigInfoService() {
		return configInfoService;
	}


	public void setConfigInfoService(IConfigInfoService configInfoService) {
		this.configInfoService = configInfoService;
	}



	public List getConfigInfoList() {
		return configInfoList;
	}


	public void setConfigInfoList(List configInfoList) {
		this.configInfoList = configInfoList;
	}


	public ConfigInfo getConfigInfo() {
		return configInfo;
	}

	public void setConfigInfo(ConfigInfo configInfo) {
		this.configInfo = configInfo;
	}
		
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getConfigType() {
		return configType;
	}

	public void setConfigType(String configType) {
		this.configType = configType;
	}

	public String getConfigCode() {
		return configCode;
	}

	public void setConfigCode(String configCode) {
		this.configCode = configCode;
	}

	public String getConfigValue() {
		return configValue;
	}

	public void setConfigValue(String configValue) {
		this.configValue = configValue;
	}

	public String getConfigDesc() {
		return configDesc;
	}

	public void setConfigDesc(String configDesc) {
		this.configDesc = configDesc;
	}
	
	public String getValidate() {
		return validate;
	}

	public void setValidate(String validate) {
		this.validate = validate;
	}	
	
	
}
