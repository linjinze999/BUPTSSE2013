package com.buptsse.spm.action;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.buptsse.spm.domain.Page;
import com.buptsse.spm.service.PageRoleService;
import com.buptsse.spm.service.PageService;
import com.opensymphony.xwork2.ActionContext;

/**
 * @author Jinze Lin
 * @date 2016年04月14日 上午10:40
 * @description 实现角色页面管理逻辑
 * @modify
 * @modifyDate
 */
@Controller
public class PageRoleAction {
	private static Logger LOG = LoggerFactory.getLogger(PageRoleAction.class);
	private String role;
	private Integer pageId;
	private List<Integer> updatePages;
	@Resource
	private PageRoleService pageRoleService;
	@Resource
	private PageService pageService;
	
	@ResponseBody
	public String searchPagesByRole(){
		try{
			Map session = (Map) ActionContext.getContext().getSession();
			role = session.get("userRole").toString();
			List<Integer> pageids = pageRoleService.searchPagesByRole(role);
			List<Page> pages= pageService.findPageByIds(pageids);
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
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	public String searchPagesByRoleIn(){
		try{
			List<Integer> pageids = pageRoleService.searchPagesByRole(role);
			List<Page> pages= pageService.findRootPageByIds(pageids);
			JSONArray json = new JSONArray();
			for(int i=0; i<pages.size(); i++){
				JSONObject jo = new JSONObject();
				jo.put("id", pages.get(i).getId());
				jo.put("name", pages.get(i).getName());
				jo.put("link", pages.get(i).getLink());
				jo.put("img", pages.get(i).getImg());
				jo.put("level", pages.get(i).getLevel());
				jo.put("parentId", pages.get(i).getParentId());
				json.add(jo);
				
			}
			ServletActionContext.getResponse().getWriter().write(json.toString());
		}catch(Exception e){}
		return null;
	}
	public String updateRolePages(){
		String str="";
		try{
			//List<Integer> pageids = pageRoleService.searchPagesByRole(role);
			//List<Page> pages= pageService.findPageByIds(pageids);
			boolean success = pageRoleService.updateRolePages(role, updatePages);
			if(success){
				str="成功：分配页面成功！等待页面刷新。";
			}else{
				str="失败：分配页面失败！请检查数据网络。";
			}
			String message = JSONObject.toJSONString(str);
			ServletActionContext.getResponse().getWriter().write(message);
		}catch(Exception e){
			str="失败：分配页面失败！请检查数据网络。";
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
	
	
	public String getRole(){
		return role;
	}
	public void setRole(String role){
		this.role = role;
	}
	public Integer getPageId(){
		return pageId;
	}
	public void setPageId(Integer pageId){
		this.pageId = pageId;
	}
	public PageService getPageService(){
		return pageService;
	}
	public void setPageService(PageService pageService){
		this.pageService = pageService;
	}
	public PageRoleService getPageRoleService(){
		return pageRoleService;
	}
	public void setPageRoleService(PageRoleService pageRoleService){
		this.pageRoleService = pageRoleService;
	}
	public List<Integer> getUpdatePages(){
		return updatePages;
	}
	public void setUpdatePages(List<Integer> updatePages){
		this.updatePages = updatePages;
	}
}
