/**
 * 
 */
package com.buptsse.spm.service;

import java.util.List;

import com.buptsse.spm.domain.SpChapter;
import com.buptsse.spm.domain.SpChapterVideo;

/**
 * @author libing
 * @date 2015年11月23日 下午4:05:37
 * @description 视频播放相关功能接口定义，包括增删改查
 * @modify
 *
 */

public interface ISpChapterVideoService {
	public SpChapterVideo findSpChapterVideoById(String id);
	public boolean insertSpChapterVideo(SpChapterVideo spChapterVideo);
	public List<SpChapterVideo> findAllSpChapterVideo();
	public boolean deleteSpChapterVideo(String id);
	public boolean saveOrUpdate(SpChapterVideo spChapterVideo);
	public List<SpChapterVideo> findSpChapterVideoByChapterId(Integer chapterId);
	public List<SpChapterVideo> findSpChapterVideoByStepOrder(Integer stepOrder);
}
