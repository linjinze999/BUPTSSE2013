/**
 * 
 */
package com.buptsse.spm.service;

import java.util.List;

import com.buptsse.spm.domain.TradeInfo;

/**
 * @author libing
 * @date 2015年11月23日 下午4:05:37
 * @description 行业信息相关功能接口定义，包括增删改查
 * @modify
 *
 */

public interface ITradeInfoService {
	public TradeInfo findTradeInfoById(String id);
	public boolean insertTradeInfo(TradeInfo TradeInfo);
	public List<TradeInfo> findAllTradeInfo();
	public boolean deleteTradeInfo(String id);
	public boolean saveOrUpdate(TradeInfo TradeInfo);
}
