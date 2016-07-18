package com.buptsse.spm.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.buptsse.spm.dao.ISpChapterVideoDao;
import com.buptsse.spm.domain.SpChapter;
import com.buptsse.spm.domain.SpChapterVideo;
import com.buptsse.spm.service.ISpChapterVideoService;


/**
 * @author libing 
 * @date 2015年11月16日 下午3:53
 * @description 视频播放的service层实现类定义 
 * @modify
 * @modifyDate 
 */

@Transactional
@Service
public class SpChapterVideoServiceImpl implements ISpChapterVideoService{

	@Resource
	private ISpChapterVideoDao iSpChapterVideoDao;



	@Override
	public SpChapterVideo findSpChapterVideoById(String id) {
		// TODO Auto-generated method stub
		return iSpChapterVideoDao.findSpChapterVideoById(new Integer(id));
	}

	public List<SpChapterVideo> findSpChapterVideoByChapterId(Integer chapterId){
		String hql = "from SpChapterVideo where chapter_id=? ";
		List listParam = new ArrayList();
		listParam.add(chapterId);
		
		return iSpChapterVideoDao.findSpChapterVideo(hql, listParam);
	}
	
	
	public List<SpChapterVideo> findSpChapterVideoByStepOrder(Integer stepOrder){
		String hql = "from SpChapterVideo where video_step_order=? ";
		List listParam = new ArrayList();
		listParam.add(stepOrder);
		
		return iSpChapterVideoDao.findSpChapterVideo(hql, listParam);
	}	
	
	
	
	@Override
	public boolean insertSpChapterVideo(SpChapterVideo SpChapterVideo) {
		// TODO Auto-generated method stub
		return iSpChapterVideoDao.saveSpChapterVideo(SpChapterVideo);
	}

	@Override
	public List<SpChapterVideo> findAllSpChapterVideo() {
		// TODO Auto-generated method stub
		String hql = "from SpChapterVideo";
		List list = new ArrayList();
		return iSpChapterVideoDao.findSpChapterVideo(hql, list);
	}

	@Override
	public boolean deleteSpChapterVideo(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean saveOrUpdate(SpChapterVideo SpChapterVideo) {
		// TODO Auto-generated method stub
		return false;
	}
	

	public ISpChapterVideoDao getiSpChapterVideoDao() {
		return iSpChapterVideoDao;
	}

	public void setiSpChapterVideoDao(ISpChapterVideoDao iSpChapterVideoDao) {
		this.iSpChapterVideoDao = iSpChapterVideoDao;
	}




}
