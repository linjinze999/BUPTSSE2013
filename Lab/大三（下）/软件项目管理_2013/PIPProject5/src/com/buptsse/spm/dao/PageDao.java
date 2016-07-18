package com.buptsse.spm.dao;

import java.util.List;

import com.buptsse.spm.domain.Page;

/**
 * @author Jinze Lin
 * @date 2016年04月14日 上午9:15
 * @description	页面信息持久层接口定义，包括用户增加，保存，查询
 * @modify
 * @modifyDate 
 */

public interface PageDao {
	public List<Page> findPagelevel1();
	public Page findPageByPage(Page page);
	public Page findPageById(int idPage);
	public List<Page> findPageByIds(List<Integer> ids);
	public List<Page> findChildPages(List<Page> pages);
	public List<Page> findParentPages(List<Page> pages);
	public boolean insertPage(Page page);
	public List<Page> searchPage(List<Object> choose);
	public boolean deletePage(Page page);
	public boolean addPage(Page page);
	public boolean updatePage(Page page);
	public List findPage(String hql,List param , Integer page,Integer rows);	
	public Long countPage(String hql, List param);
}
