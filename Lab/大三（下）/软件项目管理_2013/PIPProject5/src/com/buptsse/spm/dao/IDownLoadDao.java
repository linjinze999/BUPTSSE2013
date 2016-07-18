package com.buptsse.spm.dao;

import java.util.List;

import com.buptsse.spm.domain.Course;
import com.buptsse.spm.domain.DownLoad;


/**
 * @author libing
 * @param <T>
 * @date 2015年11月23日 下午2:46
 * @description	文件下载持久层接口定义
 * @modify
 * @modifyDate 
 */

public interface IDownLoadDao {
	public boolean saveDownLoad(DownLoad downLoad);
	public boolean updateDownLoad(DownLoad downLoad);
	public boolean deleteDownLoad(DownLoad downLoad);
	public List<DownLoad> findDownLoad(String hql,Object[] param);
	public List<DownLoad> findDownLoad(String hql,List param);
	public boolean saveOrUpdateDownLoad(DownLoad downLoad);
	public Long countDownLoad(String hql,List param);
	public DownLoad findDownLoadById(Integer id);
	

}
