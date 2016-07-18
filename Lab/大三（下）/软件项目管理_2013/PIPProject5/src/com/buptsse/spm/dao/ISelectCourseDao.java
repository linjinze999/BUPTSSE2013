package com.buptsse.spm.dao;

import java.util.List;

import com.buptsse.spm.domain.Course;


/**
 * @author xinyu han
 * @param <T>
 * @date 2015年11月01日 下午2:46
 * @description	选课持久层接口定义，包括课程信息的增加，修改，保存，删除，以及根据条件查询
 * @modify
 * @modifyDate 
 */

public interface ISelectCourseDao {
	public boolean insertCourse(Course course);
	public Course findCourse(String studentId);
	public boolean updateCourse(Course course);
	public boolean deleteCourse(Course course);
	public List<Course> findAllCourse();
	public List findPage(String hql,List param , Integer page,Integer rows);
	public Long countCourse(String hql, List param);
	public boolean saveOrUpdateCourse(Course course);
	//public void changeStatus(String studentId,int pre_status,int new_status);
	
	//public List<Course> search(String choose);
}
