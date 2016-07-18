package com.buptsse.spm.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.buptsse.spm.dao.IDownLoadDao;
import com.buptsse.spm.domain.Course;
import com.buptsse.spm.domain.DownLoad;


/**
 * @author libing
 * @date 2015年11月16日 下午3:53:50
 * @description
 * @modify	
 * @modifyDate 
 */

@Repository
public class DownLoadDaoImpl extends BaseDAOImpl<DownLoad> implements IDownLoadDao {
	private static Logger LOG = Logger.getLogger(DownLoadDaoImpl.class);


	@Override
	public boolean saveDownLoad(DownLoad downLoad) {
		// TODO Auto-generated method stub
		try{
			super.save(downLoad);
		}catch(Exception e){
			e.printStackTrace();
			LOG.error(e);
			return false;
		}
		return true;		
	}

	@Override
	public boolean updateDownLoad(DownLoad downLoad) {
		// TODO Auto-generated method stub
		try{
			super.update(downLoad);
		}catch(Exception e){
			e.printStackTrace();
			LOG.error(e);
			return false;
		}
		return true;
	}

	@Override
	public boolean deleteDownLoad(DownLoad downLoad) {
		// TODO Auto-generated method stub
		try{
			super.delete(downLoad);
		}catch(Exception e){
			e.printStackTrace();
			LOG.error(e);
			return false;
		}
		return true;
	}



	@Override
	public boolean saveOrUpdateDownLoad(DownLoad downLoad) {
		// TODO Auto-generated method stub
		try{
			super.saveOrUpdate(downLoad);
		}catch(Exception e){
			e.printStackTrace();
			LOG.error(e);
			return false;
		}
		return true;
	}


	@Override
	public List<DownLoad> findDownLoad(String hql, List param) {
		// TODO Auto-generated method stub
		return super.find(hql, param);
		
	}

	@Override
	public List<DownLoad> findDownLoad(String hql, Object[] param) {
		// TODO Auto-generated method stub
		return super.find(hql, param);
	}




	@Override
	public Long countDownLoad(String hql, List param) {
		// TODO Auto-generated method stub
		return super.count(hql, param);
	}

	
	@Override
	public DownLoad findDownLoadById(Integer id){
		// TODO Auto-generated method stub
		return super.get(DownLoad.class, id);
		
	}

}
