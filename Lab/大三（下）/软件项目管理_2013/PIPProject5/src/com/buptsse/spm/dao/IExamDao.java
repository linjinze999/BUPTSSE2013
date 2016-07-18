package com.buptsse.spm.dao;

import java.util.List;

import com.buptsse.spm.domain.Course;
import com.buptsse.spm.domain.Exam;


/**
 * @author libing
 * @param <T>
 * @date 2015年11月23日 下午2:46
 * @description	在线测试持久层接口定义
 * @modify
 * @modifyDate 
 */

public interface IExamDao {
	public boolean saveExam(Exam exam);
	public boolean updateExam(Exam exam);
	public boolean deleteExam(Exam exam);
	public List<Exam> findExam(String hql,Object[] param);
	public List<Exam> findExam(String hql,List param);
	public boolean saveOrUpdateExam(Exam exam);
	public Long countExam(String hql,List param);
	public List findPage(String hql,List param , Integer page,Integer rows);	

}
