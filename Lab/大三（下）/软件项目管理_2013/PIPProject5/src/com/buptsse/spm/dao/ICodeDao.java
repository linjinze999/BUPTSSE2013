package com.buptsse.spm.dao;

import java.util.List;

import com.buptsse.spm.domain.Course;
import com.buptsse.spm.domain.Code;


/**
 * @author libing
 * @param <T>
 * @date 2015年11月23日 下午2:46
 * @description	数据字典持久层接口定义
 * @modify
 * @modifyDate 
 */

public interface ICodeDao {
	public boolean saveCode(Code Code);
	public boolean updateCode(Code Code);
	public boolean deleteCode(Code Code);
	public List<Code> findCode(String hql,Object[] param);
	public List<Code> findCode(String hql,List param);
	public boolean saveOrUpdateCode(Code Code);
	public Long countCode(String hql,List param);
	public Code findCodeById(Integer id);
	

}
