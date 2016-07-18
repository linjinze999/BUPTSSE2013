package Rmi;

import java.util.Date;

import javax.xml.crypto.Data;

public class MeetingInfo {//会晤信息
	private String description_label;//描述标签
	private String start_user;//发起用户
	private String book_user;//预约用户
	private Date start_time;//开始时间
	private Date end_time;//结束时间
	
	public MeetingInfo(String description_label,String start_user,String book_user,Date start_time,Date end_time){
		//初始化
		this.setDescriptionLabel(description_label);
		this.setStartUser(start_user);
		this.setBookUser(book_user);
		this.setStartTime(start_time);
		this.setEndTime(end_time);
	}
	
	//getter
	public String getDescriptionLabel(){
		return this.description_label;
	}
	
	public String getStartUser(){
		return this.start_user;
	}
	
	public String getBookUser(){
		return this.book_user;
	}
	
	public Date getStartTime(){
		return this.start_time;
	}
	
	public Date getEndTime(){
		return this.end_time;
	}
	
	//setter
	public void setDescriptionLabel(String DescriptionLabel){
		this.description_label = DescriptionLabel;
	}
	
	public void setStartUser(String StartUser){
		this.start_user = StartUser;
	}
	
	public void setBookUser(String BookUser){
		this.book_user = BookUser;
	}
	
	public void setStartTime(Date StartTime){
		this.start_time = StartTime;
	}
	
	public void setEndTime(Date EndTime){
		this.end_time = EndTime;
	}
	
}
