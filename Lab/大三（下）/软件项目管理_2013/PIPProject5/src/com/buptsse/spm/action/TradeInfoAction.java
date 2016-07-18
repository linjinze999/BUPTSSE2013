package com.buptsse.spm.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import com.buptsse.spm.domain.TradeInfo;
import com.buptsse.spm.service.ITradeInfoService;

import com.opensymphony.xwork2.ActionSupport;


/**
 * @author liibng
 * @date 2015年11月23日 下午4:17
 * @description 实现行业信息相关功能 
 * @modify
 * @modifyDate 
 */
public class TradeInfoAction extends ActionSupport{

	private static final long serialVersionUID = 1L;
	private static Logger LOG = LoggerFactory.getLogger(TradeInfoAction.class);
	@Resource
	private ITradeInfoService tradeInfoService;
	
	public List tradeInfoList = new ArrayList();
	
	public TradeInfo tradeInfo;
	
	public String idTradeInfo="";




	/**
	 * 查找行业信息列表
	 * @return
	 * @throws Exception
	 */
	public String findTradeInfoList() throws Exception{
		
		tradeInfoList = tradeInfoService.findAllTradeInfo();
		
		return "success";
	}	
	
	
	/**
	 * 根据id查找行业信息
	 * @return
	 * @throws Exception
	 */
	public String findTradeInfo() throws Exception{
		
		tradeInfo = tradeInfoService.findTradeInfoById(idTradeInfo);
		
		return "success";
	}		
	
	
	/**
	 * 增加行业信息
	 * @return
	 * @throws Exception
	 */
	public String insertTradeInfo() throws Exception{
		String msg = "";
		
		boolean flag = tradeInfoService.insertTradeInfo(tradeInfo);
		if(flag){
			msg = "1";//表示保存成功
		}else{
			msg = "2";//表示保存失败
		}
		try {
			ServletActionContext.getResponse().getWriter().write(msg);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
		return null;
	}		
	



	
	
	public ITradeInfoService getTradeInfoService() {
		return tradeInfoService;
	}


	public void setTradeInfoService(ITradeInfoService tradeInfoService) {
		this.tradeInfoService = tradeInfoService;
	}



	public List getTradeInfoList() {
		return tradeInfoList;
	}


	public void setTradeInfoList(List tradeInfoList) {
		this.tradeInfoList = tradeInfoList;
	}


	public TradeInfo getTradeInfo() {
		return tradeInfo;
	}

	public void setTradeInfo(TradeInfo tradeInfo) {
		this.tradeInfo = tradeInfo;
	}
	

	public String getIdTradeInfo() {
		return idTradeInfo;
	}


	public void setIdTradeInfo(String idTradeInfo) {
		this.idTradeInfo = idTradeInfo;
	}
	
		
}
