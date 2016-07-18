package com.buptsse.spm.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.buptsse.spm.dao.IExamDao;
import com.buptsse.spm.domain.Course;
import com.buptsse.spm.domain.Exam;


/**
 * @author libing
 * @date 2015年11月16日 下午3:53:50
 * @description
 * @modify	
 * @modifyDate 
 */

@Repository
public class ExamDaoImpl extends BaseDAOImpl<Exam> implements IExamDao {
	private static Logger LOG = Logger.getLogger(ExamDaoImpl.class);


	@Override
	public boolean saveExam(Exam exam) {
		// TODO Auto-generated method stub
		try{
			super.save(exam);
		}catch(Exception e){
			e.printStackTrace();
			LOG.error(e);
			return false;
		}
		return true;		
	}

	@Override
	public boolean updateExam(Exam exam) {
		// TODO Auto-generated method stub
		try{
			super.update(exam);
		}catch(Exception e){
			e.printStackTrace();
			LOG.error(e);
			return false;
		}
		return true;
	}

	@Override
	public boolean deleteExam(Exam exam) {
		// TODO Auto-generated method stub
		try{
			super.delete(exam);
		}catch(Exception e){
			e.printStackTrace();
			LOG.error(e);
			return false;
		}
		return true;
	}



	@Override
	public boolean saveOrUpdateExam(Exam exam) {
		// TODO Auto-generated method stub
		try{
			super.saveOrUpdate(exam);
		}catch(Exception e){
			e.printStackTrace();
			LOG.error(e);
			return false;
		}
		return true;
	}


	@Override
	public List<Exam> findExam(String hql, List param) {
		// TODO Auto-generated method stub
		return super.find(hql, param);
		
	}

	@Override
	public List<Exam> findExam(String hql, Object[] param) {
		// TODO Auto-generated method stub
		return super.find(hql, param);
	}




	@Override
	public Long countExam(String hql, List param) {
		// TODO Auto-generated method stub
		return super.count(hql, param);
	}



	@Override
	public List findPage(String hql, List param, Integer page, Integer rows) {
		// TODO Auto-generated method stub
		
		System.out.println("***开始 分页查询****");
		return super.find(hql, param, page, rows);		
		
	}



}
