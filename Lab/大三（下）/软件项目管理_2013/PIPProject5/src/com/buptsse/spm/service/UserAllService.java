package com.buptsse.spm.service;

import java.util.List;
import java.util.Map;

import com.buptsse.spm.domain.UserAll;

/**
 * @author Jinze Lin
 * @date 2016年04月13日 上午9:26
 * @description 用户相关功能接口定义，包括增删改查
 * @modify
 *
 */

public interface UserAllService {
	public UserAll findUserAllByAccount(String account);
	public UserAll findUserAll(String account,String password);
	public UserAll findUserAllById(int id);
	public boolean insertUserAll(UserAll userAll);
	public List<UserAll> searchUserAll(String choose);
	public List<UserAll> searchUserAllByUser(UserAll userAll);
	public boolean deleteUserAll(UserAll userAll);
	public boolean addUserAll(UserAll userAll);
	public boolean updateUserAll(UserAll userAll);	
	public List findPage(Map param,Integer page,Integer rows);
	public Long count(Map param );
	public int updateUserAllScore(UserAll userAll);
}
