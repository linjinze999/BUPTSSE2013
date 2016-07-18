package com.buptsse.spm.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.buptsse.spm.dao.IMessageDao;
import com.buptsse.spm.domain.Course;
import com.buptsse.spm.domain.Message;


/**
 * @author libing
 * @date 2015年11月16日 下午3:53:50
 * @description
 * @modify	
 * @modifyDate 
 */

@Repository
public class MessageDaoImpl extends BaseDAOImpl<Message> implements IMessageDao {
	private static Logger LOG = Logger.getLogger(MessageDaoImpl.class);


	@Override
	public boolean saveMessage(Message message) {
		// TODO Auto-generated method stub
		try{
			super.save(message);
		}catch(Exception e){
			e.printStackTrace();
			LOG.error(e);
			return false;
		}
		return true;		
	}

	@Override
	public boolean updateMessage(Message message) {
		// TODO Auto-generated method stub
		try{
			super.update(message);
		}catch(Exception e){
			e.printStackTrace();
			LOG.error(e);
			return false;
		}
		return true;
	}

	@Override
	public boolean deleteMessage(Message message) {
		// TODO Auto-generated method stub
		try{
			super.delete(message);
		}catch(Exception e){
			e.printStackTrace();
			LOG.error(e);
			return false;
		}
		return true;
	}



	@Override
	public boolean saveOrUpdateMessage(Message message) {
		// TODO Auto-generated method stub
		try{
			super.saveOrUpdate(message);
		}catch(Exception e){
			e.printStackTrace();
			LOG.error(e);
			return false;
		}
		return true;
	}


	@Override
	public List<Message> findMessage(String hql, List param) {
		// TODO Auto-generated method stub
		return super.find(hql, param);
		
	}

	@Override
	public List<Message> findMessage(String hql, Object[] param) {
		// TODO Auto-generated method stub
		return super.find(hql, param);
	}




	@Override
	public Long countMessage(String hql, List param) {
		// TODO Auto-generated method stub
		return super.count(hql, param);
	}

	
	@Override
	public Message findMessageById(Integer id){
		// TODO Auto-generated method stub
		return super.get(Message.class, id);
		
	}

}
