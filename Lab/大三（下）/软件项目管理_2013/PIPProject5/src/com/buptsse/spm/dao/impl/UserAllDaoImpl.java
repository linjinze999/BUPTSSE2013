package com.buptsse.spm.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.buptsse.spm.dao.UserAllDao;
import com.buptsse.spm.domain.UserAll;

/**
 * @author Jinze Lin
 * @date 2016年04月13日 上午8:35
 * @description 实现UserAllDao接口
 * @modify
 * @modifyDate
 */

@Repository
public class UserAllDaoImpl  extends BaseDAOImpl<UserAll> implements UserAllDao{
	private static Logger LOG = Logger.getLogger(UserAllDaoImpl.class);
	
	/* (non-Javadoc)
	 * @see com.buptsse.spm.dao.IBaseDAO#find(java.lang.String, java.lang.Object[])
	 * 查询
	 */
	@Override
	public List<UserAll> find(String hql, UserAll[] param) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/* (non-Javadoc)
	 * @see com.buptsse.spm.dao.IBaseDAO#get(java.lang.String, java.lang.Object[])
	 */
	@Override
	public UserAll get(String hql, UserAll[] param) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/* (non-Javadoc)
	 * @see com.buptsse.spm.dao.IBaseDAO#count(java.lang.String, java.lang.Object[])
	 */
	@Override
	public Long count(String hql, UserAll[] param) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.buptsse.spm.dao.IBaseDAO#executeHql(java.lang.String, java.lang.Object[])
	 */
	@Override
	public Integer executeHql(String hql, UserAll[] param) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.buptsse.spm.dao.UserAllDao#findUserAll(com.buptsse.spm.domain.UserAll)
	 */
	@Override
	public UserAll findUserAll(UserAll userAll) {
		// TODO Auto-generated method stub
		try{
			List<UserAll> list = new ArrayList<UserAll>();
			list = super.find("from UserAll");
			for(int i = 0;i < list.size();i++){
				if(userAll.getAccountUserAll().equals(list.get(i).getAccountUserAll())){
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
	 * @see com.buptsse.spm.dao.UserAllDao#findUserAllById(java.lang.Integer)
	 */
	@Override
	public UserAll findUserAllById(int idUserAll) {
		// TODO Auto-generated method stub
		try{
			List<UserAll> list = new ArrayList<UserAll>();
			list = super.find("from UserAll");
			for(int i = 0;i < list.size();i++){
				if(idUserAll==list.get(i).getIdUserAll()){
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
	 * @see com.buptsse.spm.dao.UserAllDao#insertUserAll(com.buptsse.spm.domain.UserAll)
	 */
	@Override
	public boolean insertUserAll(UserAll userAll) {
		// TODO Auto-generated method stub
		try {
			super.save(userAll);
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error(e);
			return false;
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see com.buptsse.spm.dao.UserAllDao#searchUserAll(java.util.List)
	 */
	@Override
	public List<UserAll> searchUser(List<Object> choose) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.buptsse.spm.dao.UserAllDao#deleteUserAll(com.buptsse.spm.domain.UserAll)
	 */
	@Override
	public boolean deleteUserAll(UserAll userAll) {
		// TODO Auto-generated method stub
		try{
			super.delete(userAll);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			LOG.error(e);
			return false;
		}		
	}

	/* (non-Javadoc)
	 * @see com.buptsse.spm.dao.UserAllDao#addUserAll(com.buptsse.spm.domain.User)
	 */
	@Override
	public boolean addUserAll(UserAll userAll) {
		// TODO Auto-generated method stub
		return insertUserAll(userAll);
	}

	/* (non-Javadoc)
	 * @see com.buptsse.spm.dao.UserAllDao#updateUserAll(com.buptsse.spm.domain.User)
	 */
	@Override
	public boolean updateUserAll(UserAll userAll) {
		// TODO Auto-generated method stub
		try{
			super.update(userAll);
		}catch(Exception e){
			e.printStackTrace();
			LOG.error(e);
			return false;
		}		
		return true;
	}

	/* (non-Javadoc)
	 * @see com.buptsse.spm.dao.UserAllDao#findPage(java.lang.String,java.util.List,java.lang.Integer,java.lang.Integer)
	 */
	@Override
	public List findPage(String hql, List param, Integer page, Integer rows) {
		// TODO Auto-generated method stub
		System.out.println("***开始 分页查询****");
		return super.find(hql, param, page, rows);
	}

	/* (non-Javadoc)
	 * @see com.buptsse.spm.dao.UserAllDao#countUser(java.lang.String,java.util.List)
	 */
	@Override
	public Long countUser(String hql, List param) {
		// TODO Auto-generated method stub
		return super.count(hql, param);
	}

	@Override
	public List<UserAll> searchUserAllByUserAll(UserAll userAll) {
		// TODO Auto-generated method stub
		try{
			String str="from UserAll";
			boolean more = false;
			if(userAll.getRoleUserAll()!=null && !"".equals(userAll.getRoleUserAll())){
				str+=" where role_user_all ='"+userAll.getRoleUserAll()+"'";
				more=true;
			}
			if(userAll.getIdUserAll()>0){
				if(more){
					str+=" and id_user_all ="+userAll.getIdUserAll();
				}else{
					str+=" where id_user_all ="+userAll.getIdUserAll();
					more=true;
				}
				return super.find(str);
			}
			if(userAll.getAccountUserAll()!=null && !"".equals(userAll.getAccountUserAll())){
				if(more){
					str+=" and account_user_all like'%"+userAll.getAccountUserAll()+"%'";
				}else{
					str+=" where account_user_all like'%"+userAll.getAccountUserAll()+"%'";
					more=true;
				}
			}
			if(userAll.getNameUserAll()!=null && !"".equals(userAll.getNameUserAll())){
				if(more){
					str+=" and name_user_all like'%"+userAll.getNameUserAll()+"%'";
				}else{
					str+=" where name_user_all like'%"+userAll.getNameUserAll()+"%'";
					more=true;
				}
			}
			if(userAll.getSexUserAll()!=null && !"".equals(userAll.getSexUserAll())){
				if(more){
					str+=" and sex_user_all like'%"+userAll.getSexUserAll()+"%'";
				}else{
					str+=" where sex_user_all like'%"+userAll.getSexUserAll()+"%'";
					more=true;
				}
			}
			if(userAll.getIdcardUserAll()!=null && !"".equals(userAll.getIdcardUserAll())){
				if(more){
					str+=" and idcard_user_all like'%"+userAll.getIdcardUserAll()+"%'";
				}else{
					str+=" where idcard_user_all like'%"+userAll.getIdcardUserAll()+"%'";
					more=true;
				}
			}
			if(userAll.getCollegeUserAll()!=null && !"".equals(userAll.getCollegeUserAll())){
				if(more){
					str+=" and college_user_all like'%"+userAll.getCollegeUserAll()+"%'";
				}else{
					str+=" where college_user_all like'%"+userAll.getCollegeUserAll()+"%'";
					more=true;
				}
			}
			if(userAll.getMajorUserAll()!=null && !"".equals(userAll.getMajorUserAll())){
				if(more){
					str+=" and major_user_all like'%"+userAll.getMajorUserAll()+"%'";
				}else{
					str+=" where major_user_all like'%"+userAll.getMajorUserAll()+"%'";
					more=true;
				}
			}
			if(userAll.getGradeUserAll()!=null && !"".equals(userAll.getGradeUserAll())){
				if(more){
					str+=" and grade_user_all like'%"+userAll.getGradeUserAll()+"%'";
				}else{
					str+=" where grade_user_all like'%"+userAll.getGradeUserAll()+"%'";
					more=true;
				}
			}
			if(userAll.getStateUserAll()!=null && !"".equals(userAll.getStateUserAll())){
				if(more){
					str+=" and state_user_all like'%"+userAll.getStateUserAll()+"%'";
				}else{
					str+=" where state_user_all like'%"+userAll.getStateUserAll()+"%'";
					more=true;
				}
			}
			if(userAll.getPhone1UserAll()!=null && !"".equals(userAll.getPhone1UserAll())){
				if(more){
					str+=" and phone1_user_all like'%"+userAll.getPhone1UserAll()+"%'";
				}else{
					str+=" where phone1_user_all like'%"+userAll.getPhone1UserAll()+"%'";
					more=true;
				}
			}
			if(userAll.getPhone2UserAll()!=null && !"".equals(userAll.getPhone2UserAll())){
				if(more){
					str+=" and phone2_user_all like'%"+userAll.getPhone2UserAll()+"%'";
				}else{
					str+=" where phone2_user_all like'%"+userAll.getPhone2UserAll()+"%'";
					more=true;
				}
			}
			if(userAll.getQqUserAll()!=null && !"".equals(userAll.getQqUserAll())){
				if(more){
					str+=" and qq_user_all like'%"+userAll.getQqUserAll()+"%'";
				}else{
					str+=" where qq_user_all like'%"+userAll.getQqUserAll()+"%'";
					more=true;
				}
			}
			if(userAll.getEmailUserAll()!=null && !"".equals(userAll.getEmailUserAll()) ){
				if(more){
					str+=" and email_user_all like'%"+userAll.getEmailUserAll()+"%'";
				}else{
					str+=" where email_user_all like'%"+userAll.getEmailUserAll()+"%'";
					more=true;
				}
			}
			if(userAll.getAddressUserAll()!=null && !"".equals(userAll.getAddressUserAll()) ){
				if(more){
					str+=" and address_user_all like'%"+userAll.getAddressUserAll()+"%'";
				}else{
					str+=" where address_user_all like'%"+userAll.getAddressUserAll()+"%'";
					more=true;
				}
			}
			if(userAll.getLoginipUserAll()!=null && !"".equals(userAll.getLoginipUserAll())){
				if(more){
					str+=" and loginip_user_all like'%"+userAll.getLoginipUserAll()+"%'";
				}else{
					str+=" where loginip_user_all like'%"+userAll.getLoginipUserAll()+"%'";
					more=true;
				}
			}
			if(userAll.getPhotoUserAll()!=null && !"".equals(userAll.getPhotoUserAll()) ){
				if(more){
					str+=" and photo_user_all like'%"+userAll.getPhotoUserAll()+"%'";
				}else{
					str+=" where photo_user_all like'%"+userAll.getPhotoUserAll()+"%'";
					more=true;
				}
			}
			if(userAll.getTeamidUserAll()!=null && userAll.getTeamidUserAll()>0 ){
				if(more){
					str+=" and teamid_user_all ="+userAll.getTeamidUserAll();
				}else{
					str+=" where teamid_user_all ="+userAll.getTeamidUserAll();
					more=true;
				}
			}
			
			return super.find(str);
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

}
