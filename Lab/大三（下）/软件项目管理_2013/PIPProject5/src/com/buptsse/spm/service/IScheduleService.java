/**
 * 
 */
package com.buptsse.spm.service;

import java.util.List;

import com.buptsse.spm.domain.Course;
import com.buptsse.spm.domain.Schedule;
import com.buptsse.spm.domain.User;

/**
 * @author libing
 * @date 2015年11月23日 下午4:05:37
 * @description 视频调度相关功能接口定义，包括增删改查
 * @modify
 *
 */

public interface IScheduleService {
	public Schedule findScheduleById(String id);
	public boolean insertSchedule(Schedule schedule);
	public List<Schedule> findAllSchedule();
	public boolean deleteSchedule(String id);
	public boolean saveOrUpdate(Schedule schedule);
	public List<Schedule> findScheduleByUserIdAndStepOrder(Integer stepOrder,String userId);
	public List<Schedule> findScheduleByUserIdAndChapterId(Integer chapterId,String userId);
}
