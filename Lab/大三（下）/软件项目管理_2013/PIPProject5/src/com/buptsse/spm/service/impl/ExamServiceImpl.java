package com.buptsse.spm.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.buptsse.spm.dao.IExamDao;
import com.buptsse.spm.domain.Exam;
import com.buptsse.spm.service.IExamService;


/**
 * @author libing 
 * @date 2015年11月16日 下午3:53
 * @description 在线测试的service层实现类定义 
 * @modify
 * @modifyDate 
 */

@Transactional
@Service
public class ExamServiceImpl implements IExamService{

	@Resource
	private IExamDao iExamDao;


	@Override
	public Exam findExamById(String examName,Integer number) {
		// TODO Auto-generated method stub
		String hql  = "from Exam where examName = ? and number= ? ";
		List listParam = new ArrayList();
		listParam.add(examName);
		listParam.add(number);
		List<Exam> listExam = iExamDao.findExam(hql, listParam);
		if(listExam.size()>0){
			return listExam.get(0);
		}else{
			return null;			
		}
	}
	
	
	@Override
	public List findExamByName(String examName) {
		// TODO Auto-generated method stub
		String hql  = "from Exam where examName = ? order by number ";
		List listParam = new ArrayList();
		listParam.add(examName);
		List<Exam> listExam = iExamDao.findExam(hql, listParam);
		return listExam;
	}	
	
	@Override
	public int findExamMaxId(String examName) {
		// TODO Auto-generated method stub
		String hql  = "from Exam where examName = ? order by number desc";
		List listParam = new ArrayList();
		listParam.add(examName);
		List<Exam> listExam = iExamDao.findExam(hql, listParam);
		if(listExam.size()>0){
			return listExam.get(0).getNumber();
		}else{
			return 0;			
		}
	}

	
	@Override
	public boolean insertExam(Exam Exam) {
		// TODO Auto-generated method stub
		return iExamDao.saveExam(Exam);
	}

	@Override
	public List<Exam> findAllExam() {
		// TODO Auto-generated method stub
		String hql = "select distinct examName from Exam";
		List list = new ArrayList();
		return iExamDao.findExam(hql, list);
	}

	@Override
	public boolean deleteExam(String examName,Integer number) {
		// TODO Auto-generated method stub
		Exam exam = this.findExamById(examName, number);
		return iExamDao.deleteExam(exam);
	}

	@Override
	public boolean saveOrUpdate(Exam exam) {
		// TODO Auto-generated method stub
		return iExamDao.saveOrUpdateExam(exam);
		
	}
	

	public IExamDao getiExamDao() {
		return iExamDao;
	}

	public void setiExamDao(IExamDao iExamDao) {
		this.iExamDao = iExamDao;
	}

	@Override
	public List findPage(Map param, Integer page, Integer rows) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		System.out.println("$$$$$$$$进入service**查询");
		String hql = "from Exam where 1=1 ";
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
		return iExamDao.findPage(hql,paramList, page, rows);
		//return iSelectCourseDao.findAllCourse();		
		
	}

	@Override
	public Long count(Map param) {
		// TODO Auto-generated method stub
		
		// TODO Auto-generated method stub
		String hql = "select count(*) from Exam where 1=1";
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
		
		return iExamDao.countExam(hql, paramList);		
		
	}



}
