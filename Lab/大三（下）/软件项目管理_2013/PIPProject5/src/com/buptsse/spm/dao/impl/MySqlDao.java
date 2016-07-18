package com.buptsse.spm.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class MySqlDao {
	Connection con = null;
	Statement stat = null;
	ResultSet rs = null;

	public MySqlDao(String url, String username, String password) {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			con = DriverManager.getConnection("jdbc:mysql://" + url , username, password);
			stat = con.createStatement();
		} catch (Exception e) {
			con = null;
		}
	}
	

	public Boolean execute(String sql) {
		try {
			boolean result = stat.execute(sql);
			return result;
		} catch (Exception e) {
			return false;
		}
	}

	public ResultSet executeQuery(String sql) {
		try {
			rs = stat.executeQuery(sql);
		} catch (Exception e) {
			rs = null;
		}
		return rs;
	}

	public boolean executeUpdate(String sql) {
		try {
			stat.executeUpdate(sql);
			return true;
		} catch (Exception e) {
			// 更新失败
		}
		return false;
	}
	
	public int executeUpdateBatch(String[] sqls) {
		if(sqls==null)
			return 0;
		try {
			for(int i=0;i<sqls.length;i++)
				stat.addBatch(sqls[i]);
			int[] result=stat.executeBatch();
			for(int i=0;i<result.length;i++)
				if(result[i]<0)
					return (i+1);
			return 0;
		} catch (Exception e) {
			e.printStackTrace();
			// 更新失败
		}
		return -1;
	}
	
	public Connection getCon() {
		return con;
	}

	public void setCon(Connection con) {
		this.con = con;
	}

	public Statement getStat() {
		return stat;
	}

	public void setStat(Statement stat) {
		this.stat = stat;
	}

	public ResultSet getRs() {
		return rs;
	}

	public void setRs(ResultSet rs) {
		this.rs = rs;
	}

	
}
