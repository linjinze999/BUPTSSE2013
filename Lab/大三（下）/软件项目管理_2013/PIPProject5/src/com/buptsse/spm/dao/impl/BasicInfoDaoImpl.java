package com.buptsse.spm.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import com.buptsse.spm.dao.IBasicInfoDao;
import com.buptsse.spm.domain.BasicInfo;

/**
 * 
 * @author yifei Xue
 * @date 2015年11月6日 下午9:56:21
 * @description 对基本信息持久层操作的实现
 * @modify
 * @modifyDate
 */

@Repository
public class BasicInfoDaoImpl extends BaseDAOImpl<BasicInfo> implements IBasicInfoDao{
	private static Logger LOG = Logger.getLogger(BasicInfoDaoImpl.class);


	@Override
	public boolean saveBasicInfo(BasicInfo basicInfo) {
		// TODO Auto-generated method stub
		try{
			super.save(basicInfo);
		}catch(Exception e){
			e.printStackTrace();
			LOG.error(e);
			return false;
		}
		return true;		
	}

	@Override
	public boolean updateBasicInfo(BasicInfo basicInfo) {
		// TODO Auto-generated method stub
		try{
			super.update(basicInfo);
		}catch(Exception e){
			e.printStackTrace();
			LOG.error(e);
			return false;
		}
		return true;
	}

	@Override
	public boolean deleteBasicInfo(BasicInfo basicInfo) {
		// TODO Auto-generated method stub
		try{
			super.delete(basicInfo);
		}catch(Exception e){
			e.printStackTrace();
			LOG.error(e);
			return false;
		}
		return true;
	}



	@Override
	public boolean saveOrUpdateBasicInfo(BasicInfo basicInfo) {
		// TODO Auto-generated method stub
		try{
			super.saveOrUpdate(basicInfo);
		}catch(Exception e){
			e.printStackTrace();
			LOG.error(e);
			return false;
		}
		return true;
	}


	@Override
	public List<BasicInfo> findBasicInfo(String hql, List param) {
		// TODO Auto-generated method stub
		return super.find(hql, param);
		
	}

	@Override
	public List<BasicInfo> findBasicInfo(String hql, Object[] param) {
		// TODO Auto-generated method stub
		return super.find(hql, param);
	}




	@Override
	public Long countBasicInfo(String hql, List param) {
		// TODO Auto-generated method stub
		return super.count(hql, param);
	}

	
	@Override
	public BasicInfo findBasicInfoById(Integer id){
		// TODO Auto-generated method stub
		return super.get(BasicInfo.class, id);
		
	}

/*	@Override
	public void modifyBasicinfo(BasicInfo basicInfo) {
		// TODO Auto-generated method stub
		
	}*/

/*	@Override
	public BasicInfo findBasicinfo(String name) {
		// TODO Auto-generated method stub
		BasicInfo tempBasicInfo = new BasicInfo();
		tempBasicInfo.setName(name);
		try{
			List<BasicInfo> list = new ArrayList<BasicInfo>();
			list = super.find("from BasicInfo");
			for(int i = 0;i < list.size();i++){
				if(tempBasicInfo.getName().equals(list.get(i).getName())){
					return list.get(i);
			}
		}			
		}catch(Exception e){
			e.printStackTrace();
			LOG.error(e);
			return null;
		}
		return null;
	}*/
}
