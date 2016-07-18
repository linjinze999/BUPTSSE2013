package com.buptsse.spm.dao;

import java.util.List;

import com.buptsse.spm.domain.BasicInfo;
import com.buptsse.spm.domain.BasicInfo;

/**
 * 
 * @author yifei Xue
 * @date 2015年11月6日 下午9:55:17
 * @description 基本信息持久层接口定义，包括对其的修改，查找等
 * @modify
 * @modifyDate
 */

public interface IBasicInfoDao {
	//public void modifyBasicinfo(BasicInfo basicInfo);
	//public BasicInfo findBasicinfo(String name);
	
	public boolean saveBasicInfo(BasicInfo basicInfo);
	public boolean updateBasicInfo(BasicInfo basicInfo);
	public boolean deleteBasicInfo(BasicInfo basicInfo);
	public List<BasicInfo> findBasicInfo(String hql,Object[] param);
	public List<BasicInfo> findBasicInfo(String hql,List param);
	public boolean saveOrUpdateBasicInfo(BasicInfo basicInfo);
	public Long countBasicInfo(String hql,List param);
	public BasicInfo findBasicInfoById(Integer id);	
	
}
