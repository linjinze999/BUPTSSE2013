package com.buptsse.spm.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.buptsse.spm.dao.PageDao;
import com.buptsse.spm.domain.Page;

/**
 * @author Jinze Lin
 * @date 2016年04月14日 上午9:21
 * @description 实现PageDao接口
 * @modify
 * @modifyDate
 */
@Repository
public class PageDaoImpl extends BaseDAOImpl<Page> implements PageDao{
	
	private static Logger LOG = Logger.getLogger(PageDaoImpl.class);
	
	/* (non-Javadoc)
	 * @see com.buptsse.spm.dao.IBaseDAO#find(java.lang.String, java.lang.Object[])
	 * 查询
	 */
	@Override
	public List<Page> find(String hql, Page[] param) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.buptsse.spm.dao.IBaseDAO#get(java.lang.String, java.lang.Object[])
	 */
	@Override
	public Page get(String hql, Page[] param) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.buptsse.spm.dao.IBaseDAO#count(java.lang.String, java.lang.Object[])
	 */
	@Override
	public Long count(String hql, Page[] param) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.buptsse.spm.dao.IBaseDAO#executeHql(java.lang.String, java.lang.Object[])
	 */
	@Override
	public Integer executeHql(String hql, Page[] param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page findPageByPage(Page page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page findPageById(int idPage) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insertPage(Page page) {
		// TODO Auto-generated method stub
		try{
			super.save(page);
		}catch(Exception e){
			e.printStackTrace();
			LOG.error(e);
			return false;
		}
		return true;
	}

	@Override
	public List<Page> searchPage(List<Object> choose) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deletePage(Page page) {
		// TODO Auto-generated method stub
		try{
			super.delete(page);
		}catch(Exception e){
			e.printStackTrace();
			LOG.error(e);
			return false;
		}
		return true;
	}

	@Override
	public boolean addPage(Page page) {
		// TODO Auto-generated method stub
		try{
			super.save(page);
		}catch(Exception e){
			e.printStackTrace();
			LOG.error(e);
			return false;
		}
		return true;
	}

	@Override
	public boolean updatePage(Page page) {
		// TODO Auto-generated method stub
		try{
			super.update(page);
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
		return null;
	}

	@Override
	public Long countPage(String hql, List param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Page> findPageByIds(List<Integer> ids) {
		// TODO Auto-generated method stub
		String sql = "from Page where id_page in(";
		for(int i=0;i<ids.size();i++){
			sql+=ids.get(i);
			if(i<ids.size()-1){
				sql+=",";
			}
		}
		sql+=")";
		return super.find(sql);
	}

	@Override
	public List<Page> findChildPages(List<Page> pages) {
		// TODO Auto-generated method stub
		String sql = "from Page where parent_id in(";
		for(int i=0;i<pages.size();i++){
			sql+=pages.get(i).getId();
			if(i<pages.size()-1){
				sql+=",";
			}
		}
		sql+=")";
		return super.find(sql);
	}

	@Override
	public List<Page> findPagelevel1() {
		// TODO Auto-generated method stub
		String sql = "from Page where level_page=1";
		return super.find(sql);
	}

	@Override
	public List<Page> findParentPages(List<Page> pages) {
		// TODO Auto-generated method stub
		
		String str="";
		for(int i=0;i<pages.size();i++){
			if(pages.get(i).getParentId()!=null){
				if(i>0 && !"".equals(str)){
					str+=",";
				}
				str+=pages.get(i).getParentId();
				
			}
		}
		if("".equals(str)){
			return null;
		}else{
			String sql = "from Page where id_page in("+str+")";
			return super.find(sql);
		}
	}

}
