package com.buptsse.spm.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.buptsse.spm.dao.ITeachingPlanDao;
import com.buptsse.spm.domain.TeachingPlan;
import com.buptsse.spm.service.ITeachingPlanService;


/**
 * @author libing 
 * @date 2015年11月16日 下午3:53
 * @description 教案的service层实现类定义 
 * @modify
 * @modifyDate 
 */

@Transactional
@Service
public class TeachingPlanServiceImpl implements ITeachingPlanService{

	@Resource
	private ITeachingPlanDao iTeachingPlanDao;



	@Override
	public TeachingPlan findTeachingPlanById(String id) {
		// TODO Auto-generated method stub
		return iTeachingPlanDao.findTeachingPlanById(new Integer(id));
	}

	@Override
	public boolean insertTeachingPlan(TeachingPlan teachingPlan) {
		// TODO Auto-generated method stub
		return iTeachingPlanDao.saveTeachingPlan(teachingPlan);
	}

	@Override
	public List<TeachingPlan> findAllTeachingPlan() {
		// TODO Auto-generated method stub
		String hql = "from TeachingPlan";
		List list = new ArrayList();
		return iTeachingPlanDao.findTeachingPlan(hql, list);
	}

	@Override
	public boolean deleteTeachingPlan(Integer id) {
		// TODO Auto-generated method stub
		TeachingPlan teachingPlan = iTeachingPlanDao.findTeachingPlanById(id);
		return iTeachingPlanDao.deleteTeachingPlan(teachingPlan);
		
	}

	@Override
	public boolean saveOrUpdate(TeachingPlan teachingPlan) {
		// TODO Auto-generated method stub
		return false;
	}
	

	@Override
	public List<TeachingPlan> findAllPiece() {
		// TODO Auto-generated method stub
	//	String hql = "from TeachingPlan group by pieceid";
		String hql = "from TeachingPlan";
		List listParam  = new ArrayList();
		return iTeachingPlanDao.findTeachingPlan(hql, listParam);
	}	
	
	
	public ITeachingPlanDao getiTeachingPlanDao() {
		return iTeachingPlanDao;
	}

	public void setiTeachingPlanDao(ITeachingPlanDao iTeachingPlanDao) {
		this.iTeachingPlanDao = iTeachingPlanDao;
	}




}
