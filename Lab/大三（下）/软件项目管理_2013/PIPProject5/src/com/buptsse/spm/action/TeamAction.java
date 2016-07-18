package com.buptsse.spm.action;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.buptsse.spm.domain.Team;
import com.buptsse.spm.domain.UserAll;
import com.buptsse.spm.service.TeamService;
import com.buptsse.spm.service.UserAllService;

public class TeamAction {
	private static Logger LOG = LoggerFactory.getLogger(TeamAction.class);
	private Integer memberId;
	private Team team;
	@Resource
	private TeamService teamService;
	@Resource
	private UserAllService userService;
	@Resource
	private UserAllService userAllService;
	
	public String searchAllTeam(){
		try{
			List<Team> result = teamService.searchAllTeam();
			if(result==null || result.size()==0){
				JSONArray json = new JSONArray();
				ServletActionContext.getResponse().getWriter().write(json.toString());
			}else{
				JSONArray json = new JSONArray();
				for(int i=0;i<result.size();i++){
					JSONObject jo = new JSONObject();
					jo.put("id", result.get(i).getId());
					jo.put("name", result.get(i).getName());
					jo.put("member", result.get(i).getMember());
					if(result.get(i).getUrl()!=null && !"".equals(result.get(i).getUrl())){
						jo.put("url", result.get(i).getUrl());
					}else{
						jo.put("url", "未部署");
					}
					if(result.get(i).getMember()==null || "".equals(result.get(i).getMember())){
						jo.put("membername", "");
						jo.put("memberscore", "");
					}else{
						String[] membersid = result.get(i).getMember().split(";");
						String members="";
						String memberscore="";
						if(membersid!=null && membersid.length>0){
							for(int j=0;j<membersid.length;j++){
								UserAll ua = userService.findUserAllById(Integer.parseInt(membersid[j]));
								members+=ua.getNameUserAll()+";";
								if(ua.getScoreUserAll()==null){
									memberscore+="0;";
								}else{
									memberscore+=ua.getScoreUserAll()+";";
								}
							}
						}
						jo.put("membername", members);
						jo.put("memberscore", memberscore);
					}
					jo.put("money", result.get(i).getMoney());
					jo.put("code", result.get(i).getCode());
					jo.put("deployUrl", result.get(i).getUrl());
					jo.put("requireDocu", result.get(i).getRequireDocu());
					jo.put("designDocu", result.get(i).getDesignDocu());
					jo.put("detailDocu", result.get(i).getDetailDocu());
					jo.put("score", result.get(i).getScore());
					jo.put("deployScore", result.get(i).getDeployScore());
					jo.put("loginScore", result.get(i).getLoginScore());
					jo.put("functionScore", result.get(i).getFunctionScore());
					jo.put("performanceScore", result.get(i).getPerformanceScore());
					jo.put("code1Score", result.get(i).getCode1Score());
					jo.put("document1Score", result.get(i).getDocument1Score());
					jo.put("code2Score", result.get(i).getCode2Score());
					jo.put("document2Score", result.get(i).getDocument2Score());
					jo.put("linkScore", result.get(i).getLinkScore());
					json.add(jo);
				}
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
	
	public String findTeamByMember(){
		try{
			JSONObject jo = new JSONObject();
			if(memberId==null){
				ServletActionContext.getResponse().getWriter().write(jo.toString());
			}else{
				Team result = teamService.searchTeamByMember(memberId);
				if(result==null){
					ServletActionContext.getResponse().getWriter().write(jo.toString());
				}else{
					jo.put("id", result.getId());
					jo.put("name", result.getName());
					String[] membersid = result.getMember().split(";");
					String members="";
					if(membersid!=null && membersid.length>0){
						for(int i=0;i<membersid.length;i++){
							UserAll ua = userService.findUserAllById(Integer.parseInt(membersid[i]));
							members+=ua.getNameUserAll();
							if(i<membersid.length-1){
								members+="、";
							}
						}
					}
					jo.put("member", members);
					jo.put("money", result.getMoney());
					if(result.getUrl()!=null && !"".equals(result.getUrl())){
						jo.put("url", result.getUrl());
					}else{
						jo.put("url", "未部署");
					}
					if(result.getCode()!=null && !"".equals(result.getCode())){
						jo.put("code", result.getCode());
					}else{
						jo.put("code", "未提交");
					}
					if(result.getRequireDocu()!=null && !"".equals(result.getRequireDocu())){
						jo.put("requireDocu", result.getRequireDocu());
					}else{
						jo.put("requireDocu", "未提交");
					}
					if(result.getDesignDocu()!=null && !"".equals(result.getDesignDocu())){
						jo.put("designDocu", result.getDesignDocu());
					}else{
						jo.put("designDocu", "未提交");
					}
					if(result.getDetailDocu()!=null && !"".equals(result.getDetailDocu())){
						jo.put("detailDocu", result.getDetailDocu());
					}else{
						jo.put("detailDocu", "未提交");
					}
					
					jo.put("score", result.getScore());
					jo.put("deployScore", result.getDeployScore());
					jo.put("loginScore", result.getLoginScore());
					jo.put("functionScore", result.getFunctionScore());
					jo.put("performanceScore", result.getPerformanceScore());
					jo.put("code1Score", result.getCode1Score());
					jo.put("document1Score", result.getDocument1Score());
					jo.put("code2Score", result.getCode2Score());
					jo.put("document2Score", result.getDocument2Score());
					jo.put("linkScore", result.getLinkScore());
					UserAll ua=userAllService.findUserAllById(memberId);
					jo.put("personScore", ua.getScoreUserAll());
				}
				ServletActionContext.getResponse().getWriter().write(jo.toString());
			}
		}catch(Exception e){
			e.printStackTrace();
			JSONObject jo = new JSONObject();
			try {
				ServletActionContext.getResponse().getWriter().write(jo.toString());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return null;
	}
	
	public String addTeam(){
		String str="";
		try{
			int result = teamService.addTeam(team);
			if(result==-2){
				str="失败：添加团队失败，请检查数据、网络。";
			}else if(result==-1){
				str="失败：已有该团队名。";
			}else if(result>0){
				str="添加成功，但有"+result+"位成员未分配到该团队。";
			}else{
				str="成功：添加团队成功，";
			}
			String message = JSONObject.toJSONString(str);
			ServletActionContext.getResponse().getWriter().write(message);
		}catch(Exception e){
			e.printStackTrace();
			str="错误：添加时发生未知错误。";
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
	
	public String deleteTeam(){
		String str="";
		try{
			int result = teamService.deleteTeam(team);
			if(result==-1){
				str="失败：删除团队失败，请检查数据、网络。";
			}else if(result>0){
				str="删除成功，但有"+result+"位成员未移除其团队信息。";
			}else{
				str="成功：删除团队成功.";
			}
			String message = JSONObject.toJSONString(str);
			ServletActionContext.getResponse().getWriter().write(message);
		}catch(Exception e){
			e.printStackTrace();
			str="错误：删除时发生未知错误。";
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
	
	public String updatePartTeam(){
		String str="";
		try{
			if(team==null || team.getId()==0){
				str="失败：未选择要更新的团队。";
			}else{
				int result = teamService.updatePartTeam(team);
				if(result==-1){
					str="失败：更新团队失败，请检查数据、网络。";
				}else if(result==-2){
					str="失败：未选择要更新的团队。";
				}else if(result>0){
					str="更新成功，但有"+result+"次成员的团队信息更新失败。";
				}else{
					str="成功：更新团队成功.";
				}
			}
			String message = JSONObject.toJSONString(str);
			ServletActionContext.getResponse().getWriter().write(message);
		}catch(Exception e){
			e.printStackTrace();
			str="错误：更新时发生未知错误。";
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
	
	public String updateTeamScore(){
		String str="";
		try{
			if(team==null || team.getId()==0){
				str="失败：未选择要更新的团队。";
			}else{
				int result = teamService.updateTeamScore(team);
				if(result==-1){
					str="失败：更新团队成绩失败，请检查数据、网络。";
				}else if(result==-2){
					str="失败：未选择要更新的团队。";
				}else{
					str="成功：更新团队成绩成功.";
				}
			}
			String message = JSONObject.toJSONString(str);
			ServletActionContext.getResponse().getWriter().write(message);
		}catch(Exception e){
			e.printStackTrace();
			str="错误：更新时发生未知错误。";
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
	
	public Integer getMemberId(){
		return memberId;
	}
	public void setMemberId(Integer memberId){
		this.memberId = memberId;
	}
	public Team getTeam(){
		return team;
	}
	public void setTeam(Team team){
		this.team = team;
	}
}
