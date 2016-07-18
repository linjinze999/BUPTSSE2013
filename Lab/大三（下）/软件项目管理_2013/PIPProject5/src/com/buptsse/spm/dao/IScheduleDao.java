package com.buptsse.spm.dao;

import java.util.List;

import com.buptsse.spm.domain.Course;
import com.buptsse.spm.domain.Schedule;


/**
 * @author libing
 * @param <T>
 * @date 2015年11月23日 下午2:46
 * @description	视频调度持久层接口定义
 * @modify
 * @modifyDate 
 */

public interface IScheduleDao {
	public boolean saveSchedule(Schedule schedule);
	public boolean updateSchedule(Schedule schedule);
	public boolean deleteSchedule(Schedule schedule);
	public List<Schedule> findSchedule(String hql,Object[] param);
	public List<Schedule> findSchedule(String hql,List param);
	public boolean saveOrUpdateSchedule(Schedule schedule);
	public Long countSchedule(String hql,List param);
	public Schedule findScheduleById(Integer id);
	

}
