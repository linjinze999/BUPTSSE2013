package com.buptsse.spm.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.buptsse.spm.dao.IConfigInfoDao;
import com.buptsse.spm.domain.ConfigInfo;


/**
 * @author libing
 * @date 2015年11月16日 下午3:53:50
 * @description
 * @modify	
 * @modifyDate 
 */

@Repository
public class ConfigInfoDaoImpl extends BaseDAOImpl<ConfigInfo> implements IConfigInfoDao {
	private static Logger LOG = Logger.getLogger(ConfigInfoDaoImpl.class);


	@Override
	public boolean saveConfigInfo(ConfigInfo configInfo) {
		// TODO Auto-generated method stub
		try{
			super.save(configInfo);
		}catch(Exception e){
			e.printStackTrace();
			LOG.error(e);
			return false;
		}
		return true;		
	}

	@Override
	public boolean updateConfigInfo(ConfigInfo configInfo) {
		// TODO Auto-generated method stub
		try{
			super.update(configInfo);
		}catch(Exception e){
			e.printStackTrace();
			LOG.error(e);
			return false;
		}
		return true;
	}

	@Override
	public boolean deleteConfigInfo(ConfigInfo configInfo) {
		// TODO Auto-generated method stub
		try{
			super.delete(configInfo);
		}catch(Exception e){
			e.printStackTrace();
			LOG.error(e);
			return false;
		}
		return true;
	}



	@Override
	public boolean saveOrUpdateConfigInfo(ConfigInfo configInfo) {
		// TODO Auto-generated method stub
		try{
			super.saveOrUpdate(configInfo);
		}catch(Exception e){
			e.printStackTrace();
			LOG.error(e);
			return false;
		}
		return true;
	}


	@Override
	public List<ConfigInfo> findConfigInfo(String hql, List param) {
		// TODO Auto-generated method stub
		return super.find(hql, param);
		
	}

	@Override
	public List<ConfigInfo> findConfigInfo(String hql, Object[] param) {
		// TODO Auto-generated method stub
		return super.find(hql, param);
	}




	@Override
	public Long countConfigInfo(String hql, List param) {
		// TODO Auto-generated method stub
		return super.count(hql, param);
	}

	
	@Override
	public ConfigInfo findConfigInfoById(Integer id){
		// TODO Auto-generated method stub
		return super.get(ConfigInfo.class, id);
		
	}

}
