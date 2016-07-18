package com.buptsse.spm.dao;

import java.util.List;

import com.buptsse.spm.domain.Notice;

public interface NoticeDao {
	public boolean addNotice(Notice notice);
	public List<Notice> searchNotice(Notice notice);
	public Notice findNotice(Notice notice);
	public boolean updateNotice(Notice notice);
}
