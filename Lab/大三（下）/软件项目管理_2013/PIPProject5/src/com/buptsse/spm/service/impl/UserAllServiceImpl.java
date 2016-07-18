package com.buptsse.spm.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.buptsse.spm.dao.UserAllDao;
import com.buptsse.spm.domain.UserAll;
import com.buptsse.spm.service.UserAllService;

/**
 * @author Jinze Lin
 * @date 2016年04月13日 上午9:35
 * @description
 */


@Transactional
@Service
public class UserAllServiceImpl implements UserAllService{
	@Resource
	private UserAllDao userAllDao;

	/* (non-Javadoc)
	 * @see com.buptsse.spm.service.UserAllService#findUserAll(java.lang.String, java.lang.String)
	 */
	@Override
	public UserAll findUserAll(String account, String password) {
		// TODO Auto-generated method stub
		UserAll user = new UserAll();
		user.setAccountUserAll(account);
		user.setPasswordUserAll(password);
		user = userAllDao.findUserAll(user);
		if(user==null){
			return null;
		}else if(user.getPasswordUserAll().compareTo(password)!=0){
			UserAll userr = new UserAll();
			userr.setIdUserAll(-1);
			userr.setAccountUserAll(user.getAccountUserAll());
			userr.setPasswordUserAll(password);
			userr.setRoleUserAll(user.getRoleUserAll());
			return userr;
		}else{
			return user;
		}
	}

	/* (non-Javadoc)
	 * @see com.buptsse.spm.service.UserAllService#findUserAllById(java.lang.Integer)
	 */
	@Override
	public UserAll findUserAllById(int id) {
		// TODO Auto-generated method stub
		return userAllDao.findUserAllById(id);
	}

	/* (non-Javadoc)
	 * @see com.buptsse.spm.service.UserAllService#insertUserAll(com.buptsse.spm.domain.UserAll)
	 */
	@Override
	public boolean insertUserAll(UserAll userAll) {
		// TODO Auto-generated method stub
		try{
			return userAllDao.insertUserAll(userAll);
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see com.buptsse.spm.service.UserAllService#searchUserAll(java.lang.String)
	 */
	@Override
	public List<UserAll> searchUserAll(String choose) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.buptsse.spm.service.UserAllService#deleteUserAll(java.lang.Integer)
	 */
	@Override
	public boolean deleteUserAll(UserAll userAll) {
		// TODO Auto-generated method stub
		try{
			return userAllDao.deleteUserAll(userAll);
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see com.buptsse.spm.service.UserAllService#addUserAll(com.buptsse.spm.domain.UserAll)
	 */
	@Override
	public boolean addUserAll(UserAll userAll) {
		// TODO Auto-generated method stub
		try{
			return userAllDao.insertUserAll(userAll);
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see com.buptsse.spm.service.UserAllService#updateUserAll(com.buptsse.spm.domain.UserAll)
	 */
	@Override
	public boolean updateUserAll(UserAll userAll) {
		// TODO Auto-generated method stub
		try{
			return this.userAllDao.updateUserAll(userAll);
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see com.buptsse.spm.service.UserAllService#findPage(java.util.Map,java.lang.Integer,java.lang.Integer)
	 */
	@Override
	public List findPage(Map param, Integer page, Integer rows) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.buptsse.spm.service.UserAllService#count(java.util.Map)
	 */
	@Override
	public Long count(Map param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserAll> searchUserAllByUser(UserAll userAll) {
		// TODO Auto-generated method stub
		return userAllDao.searchUserAllByUserAll(userAll);
	}

	@Override
	public UserAll findUserAllByAccount(String account) {
		// TODO Auto-generated method stub
		UserAll user = new UserAll();
		user.setAccountUserAll(account);
		user = userAllDao.findUserAll(user);
		if(user==null){
			return null;
		}else{
			return user;
		}
	}

	@Override
	public int updateUserAllScore(UserAll userAll) {
		// TODO Auto-generated method stub
		if(userAll.getScoreUserAll()==null || userAll.getScoreUserAll()<0){
			return -2;
		}
		UserAll user = new UserAll();
		user = userAllDao.findUserAllById(userAll.getIdUserAll());
		if(user==null){
			return -1;
		}else{
			UserAll userr = user;
			userr.setScoreUserAll(userAll.getScoreUserAll());
			boolean result = userAllDao.updateUserAll(userr);
			if(result)
				return 1;
			else
				return 0;
		}
	}

}
