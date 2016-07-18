package com.buptsse.spm.dao;

import java.util.List;

import com.buptsse.spm.domain.Course;
import com.buptsse.spm.domain.Message;


/**
 * @author libing
 * @param <T>
 * @date 2015年11月23日 下午2:46
 * @description	留言板持久层接口定义
 * @modify
 * @modifyDate 
 */

public interface IMessageDao {
	public boolean saveMessage(Message message);
	public boolean updateMessage(Message message);
	public boolean deleteMessage(Message message);
	public List<Message> findMessage(String hql,Object[] param);
	public List<Message> findMessage(String hql,List param);
	public boolean saveOrUpdateMessage(Message message);
	public Long countMessage(String hql,List param);
	public Message findMessageById(Integer id);
	

}
