package com.buptsse.spm.dao;

import java.util.List;

import com.buptsse.spm.domain.Team;

/**
 * @author Jinze Lin
 * @date 2016年04月21日 上午8:27
 * @description	团队信息持久层接口定义，包括用户增加，保存，查询
 * @modify
 * @modifyDate 
 */

public interface TeamDao {
	public List<Team> searchAllTeam();
	public Team searchTeamByMember(Integer memberid);
	public int addTeam(Team team);
	public boolean deleteTeam(Team team);
	public boolean updateTeam(Team team);
	public Team searchTeamByTeam(Team team);
}
