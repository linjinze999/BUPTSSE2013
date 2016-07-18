package com.buptsse.spm.action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.buptsse.spm.domain.Page;
import com.buptsse.spm.service.PageService;
import com.opensymphony.xwork2.ActionContext;

/**
 * @author Jinze Lin
 * @date 2016年04月14日 上午9:40
 * @description 实现页面管理逻辑
 * @modify
 * @modifyDate
 */
public class PageAction {
	private static Logger LOG = LoggerFactory.getLogger(PageAction.class);
	private Page page;
	//private String pageName;
	@Resource
	private PageService pageService;
	
	public String searchPageLevel1(){
		try{
			List<Page> pages= pageService.searchPageLevel1();
			JSONArray json = new JSONArray();
			for(int i=0; i<pages.size(); i++){
					JSONObject jo = new JSONObject();
					jo.put("id", pages.get(i).getId());
					jo.put("name", pages.get(i).getName());
					jo.put("link", pages.get(i).getLink());
					jo.put("img", pages.get(i).getImg());
					jo.put("level", pages.get(i).getLevel());
					jo.put("parentId", pages.get(i).getParentId());
					jo.put("dcb", pages.get(i).getDescrib());
					json.add(jo);
			}
			ServletActionContext.getResponse().getWriter().write(json.toString());
		}catch(Exception e){}
		return null;
	}
	
	public String addPage(){
		String str="";
		try{
			boolean success=pageService.insertPage(page);
			if(success){
				str="成功：添加页面成功！等待页面刷新。";
			}else{
				str="失败：添加页面失败！请检查数据网络。";
			}
			String message = JSONObject.toJSONString(str);
			ServletActionContext.getResponse().getWriter().write(message);
		}catch(Exception e){
			e.printStackTrace();
			str="失败：添加页面失败！请检查数据网络。";
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
	
	public String deletePage(){
		String str="";
		try{
			boolean success=pageService.deletePage(page);
			if(success){
				str="成功：删除页面成功！等待页面刷新。";
			}else{
				str="失败：删除页面失败！请检查数据网络。";
			}
			String message = JSONObject.toJSONString(str);
			ServletActionContext.getResponse().getWriter().write(message);
		}catch(Exception e){
			e.printStackTrace();
			str="失败：删除页面失败！请检查数据网络。";
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
	
	public String updatePage(){
		String str="";
		try{
			boolean success=pageService.updatePage(page);
			if(success){
				str="成功：修改页面成功！等待页面刷新。";
			}else{
				str="失败：修改页面失败！请检查数据网络。";
			}
			String message = JSONObject.toJSONString(str);
			ServletActionContext.getResponse().getWriter().write(message);
		}catch(Exception e){
			e.printStackTrace();
			str="失败：修改页面失败！请检查数据网络。";
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
	
	public String searchAllPages(){
		try{
			List<Page> pages= pageService.searchAllPage();
			JSONArray json = new JSONArray();
			for(int i=0; i<pages.size(); i++){
				if(pages.get(i).getLevel()==1){
					JSONObject jo = new JSONObject();
					jo.put("id", pages.get(i).getId());
					jo.put("name", pages.get(i).getName());
					jo.put("link", pages.get(i).getLink());
					jo.put("img", pages.get(i).getImg());
					jo.put("level", pages.get(i).getLevel());
					jo.put("parentId", pages.get(i).getParentId());
					jo.put("dcb", pages.get(i).getDescrib());
					JSONArray jsona = new JSONArray();
					for(int n=0; n<pages.size(); n++){
						if(pages.get(n).getLevel()==2 && pages.get(n).getParentId()==pages.get(i).getId()){
							JSONObject jo1 = new JSONObject();
							jo1.put("id", pages.get(n).getId());
							jo1.put("name", pages.get(n).getName());
							jo1.put("link", pages.get(n).getLink());
							jo1.put("img", pages.get(n).getImg());
							jo1.put("level", pages.get(n).getLevel());
							jo1.put("parentId", pages.get(n).getParentId());
							jo1.put("dcb", pages.get(n).getDescrib());
							JSONArray jsonar = new JSONArray();
							for(int m=0; m<pages.size(); m++){
								if(pages.get(m).getLevel()==3 && pages.get(m).getParentId()==pages.get(n).getId()){
									JSONObject jo2 = new JSONObject();
									jo2.put("id", pages.get(m).getId());
									jo2.put("name", pages.get(m).getName());
									jo2.put("link", pages.get(m).getLink());
									jo2.put("img", pages.get(m).getImg());
									jo2.put("level", pages.get(m).getLevel());
									jo2.put("parentId", pages.get(m).getParentId());
									jo2.put("dcb", pages.get(m).getDescrib());
									jsonar.add(jo2);
								}
							}
							jo1.put("childPages", jsonar);
							jsona.add(jo1);
						}
					}
					jo.put("childPages", jsona);
					json.add(jo);
				}
				
			}
			ServletActionContext.getResponse().getWriter().write(json.toString());
		}catch(Exception e){}
		return null;
	}
	
	public String searchAllPagesNolayered(){
		try{
			List<Page> pages= pageService.searchAllPage();
			JSONArray json = new JSONArray();
			for(int i=0; i<pages.size(); i++){
					JSONObject jo = new JSONObject();
					jo.put("id", pages.get(i).getId());
					jo.put("name", pages.get(i).getName());
					jo.put("link", pages.get(i).getLink());
					jo.put("img", pages.get(i).getImg());
					jo.put("level", pages.get(i).getLevel());
					jo.put("parentId", pages.get(i).getParentId());
					jo.put("dcb", pages.get(i).getDescrib());
					json.add(jo);
			}
			ServletActionContext.getResponse().getWriter().write(json.toString());
		}catch(Exception e){}
		return null;
	}
	
	public PageService getPageService(){
		return pageService;
	}
	public void setPageService(PageService pageService){
		this.pageService = pageService;
	}
	public Page getPage(){
		return page;
	}
	public void setPage(Page page){
		this.page = page;
	}
	/*
	public String getPageName(){
		return pageName;
	}
	public void setPageName(String pageName){
		try {
			this.pageName = URLDecoder.decode(pageName, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	*/
}
