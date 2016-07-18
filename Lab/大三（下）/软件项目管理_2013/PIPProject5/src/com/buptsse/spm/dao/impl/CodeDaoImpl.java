package com.buptsse.spm.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.buptsse.spm.dao.ICodeDao;
import com.buptsse.spm.domain.Course;
import com.buptsse.spm.domain.Code;


/**
 * @author libing
 * @date 2015年11月16日 下午3:53:50
 * @description
 * @modify	
 * @modifyDate 
 */

@Repository
public class CodeDaoImpl extends BaseDAOImpl<Code> implements ICodeDao {
	private static Logger LOG = Logger.getLogger(CodeDaoImpl.class);


	@Override
	public boolean saveCode(Code code) {
		// TODO Auto-generated method stub
		try{
			super.save(code);
		}catch(Exception e){
			e.printStackTrace();
			LOG.error(e);
			return false;
		}
		return true;		
	}

	@Override
	public boolean updateCode(Code code) {
		// TODO Auto-generated method stub
		try{
			super.update(code);
		}catch(Exception e){
			e.printStackTrace();
			LOG.error(e);
			return false;
		}
		return true;
	}

	@Override
	public boolean deleteCode(Code code) {
		// TODO Auto-generated method stub
		try{
			super.delete(code);
		}catch(Exception e){
			e.printStackTrace();
			LOG.error(e);
			return false;
		}
		return true;
	}



	@Override
	public boolean saveOrUpdateCode(Code code) {
		// TODO Auto-generated method stub
		try{
			super.saveOrUpdate(code);
		}catch(Exception e){
			e.printStackTrace();
			LOG.error(e);
			return false;
		}
		return true;
	}


	@Override
	public List<Code> findCode(String hql, List param) {
		// TODO Auto-generated method stub
		return super.find(hql, param);
		
	}

	@Override
	public List<Code> findCode(String hql, Object[] param) {
		// TODO Auto-generated method stub
		return super.find(hql, param);
	}




	@Override
	public Long countCode(String hql, List param) {
		// TODO Auto-generated method stub
		return super.count(hql, param);
	}

	
	@Override
	public Code findCodeById(Integer id){
		// TODO Auto-generated method stub
		return super.get(Code.class, id);
		
	}

}
