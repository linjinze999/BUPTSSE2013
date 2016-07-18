package com.buptsse.spm.dao.impl;

import java.io.File;

import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.SQLExec;
import org.apache.tools.ant.types.EnumeratedAttribute;

public class AntSqlExec {
	SQLExec sqlExec = new SQLExec();
	public AntSqlExec(String driver, String url,String user, String password){
		//设置数据库参数  
        sqlExec.setDriver(driver);  
        sqlExec.setUrl(url);  
        sqlExec.setUserid(user);  
        sqlExec.setPassword(password); 
	}
	public void execSql(String filePath) throws Exception {
        //要执行的脚本  
        sqlExec.setSrc(new File(filePath));  
        //有出错的语句该如何处理  
        sqlExec.setOnerror((SQLExec.OnError)(EnumeratedAttribute.getInstance(SQLExec.OnError.class, "abort")));  
        sqlExec.setPrint(true); //设置是否输出  
        //输出到文件 sql.out 中；不设置该属性，默认输出到控制台  
        //sqlExec.setOutput(new File("src/sql.out"));   
        sqlExec.setProject(new Project()); // 要指定这个属性，不然会出错  
        sqlExec.execute();  
    }  

}
