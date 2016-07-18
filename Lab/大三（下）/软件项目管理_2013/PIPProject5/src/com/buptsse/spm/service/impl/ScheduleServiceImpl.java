package com.buptsse.spm.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.buptsse.spm.dao.IScheduleDao;
import com.buptsse.spm.domain.Schedule;
import com.buptsse.spm.service.IScheduleService;


/**
 * @author libing 
 * @date 2015年11月16日 下午3:53
 * @description 视频进度的service层实现类定义 
 * @modify
 * @modifyDate 
 */

@Transactional
@Service
public class ScheduleServiceImpl implements IScheduleService{

	@Resource
	private IScheduleDao iScheduleDao;



	@Override
	public Schedule findScheduleById(String id) {
		// TODO Auto-generated method stub
		return iScheduleDao.findScheduleById(new Integer(id));
	}

	@Override
	public boolean insertSchedule(Schedule schedule) {
		// TODO Auto-generated method stub
		return iScheduleDao.saveSchedule(schedule);
	}

	@Override
	public List<Schedule> findAllSchedule() {
		// TODO Auto-generated method stub
		String hql = "from Schedule";
		List list = new ArrayList();
		return iScheduleDao.findSchedule(hql, list);
	}

	@Override
	public List<Schedule> findScheduleByUserIdAndStepOrder(Integer stepOrder,String userId) {
		// TODO Auto-generated method stub
		
		String hql = "from Schedule where userId=? and video_step_order=?";
		List listParam = new ArrayList();
		listParam.add(userId);
		listParam.add(stepOrder);
		return iScheduleDao.findSchedule(hql, listParam);		
	}	
	
	@Override
	public boolean deleteSchedule(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean saveOrUpdate(Schedule schedule) {
		// TODO Auto-generated method stub
		//List<Schedule> list = this.findScheduleByUserIdAndStepOrder(schedule.getVideo_step_order(), schedule.getUserid());
		
		//if(list!=null && list.size()>0){
		//	Schedule scheduleTmp = list.get(0);
		//	scheduleTmp.setPercent(schedule.getPercent());
		//}
		//return false;
		
		return iScheduleDao.saveOrUpdateSchedule(schedule);
	}
	

	@Override
	public List<Schedule> findScheduleByUserIdAndChapterId(Integer chapterId,
			String userId) {
		// TODO Auto-generated method stub
		String hql = "from Schedule where userId=? and chapter_id=?";
		List listParam = new ArrayList();
		listParam.add(userId);
		listParam.add(chapterId);
		return iScheduleDao.findSchedule(hql, listParam);			
		
	}
	
	
	
	
	
	public IScheduleDao getiScheduleDao() {
		return iScheduleDao;
	}

	public void setiScheduleDao(IScheduleDao iScheduleDao) {
		this.iScheduleDao = iScheduleDao;
	}






}
