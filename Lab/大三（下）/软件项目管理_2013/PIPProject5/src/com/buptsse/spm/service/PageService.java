package com.buptsse.spm.service;

import java.util.List;
import java.util.Map;

import com.buptsse.spm.domain.Page;

/**
 * @author Jinze Lin
 * @date 2016年04月14日 上午9:31
 * @description "页面"相关功能接口定义，包括增删改查
 * @modify
 *
 */
public interface PageService {
	public List<Page> searchAllPage();
	public List<Page> searchPageLevel1();
	public Page findPageByPage(Page page);
	public Page findPageById(int idPage);
	public List<Page> findPageByIds(List<Integer> ids);
	public List<Page> findRootPageByIds(List<Integer> ids);
	public boolean insertPage(Page page);
	public List<Page> searchPage(String choose);
	public boolean deletePage(Page page);
	public boolean addPage(Page page);
	public boolean updatePage(Page page);
	public List findPage(Map param,Integer page,Integer rows);	
	public Long countPage(Map param);
}
