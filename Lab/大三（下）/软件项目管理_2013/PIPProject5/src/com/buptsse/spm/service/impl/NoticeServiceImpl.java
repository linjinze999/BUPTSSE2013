package com.buptsse.spm.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.buptsse.spm.dao.NoticeDao;
import com.buptsse.spm.dao.UserAllDao;
import com.buptsse.spm.domain.Notice;
import com.buptsse.spm.domain.NoticeShow;
import com.buptsse.spm.domain.UserAll;
import com.buptsse.spm.service.NoticeService;

@Transactional
@Service
public class NoticeServiceImpl implements NoticeService{
	@Resource
	private NoticeDao noticeDao;
	@Resource
	private UserAllDao userDao;
	
	@Override
	public List<Notice> searchNotice(Notice notice) {
		// TODO Auto-generated method stub
		return noticeDao.searchNotice(notice);
	}

	@Override
	public Notice findNotice(Notice notice) {
		// TODO Auto-generated method stub
		return noticeDao.findNotice(notice);
	}

	@Override
	public List<NoticeShow> searchInform(Notice notice) {
		try{
			// TODO Auto-generated method stub
			Notice notice1=new Notice();
			notice1.setType("通知");
			notice1.setReceiveUserRole(notice.getReceiveUserRole());
			List<Notice> result1 = noticeDao.searchNotice(notice1);
			Notice notice2=new Notice();
			notice2.setType("通知");
			notice2.setReceiveUserId(notice.getReceiveUserId());
			List<Notice> result2 = noticeDao.searchNotice(notice2);
			List<Notice> result = new ArrayList<Notice>();
			result.addAll(result1);
			result.addAll(result2);
			List<NoticeShow> show = new ArrayList<NoticeShow>();
			List<Integer> allid = new ArrayList<Integer>();
			boolean over = false;
			for(int i=0;i<result.size();i++){
				over = false;
				for(int n=0;n<allid.size();n++){
					if(result.get(i).getId()==allid.get(n)){
						result.remove(i);
						over = true;
						break;
					}
				}
				if(!over){
					NoticeShow ns = new NoticeShow();
					ns.setId(result.get(i).getId());
					ns.setTitle(result.get(i).getTitle());
					UserAll ua = new UserAll();
					if(result.get(i).getSendUserId()!=null && result.get(i).getSendUserId()>0){
						ua=userDao.findUserAllById(result.get(i).getSendUserId());
						ns.setSender(ua.getNameUserAll());
					}else{
						ns.setSender("无");
					}
					ns.setContent(result.get(i).getContent());
					show.add(ns);
					allid.add(result.get(i).getId());
				}else{
					i--;
				}
			}
			return show;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
		
	}

	@Override
	public List<NoticeShow> searchMessage(Notice notice) {
		// TODO Auto-generated method stub
		Notice notice1=new Notice();
		notice1.setType("留言");
		notice1.setReceiveUserRole(notice.getReceiveUserRole());
		List<Notice> result1 = noticeDao.searchNotice(notice1);
		Notice notice2=new Notice();
		notice2.setType("留言");
		notice2.setReceiveUserId(notice.getReceiveUserId());
		List<Notice> result2 = noticeDao.searchNotice(notice2);
		List<Notice> result = new ArrayList<Notice>();
		result.addAll(result1);
		result.addAll(result2);
		List<NoticeShow> show = new ArrayList<NoticeShow>();
		List<Integer> allid = new ArrayList<Integer>();
		boolean over = false;
		for(int i=0;i<result.size();i++){
			over = false;
			for(int n=0;n<allid.size();n++){
				if(result.get(i).getId()==allid.get(n)){
					result.remove(i);
					over = true;
					break;
				}
			}
			if(!over){
				NoticeShow ns = new NoticeShow();
				ns.setId(result.get(i).getId());
				ns.setTitle(result.get(i).getTitle());
				UserAll ua = new UserAll();
				if(result.get(i).getSendUserId()!=null && result.get(i).getSendUserId()>0){
					ua=userDao.findUserAllById(result.get(i).getSendUserId());
					ns.setSender(ua.getNameUserAll());
				}else{
					ns.setSender("无");
				}
				ns.setContent(result.get(i).getContent());
				show.add(ns);
				allid.add(result.get(i).getId());
			}else{
				i--;
			}
		}
		return show;
	}

	@Override
	public List<NoticeShow> searchTask(Notice notice) {
		// TODO Auto-generated method stub
		Notice notice1=new Notice();
		notice1.setType("作业");
		List<Notice> result = noticeDao.searchNotice(notice1);
		List<NoticeShow> show = new ArrayList<NoticeShow>();
		for(int i=0;i<result.size();i++){
			NoticeShow ns = new NoticeShow();
			ns.setId(result.get(i).getId());
			ns.setTitle(result.get(i).getTitle());
			UserAll ua = new UserAll();
			if(result.get(i).getSendUserId()!=null && result.get(i).getSendUserId()>0){
				ua=userDao.findUserAllById(result.get(i).getSendUserId());
				ns.setSender(ua.getNameUserAll());
			}else{
				ns.setSender("无");
			}
			ns.setContent(result.get(i).getContent());
			show.add(ns);
		}
		return show;
	}

	@Override
	public boolean addNotice(Notice notice) {
		// TODO Auto-generated method stub
		return noticeDao.addNotice(notice);
	}

	@Override
	public boolean updateNotice(Notice notice) {
		// TODO Auto-generated method stub
		return noticeDao.updateNotice(notice);
	}
}
