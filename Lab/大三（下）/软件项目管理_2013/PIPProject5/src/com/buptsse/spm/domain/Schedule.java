package com.buptsse.spm.domain;

import java.io.Serializable;

public class Schedule implements Serializable{
	private static final long serialVersionUID = 1L;
	private int chapter_id;
	private int video_step_order;
	private int percent;
	private String userid;
	
	public int getChapter_id() {
		return chapter_id;
	}
	public void setChapter_id(int chapter_id) {
		this.chapter_id = chapter_id;
	}
	public int getVideo_step_order() {
		return video_step_order;
	}
	public void setVideo_step_order(int video_step_order) {
		this.video_step_order = video_step_order;
	}
	public int getPercent() {
		return percent;
	}
	public void setPercent(int percent) {
		this.percent = percent;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}

	
	

}
