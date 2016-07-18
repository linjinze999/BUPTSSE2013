package com.buptsse.spm.domain;

import java.io.Serializable;

/**
 * @author jinze lin
 * @date 2016年04月14日 上午10:07
 * @description	page_role表的信息记录,用来记录角色拥有的页面
 * @modify
 * @modifyDate 
 */
public class PageRole implements Serializable {
	private Integer id;		//本信息id
	private String role;	//角色
	private Integer pageId;	//角色拥有的页面id
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	public Integer getPageId() {
		return pageId;
	}

	public void setPageId(Integer pageId) {
		this.pageId = pageId;
	}
}
