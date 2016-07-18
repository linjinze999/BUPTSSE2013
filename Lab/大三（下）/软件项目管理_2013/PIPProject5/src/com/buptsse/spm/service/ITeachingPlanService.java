/**
 * 
 */
package com.buptsse.spm.service;

import java.util.List;

import com.buptsse.spm.domain.Course;
import com.buptsse.spm.domain.TeachingPlan;
import com.buptsse.spm.domain.User;

/**
 * @author libing
 * @date 2015年11月23日 下午4:05:37
 * @description 教案相关功能接口定义，包括增删改查
 * @modify
 *
 */

public interface ITeachingPlanService {
	public TeachingPlan findTeachingPlanById(String id);
	public boolean insertTeachingPlan(TeachingPlan teachingPlan);
	public List<TeachingPlan> findAllTeachingPlan();
	public boolean deleteTeachingPlan(Integer id);
	public boolean saveOrUpdate(TeachingPlan teachingPlan);
	public List<TeachingPlan> findAllPiece();
	
}
