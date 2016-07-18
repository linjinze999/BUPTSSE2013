package com.buptsse.spm.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import com.buptsse.spm.domain.Schedule;
import com.buptsse.spm.domain.SpChapterVideo;
import com.buptsse.spm.domain.User;
import com.buptsse.spm.service.IScheduleService;
import com.buptsse.spm.service.ISpChapterVideoService;
import com.buptsse.spm.service.IUserService;

import com.opensymphony.xwork2.ActionSupport;


/**
 * @author li bing 
 * @date 2015年11月29日 下午4:17
 * @description 视频调度处理action
 * @modify
 * @modifyDate 
 */
public class CourseScheduleAction extends ActionSupport{

	private static final long serialVersionUID = 1L;
	private static Logger LOG = LoggerFactory.getLogger(CourseScheduleAction.class);
	@Resource
	private IScheduleService scheduleService;
	@Resource
	private ISpChapterVideoService spChapterVideoService;
	@Resource
	private IUserService userService;	
	
	public List scheduleList = new ArrayList();
	
	public Schedule schedule;
	
	public String bofangP="";

	public String bofangE="";
	
	
	/**
	 * 视频调度处理方法，包括暂停和关闭
	 * @return
	 * @throws Exception
	 */
	public String pauseSchedule() throws Exception{
		
		String uid = "1";//先写死为第一个用户，后续需要从session中取。
		
		User user = (User)ServletActionContext.getRequest().getSession().getAttribute("user");
		
	   Integer bofangTime=0;
	   Integer watchCourseStep=0;		
		
	    String[] names = bofangP.split("\\.");
	    if(bofangP!=""){
	        for (int i = 0; i < names.length; i++) {
	            System.out.println(names[i]);
	        }
	         bofangTime=Integer.valueOf(names[0]);
	         watchCourseStep= Integer.valueOf(names[1]);
	     }
	        
	    
	    String[] names1 = bofangE.split("\\.");
	    Integer bofangTime1=0;//结束时		
	    if(bofangE!=""){
	        for (int i = 0; i < names1.length; i++) {
	            System.out.println(names1[i]);
	        }
	        bofangTime1=Integer.valueOf(names1[0]);
	        watchCourseStep= Integer.valueOf(names1[1]);
	    } 		
		
	    
	    Integer uCourseStep = Integer.MAX_VALUE;	    
		int videoTime=0;
		if(bofangTime1==0){
			videoTime=(bofangTime/(4));
			System.out.println("暂停数据"+videoTime);
		}else{
			videoTime=(bofangTime1/4);
			System.out.println("结束数据"+videoTime);
		}	    
		double compare=videoTime/60.0;
		System.out.println("compare:"+compare);
		int computeTime=0;
		if(compare-Math.floor(compare)>=0.5){
			computeTime=(int)compare+1;
		}else{
			computeTime=(int)compare;
		}	    
		
		//取播放时间
		int yourTime=user.getVideoTime()+computeTime;		
		
		
		if (bofangE=="") {
			
			user.setVideoTime(yourTime);
			
			List<SpChapterVideo> videoList = spChapterVideoService.findSpChapterVideoByStepOrder(watchCourseStep);
			
			List<Schedule> percentList = scheduleService.findScheduleByUserIdAndStepOrder(watchCourseStep, user.getUserId());
			
	        int percent=percentList.get(0).getPercent();//取出当前的百分比
	        
	        System.out.println("percent:"+percent);
	        int time=videoList.get(0).getVideo_time();//这个视频的时间
	         System.out.println("time:"+time);		

	         double temp=(time)*new Double(percent)/100;
	         System.out.println("temp:"+temp);
	        double nowTime1=(temp+compare)*100/time;
	       //四舍五入
	        int nowTime =0;
			if(nowTime1-Math.floor(nowTime1)>=0.5){
				nowTime=(int)(nowTime1+1);
			}else{
				nowTime=(int)nowTime1;
			}
			
	        //int nowTime=(int)(nowTime1+1);
	        System.out.println("nowTime:"+nowTime);
	        System.out.println("computeTime:"+computeTime);         
	        if(nowTime>100){
	        	nowTime=100;
	        }		
			
	        // 更新Schedule表数据
	        Schedule schedule = new Schedule();
	        schedule.setPercent(nowTime);
	        schedule.setUserid(user.getUserId());
	        schedule.setVideo_step_order(watchCourseStep);
	        schedule.setChapter_id(videoList.get(0).getChapter_id());
			
	        
	        scheduleService.saveOrUpdate(schedule);
			//更新用户播放时间
			userService.updateUser(user);
		
			//更新session中的数据
			ServletActionContext.getRequest().getSession().setAttribute("user", user);	        
			
		}		
		
		return null;
	}		
		
	
	
	public IScheduleService getScheduleService() {
		return scheduleService;
	}


	public void setScheduleService(IScheduleService scheduleService) {
		this.scheduleService = scheduleService;
	}



	public List getScheduleList() {
		return scheduleList;
	}


	public void setScheduleList(List scheduleList) {
		this.scheduleList = scheduleList;
	}


	public Schedule getSchedule() {
		return schedule;
	}

	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}
		

	public String getBofangP() {
		return bofangP;
	}

	public void setBofangP(String bofangP) {
		this.bofangP = bofangP;
	}

	public String getBofangE() {
		return bofangE;
	}

	public void setBofangE(String bofangE) {
		this.bofangE = bofangE;
	}

	public ISpChapterVideoService getSpChapterVideoService() {
		return spChapterVideoService;
	}

	public void setSpChapterVideoService(
			ISpChapterVideoService spChapterVideoService) {
		this.spChapterVideoService = spChapterVideoService;
	}

	public IUserService getUserService() {
		return userService;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	
	
}
