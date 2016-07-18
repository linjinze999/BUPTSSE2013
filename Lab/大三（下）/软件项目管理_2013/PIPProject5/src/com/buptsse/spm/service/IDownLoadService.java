/**
 * 
 */
package com.buptsse.spm.service;

import java.util.List;

import com.buptsse.spm.domain.Course;
import com.buptsse.spm.domain.DownLoad;
import com.buptsse.spm.domain.User;

/**
 * @author libing
 * @date 2015年11月23日 下午4:05:37
 * @description 文件下载相关功能接口定义，包括增删改查
 * @modify
 *
 */

public interface IDownLoadService {
	public DownLoad findDownLoadById(String id);
	public boolean insertDownLoad(DownLoad downLoad);
	public List<DownLoad> findAllDownLoad();
	public boolean deleteDownLoad(String id);
	public boolean saveOrUpdate(DownLoad downLoad);
}
