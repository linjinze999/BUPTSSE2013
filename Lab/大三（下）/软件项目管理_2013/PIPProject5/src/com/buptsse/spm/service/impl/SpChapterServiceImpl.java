package com.buptsse.spm.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.buptsse.spm.dao.ISpChapterDao;
import com.buptsse.spm.domain.SpChapter;
import com.buptsse.spm.service.ISpChapterService;


/**
 * @author libing 
 * @date 2015年11月16日 下午3:53
 * @description 视频学习的service层实现类定义 
 * @modify
 * @modifyDate 
 */

@Transactional
@Service
public class SpChapterServiceImpl implements ISpChapterService{

	@Resource
	private ISpChapterDao iSpChapterDao;



	@Override
	public SpChapter findSpChapterById(String id) {
		// TODO Auto-generated method stub
		return iSpChapterDao.findSpChapterById(new Integer(id));
	}

	@Override
	public boolean insertSpChapter(SpChapter spChapter) {
		// TODO Auto-generated method stub
		return iSpChapterDao.saveSpChapter(spChapter);
	}

	@Override
	public List<SpChapter> findAllSpChapter() {
		// TODO Auto-generated method stub
		String hql = "from SpChapter";
		List list = new ArrayList();
		return iSpChapterDao.findSpChapter(hql, list);
	}
	
	
	@Override
	public List findSpChapterDetial() {
		// TODO Auto-generated method stub
		
		
		String hql = "SELECT c.chapter_id,c.chapter_name_number,c.chapter_name,c.chapter_pic,c.chapter_desc,"
			       +"SUM(cv.video_time) as sum_time,count(*) as video_size "
			       +"FROM sp_chapter c,sp_chapter_video cv WHERE c.chapter_id = cv.chapter_id "
			       +"GROUP BY c.chapter_id,c.chapter_name_number,c.chapter_name,c.chapter_pic,c.chapter_desc "
			       +"ORDER BY c.chapter_name_number";
		
		
		System.out.println("hql:"+hql);
		
		List list = new ArrayList();
		
		//List resultList = iSpChapterDao.findSpChapter(hql, list);
		List resultList = iSpChapterDao.findSpChapterDetial(hql);
		//iSpChapterDao.findSpChapterDetial(hql);
		System.out.println("**resultList***:"+resultList.size());
		
		return resultList;
		
	}	

	@Override
	public boolean deleteSpChapter(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean saveOrUpdate(SpChapter spChapter) {
		// TODO Auto-generated method stub
		return false;
	}
	

	public ISpChapterDao getiSpChapterDao() {
		return iSpChapterDao;
	}

	public void setiSpChapterDao(ISpChapterDao iSpChapterDao) {
		this.iSpChapterDao = iSpChapterDao;
	}



}
