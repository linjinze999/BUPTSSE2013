package com.buptsse.spm.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.buptsse.spm.dao.PageRoleDao;
import com.buptsse.spm.domain.Page;
import com.buptsse.spm.domain.PageRole;

/**
 * @author Jinze Lin
 * @date 2016年04月14日 上午10:21
 * @description 实现PageRoleDao接口
 * @modify
 * @modifyDate
 */
@Repository
public class PageRoleDaoImpl extends BaseDAOImpl<PageRole> implements PageRoleDao{
	
	private static Logger LOG = Logger.getLogger(PageRoleDaoImpl.class);

	@Override
	public List<PageRole> find(String hql, PageRole[] param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageRole get(String hql, PageRole[] param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long count(String hql, PageRole[] param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer executeHql(String hql, PageRole[] param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageRole findPageRole(Page page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageRole findPageRoleById(int idPage) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insertPageRole(PageRole pageRole) {
		// TODO Auto-generated method stub
		try{
			super.save(pageRole);
		}catch(Exception e){
			e.printStackTrace();
			LOG.error(e);
			return false;
		}
		return true;
	}

	@Override
	public List<PageRole> searchPageRole(List<Object> choose) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deletePageRole(PageRole pageRole) {
		// TODO Auto-generated method stub
		try{
			super.delete(pageRole);
		}catch(Exception e){
			e.printStackTrace();
			LOG.error(e);
			return false;
		}
		return true;
	}

	@Override
	public boolean addPageRole(PageRole pageRole) {
		// TODO Auto-generated method stub
		try{
			super.save(pageRole);
		}catch(Exception e){
			e.printStackTrace();
			LOG.error(e);
			return false;
		}
		return true;
	}

	@Override
	public boolean updatePageRole(PageRole pageRole) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List findPage(String hql, List param, Integer page, Integer rows) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long countPage(String hql, List param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PageRole> searchPageRoleByRole(String role) {
		// TODO Auto-generated method stub
		return super.find("from PageRole where role='"+role+"'");
	}

}
