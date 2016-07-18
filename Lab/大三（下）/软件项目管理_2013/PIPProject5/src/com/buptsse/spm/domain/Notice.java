package com.buptsse.spm.domain;

public class Notice {
	private int id;			//ID
	private String title;	//标题
	private String content;	//内容
	private String type;	//类型（通知、留言、作业）
	private Integer receiveUserId;	//接收方Id
	private String receiveUserRole;//接收方角色
	private Integer sendUserId;	//发送方Id
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Integer getReceiveUserId() {
		return receiveUserId;
	}
	public void setReceiveUserId(Integer receiveUserId) {
		this.receiveUserId = receiveUserId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getReceiveUserRole() {
		return receiveUserRole;
	}
	public void setReceiveUserRole(String receiveUserRole) {
		this.receiveUserRole = receiveUserRole;
	}
	public Integer getSendUserId() {
		return sendUserId;
	}
	public void setSendUserId(Integer sendUserId) {
		this.sendUserId = sendUserId;
	}
}
