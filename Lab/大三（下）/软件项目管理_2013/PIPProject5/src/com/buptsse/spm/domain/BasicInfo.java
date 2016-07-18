package com.buptsse.spm.domain;

import java.io.Serializable;
/**
 * 
 * @author yifei xue 
 * @date 2015年11月6日 下午9:45:15
 * @description BasicInf表的信息
 * @modify
 * @modifyDate
 */

public class BasicInfo implements Serializable{
	private int idCourseInfo;
	private String name;
	private String content;
	
	public int getIdCourseInfo() {
		return idCourseInfo;
	}
	public void setIdCourseInfo(int idCourseInfo) {
		this.idCourseInfo = idCourseInfo;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
