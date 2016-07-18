package com.buptsse.spm.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.buptsse.spm.dao.TeamDao;
import com.buptsse.spm.domain.Team;

/**
 * @author Jinze Lin
 * @date 2016年04月21日 上午8:35
 * @description 实现TeamDao接口
 * @modify
 * @modifyDate
 */

@Repository
public class TeamDaoImpl extends BaseDAOImpl<Team> implements TeamDao{
	private static Logger LOG = Logger.getLogger(TeamDaoImpl.class);

	@Override
	public List<Team> find(String hql, Team[] param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Team get(String hql, Team[] param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long count(String hql, Team[] param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer executeHql(String hql, Team[] param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Team> searchAllTeam() {
		// TODO Auto-generated method stub
		String sql="from Team";
		List<Team> all = super.find(sql);
		return all;
	}

	@Override
	public Team searchTeamByMember(Integer memberid) {
		// TODO Auto-generated method stub
		String sql="from Team";
		List<Team> all = super.find(sql);
		if(all!=null && all.size()>0){
			for(int i=0;i<all.size();i++){
				String[] str = all.get(i).getMember().split(";");
				for(int j=0;j<str.length;j++){
						if(memberid==Integer.parseInt(str[j])){
							return all.get(i);
						}
				}
			}
		}
		return null;
	}

	@Override
	public int addTeam(Team team) {
		// TODO Auto-generated method stub
		try{
			String sql="from Team";
			List<Team> all = super.find(sql);
			if(all!=null && all.size()>0){
				for(int i=0;i<all.size();i++){
					if(all.get(i).getName().equals(team.getName())){
						return -1;
					}
				}
			}
			super.save(team);
		}catch(Exception e){
			e.printStackTrace();
			return -2;
		}
		return 1;
	}

	@Override
	public boolean deleteTeam(Team team) {
		// TODO Auto-generated method stub
		try{
			super.delete(team);
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean updateTeam(Team team) {
		// TODO Auto-generated method stub
		try{
			super.update(team);
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public Team searchTeamByTeam(Team team) {
		// TODO Auto-generated method stub
		String sql="from Team where id_team="+team.getId();
		List<Team> result = super.find(sql);
		if(result!=null && result.size()!=0){
			return result.get(0);
		}
		return null;
	}
}
