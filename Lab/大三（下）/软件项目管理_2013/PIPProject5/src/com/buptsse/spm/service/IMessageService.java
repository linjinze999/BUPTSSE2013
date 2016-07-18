/**
 * 
 */
package com.buptsse.spm.service;

import java.util.List;

import com.buptsse.spm.domain.Course;
import com.buptsse.spm.domain.Message;
import com.buptsse.spm.domain.User;

/**
 * @author libing
 * @date 2015年11月23日 下午4:05:37
 * @description 留言板相关功能接口定义，包括增删改查
 * @modify
 *
 */

public interface IMessageService {
	public Message findMessageById(String id);
	public boolean insertMessage(Message message);
	public List<Message> findAllMessage();
	public boolean deleteMessage(Integer id);
	public boolean saveOrUpdate(Message message);
}
