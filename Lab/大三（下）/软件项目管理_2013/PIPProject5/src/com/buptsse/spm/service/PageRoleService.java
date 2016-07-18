package com.buptsse.spm.service;

import java.util.List;
import java.util.Map;

import com.buptsse.spm.domain.PageRole;

/**
 * @author Jinze Lin
 * @date 2016年04月14日 上午10:31
 * @description 角色拥有"页面"相关功能接口定义，包括增删改查
 * @modify
 *
 */
public interface PageRoleService {
	public boolean updateRolePages(String role,List<Integer> pageids);
	public List<Integer> searchPagesByRole(String userRole);
	public PageRole findPageRole(PageRole pageRole);
	public PageRole findPageRoleById(int idPageRole);
	public boolean insertPageRole(PageRole pageRole);
	public List<PageRole> searchPageRole(String choose);
	public boolean deletePageRoleById(int id);
	public boolean addPageRole(PageRole pageRole);
	public boolean updatePageRole(PageRole pageRole);
	public List findPage(Map param,Integer page,Integer rows);	
	public Long countPage(Map param);
}
