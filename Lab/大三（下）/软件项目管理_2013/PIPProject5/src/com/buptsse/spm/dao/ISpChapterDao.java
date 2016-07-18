package com.buptsse.spm.dao;

import java.util.List;

import com.buptsse.spm.domain.Course;
import com.buptsse.spm.domain.SpChapter;


/**
 * @author libing
 * @param <T>
 * @date 2015年11月23日 下午2:46
 * @description	视频教学持久层接口定义
 * @modify
 * @modifyDate 
 */

public interface ISpChapterDao {
	public boolean saveSpChapter(SpChapter spChapter);
	public boolean updateSpChapter(SpChapter spChapter);
	public boolean deleteSpChapter(SpChapter spChapter);
	public List<SpChapter> findSpChapter(String hql,Object[] param);
	public List<SpChapter> findSpChapter(String hql,List param);
	public boolean saveOrUpdateSpChapter(SpChapter spChapter);
	public Long countSpChapter(String hql,List param);
	public SpChapter findSpChapterById(Integer id);
	
	public List findSpChapterDetial(String hql);
	

}
