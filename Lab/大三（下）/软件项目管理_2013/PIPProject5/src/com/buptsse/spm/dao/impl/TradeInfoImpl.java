package com.buptsse.spm.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.buptsse.spm.dao.ITradeInfoDao;
import com.buptsse.spm.domain.Course;
import com.buptsse.spm.domain.TradeInfo;


/**
 * @author libing
 * @date 2015年11月16日 下午3:53:50
 * @description
 * @modify	
 * @modifyDate 
 */

@Repository
public class TradeInfoImpl extends BaseDAOImpl<TradeInfo> implements ITradeInfoDao {
	private static Logger LOG = Logger.getLogger(TradeInfoImpl.class);


	@Override
	public boolean saveTradeInfo(TradeInfo tradeInfo) {
		// TODO Auto-generated method stub
		try{
			super.save(tradeInfo);
		}catch(Exception e){
			e.printStackTrace();
			LOG.error(e);
			return false;
		}
		return true;		
	}

	@Override
	public boolean updateTradeInfo(TradeInfo tradeInfo) {
		// TODO Auto-generated method stub
		try{
			super.update(tradeInfo);
		}catch(Exception e){
			e.printStackTrace();
			LOG.error(e);
			return false;
		}
		return true;
	}

	@Override
	public boolean deleteTradeInfo(TradeInfo tradeInfo) {
		// TODO Auto-generated method stub
		try{
			super.delete(tradeInfo);
		}catch(Exception e){
			e.printStackTrace();
			LOG.error(e);
			return false;
		}
		return true;
	}



	@Override
	public boolean saveOrUpdateTradeInfo(TradeInfo TradeInfo) {
		// TODO Auto-generated method stub
		try{
			super.saveOrUpdate(TradeInfo);
		}catch(Exception e){
			e.printStackTrace();
			LOG.error(e);
			return false;
		}
		return true;
	}


	@Override
	public List<TradeInfo> findTradeInfo(String hql, List param) {
		// TODO Auto-generated method stub
		return super.find(hql, param);
		
	}

	@Override
	public List<TradeInfo> findTradeInfo(String hql, Object[] param) {
		// TODO Auto-generated method stub
		return super.find(hql, param);
	}




	@Override
	public Long countTradeInfo(String hql, List param) {
		// TODO Auto-generated method stub
		return super.count(hql, param);
	}

	
	@Override
	public TradeInfo findTradeInfoById(Integer id){
		// TODO Auto-generated method stub
		return super.get(TradeInfo.class, id);
		
	}

}
