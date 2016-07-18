package com.buptsse.spm.domain;

import java.io.Serializable;

/**
 * @author yifei xue
 * @date 2015年11月01日 下午2:34
 * @description	Sp_Chapter_Video表的信息记录
 * @modify
 * @modifyDate 
 */

public class SpChapter implements Serializable{
	private int chapter_id;
	private int chapter_name_number;
	private String chapter_name;
	private String  chapter_desc;
	private String chapter_pic;
	private String chapter_courseware;
	public int getChapter_id() {
		return chapter_id;
	}
	public void setChapter_id(int chapter_id) {
		this.chapter_id = chapter_id;
	}
	public int getChapter_name_number() {
		return chapter_name_number;
	}
	public void setChapter_name_number(int chapter_name_number) {
		this.chapter_name_number = chapter_name_number;
	}
	public String getChapter_name() {
		return chapter_name;
	}
	public void setChapter_name(String chapter_name) {
		this.chapter_name = chapter_name;
	}
	public String getChapter_desc() {
		return chapter_desc;
	}
	public void setChapter_desc(String chapter_desc) {
		this.chapter_desc = chapter_desc;
	}
	public String getChapter_pic() {
		return chapter_pic;
	}
	public void setChapter_pic(String chapter_pic) {
		this.chapter_pic = chapter_pic;
	}
	public String getChapter_courseware() {
		return chapter_courseware;
	}
	public void setChapter_courseware(String chapter_courseware) {
		this.chapter_courseware = chapter_courseware;
	}
	
	
}
