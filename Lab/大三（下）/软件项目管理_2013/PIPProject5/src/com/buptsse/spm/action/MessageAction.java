package com.buptsse.spm.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import com.alibaba.fastjson.JSONObject;
import com.buptsse.spm.domain.Message;
import com.buptsse.spm.service.IMessageService;
import com.buptsse.spm.service.ISelectCourseService;

import com.opensymphony.xwork2.ActionSupport;


/**
 * @author libing
 * @date 2015年11月25日 下午4:17
 * @description 实现留言板相关功能
 * @modify
 * @modifyDate 
 */
public class MessageAction extends ActionSupport{

	private static final long serialVersionUID = 1L;
	private static Logger LOG = LoggerFactory.getLogger(MessageAction.class);
	@Resource
	private IMessageService messageService;
	
	public List messageList = new ArrayList();
	
	public Message message;



	/**
	 * 查询所有的留言内容
	 * @return
	 * @throws Exception
	 */
	public String findMessageList() throws Exception{
		
		messageList = messageService.findAllMessage();
		
		return "success";
	}	
	
	/**
	 * 增加留言方法
	 * @return
	 * @throws Exception
	 */
	public String insertMessage() throws Exception{
		String msg = "";
		boolean flag = messageService.insertMessage(message);
		if(flag){
			msg = "1";//表示保存成功
		}else{
			msg = "2";//表示保存失败
		}
		
		try {
			ServletActionContext.getResponse().getWriter().write(msg);
			
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
	public String deleteMessage() throws Exception{
		String msg = "";
		
		boolean flag = messageService.deleteMessage(message.getId());
		
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
	


	
	
	public IMessageService getMessageService() {
		return messageService;
	}


	public void setMessageService(IMessageService messageService) {
		this.messageService = messageService;
	}



	public List getMessageList() {
		return messageList;
	}


	public void setMessageList(List messageList) {
		this.messageList = messageList;
	}


	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}
		
}
