/**
 * 
 */
package com.buptsse.spm.service;

import java.util.List;

import com.buptsse.spm.domain.Course;
import com.buptsse.spm.domain.Code;
import com.buptsse.spm.domain.User;

/**
 * @author libing
 * @date 2015年11月23日 下午4:05:37
 * @description 数据字典相关功能接口定义
 * @modify
 *
 */

public interface ICodeService {
	public Code findCodeById(String id);
	public Code findCodeName(String codeType,String codeCode);
}
