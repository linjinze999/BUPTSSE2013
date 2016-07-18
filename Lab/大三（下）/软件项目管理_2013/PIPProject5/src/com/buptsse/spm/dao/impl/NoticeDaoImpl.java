package com.buptsse.spm.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.buptsse.spm.dao.NoticeDao;
import com.buptsse.spm.domain.Notice;

@Repository
public class NoticeDaoImpl extends BaseDAOImpl<Notice> implements NoticeDao{
	private static Logger LOG = Logger.getLogger(NoticeDaoImpl.class);

	@Override
	public List<Notice> find(String hql, Notice[] param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Notice get(String hql, Notice[] param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long count(String hql, Notice[] param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer executeHql(String hql, Notice[] param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Notice> searchNotice(Notice notice) {
		// TODO Auto-generated method stub
		String sql="from Notice";
		String str="";
		boolean condition=false;
		if(notice.getTitle()!=null && !"".equals(notice.getTitle())){
			str+=" where title_notice like'%"+notice.getId()+"%'";
			condition = true;
		}
		if(notice.getContent()!=null && !"".equals(notice.getContent())){
			if(condition){
				str+=" and content_notice like'%"+notice.getContent()+"%'";
			}else{
				str+=" where content_notice like'%"+notice.getContent()+"%'";
				condition = true;
			}
		}
		if(notice.getType()!=null && !"".equals(notice.getType())){
			if(condition){
				str+=" and type_notice ='"+notice.getType()+"'";
			}else{
				str+=" where type_notice ='"+notice.getType()+"'";
				condition = true;
			}
		}
		if(notice.getReceiveUserId()!=null && notice.getReceiveUserId()>0){
			if(condition){
				str+=" and receive_userid_notice ="+notice.getReceiveUserId();
			}else{
				str+=" where receive_userid_notice ="+notice.getReceiveUserId();
				condition = true;
			}
		}
		if(notice.getReceiveUserRole()!=null && !"".equals(notice.getReceiveUserRole())){
			if(condition){
				str+=" and receive_userrole_notice like'%"+notice.getReceiveUserRole()+"%'";
			}else{
				str+=" where receive_userrole_notice like'%"+notice.getReceiveUserRole()+"%'";
				condition = true;
			}
		}
		if(notice.getSendUserId()!=null && notice.getSendUserId()>0){
			if(condition){
				str+=" and send_userid_notice ="+notice.getSendUserId();
			}else{
				str+=" where send_userid_notice ="+notice.getSendUserId();
				condition = true;
			}
		}
		
		if(condition){
			sql+=str;
		}
		return super.find(sql);
	}

	@Override
	public Notice findNotice(Notice notice) {
		// TODO Auto-generated method stub
		String sql="from Notice";
		String str="";
		boolean condition=false;
		if(notice.getId()>0){
			str+=" where id_notice ="+notice.getId();
			sql+=str;
			List<Notice> result = super.find(sql);
			if(result!=null && result.size()>0){
				return result.get(0);
			}else{
				return null;
			}
		}
		
		return null;
		/*
		if(notice.getTitle()!=null && !"".equals(notice.getTitle())){
			str+=" where title_notice like'%"+notice.getId()+"%'";
			condition = true;
		}
		if(notice.getContent()!=null && !"".equals(notice.getContent())){
			if(condition){
				str+=" and content_notice like'%"+notice.getContent()+"%'";
			}else{
				str+=" where content_notice like'%"+notice.getContent()+"%'";
				condition = true;
			}
		}
		if(notice.getType()!=null && !"".equals(notice.getType())){
			if(condition){
				str+=" and type_notice ='"+notice.getType()+"'";
			}else{
				str+=" where type_notice ='"+notice.getType()+"'";
				condition = true;
			}
		}
		
		if(condition){
			sql+=str;
		}
		return super.find(sql);
		*/
	}

	@Override
	public boolean addNotice(Notice notice) {
		// TODO Auto-generated method stub
		try{
			super.save(notice);
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean updateNotice(Notice notice) {
		// TODO Auto-generated method stub
		try{
			super.update(notice);
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
