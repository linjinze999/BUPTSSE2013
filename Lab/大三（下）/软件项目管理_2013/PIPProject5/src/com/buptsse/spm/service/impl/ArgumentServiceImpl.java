package com.buptsse.spm.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.buptsse.spm.dao.ArgumentDao;
import com.buptsse.spm.domain.MyArgument;
import com.buptsse.spm.service.ArgumentService;

@Transactional
@Service
public class ArgumentServiceImpl implements ArgumentService{
	@Resource
	private ArgumentDao argumentDao;
	
	public ArgumentDao getArgumentDao() {
		return argumentDao;
	}

	public void setArgumentDao(ArgumentDao argumentDao) {
		this.argumentDao = argumentDao;
	}

	@Override
	public int updateDeployArgs(MyArgument argument) {
		// TODO Auto-generated method stub
		if(argument.getDeployIp()==null || "".equals(argument.getDeployIp()))
			return -2;
		else{
			List<MyArgument> args = argumentDao.searchArgument();
			if(args==null || args.size()==0){
				return -1;
			}else{
				MyArgument arg = args.get(args.size()-1);
				arg.setDeployAccount(argument.getDeployAccount());
				arg.setDeployIp(argument.getDeployIp());
				arg.setDeployPassword(argument.getDeployPassword());
				arg.setDeployPort(argument.getDeployPort());
				return argumentDao.updateArgument(arg);
			}
		}
	}

	@Override
	public int updateDatabaseArgs(MyArgument argument) {
		// TODO Auto-generated method stub
		if(argument.getDbIp()==null || "".equals(argument.getDbIp()))
			return -2;
		else{
			List<MyArgument> args = argumentDao.searchArgument();
			if(args==null || args.size()==0){
				return -1;
			}else{
				MyArgument arg = args.get(args.size()-1);
				arg.setDbAccount(argument.getDbAccount());
				arg.setDbIp(argument.getDbIp());
				arg.setDbPassword(argument.getDbPassword());
				arg.setDbPort(argument.getDbPort());
				return argumentDao.updateArgument(arg);
			}
		}
	}

	@Override
	public int updateDocuPath(MyArgument argument) {
		// TODO Auto-generated method stub
		if(argument.getDocuPath()==null || "".equals(argument.getDocuPath()))
			return -2;
		else{
			List<MyArgument> args = argumentDao.searchArgument();
			if(args==null || args.size()==0){
				return -1;
			}else{
				MyArgument arg = args.get(args.size()-1);
				arg.setDocuPath(argument.getDocuPath());
				return argumentDao.updateArgument(arg);
			}
		}
	}

	@Override
	public MyArgument searchArgument() {
		// TODO Auto-generated method stub
		List<MyArgument> args = argumentDao.searchArgument();
		if(args==null || args.size()==0){
			return null;
		}else{
			return args.get(args.size()-1);
		}
	}
}
