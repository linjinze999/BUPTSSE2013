package com.buptsse.spm.service;

import java.util.List;

import com.buptsse.spm.domain.Team;

/**
 * @author Jinze Lin
 * @date 2016年04月21日 上午9:26
 * @description 团队相关功能接口定义，包括增删改查
 * @modify
 *
 */

public interface TeamService {
	public List<Team> searchAllTeam();
	public Team searchTeamByMember(Integer memberid);
	public int addTeam(Team team);
	public int deleteTeam(Team team);
	public int updateTeam(Team team);
	public int updatePartTeam(Team team);
	public int updateTeamScore(Team team);
	public int updateTeamTsak(Team team);
	public Team findTeamByTeamId(Team team);
	public int isCodeSubmit(Integer userId);
	public int isFunctionTest(Integer userId);
	public int isPerformanceTest(Integer userId);
	public int isDocumentRequireSubmit(Integer userId);
	public int isDocumentDesignSubmit(Integer userId);
	public int isDocumentDetailSubmit(Integer userId);
}
