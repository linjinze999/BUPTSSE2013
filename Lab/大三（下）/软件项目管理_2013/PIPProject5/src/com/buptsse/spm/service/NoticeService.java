package com.buptsse.spm.service;

import java.util.List;

import com.buptsse.spm.domain.Notice;
import com.buptsse.spm.domain.NoticeShow;

public interface NoticeService {
	public boolean addNotice(Notice notice);
	public List<Notice> searchNotice(Notice notice);
	public List<NoticeShow> searchInform(Notice notice);
	public List<NoticeShow> searchMessage(Notice notice);
	public List<NoticeShow> searchTask(Notice notice);
	public Notice findNotice(Notice notice);
	public boolean updateNotice(Notice notice);
}
