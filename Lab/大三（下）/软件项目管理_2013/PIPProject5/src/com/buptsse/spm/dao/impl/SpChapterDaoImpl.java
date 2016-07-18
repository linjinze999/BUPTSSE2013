package com.buptsse.spm.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import com.buptsse.spm.dao.ISpChapterDao;
import com.buptsse.spm.domain.SpChapter;


/**
 * @author libing
 * @date 2015年11月28日 下午3:53:50
 * @description
 * @modify	
 * @modifyDate 
 */

@Repository
public class SpChapterDaoImpl extends BaseDAOImpl<SpChapter> implements ISpChapterDao {
	private static Logger LOG = Logger.getLogger(SpChapterDaoImpl.class);


	@Override
	public boolean saveSpChapter(SpChapter spChapter) {
		// TODO Auto-generated method stub
		try{
			super.save(spChapter);
		}catch(Exception e){
			e.printStackTrace();
			LOG.error(e);
			return false;
		}
		return true;		
	}

	@Override
	public boolean updateSpChapter(SpChapter spChapter) {
		// TODO Auto-generated method stub
		try{
			super.update(spChapter);
		}catch(Exception e){
			e.printStackTrace();
			LOG.error(e);
			return false;
		}
		return true;
	}

	@Override
	public boolean deleteSpChapter(SpChapter spChapter) {
		// TODO Auto-generated method stub
		try{
			super.delete(spChapter);
		}catch(Exception e){
			e.printStackTrace();
			LOG.error(e);
			return false;
		}
		return true;
	}



	@Override
	public boolean saveOrUpdateSpChapter(SpChapter spChapter) {
		// TODO Auto-generated method stub
		try{
			super.saveOrUpdate(spChapter);
		}catch(Exception e){
			e.printStackTrace();
			LOG.error(e);
			return false;
		}
		return true;
	}


	@Override
	public List<SpChapter> findSpChapter(String hql, List param) {
		// TODO Auto-generated method stub
		SQLQuery sqlQuery = super.getSessionFactory().getCurrentSession().createSQLQuery(hql);
		
		return super.find(hql, param);
		
	}

	
	@Override
	public List findSpChapterDetial(String hql) {
		// TODO Auto-generated method stub
		SQLQuery sqlQuery = super.getSessionFactory().getCurrentSession().createSQLQuery(hql);
		List list = sqlQuery.list();
		return list;
	}	
	
	
	
	@Override
	public List<SpChapter> findSpChapter(String hql, Object[] param) {
		// TODO Auto-generated method stub
		return super.find(hql, param);
	}




	@Override
	public Long countSpChapter(String hql, List param) {
		// TODO Auto-generated method stub
		return super.count(hql, param);
	}

	
	@Override
	public SpChapter findSpChapterById(Integer id){
		// TODO Auto-generated method stub
		return super.get(SpChapter.class, id);
		
	}

}
