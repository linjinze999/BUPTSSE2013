package com.buptsse.spm.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.buptsse.spm.dao.IConfigInfoDao;
import com.buptsse.spm.domain.ConfigInfo;
import com.buptsse.spm.service.IConfigInfoService;


/**
 * @author libing 
 * @date 2015年11月16日 下午3:53
 * @description 留言板的service层实现类定义 
 * @modify
 * @modifyDate 
 */

@Transactional
@Service
public class ConfigInfoServiceImpl implements IConfigInfoService{

	@Resource
	private IConfigInfoDao iConfigInfoDao;



	@Override
	public ConfigInfo findConfigInfoById(String id) {
		// TODO Auto-generated method stub
		return iConfigInfoDao.findConfigInfoById(new Integer(id));
	}

	@Override
	public boolean insertConfigInfo(ConfigInfo configInfo) {
		// TODO Auto-generated method stub
		return iConfigInfoDao.saveConfigInfo(configInfo);
	}

	@Override
	public List<ConfigInfo> findAllConfigInfo() {
		// TODO Auto-generated method stub
		String hql = "from ConfigInfo";
		List list = new ArrayList();
		return iConfigInfoDao.findConfigInfo(hql, list);
	}

	@Override
	public boolean deleteConfigInfo(Integer id) {
		// TODO Auto-generated method stub
		ConfigInfo ConfigInfo = iConfigInfoDao.findConfigInfoById(id);
		return iConfigInfoDao.deleteConfigInfo(ConfigInfo);
	}

	@Override
	public boolean saveOrUpdate(ConfigInfo configInfo) {
		// TODO Auto-generated method stub
		return iConfigInfoDao.saveOrUpdateConfigInfo(configInfo);
		
	}
	

	public IConfigInfoDao getiConfigInfoDao() {
		return iConfigInfoDao;
	}

	public void setiConfigInfoDao(IConfigInfoDao iConfigInfoDao) {
		this.iConfigInfoDao = iConfigInfoDao;
	}

	@Override
	public ConfigInfo findByTypeAndCode(String configType, String configValue) {
		// TODO Auto-generated method stub
		String hql = "from ConfigInfo where validate='1' and configType=? and configCode=? ";
		List list = new ArrayList();
		list.add(configType);
		list.add(configValue);
		List resultList = new ArrayList();
		resultList =  iConfigInfoDao.findConfigInfo(hql, list);	
		if(resultList!=null && resultList.size()>0){
			return (ConfigInfo)resultList.get(0);
		}else{
			return new ConfigInfo();
		}
		
	}



}
