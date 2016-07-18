package com.buptsse.spm.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.buptsse.spm.dao.TeamDao;
import com.buptsse.spm.dao.UserAllDao;
import com.buptsse.spm.domain.Team;
import com.buptsse.spm.domain.UserAll;
import com.buptsse.spm.service.TeamService;

@Transactional
@Service
public class TeamServiceImpl implements TeamService{
	@Resource
	private TeamDao teamDao;
	@Resource
	private UserAllDao userAllDao;

	@Override
	public List<Team> searchAllTeam() {
		// TODO Auto-generated method stub
		return teamDao.searchAllTeam();
	}

	@Override
	public Team searchTeamByMember(Integer memberid) {
		// TODO Auto-generated method stub
		return teamDao.searchTeamByMember(memberid);
	}

	@Override
	public int addTeam(Team team) {
		// TODO Auto-generated method stub
		//向团队表中添加团队
		int add = teamDao.addTeam(team);
		int result = 0;
		if(add>0){
			//若成员不为空，则添加成员表中团队的id
			if(team.getMember()!=null && !"".equals(team.getMember())){
				//获取团队id
				List<Team> teamList = teamDao.searchAllTeam();
				Integer teamid = teamList.get(teamList.size()-1).getId();
				//获取成员id
				String[] mem = team.getMember().split(";");
				//循环添加成员表中团队的id
				for(int i=0;i<mem.length;i++){
					if(mem[i]!=null && !"".equals(mem[i])){
						//若更新成员的团队id失败，则记录失败几次
						try{
							Integer memid = Integer.parseInt(mem[i]);
							UserAll ua = userAllDao.findUserAllById(memid);
							ua.setTeamidUserAll(teamid);
							if(!userAllDao.updateUserAll(ua)){
								result++;
							}
						}catch(Exception e){
							e.printStackTrace();
							result++;
						}
					}
				}
			}
			return result;
		}
		return add;
	}

	@Override
	public int deleteTeam(Team team) {
		// TODO Auto-generated method stub
		Team oldTeam = teamDao.searchTeamByTeam(team);
		//删除团队表中的团队
		boolean delete = teamDao.deleteTeam(team);
		int result = 0;
		if(delete){
			//若成员不为空，则删除成员表中团队的id
			if(oldTeam.getMember()!=null && !"".equals(oldTeam.getMember())){
				//获取成员id
				String[] mem = oldTeam.getMember().split(";");
				//循环删除成员表中团队的id
				for(int i=0;i<mem.length;i++){
					if(mem[i]!=null && !"".equals(mem[i])){
						//若更新成员的团队id失败，则记录失败几次
						try{
							Integer memid = Integer.parseInt(mem[i]);
							UserAll ua = userAllDao.findUserAllById(memid);
							ua.setTeamidUserAll(null);
							if(!userAllDao.updateUserAll(ua)){
								result++;
							}
						}catch(Exception e){
							e.printStackTrace();
							result++;
						}
					}
				}
			}
			return result;
		}
		return -1;
	}

	@Override
	public int updateTeam(Team team) {
		// TODO Auto-generated method stub
		//修改团队表中的团队
		if(team.getId()<=0){
			return -2;
		}
		Team oldTeam = teamDao.searchTeamByTeam(team);
		boolean delete = teamDao.updateTeam(team);
		int result = 0;
		if(delete){
			/* --------------------------删除旧成员的团队信息-----------------------*/
			//若旧成员不为空，则删除成员表中团队的id
			if(oldTeam.getMember()!=null && !"".equals(oldTeam.getMember())){
				//获取成员id
				String[] mem = oldTeam.getMember().split(";");
				//循环删除成员表中团队的id
				for(int i=0;i<mem.length;i++){
					if(mem[i]!=null && !"".equals(mem[i])){
						//若更新成员的团队id失败，则记录失败几次
						try{
							Integer memid = Integer.parseInt(mem[i]);
							UserAll ua = userAllDao.findUserAllById(memid);
							ua.setTeamidUserAll(null);
							if(!userAllDao.updateUserAll(ua)){
								result++;
							}
						}catch(Exception e){
							e.printStackTrace();
							result++;
						}
					}
				}
			}
			/* --------------------------添加新成员的团队信息-----------------------*/
			//若成员不为空，则添加成员表中团队的id
			if(team.getMember()!=null && !"".equals(team.getMember())){
				//获取团队id
				Integer teamid = team.getId();
				//获取成员id
				String[] mem = team.getMember().split(";");
				//循环添加成员表中团队的id
				for(int i=0;i<mem.length;i++){
					if(mem[i]!=null && !"".equals(mem[i])){
						//若更新成员的团队id失败，则记录失败几次
						try{
							Integer memid = Integer.parseInt(mem[i]);
							UserAll ua = userAllDao.findUserAllById(memid);
							ua.setTeamidUserAll(teamid);
							if(!userAllDao.updateUserAll(ua)){
								result++;
							}
						}catch(Exception e){
							e.printStackTrace();
							result++;
						}
					}
				}
			}
			return result;
		}
		return -1;
	}

	@Override
	public Team findTeamByTeamId(Team team) {
		// TODO Auto-generated method stub
		return teamDao.searchTeamByTeam(team);
	}

	@Override
	public int updatePartTeam(Team team) {
		// TODO Auto-generated method stub
		//修改团队表中的团队
		if(team.getId()<=0){
			return -2;
		}
		Team oldTeam = teamDao.searchTeamByTeam(team);
		Team newTeam = oldTeam;
		newTeam.setName(team.getName());
		newTeam.setMoney(team.getMoney());
		newTeam.setMember(team.getMember());
		boolean delete = teamDao.updateTeam(newTeam);
		int result = 0;
		if(delete){
			/* --------------------------删除旧成员的团队信息-----------------------*/
			//若旧成员不为空，则删除成员表中团队的id
			if(oldTeam.getMember()!=null && !"".equals(oldTeam.getMember())){
				//获取成员id
				String[] mem = oldTeam.getMember().split(";");
				//循环删除成员表中团队的id
				for(int i=0;i<mem.length;i++){
					if(mem[i]!=null && !"".equals(mem[i])){
						//若更新成员的团队id失败，则记录失败几次
						try{
							Integer memid = Integer.parseInt(mem[i]);
							UserAll ua = userAllDao.findUserAllById(memid);
							ua.setTeamidUserAll(null);
							if(!userAllDao.updateUserAll(ua)){
								result++;
							}
						}catch(Exception e){
							e.printStackTrace();
							result++;
						}
					}
				}
			}
			/* --------------------------添加新成员的团队信息-----------------------*/
			//若成员不为空，则添加成员表中团队的id
			if(team.getMember()!=null && !"".equals(team.getMember())){
				//获取团队id
				Integer teamid = team.getId();
				//获取成员id
				String[] mem = team.getMember().split(";");
				//循环添加成员表中团队的id
				for(int i=0;i<mem.length;i++){
					if(mem[i]!=null && !"".equals(mem[i])){
						//若更新成员的团队id失败，则记录失败几次
						try{
							Integer memid = Integer.parseInt(mem[i]);
							UserAll ua = userAllDao.findUserAllById(memid);
							ua.setTeamidUserAll(teamid);
							if(!userAllDao.updateUserAll(ua)){
								result++;
							}
						}catch(Exception e){
							e.printStackTrace();
							result++;
						}
					}
				}
			}
			return result;
		}
		return -1;
	}

	@Override
	public int updateTeamScore(Team team) {
		// TODO Auto-generated method stub
		if(team.getId()<=0){
			return -2;
		}
		Team oldTeam = teamDao.searchTeamByTeam(team);
		Team newTeam = oldTeam;
		newTeam.setScore(0);
		if(team.getCode1Score()!=null){
			newTeam.setCode1Score(team.getCode1Score());
			newTeam.setScore(newTeam.getScore()+team.getCode1Score());
		}else if(newTeam.getCode1Score()!=null){
			newTeam.setScore(newTeam.getScore()+newTeam.getCode1Score());
		}
		if(team.getCode2Score()!=null){
			newTeam.setCode2Score(team.getCode2Score());
			newTeam.setScore(newTeam.getScore()+team.getCode2Score());
		}else if(newTeam.getCode2Score()!=null){
			newTeam.setScore(newTeam.getScore()+newTeam.getCode2Score());
		}
		if(team.getDeployScore()!=null){
			newTeam.setDeployScore(team.getDeployScore());
			newTeam.setScore(newTeam.getScore()+team.getDeployScore());
		}else if(newTeam.getDeployScore()!=null){
			newTeam.setScore(newTeam.getScore()+newTeam.getDeployScore());
		}
		if(team.getDocument1Score()!=null){
			newTeam.setDocument1Score(team.getDocument1Score());
			newTeam.setScore(newTeam.getScore()+team.getDocument1Score());
		}else if(newTeam.getDocument1Score()!=null){
			newTeam.setScore(newTeam.getScore()+newTeam.getDocument1Score());
		}
		if(team.getDocument2Score()!=null){
			newTeam.setDocument2Score(team.getDocument2Score());
			newTeam.setScore(newTeam.getScore()+team.getDocument2Score());
		}else if(newTeam.getDocument2Score()!=null){
			newTeam.setScore(newTeam.getScore()+newTeam.getDocument2Score());
		}
		if(team.getFunctionScore()!=null){
			newTeam.setFunctionScore(team.getFunctionScore());
			newTeam.setScore(newTeam.getScore()+team.getFunctionScore());
		}else if(newTeam.getFunctionScore()!=null){
			newTeam.setScore(newTeam.getScore()+newTeam.getFunctionScore());
		}
		if(team.getLinkScore()!=null){
			newTeam.setLinkScore(team.getLinkScore());
			newTeam.setScore(newTeam.getScore()+team.getLinkScore());
		}else if(newTeam.getLinkScore()!=null){
			newTeam.setScore(newTeam.getScore()+newTeam.getLinkScore());
		}
		if(team.getLoginScore()!=null){
			newTeam.setLoginScore(team.getLoginScore());
			newTeam.setScore(newTeam.getScore()+team.getLoginScore());
		}else if(newTeam.getLoginScore()!=null){
			newTeam.setScore(newTeam.getScore()+newTeam.getLoginScore());
		}
		if(team.getPerformanceScore()!=null){
			newTeam.setPerformanceScore(team.getPerformanceScore());
			newTeam.setScore(newTeam.getScore()+team.getPerformanceScore());
		}else if(newTeam.getPerformanceScore()!=null){
			newTeam.setScore(newTeam.getScore()+newTeam.getPerformanceScore());
		}
		if(newTeam.getMoney()!=null){
			newTeam.setScore(newTeam.getScore()+newTeam.getMoney());
		}
		/*
		if(team.getScore()!=oldTeam.getScore())
			newTeam.setScore(team.getScore());
			*/
		if(teamDao.updateTeam(newTeam)){
			return 1;
		}else{
			return 0;
		}
		
	}

	@Override
	public int updateTeamTsak(Team team) {
		// TODO Auto-generated method stub
		if(team.getId()<=0){
			return -2;
		}
		Team oldTeam = teamDao.searchTeamByTeam(team);
		Team newTeam = oldTeam;
		if(team.getCode()!=null && !"".equals(team.getCode())){
			newTeam.setCode(team.getCode());
		}
		if(team.getUrl()!=null && !"".equals(team.getUrl())){
			newTeam.setUrl(team.getUrl());
		}
		if(team.getRequireDocu()!=null && !"".equals(team.getRequireDocu())){
			newTeam.setRequireDocu(team.getRequireDocu());
		}
		if(team.getDesignDocu()!=null && !"".equals(team.getDesignDocu())){
			newTeam.setDesignDocu(team.getDesignDocu());
		}
		if(team.getDetailDocu()!=null && !"".equals(team.getDetailDocu())){
			newTeam.setDetailDocu(team.getDetailDocu());
		}
		if(teamDao.updateTeam(newTeam)){
			return 1;
		}else{
			return 0;
		}
	}

	@Override
	public int isCodeSubmit(Integer userId) {
		// TODO Auto-generated method stub
		Team myteam = this.teamDao.searchTeamByMember(userId);
		if(myteam.getCode()!=null && !"".equals(myteam.getCode()))
			return 1;
		return 0;
	}

	@Override
	public int isFunctionTest(Integer userId) {
		// TODO Auto-generated method stub
		Team myteam = this.teamDao.searchTeamByMember(userId);
		if(myteam.getFunctionScore()!=null && myteam.getFunctionScore()>0)
			return 1;
		return 0;
	}

	@Override
	public int isPerformanceTest(Integer userId) {
		// TODO Auto-generated method stub
		Team myteam = this.teamDao.searchTeamByMember(userId);
		if(myteam.getPerformanceScore()!=null && myteam.getPerformanceScore()>0)
			return 1;
		return 0;
	}
	/*
	@Override
	public int isDocumentSubmit(Integer userId) {
		// TODO Auto-generated method stub
		Team myteam = this.teamDao.searchTeamByMember(userId);
		if(myteam.getDesignDocu()==null || "".equals(myteam.getDesignDocu()) ||
				myteam.getRequireDocu()==null || "".equals(myteam.getRequireDocu()) ||
				myteam.getDetailDocu()==null || "".equals(myteam.getDetailDocu()))
			return 0;
		return 1;
	}
	*/
	@Override
	public int isDocumentRequireSubmit(Integer userId) {
		// TODO Auto-generated method stub
		Team myteam = this.teamDao.searchTeamByMember(userId);
		if(myteam.getRequireDocu()==null || "".equals(myteam.getRequireDocu()))
			return 0;
		return 1;
	}

	@Override
	public int isDocumentDesignSubmit(Integer userId) {
		// TODO Auto-generated method stub
		Team myteam = this.teamDao.searchTeamByMember(userId);
		if(myteam.getDesignDocu()==null || "".equals(myteam.getDesignDocu()))
			return 0;
		return 1;
	}

	@Override
	public int isDocumentDetailSubmit(Integer userId) {
		// TODO Auto-generated method stub
		Team myteam = this.teamDao.searchTeamByMember(userId);
		if(myteam.getDetailDocu()==null || "".equals(myteam.getDetailDocu()))
			return 0;
		return 1;
	}
}
