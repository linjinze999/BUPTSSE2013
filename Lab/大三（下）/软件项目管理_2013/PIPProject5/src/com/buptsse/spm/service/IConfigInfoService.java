/**
 * 
 */
package com.buptsse.spm.service;

import java.util.List;

import com.buptsse.spm.domain.ConfigInfo;

/**
 * @author libing
 * @date 2015年11月23日 下午4:05:37
 * @description 留言板相关功能接口定义，包括增删改查
 * @modify
 *
 */

public interface IConfigInfoService {
	public ConfigInfo findConfigInfoById(String id);
	public boolean insertConfigInfo(ConfigInfo configInfo);
	public List<ConfigInfo> findAllConfigInfo();
	public boolean deleteConfigInfo(Integer id);
	public boolean saveOrUpdate(ConfigInfo configInfo);
	public ConfigInfo findByTypeAndCode(String configType,String configValue);
}
