package com.buptsse.spm.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.buptsse.spm.dao.IDownLoadDao;
import com.buptsse.spm.domain.DownLoad;
import com.buptsse.spm.service.IDownLoadService;


/**
 * @author libing 
 * @date 2015年11月16日 下午3:53
 * @description 文件下载的service层实现类定义 
 * @modify
 * @modifyDate 
 */

@Transactional
@Service
public class DownLoadServiceImpl implements IDownLoadService{

	@Resource
	private IDownLoadDao iDownLoadDao;



	@Override
	public DownLoad findDownLoadById(String id) {
		// TODO Auto-generated method stub
		return iDownLoadDao.findDownLoadById(new Integer(id));
	}

	@Override
	public boolean insertDownLoad(DownLoad downLoad) {
		// TODO Auto-generated method stub
		return iDownLoadDao.saveDownLoad(downLoad);
	}

	@Override
	public List<DownLoad> findAllDownLoad() {
		// TODO Auto-generated method stub
		String hql = "from DownLoad";
		List list = new ArrayList();
		return iDownLoadDao.findDownLoad(hql, list);
	}

	@Override
	public boolean deleteDownLoad(String id) {
		// TODO Auto-generated method stub
		DownLoad downLoad = findDownLoadById(id);
		return iDownLoadDao.deleteDownLoad(downLoad);
	}

	@Override
	public boolean saveOrUpdate(DownLoad downLoad) {
		// TODO Auto-generated method stub
		return false;
	}
	

	public IDownLoadDao getiDownLoadDao() {
		return iDownLoadDao;
	}

	public void setiDownLoadDao(IDownLoadDao iDownLoadDao) {
		this.iDownLoadDao = iDownLoadDao;
	}



}
