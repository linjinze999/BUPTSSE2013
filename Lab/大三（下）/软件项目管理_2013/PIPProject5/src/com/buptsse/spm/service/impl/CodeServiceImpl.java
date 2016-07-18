package com.buptsse.spm.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.buptsse.spm.dao.ICodeDao;
import com.buptsse.spm.domain.Code;
import com.buptsse.spm.service.ICodeService;


/**
 * @author libing 
 * @date 2015年11月16日 下午3:53
 * @description 数据字典的service层实现类定义 
 * @modify
 * @modifyDate 
 */

@Transactional
@Service
public class CodeServiceImpl implements ICodeService{

	@Resource
	private ICodeDao iCodeDao;


	@Override
	public Code findCodeById(String id) {
		// TODO Auto-generated method stub
		return iCodeDao.findCodeById(new Integer(id));
	}



	@Override
	public Code findCodeName(String codeType, String codeCode) {
		// TODO Auto-generated method stub
		Code code = new Code();
		String hql=" from Code where codeType=? and codeCode=?";
		List listParam = new ArrayList();
		listParam.add(codeType);
		listParam.add(codeCode);
		List list  = iCodeDao.findCode(hql, listParam);
		if(list!=null && list.size()>0){
			code = (Code)list.get(0);
		}
		
		return code;
	}





}
