package com.buptsse.spm.dao;

import java.util.List;

import com.buptsse.spm.domain.Page;
import com.buptsse.spm.domain.PageRole;

/**
 * @author Jinze Lin
 * @date 2016年04月14日 上午10:15
 * @description	角色拥有页面信息持久层接口定义，包括用户增加，保存，查询
 * @modify
 * @modifyDate 
 */


public interface PageRoleDao {
	public List<PageRole> searchPageRoleByRole(String role);
	public PageRole findPageRole(Page page);
	public PageRole findPageRoleById(int idPage);
	public boolean insertPageRole(PageRole pageRole);
	public List<PageRole> searchPageRole(List<Object> choose);
	public boolean deletePageRole(PageRole pageRole);
	public boolean addPageRole(PageRole pageRole);
	public boolean updatePageRole(PageRole pageRole);
	public List findPage(String hql,List param , Integer page,Integer rows);	
	public Long countPage(String hql, List param);
}
