package com.buptsse.spm.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.buptsse.spm.dao.IScheduleDao;
import com.buptsse.spm.domain.Schedule;


/**
 * @author libing
 * @date 2015年11月16日 下午3:53:50
 * @description
 * @modify	
 * @modifyDate 
 */

@Repository
public class ScheduleDaoImpl extends BaseDAOImpl<Schedule> implements IScheduleDao {
	private static Logger LOG = Logger.getLogger(ScheduleDaoImpl.class);


	@Override
	public boolean saveSchedule(Schedule schedule) {
		// TODO Auto-generated method stub
		try{
			super.save(schedule);
		}catch(Exception e){
			e.printStackTrace();
			LOG.error(e);
			return false;
		}
		return true;		
	}

	@Override
	public boolean updateSchedule(Schedule schedule) {
		// TODO Auto-generated method stub
		try{
			super.update(schedule);
		}catch(Exception e){
			e.printStackTrace();
			LOG.error(e);
			return false;
		}
		return true;
	}

	@Override
	public boolean deleteSchedule(Schedule schedule) {
		// TODO Auto-generated method stub
		try{
			super.delete(schedule);
		}catch(Exception e){
			e.printStackTrace();
			LOG.error(e);
			return false;
		}
		return true;
	}



	@Override
	public boolean saveOrUpdateSchedule(Schedule schedule) {
		// TODO Auto-generated method stub
		try{
			super.saveOrUpdate(schedule);
		}catch(Exception e){
			e.printStackTrace();
			LOG.error(e);
			return false;
		}
		return true;
	}


	@Override
	public List<Schedule> findSchedule(String hql, List param) {
		// TODO Auto-generated method stub
		return super.find(hql, param);
		
	}

	@Override
	public List<Schedule> findSchedule(String hql, Object[] param) {
		// TODO Auto-generated method stub
		return super.find(hql, param);
	}




	@Override
	public Long countSchedule(String hql, List param) {
		// TODO Auto-generated method stub
		return super.count(hql, param);
	}

	
	@Override
	public Schedule findScheduleById(Integer id){
		// TODO Auto-generated method stub
		return super.get(Schedule.class, id);
		
	}

}
