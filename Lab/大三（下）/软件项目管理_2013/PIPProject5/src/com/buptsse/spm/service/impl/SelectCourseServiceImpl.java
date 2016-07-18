package com.buptsse.spm.service.impl;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;


import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.buptsse.spm.dao.ISelectCourseDao;
import com.buptsse.spm.domain.Course;
import com.buptsse.spm.service.ISelectCourseService;


/**
 * @author xinyu han
 * @date 2015年11月01日 下午3:53
 * @description 选课的service层接口定义 
 * @modify
 * @modifyDate 
 */

@Transactional
@Service
public class SelectCourseServiceImpl implements ISelectCourseService{

	@Resource
	private ISelectCourseDao iSelectCourseDao;
	
	/*
	 * (non-Javadoc)
	 * @see com.buptsse.service.ISelectCourseService#findCourse(java.lang.String)
	 */
	@Override
	public Course findCourse(String studentId) {
		
		// TODO Auto-generated method stub
		Course course=new Course();
		course = iSelectCourseDao.findCourse(studentId);
		//if(course != null){
		//	return course;
		//}
		return course;
	}

	@Override
	public boolean insertCourse(Course course) {
		// TODO Auto-generated method stub
		try{
			iSelectCourseDao.insertCourse(course);
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}		
		
		return true;
	}

	
	public boolean saveOrUpdate(Course course) {
		// TODO Auto-generated method stub
		return iSelectCourseDao.saveOrUpdateCourse(course);
	}	
	
	@Override
	public boolean deleteCourse(String studentId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateCourse(Course course) {
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * (non-Javadoc)
	 * @see com.buptsse.service.ISelectCourseService#findAllCourse()
	 */
	@Override
	public List<Course> findAllCourse() {
		// TODO Auto-generated method stub
		return iSelectCourseDao.findAllCourse();
	}

	@Override
	public List findPage(Map param,Integer page, Integer rows) {
		// TODO Auto-generated method stub
		System.out.println("$$$$$$$$进入service**查询");
		String hql = "from Course where 1=1 ";
		List paramList = new ArrayList();
		Iterator iter = param.keySet().iterator();
		
		while (iter.hasNext()){
			String key = (String) iter.next();
			String value = (String) param.get(key);
			System.out.println("&&&&&value&&&&:"+value);
			if(!"".equals(value)){
				hql+="and "+key+"=? ";
				paramList.add(value);				
			}
		}		
		
		System.out.println("进入查询的Service:"+hql);
		return iSelectCourseDao.findPage(hql,paramList, page, rows);
		//return iSelectCourseDao.findAllCourse();
	}	
	
	/*
	 * (non-Javadoc)
	 * @see com.buptsse.service.ISelectCourseService#changeStatus(java.lang.String, int, int)
	 */
	@Override
	public boolean changeStatus(String studentId, int newStatus) {
		// TODO Auto-generated method stub
		Course course = new Course();
		course = iSelectCourseDao.findCourse(studentId);
		//if(preStutus == (newStatus - 1)){
			switch (newStatus) {
			case 1:
				//course.setStatus("待确认");
				course.setStatus("1");
				iSelectCourseDao.updateCourse(course);
				break;
			case 2:
				//course.setStatus("选课成功");
				course.setStatus("2");
				iSelectCourseDao.updateCourse(course);
				break;
			case 3:
				//course.setStatus("未选");
				course.setStatus("3");
				iSelectCourseDao.updateCourse(course);
				break;
			case 4:
				iSelectCourseDao.deleteCourse(course);
				break;				
			default:
				break;
			}
			return true;
		//}else{
		//	return false;
		//}
	}

	@Override
	public boolean savaCourse(Course course) {
		// TODO Auto-generated method stub
		
		return false;
	}

	@Override
	public Long count(Map param) {
		// TODO Auto-generated method stub
		String hql = "select count(*) from Course where 1=1 ";
		List paramList = new ArrayList();
		Iterator iter = param.keySet().iterator();
		
		while (iter.hasNext()){
			String key = (String) iter.next();
			String value = (String) param.get(key);
			System.out.println("&&&&&value&&&&:"+value);
			if(!"".equals(value)){
				hql+="and "+key+"=? ";
				paramList.add(value);				
			}
		}		
		
		return iSelectCourseDao.countCourse(hql, paramList);
	}


}
