package com.buptsse.spm.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.buptsse.spm.dao.IBasicInfoDao;
import com.buptsse.spm.domain.BasicInfo;
import com.buptsse.spm.service.IBasicInfoService;

/**
 * 
 * @author yifei xue
 * @date 2015年11月6日 下午10:17:41
 * @description 对基本信息的操作与获取
 * @modify
 * @modifyDate
 */
@Transactional
@Service
public class BasicInfoServiceImpl implements IBasicInfoService{
	@Resource
	private IBasicInfoDao iBasicInfoDao;

	@Override
	public BasicInfo findBasicInfoById(String id) {
		// TODO Auto-generated method stub
		return iBasicInfoDao.findBasicInfoById(new Integer(id));
	}

	@Override
	public BasicInfo findBasicInfoByName(String name) {
		// TODO Auto-generated method stub
		String hql=" from BasicInfo where name=?";
		List listParm = new ArrayList();
		listParm.add(name);
		List<BasicInfo> listBasicInfo = iBasicInfoDao.findBasicInfo(hql, listParm);
		if(listBasicInfo.size()>0){
			return listBasicInfo.get(0);
		}else{
			return null;			
		}		
		//return iBasicInfoDao.findBasicInfoById(new Integer(id));
	}
	
	@Override
	public boolean insertBasicInfo(BasicInfo basicInfo) {
		// TODO Auto-generated method stub
		return iBasicInfoDao.saveBasicInfo(basicInfo);
	}

	@Override
	public List<BasicInfo> findAllBasicInfo() {
		String hql = "from BasicInfo";
		List list = new ArrayList();
		return iBasicInfoDao.findBasicInfo(hql, list);
	}

	@Override
	public boolean deleteBasicInfo(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean saveOrUpdate(BasicInfo basicInfo) {
		// TODO Auto-generated method stub
		return iBasicInfoDao.saveOrUpdateBasicInfo(basicInfo);
		
	}
	
	
/*	@Override
	public BasicInfo enterBasicinfo(String name) {
		// TODO Auto-generated method stub
		BasicInfo basicInfo = new BasicInfo();
		basicInfo = basicInfoDao.findBasicinfo(name);
		if(basicInfo != null){
			System.out.println("教师团队不为空");
			return basicInfo;
		}
		System.out.println("教师团队为空");
		return null;
	}*/

}
