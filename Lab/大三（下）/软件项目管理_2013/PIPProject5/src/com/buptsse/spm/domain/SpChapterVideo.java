package com.buptsse.spm.domain;

import java.io.Serializable;

/**
 * @author yifei xue
 * @date 2015年11月01日 下午2:34
 * @description	Sp_Chapter_Video表的信息记录
 * @modify
 * @modifyDate 
 */

public class SpChapterVideo implements Serializable{
	private String id;
	private int chapter_id;
	private int video_time;
	private String video_name_number;
	private String video_name;
	private String  video_desc;
	private String video_path;
	private String video_pic;
	private int video_step_order;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getChapter_id() {
		return chapter_id;
	}
	public void setChapter_id(int chapter_id) {
		this.chapter_id = chapter_id;
	}
	public int getVideo_time() {
		return video_time;
	}
	public void setVideo_time(int video_time) {
		this.video_time = video_time;
	}
	public String getVideo_name_number() {
		return video_name_number;
	}
	public void setVideo_name_number(String video_name_number) {
		this.video_name_number = video_name_number;
	}
	public String getVideo_name() {
		return video_name;
	}
	public void setVideo_name(String video_name) {
		this.video_name = video_name;
	}
	public String getVideo_desc() {
		return video_desc;
	}
	public void setVideo_desc(String video_desc) {
		this.video_desc = video_desc;
	}
	public String getVideo_path() {
		return video_path;
	}
	public void setVideo_path(String video_path) {
		this.video_path = video_path;
	}
	public String getVideo_pic() {
		return video_pic;
	}
	public void setVideo_pic(String video_pic) {
		this.video_pic = video_pic;
	}
	public int getVideo_step_order() {
		return video_step_order;
	}
	public void setVideo_step_order(int video_step_order) {
		this.video_step_order = video_step_order;
	}
	
}
