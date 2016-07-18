package com.buptsse.spm.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.buptsse.spm.dao.IUserDao;
import com.buptsse.spm.domain.Message;
import com.buptsse.spm.domain.User;

/**
 * @author Ren Zhengzhi
 * @date 2015年10月17日 下午3:53:50
 * @description
 * @modify	Han Xinyu Xue Yifei 
 * @modifyDate 2015年10月24日 下午10:53:50
 */

@Repository
public class UserDaoImpl extends BaseDAOImpl<User> implements IUserDao {
	private static Logger LOG = Logger.getLogger(UserDaoImpl.class);

	/* (non-Javadoc)
	 * @see com.buptsse.spm.dao.IBaseDAO#find(java.lang.String, java.lang.Object[])
	 * 查询
	 */
	@Override
	public List<User> find(String hql, User[] param) {
		return super.find("from User where username= :param", param);
	}

	/* (non-Javadoc)
	 * @see com.buptsse.spm.dao.IBaseDAO#get(java.lang.String, java.lang.Object[])
	 */
	@Override
	public User get(String hql, User[] param) {
		return super.get(User.class, param);
	}

	/* (non-Javadoc)
	 * @see com.buptsse.spm.dao.IBaseDAO#count(java.lang.String, java.lang.Object[])
	 */
	@Override
	public Long count(String hql, User[] param) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.buptsse.spm.dao.IBaseDAO#executeHql(java.lang.String, java.lang.Object[])
	 */
	@Override
	public Integer executeHql(String hql, User[] param) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.buptsse.spm.dao.IUserDao#findUser(com.buptsse.spm.domain.User)
	 */
	@Override
	public User findUser(User user) {
		// TODO Auto-generated method stub
		try{
			List<User> list = new ArrayList<User>();
			list = super.find("from User");
			for(int i = 0;i < list.size();i++){
				if(user.getUserName().equals(list.get(i).getUserName())){
					return list.get(i);
			}
		}			
		}catch(Exception e){
			e.printStackTrace();
			LOG.error(e);
			return null;
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see com.buptsse.spm.dao.IUserDao#insertUser(com.buptsse.spm.domain.User)
	 */
	@Override
	public boolean insertUser(User user) {
		try {
			super.save(user);
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error(e);
			return false;
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see com.buptsse.spm.dao.IUserDao#searchUser(java.util.List)
	 */
	@Override
	public List<User> searchUser(List<Object> choose) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.buptsse.spm.dao.IUserDao#deleteUser(java.lang.String)
	 */
	@Override
	public boolean deleteUser(User user) {
		// TODO Auto-generated method stub
		try{
			super.delete(user);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			LOG.error(e);
			return false;
		}		
	}

	/* (non-Javadoc)
	 * @see com.buptsse.spm.dao.IUserDao#addUser(com.buptsse.spm.domain.User)
	 */
	@Override
	public boolean addUser(User user) {
		// TODO Auto-generated method stub
		return insertUser(user);
	}
	
	@Override
	public boolean updateUser(User user) {
		// TODO Auto-generated method stub
		try{
			super.update(user);
		}catch(Exception e){
			e.printStackTrace();
			LOG.error(e);
			return false;
		}		
		return true;
	}

	@Override
	public List findPage(String hql, List param, Integer page, Integer rows) {
		// TODO Auto-generated method stub
		System.out.println("***开始 分页查询****");
		return super.find(hql, param, page, rows);
	}

	@Override
	public Long countUser(String hql, List param) {
		// TODO Auto-generated method stub
		return super.count(hql, param);
	}	
	
	@Override
	public User findUserById(String id){
		// TODO Auto-generated method stub
		return super.get(User.class, id);
		
	}
	
}
