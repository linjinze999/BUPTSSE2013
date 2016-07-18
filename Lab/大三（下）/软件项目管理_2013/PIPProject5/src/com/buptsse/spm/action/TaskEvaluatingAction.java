package com.buptsse.spm.action;

import java.io.IOException;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.buptsse.spm.domain.Team;
import com.buptsse.spm.domain.UserAll;
import com.buptsse.spm.service.TeamService;
import com.buptsse.spm.service.UserAllService;

public class TaskEvaluatingAction {
	private static Logger LOG = LoggerFactory.getLogger(TaskEvaluatingAction.class);
	private Integer score1;
	private Integer score2;
	private Integer type;
	private Integer teamId;
	private String membersid;
	private String membersscore;
	@Resource
	private TeamService teamService;
	@Resource
	private UserAllService userService;
	
	public String updateScore(){
		JSONObject jo = new JSONObject();
		try{
			if(teamId==null || teamId<0){
				jo.put("error", "错误：未找到团队！");
			}else if(type==null){
				jo.put("error", "错误：未找到修改目标！");
			}else if(type>0){
				if( score1<0 || score1>10){
					jo.put("error", "错误：分数超出0~10！");
				}else{
					Team team = new Team();
					team.setId(teamId);
					team = this.teamService.findTeamByTeamId(team);
					if(team!=null){
						if(type==1){
							team.setDeployScore(score1);
						}else if(type==2){
							team.setLoginScore(score1);
						}else if(type==3){
							team.setLinkScore(score1);
						}else if(type==4){
							team.setFunctionScore(score1);
						}else if(type==5){
							team.setPerformanceScore(score1);
						}
						
						int result = this.teamService.updateTeamScore(team);
						if(result>0){
							jo.put("success", "成功：修改评分成功！");
						}else{
							jo.put("error", "失败：修改评分出错！");
						}
					}else{
						jo.put("error", "错误：未找到团队！");
					}
				}
			}else if(type<0){
				if( score1<0 || score1>10 || score2<0 || score2>10){
					jo.put("error", "错误：分数超出0~10！");
				}else{
					Team team = new Team();
					team.setId(teamId);
					team = this.teamService.findTeamByTeamId(team);
					if(team!=null){
						if(type==-1){
							team.setCode1Score(score1);
							team.setCode2Score(score2);
						}else if(type==-2){
							team.setDocument1Score(score1);
							team.setDocument2Score(score2);
						}
						int result = this.teamService.updateTeamScore(team);
						if(result>0){
							jo.put("success", "成功：修改评分成功！");
						}else{
							jo.put("error", "失败：修改评分出错！");
						}
					}else{
						jo.put("error", "错误：未找到团队！");
					}
				}
			}
			
			ServletActionContext.getResponse().getWriter().write(jo.toString());
		}catch(Exception e){
			e.printStackTrace();
			jo.put("error", "错误：修改评分出错！");
			try {
				ServletActionContext.getResponse().getWriter().write(jo.toString());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return null;
	}
	
	public String findTeamLastestScore(){
		JSONObject jo = new JSONObject();
		try{
			if(teamId==null || teamId<0){
				jo.put("error", "错误：未找到团队！");
			}else{
				Team team = new Team();
				team.setId(teamId);
				team = this.teamService.findTeamByTeamId(team);
				if(team!=null){
					if(team.getScore()!=null){
						jo.put("success", team.getScore());
					}
				}else{
					jo.put("error", "错误：未找到团队！");
				}
			}
			
			ServletActionContext.getResponse().getWriter().write(jo.toString());
		}catch(Exception e){
			e.printStackTrace();
			jo.put("error", "错误：获取团队最新分数发生错误！");
			try {
				ServletActionContext.getResponse().getWriter().write(jo.toString());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return null;
	}
	
	public String updateMemberScore(){
		JSONObject jo = new JSONObject();
		try{
			if(membersid==null || membersscore==null){
				jo.put("error", "错误：未找到成员！");
			}else{
				String[] ids = membersid.split(";");
				String[] scores = membersscore.split(";");
				int count=0;
				for(int i=0;i<ids.length;i++){
					try{
						System.out.println(ids[i]);
						System.out.println(scores[i]);
						int id = Integer.parseInt(ids[i]);
						int score = Integer.parseInt(scores[i]);
						UserAll userAll = new UserAll();
						System.out.println(ids[i]);
						userAll.setIdUserAll(id);
						userAll.setScoreUserAll(score);
						int r=this.userService.updateUserAllScore(userAll);
						if(r<=0)
							count++;
					}catch(Exception e){
						count++;
					}
				}
				if(count>0){
					jo.put("success", "成功：修改成功.但有"+count+"名成员修改失败，请注意分数格式！");
				}else
					jo.put("success", "成功：修改成员分数成功！");
			}
			ServletActionContext.getResponse().getWriter().write(jo.toString());
		}catch(Exception e){
			e.printStackTrace();
			jo.put("error", "错误：获取团队最新分数发生错误！");
			try {
				ServletActionContext.getResponse().getWriter().write(jo.toString());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return null;
	}
	/*
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
					}else{
						String[] membersid = result.get(i).getMember().split(";");
						String members="";
						if(membersid!=null && membersid.length>0){
							for(int j=0;j<membersid.length;j++){
								UserAll ua = userService.findUserAllById(Integer.parseInt(membersid[j]));
								members+=ua.getNameUserAll()+";";
							}
						}
						jo.put("membername", members);
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
	*/

	public Integer getTeamId() {
		return teamId;
	}

	public void setTeamId(Integer teamId) {
		this.teamId = teamId;
	}


	public Integer getType() {
		return type;
	}


	public void setType(Integer type) {
		this.type = type;
	}


	public Integer getScore1() {
		return score1;
	}


	public void setScore1(Integer score1) {
		this.score1 = score1;
	}


	public Integer getScore2() {
		return score2;
	}


	public void setScore2(Integer score2) {
		this.score2 = score2;
	}

	public String getMembersid() {
		return membersid;
	}

	public void setMembersid(String membersid) {
		this.membersid = membersid;
	}

	public String getMembersscore() {
		return membersscore;
	}

	public void setMembersscore(String membersscore) {
		this.membersscore = membersscore;
	}
}
