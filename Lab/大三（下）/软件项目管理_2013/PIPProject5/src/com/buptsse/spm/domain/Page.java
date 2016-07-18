package com.buptsse.spm.domain;

import java.io.Serializable;

/**
 * @author jinze lin
 * @date 2016年04月14日 上午9:07
 * @description	page表的信息记录
 * @modify
 * @modifyDate 
 */
public class Page implements Serializable{
	private Integer id;			//页面id
	private String name;		//页面名称
	private String link;		//页面链接
	private String img;			//页面图标
	private Integer level;		//页面级别
	private Integer parentId;	//父页ID
	private String describ;		//页面描述
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}
	
	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}
	
	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}
	
	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getDescrib() {
		return describ;
	}

	public void setDescrib(String describ) {
		this.describ = describ;
	}
}
