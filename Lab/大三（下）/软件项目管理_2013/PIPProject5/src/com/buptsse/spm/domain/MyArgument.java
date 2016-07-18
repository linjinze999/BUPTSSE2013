package com.buptsse.spm.domain;

import java.io.Serializable;

public class MyArgument implements Serializable{
	private int id;			//ID
	private String deployIp;
	private String deployPort;
	private String deployAccount;
	private String deployPassword;
	private String dbIp;
	private String dbPort;
	private String dbAccount;
	private String dbPassword;
	private String docuPath;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDeployIp() {
		return deployIp;
	}
	public void setDeployIp(String deployIp) {
		this.deployIp = deployIp;
	}
	public String getDeployPort() {
		return deployPort;
	}
	public void setDeployPort(String deployPort) {
		this.deployPort = deployPort;
	}
	public String getDeployAccount() {
		return deployAccount;
	}
	public void setDeployAccount(String deployAccount) {
		this.deployAccount = deployAccount;
	}
	public String getDeployPassword() {
		return deployPassword;
	}
	public void setDeployPassword(String deployPassword) {
		this.deployPassword = deployPassword;
	}
	public String getDbIp() {
		return dbIp;
	}
	public void setDbIp(String dbIp) {
		this.dbIp = dbIp;
	}
	public String getDbPort() {
		return dbPort;
	}
	public void setDbPort(String dbPort) {
		this.dbPort = dbPort;
	}
	public String getDbAccount() {
		return dbAccount;
	}
	public void setDbAccount(String dbAccount) {
		this.dbAccount = dbAccount;
	}
	public String getDbPassword() {
		return dbPassword;
	}
	public void setDbPassword(String dbPassword) {
		this.dbPassword = dbPassword;
	}
	public String getDocuPath() {
		return docuPath;
	}
	public void setDocuPath(String docuPath) {
		this.docuPath = docuPath;
	}
}
