package com.buptsse.spm.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.buptsse.spm.dao.ITeachingPlanDao;
import com.buptsse.spm.domain.Course;
import com.buptsse.spm.domain.TeachingPlan;


/**
 * @author libing
 * @date 2015年11月16日 下午3:53:50
 * @description
 * @modify	
 * @modifyDate 
 */

@Repository
public class TeachingPlanDaoImpl extends BaseDAOImpl<TeachingPlan> implements ITeachingPlanDao {
	private static Logger LOG = Logger.getLogger(TeachingPlanDaoImpl.class);


	@Override
	public boolean saveTeachingPlan(TeachingPlan teachingPlan) {
		// TODO Auto-generated method stub
		try{
			super.save(teachingPlan);
		}catch(Exception e){
			e.printStackTrace();
			LOG.error(e);
			return false;
		}
		return true;		
	}

	@Override
	public boolean updateTeachingPlan(TeachingPlan teachingPlan) {
		// TODO Auto-generated method stub
		try{
			super.update(teachingPlan);
		}catch(Exception e){
			e.printStackTrace();
			LOG.error(e);
			return false;
		}
		return true;
	}

	@Override
	public boolean deleteTeachingPlan(TeachingPlan teachingPlan) {
		// TODO Auto-generated method stub
		try{
			super.delete(teachingPlan);
		}catch(Exception e){
			e.printStackTrace();
			LOG.error(e);
			return false;
		}
		return true;
	}



	@Override
	public boolean saveOrUpdateTeachingPlan(TeachingPlan teachingPlan) {
		// TODO Auto-generated method stub
		try{
			super.saveOrUpdate(teachingPlan);
		}catch(Exception e){
			e.printStackTrace();
			LOG.error(e);
			return false;
		}
		return true;
	}


	@Override
	public List<TeachingPlan> findTeachingPlan(String hql, List param) {
		// TODO Auto-generated method stub
		return super.find(hql, param);
		
	}

	@Override
	public List<TeachingPlan> findTeachingPlan(String hql, Object[] param) {
		// TODO Auto-generated method stub
		return super.find(hql, param);
	}




	@Override
	public Long countTeachingPlan(String hql, List param) {
		// TODO Auto-generated method stub
		return super.count(hql, param);
	}

	
	@Override
	public TeachingPlan findTeachingPlanById(Integer id){
		// TODO Auto-generated method stub
		return super.get(TeachingPlan.class, id);
		
	}

}
