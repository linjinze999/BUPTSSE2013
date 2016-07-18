package com.buptsse.spm.dao;

import java.util.List;

import com.buptsse.spm.domain.Course;
import com.buptsse.spm.domain.ConfigInfo;


/**
 * @author libing
 * @param <T>
 * @date 2015年11月23日 下午2:46
 * @description	留言板持久层接口定义
 * @modify
 * @modifyDate 
 */

public interface IConfigInfoDao {
	public boolean saveConfigInfo(ConfigInfo ConfigInfo);
	public boolean updateConfigInfo(ConfigInfo ConfigInfo);
	public boolean deleteConfigInfo(ConfigInfo ConfigInfo);
	public List<ConfigInfo> findConfigInfo(String hql,Object[] param);
	public List<ConfigInfo> findConfigInfo(String hql,List param);
	public boolean saveOrUpdateConfigInfo(ConfigInfo ConfigInfo);
	public Long countConfigInfo(String hql,List param);
	public ConfigInfo findConfigInfoById(Integer id);
	

}
