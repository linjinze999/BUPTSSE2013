package com.buptsse.spm.dao;

import java.util.List;

import com.buptsse.spm.domain.MyArgument;

public interface ArgumentDao {
	public int updateArgument(MyArgument argument);
	public List<MyArgument> searchArgument();
}
