/**
 * 
 */
package com.buptsse.spm.service;

import java.util.List;
import java.util.Map;

import com.buptsse.spm.domain.Course;
import com.buptsse.spm.domain.Exam;
import com.buptsse.spm.domain.User;

/**
 * @author libing
 * @date 2015年11月23日 下午4:05:37
 * @description 在线测试相关功能接口定义，包括增删改查
 * @modify
 *
 */

public interface IExamService {
	public Exam findExamById(String examName,Integer number);
	public boolean insertExam(Exam Exam);
	public List<Exam> findAllExam();
	public boolean deleteExam(String examName,Integer number);
	public boolean saveOrUpdate(Exam Exam);
	public List findPage(Map param,Integer page,Integer rows);
	public Long count(Map param );
	public List findExamByName(String examName);
	public int findExamMaxId(String examName); 
	
}
