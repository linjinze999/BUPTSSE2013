package com.buptsse.spm.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.buptsse.spm.dao.ISpChapterVideoDao;
import com.buptsse.spm.domain.SpChapterVideo;

/**
 * @author yifei xue
 * @date 2015年11月01日 下午2:46
 * @description	视频章节信息持久层接口定义，包括查找所有视频信息（章节图片、视频路径、学习顺序）
 * @modify
 * @modifyDate 
 */

@Repository
public class SpChapterVideoDaoImpl extends BaseDAOImpl<SpChapterVideo> implements ISpChapterVideoDao{
	private static Logger LOG = Logger.getLogger(SpChapterVideoDaoImpl.class);
	
	@Override
	public List<SpChapterVideo> find(String hql, SpChapterVideo[] param) {
		// TODO Auto-generated method stub
		return super.find("from SpChapterVideo where chapter_id= :param", param);
	}

	@Override
	public SpChapterVideo get(String hql, SpChapterVideo[] param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long count(String hql, SpChapterVideo[] param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer executeHql(String hql, SpChapterVideo[] param) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public List<SpChapterVideo> findAllInfo() {
		// TODO Auto-generated method stub
		try{
			List<SpChapterVideo> list = new ArrayList<SpChapterVideo>();
			list = super.find("from SpChapterVideo s order by s.chapter_id");
			System.out.println("这里是在sp_chapter_video_Dao");
			return list;			
		}catch(Exception e){
			e.printStackTrace();
			LOG.error(e);
			return null;
		}
	}

	@Override
	public boolean saveSpChapterVideo(SpChapterVideo spChapterVideo) {
		// TODO Auto-generated method stub
		
		
		
		return false;
	}

	@Override
	public boolean updateSpChapterVideo(SpChapterVideo spChapterVideo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteSpChapterVideo(SpChapterVideo spChapterVideo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<SpChapterVideo> findSpChapterVideo(String hql, Object[] param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SpChapterVideo> findSpChapterVideo(String hql, List param) {
		// TODO Auto-generated method stub
		return super.find(hql, param);
	}

	@Override
	public boolean saveOrUpdateSpChapterVideo(SpChapterVideo spChapterVideo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Long countSpChapterVideo(String hql, List param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SpChapterVideo findSpChapterVideoById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
