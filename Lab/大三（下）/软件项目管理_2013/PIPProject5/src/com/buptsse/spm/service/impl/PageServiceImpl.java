package com.buptsse.spm.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.buptsse.spm.dao.PageDao;
import com.buptsse.spm.domain.Page;
import com.buptsse.spm.service.PageService;

/**
 * @author Jinze Lin
 * @date 2016年04月14日 上午9:35
 * @description
 */

@Transactional
@Service
public class PageServiceImpl implements PageService{
	@Resource
	private PageDao pageDao;

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
			pageDao.insertPage(page);
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public List<Page> searchPage(String choose) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deletePage(Page page) {
		// TODO Auto-generated method stub
		try{
			pageDao.deletePage(page);
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean addPage(Page page) {
		// TODO Auto-generated method stub
		try{
			pageDao.insertPage(page);
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean updatePage(Page page) {
		// TODO Auto-generated method stub
		try{
			pageDao.updatePage(page);
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
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
	public List<Page> findPageByIds(List<Integer> ids) {
		// TODO Auto-generated method stub
		List<Page> all = new ArrayList<Page>();
		List<Page> level1 = pageDao.findPageByIds(ids);
		if(level1!=null && level1.size()!=0){
			all.addAll(level1);
			/*
			//---------加上这些代码，角色界面目录会显示所有子目录（与下面的方法联系）---------
			List<Page> level2 = pageDao.findChildPages(level1);
			if(level2!=null && level2.size()!=0){
				all.addAll(level2);
				List<Page> level3 = pageDao.findChildPages(level2);
				if(level3!=null && level3.size()!=0){
					all.addAll(level3);
				}
			}
			*/
			List<Page> parent1 = pageDao.findParentPages(level1);
			if(parent1!=null && parent1.size()!=0){
				all.addAll(parent1);
				List<Page> parent2 = pageDao.findParentPages(parent1);
				if(parent2!=null && parent2.size()!=0){
					all.addAll(parent2);
				}
			}
		}
		List<Integer> allid = new ArrayList<Integer>();
		boolean over = false;
		for(int i=0;i<all.size();i++){
			over = false;
			for(int n=0;n<allid.size();n++){
				if(all.get(i).getId().equals(allid.get(n))){
					all.remove(i);
					over = true;
					break;
				}
			}
			if(!over){
				allid.add(all.get(i).getId());
			}else{
				i--;
			}
		}
		return all;
	}

	@Override
	public List<Page> searchAllPage() {
		// TODO Auto-generated method stub
		List<Page> all = new ArrayList<Page>();
		List<Page> level1 = pageDao.findPagelevel1();
		if(level1!=null && level1.size()!=0){
			all.addAll(level1);
			List<Page> level2 = pageDao.findChildPages(level1);
			if(level2!=null && level2.size()!=0){
				all.addAll(level2);
				List<Page> level3 = pageDao.findChildPages(level2);
				if(level3!=null && level3.size()!=0){
					all.addAll(level3);
				}
			}
		}
		return all;
	}

	@Override
	public List<Page> searchPageLevel1() {
		// TODO Auto-generated method stub
		return pageDao.findPagelevel1();
	}

	@Override
	public List<Page> findRootPageByIds(List<Integer> ids) {
		// TODO Auto-generated method stub
		List<Page> all = new ArrayList<Page>();
		List<Page> level1 = pageDao.findPageByIds(ids);
		if(level1!=null && level1.size()!=0){
			all.addAll(level1);
			/*
			//--------加上这些代码，权限管理页面目录会显示某角色拥有目录的所有子目录（与上面的方法联系）---------
			List<Page> level2 = pageDao.findChildPages(level1);
			if(level2!=null && level2.size()!=0){
				all.addAll(level2);
				List<Page> level3 = pageDao.findChildPages(level2);
				if(level3!=null && level3.size()!=0){
					all.addAll(level3);
				}
			}
			*/
		}
		return all;
	}

}
