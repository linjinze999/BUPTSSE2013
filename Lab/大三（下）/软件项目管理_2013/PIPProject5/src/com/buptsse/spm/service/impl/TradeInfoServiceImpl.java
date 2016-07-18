package com.buptsse.spm.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.buptsse.spm.dao.ITradeInfoDao;
import com.buptsse.spm.domain.TradeInfo;
import com.buptsse.spm.service.ITradeInfoService;


/**
 * @author libing 
 * @date 2015年11月16日 下午3:53
 * @description 留言板的service层实现类定义 
 * @modify
 * @modifyDate 
 */

@Transactional
@Service
public class TradeInfoServiceImpl implements ITradeInfoService{

	@Resource
	private ITradeInfoDao iTradeInfoDao;



	@Override
	public TradeInfo findTradeInfoById(String id) {
		// TODO Auto-generated method stub
		return iTradeInfoDao.findTradeInfoById(new Integer(id));
	}

	@Override
	public boolean insertTradeInfo(TradeInfo tradeInfo) {
		// TODO Auto-generated method stub
		return iTradeInfoDao.saveTradeInfo(tradeInfo);
	}

	@Override
	public List<TradeInfo> findAllTradeInfo() {
		// TODO Auto-generated method stub
		String hql = "from TradeInfo";
		List list = new ArrayList();
		return iTradeInfoDao.findTradeInfo(hql, list);
	}

	@Override
	public boolean deleteTradeInfo(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean saveOrUpdate(TradeInfo TradeInfo) {
		// TODO Auto-generated method stub
		return false;
	}
	

	public ITradeInfoDao getiTradeInfoDao() {
		return iTradeInfoDao;
	}

	public void setiTradeInfoDao(ITradeInfoDao iTradeInfoDao) {
		this.iTradeInfoDao = iTradeInfoDao;
	}



}
