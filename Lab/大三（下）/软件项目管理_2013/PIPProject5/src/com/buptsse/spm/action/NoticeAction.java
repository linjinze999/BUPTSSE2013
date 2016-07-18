package com.buptsse.spm.action;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.buptsse.spm.domain.Notice;
import com.buptsse.spm.domain.NoticeShow;
import com.buptsse.spm.service.NoticeService;

public class NoticeAction {
	private static Logger LOG = LoggerFactory.getLogger(NoticeAction.class);
	private Notice notice;
	@Resource
	private NoticeService noticeService;
	
	//查询公告
	public String searchNotice(){
		JSONObject jo = new JSONObject();
		try{
			Notice notice=new Notice();
			notice.setType("公告");
			List<Notice> result =noticeService.searchNotice(notice);
			if(result!=null && result.size()>0){
				jo.put("id", result.get(0).getId());
				jo.put("content", result.get(0).getContent());
			}else{
				jo.put("content", "找不到公告~！");
			}
			ServletActionContext.getResponse().getWriter().write(jo.toString());
		}catch(Exception e){
			try {
				jo.put("content", "寻找过程发生未知错误~！");
				ServletActionContext.getResponse().getWriter().write(jo.toString());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return null;
	}
	
	//
	public String searchInform(){
		try{
			if(notice==null){
				JSONArray json = new JSONArray();
				ServletActionContext.getResponse().getWriter().write(json.toString());
			}else{
				if(notice.getReceiveUserId()==null || notice.getReceiveUserId()==0 || notice.getReceiveUserRole()==null){
					JSONArray json = new JSONArray();
					ServletActionContext.getResponse().getWriter().write(json.toString());
				}
				List<NoticeShow> result =noticeService.searchInform(notice);
				if(result!=null && result.size()>0){
					JSONArray json = new JSONArray();
					for(int i=0;i<result.size();i++){
						JSONObject jo = new JSONObject();
						jo.put("id", result.get(i).getId());
						jo.put("title", result.get(i).getTitle());
						jo.put("sender", result.get(i).getSender());
						jo.put("content", result.get(i).getContent());
						json.add(jo);
					}
					ServletActionContext.getResponse().getWriter().write(json.toString());
				}else{
					JSONArray json = new JSONArray();
					ServletActionContext.getResponse().getWriter().write(json.toString());
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	public String searchMessage(){
		try{
			if(notice==null){
				JSONArray json = new JSONArray();
				ServletActionContext.getResponse().getWriter().write(json.toString());
			}else{
				if(notice.getReceiveUserId()==null || notice.getReceiveUserId()==0 || notice.getReceiveUserRole()==null){
					JSONArray json = new JSONArray();
					ServletActionContext.getResponse().getWriter().write(json.toString());
				}
				List<NoticeShow> result =noticeService.searchMessage(notice);
				if(result!=null && result.size()>0){
					JSONArray json = new JSONArray();
					for(int i=0;i<result.size();i++){
						JSONObject jo = new JSONObject();
						jo.put("id", result.get(i).getId());
						jo.put("title", result.get(i).getTitle());
						jo.put("sender", result.get(i).getSender());
						jo.put("content", result.get(i).getContent());
						json.add(jo);
					}
					ServletActionContext.getResponse().getWriter().write(json.toString());
				}else{
					JSONArray json = new JSONArray();
					ServletActionContext.getResponse().getWriter().write(json.toString());
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	public String searchTask(){
		try{
			List<NoticeShow> result =noticeService.searchTask(notice);
			if(result!=null && result.size()>0){
				JSONArray json = new JSONArray();
				for(int i=0;i<result.size();i++){
					JSONObject jo = new JSONObject();
					jo.put("id", result.get(i).getId());
					jo.put("title", result.get(i).getTitle());
					jo.put("sender", result.get(i).getSender());
					jo.put("content", result.get(i).getContent());
					json.add(jo);
				}
				ServletActionContext.getResponse().getWriter().write(json.toString());
			}else{
				JSONArray json = new JSONArray();
				ServletActionContext.getResponse().getWriter().write(json.toString());
			}
		}catch(Exception e){
			e.printStackTrace();
			JSONArray json = new JSONArray();
			try {
				ServletActionContext.getResponse().getWriter().write(json.toString());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return null;
	}
	
	public String addNotice(){
		String str="";
		try{
			if(notice==null){
				str="失败：未找到数据。";
			}else{
				if(notice.getTitle()==null || "".equals(notice.getTitle())){
					str="失败：请输入标题。";
				}else if(notice.getType()==null || "".equals(notice.getType())){
					str="失败：未找到类型。";
				}else{
					boolean success=noticeService.addNotice(notice);
					if(success){
						str="成功：添加成功！等待页面刷新。";
					}else{
						str="失败：添加失败！请检查数据网络。";
					}
				}
			}
			String message = JSONObject.toJSONString(str);
			ServletActionContext.getResponse().getWriter().write(message);
		}catch(Exception e){
			e.printStackTrace();
			str="失败：添加失败！请检查数据网络。";
			String message = JSONObject.toJSONString(str);
			try {
				ServletActionContext.getResponse().getWriter().write(message);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		return null;
	}
	
	public String updateNotice(){
		String str="";
		try{
			if(notice==null){
				str="失败：未找到数据。";
			}else{
				boolean success=noticeService.updateNotice(notice);
				if(success){
					str="成功：修改成功！等待页面刷新。";
				}else{
					str="失败：修改失败！请检查数据网络。";
				}
			}
			String message = JSONObject.toJSONString(str);
			ServletActionContext.getResponse().getWriter().write(message);
		}catch(Exception e){
			e.printStackTrace();
			str="失败：修改失败！请检查数据网络。";
			String message = JSONObject.toJSONString(str);
			try {
				ServletActionContext.getResponse().getWriter().write(message);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return null;
	}
	
	public Notice getNotice(){
		return notice;
	}
	public void setNotice(Notice notice){
		this.notice = notice;
	}
}
