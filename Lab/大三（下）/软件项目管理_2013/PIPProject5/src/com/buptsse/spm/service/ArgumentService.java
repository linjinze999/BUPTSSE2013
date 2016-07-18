package com.buptsse.spm.service;

import com.buptsse.spm.domain.MyArgument;

public interface ArgumentService {
	public int updateDeployArgs(MyArgument argument);
	public int updateDatabaseArgs(MyArgument argument);
	public int updateDocuPath(MyArgument argument);
	public MyArgument searchArgument();
}
