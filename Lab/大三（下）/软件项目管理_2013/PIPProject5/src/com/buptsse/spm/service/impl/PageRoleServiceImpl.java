package com.buptsse.spm.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.buptsse.spm.dao.PageRoleDao;
import com.buptsse.spm.domain.PageRole;
import com.buptsse.spm.service.PageRoleService;

/**
 * @author Jinze Lin
 * @date 2016年04月14日 上午10:35
 * @description
 */

@Transactional
@Service
public class PageRoleServiceImpl implements PageRoleService{
	
	@Resource
	private PageRoleDao pageRoleDao;
	
	@Override
	public PageRole findPageRole(PageRole pageRole) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageRole findPageRoleById(int idPageRole) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insertPageRole(PageRole pageRole) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<PageRole> searchPageRole(String choose) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deletePageRoleById(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addPageRole(PageRole pageRole) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updatePageRole(PageRole pageRole) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List findPage(Map param, Integer page, Integer rows) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long countPage(Map param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Integer> searchPagesByRole(String userRole) {
		// TODO Auto-generated method stub
		List<PageRole> list1=pageRoleDao.searchPageRoleByRole(userRole);
		List<Integer> list2 = new ArrayList<Integer>();
		if(list1!=null){
			for(int i=0;i<list1.size();i++){
				list2.add(list1.get(i).getPageId());
			}
		}
		return list2;
	}

	@Override
	public boolean updateRolePages(String role,List<Integer> pageids) {
		// TODO Auto-generated method stub
		//PageRole deleteRole = new PageRole();
		//deleteRole.setRole(role);
		//boolean dr = pageRoleDao.deletePageRole(deleteRole);
		boolean dr = true;
		List<PageRole> deleteRole = pageRoleDao.searchPageRoleByRole(role);
		for(int n=0;n<deleteRole.size();n++){
			if(!pageRoleDao.deletePageRole(deleteRole.get(n))){
				dr=false;
			}
		}
		if(dr){
			boolean result = true;
			for(int i=0;i<pageids.size();i++){
				if(pageids.get(i)==0){
					pageids.remove(i);
					i--;
				}
			}
			for(int i=0;i<pageids.size();i++){
				PageRole newRolePage = new PageRole();
				newRolePage.setRole(role);
				newRolePage.setPageId(pageids.get(i));
				if(!pageRoleDao.insertPageRole(newRolePage)){
					return false;
				}
			}
			return true;
		}
		return false;
	}

}
