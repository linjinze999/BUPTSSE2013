package com.buptsse.spm.dao;

import java.util.List;
import com.buptsse.spm.domain.UserAll;

/**
 * @author Jinze Lin
 * @date 2016年04月13日 上午8:27
 * @description	用户信息持久层接口定义，包括用户增加，保存，查询
 * @modify
 * @modifyDate 
 */

public interface UserAllDao {
	public List<UserAll> searchUserAllByUserAll(UserAll userAll);
	public UserAll findUserAll(UserAll userAll);
	public UserAll findUserAllById(int idUserAll);
	public boolean insertUserAll(UserAll userAll);
	public List<UserAll> searchUser(List<Object> choose);
	public boolean deleteUserAll(UserAll userAll);
	public boolean addUserAll(UserAll userAll);
	public boolean updateUserAll(UserAll userAll);
	public List findPage(String hql,List param , Integer page,Integer rows);	
	public Long countUser(String hql, List param);
}
