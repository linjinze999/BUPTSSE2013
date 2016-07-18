package com.buptsse.spm.dao;
import java.util.List;
import com.buptsse.spm.domain.User;

/**
 * @author zhengzhi ren
 * @date 2015年10月11日 下午2:46
 * @description	用户信息持久层接口定义，包括用户增加，保存，查询
 * @modify
 * @modifyDate 
 */

public interface IUserDao {
	public User findUser(User user);
	public User findUserById(String id);
	public boolean insertUser(User user);
	public List<User> searchUser(List<Object> choose);
	public boolean deleteUser(User user);
	public boolean addUser(User user);
	public boolean updateUser(User user);
	public List findPage(String hql,List param , Integer page,Integer rows);	
	public Long countUser(String hql, List param);
}
