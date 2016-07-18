package com.buptsse.spm.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.buptsse.spm.dao.IMessageDao;
import com.buptsse.spm.domain.Message;
import com.buptsse.spm.service.IMessageService;


/**
 * @author libing 
 * @date 2015年11月16日 下午3:53
 * @description 留言板的service层实现类定义 
 * @modify
 * @modifyDate 
 */

@Transactional
@Service
public class MessageServiceImpl implements IMessageService{

	@Resource
	private IMessageDao iMessageDao;



	@Override
	public Message findMessageById(String id) {
		// TODO Auto-generated method stub
		return iMessageDao.findMessageById(new Integer(id));
	}

	@Override
	public boolean insertMessage(Message message) {
		// TODO Auto-generated method stub
		return iMessageDao.saveMessage(message);
	}

	@Override
	public List<Message> findAllMessage() {
		// TODO Auto-generated method stub
		String hql = "from Message";
		List list = new ArrayList();
		return iMessageDao.findMessage(hql, list);
	}

	@Override
	public boolean deleteMessage(Integer id) {
		// TODO Auto-generated method stub
		Message message = iMessageDao.findMessageById(id);
		return iMessageDao.deleteMessage(message);
	}

	@Override
	public boolean saveOrUpdate(Message message) {
		// TODO Auto-generated method stub
		return false;
	}
	

	public IMessageDao getiMessageDao() {
		return iMessageDao;
	}

	public void setiMessageDao(IMessageDao iMessageDao) {
		this.iMessageDao = iMessageDao;
	}



}
