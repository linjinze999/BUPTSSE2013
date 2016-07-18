/**
 * 
 */
package com.buptsse.spm.service;

import java.util.List;

import com.buptsse.spm.domain.SpChapter;

/**
 * @author libing
 * @date 2015年11月23日 下午4:05:37
 * @description 视频学习相关功能接口定义，包括增删改查
 * @modify
 *
 */

public interface ISpChapterService {
	public SpChapter findSpChapterById(String id);
	public boolean insertSpChapter(SpChapter spChapter);
	public List<SpChapter> findAllSpChapter();
	public boolean deleteSpChapter(String id);
	public boolean saveOrUpdate(SpChapter spChapter);
	
	public List findSpChapterDetial();
	
	
}
