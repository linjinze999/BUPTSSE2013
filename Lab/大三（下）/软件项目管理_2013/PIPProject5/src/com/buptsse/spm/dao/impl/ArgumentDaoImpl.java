package com.buptsse.spm.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.buptsse.spm.dao.ArgumentDao;
import com.buptsse.spm.domain.MyArgument;

@Repository
public class ArgumentDaoImpl  extends BaseDAOImpl<MyArgument> implements ArgumentDao{
	private static Logger LOG = Logger.getLogger(ArgumentDaoImpl.class);

	@Override
	public List<MyArgument> find(String hql, MyArgument[] param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MyArgument get(String hql, MyArgument[] param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long count(String hql, MyArgument[] param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer executeHql(String hql, MyArgument[] param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateArgument(MyArgument argument) {
		// TODO Auto-generated method stub
		try{
			super.update(argument);
			return 1;
		}catch(Exception e){
			return 0;
		}
	}

	@Override
	public List<MyArgument> searchArgument() {
		// TODO Auto-generated method stub
		try{
			String sql="from MyArgument";
			List<MyArgument> args = new ArrayList<MyArgument>();
			args = super.find(sql);
			return args;
		}catch(Exception e){
			return null;
		}
		
	}

	public static Logger getLOG() {
		return LOG;
	}

	public static void setLOG(Logger lOG) {
		LOG = lOG;
	}
}
