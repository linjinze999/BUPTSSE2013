package com.buptsse.spm.dao;

import java.util.List;

import com.buptsse.spm.domain.Course;
import com.buptsse.spm.domain.TeachingPlan;


/**
 * @author libing
 * @param <T>
 * @date 2015年11月23日 下午2:46
 * @description	教案持久层接口定义
 * @modify
 * @modifyDate 
 */

public interface ITeachingPlanDao {
	public boolean saveTeachingPlan(TeachingPlan teachingPlan);
	public boolean updateTeachingPlan(TeachingPlan teachingPlan);
	public boolean deleteTeachingPlan(TeachingPlan teachingPlan);
	public List<TeachingPlan> findTeachingPlan(String hql,Object[] param);
	public List<TeachingPlan> findTeachingPlan(String hql,List param);
	public boolean saveOrUpdateTeachingPlan(TeachingPlan teachingPlan);
	public Long countTeachingPlan(String hql,List param);
	public TeachingPlan findTeachingPlanById(Integer id);
	

}
