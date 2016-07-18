package com.buptsse.spm.dao;

import java.util.List;

import com.buptsse.spm.domain.Course;
import com.buptsse.spm.domain.TradeInfo;


/**
 * @author libing
 * @param <T>
 * @date 2015年11月23日 下午2:46
 * @description	行业信息持久层接口定义
 * @modify
 * @modifyDate 
 */

public interface ITradeInfoDao {
	public boolean saveTradeInfo(TradeInfo tradeInfo);
	public boolean updateTradeInfo(TradeInfo tradeInfo);
	public boolean deleteTradeInfo(TradeInfo tradeInfo);
	public List<TradeInfo> findTradeInfo(String hql,Object[] param);
	public List<TradeInfo> findTradeInfo(String hql,List param);
	public boolean saveOrUpdateTradeInfo(TradeInfo tradeInfo);
	public Long countTradeInfo(String hql,List param);
	public TradeInfo findTradeInfoById(Integer id);
	

}
