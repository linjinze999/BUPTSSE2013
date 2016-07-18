package com.buptsse.spm.dao;

import java.util.List;

import com.buptsse.spm.domain.SpChapterVideo;

/**
 * @author yifei xue
 * @date 2015年11月01日 下午2:46
 * @description	视频章节信息持久层接口定义，包括查找所有视频信息（章节图片、视频路径、学习顺序）
 * @modify
 * @modifyDate 
 */

public interface ISpChapterVideoDao {

	public List<SpChapterVideo> findAllInfo();
	
	public boolean saveSpChapterVideo(SpChapterVideo spChapterVideo);
	public boolean updateSpChapterVideo(SpChapterVideo spChapterVideo);
	public boolean deleteSpChapterVideo(SpChapterVideo spChapterVideo);
	public List<SpChapterVideo> findSpChapterVideo(String hql,Object[] param);
	public List<SpChapterVideo> findSpChapterVideo(String hql,List param);
	public boolean saveOrUpdateSpChapterVideo(SpChapterVideo spChapterVideo);
	public Long countSpChapterVideo(String hql,List param);
	public SpChapterVideo findSpChapterVideoById(Integer id);	
	
	
}
