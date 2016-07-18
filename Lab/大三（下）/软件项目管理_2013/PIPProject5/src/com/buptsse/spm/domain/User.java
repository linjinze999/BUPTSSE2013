package com.buptsse.spm.domain;

import java.io.Serializable;
/**
 * @author zhengzhi ren
 * @date 2015年11月01日 下午2:34
 * @description	User表的信息记录
 * @modify
 * @modifyDate 
 */
public class User implements Serializable{
	private String id;
	private String userName;
	private String password;
	private String password1;
	private String position;
	private String userId;
	private int videoTime;

	public int getVideoTime() {
		return videoTime;
	}

	public void setVideoTime(int videoTime) {
		this.videoTime = videoTime;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password=password;
	}
	
	public String getPassword1() {
		return this.password1;
	}

	public void setPassword1(String password1) {
		this.password1=password1;
	}

	public String getPosition() {
		return this.position;
	}

	public void setPosition(String position) {
		this.position=position;
	}
	
	public void setUserId(String userId){
		this.userId = userId;
	}
	
	public String getUserId(){
		return this.userId;
	}

}
