package com.buptsse.spm.domain;

import java.io.Serializable;
import java.util.Calendar;

/**
 * @author Jinze Lin
 * @date 2016年04月13日 上午8:16
 * @description	UserAll表的信息记录
 * @modify
 * @modifyDate 
 */
public class UserAll implements Serializable{
	private int idUserAll;			//用户ID
	private String accountUserAll;	//用户账号
	private String passwordUserAll;	//用户密码
	private String roleUserAll;		//用户角色
	private String nameUserAll;		//用户名称
	private String sexUserAll;		//用户性别
	private String idcardUserAll;	//用户身份证
	private String collegeUserAll;	//所在学院
	private String majorUserAll;	//所在专业
	private String gradeUserAll;	//所属年级
	private String stateUserAll;	//用户状态
	private String phone1UserAll;	//联系方式-手机1
	private String phone2UserAll;	//联系方式-手机1
	private String qqUserAll;		//联系方式-QQ
	private String emailUserAll;	//联系方式-邮箱
	private String addressUserAll;	//联系方式-地址
	private Calendar logintimeUserAll;//上次登录时间
	private String loginipUserAll;	//上次登录ip
	private String photoUserAll;	//用户头像
	private Integer noticeUserAll;	//通知
	private Integer messageUserAll;	//留言
	private Integer taskUserAll;	//任务-作业
	private Integer teamidUserAll;	//团队
	private Integer scoreUserAll;	//成绩
	public int getIdUserAll() {
		return idUserAll;
	}

	public void setIdUserAll(int idUserAll) {
		this.idUserAll = idUserAll;
	}

	public String getAccountUserAll() {
		return accountUserAll;
	}

	public void setAccountUserAll(String accountUserAll) {
		this.accountUserAll = accountUserAll;
	}

	public String getPasswordUserAll() {
		return this.passwordUserAll;
	}

	public void setPasswordUserAll(String passwordUserAll) {
		this.passwordUserAll=passwordUserAll;
	}
	
	public String getRoleUserAll() {
		return this.roleUserAll;
	}

	public void setRoleUserAll(String roleUserAll) {
		this.roleUserAll=roleUserAll;
	}
	public String getNameUserAll() {
		return nameUserAll;
	}

	public void setNameUserAll(String nameUserAll) {
		this.nameUserAll = nameUserAll;
	}
	public String getSexUserAll() {
		return sexUserAll;
	}

	public void setSexUserAll(String sexUserAll) {
		this.sexUserAll = sexUserAll;
	}
	public String getIdcardUserAll() {
		return idcardUserAll;
	}

	public void setIdcardUserAll(String idcardUserAll) {
		this.idcardUserAll = idcardUserAll;
	}
	public String getCollegeUserAll() {
		return collegeUserAll;
	}

	public void setCollegeUserAll(String collegeUserAll) {
		this.collegeUserAll = collegeUserAll;
	}
	
	public String getMajorUserAll() {
		return majorUserAll;
	}

	public void setMajorUserAll(String majorUserAll) {
		this.majorUserAll = majorUserAll;
	}
	public String getGradeUserAll() {
		return gradeUserAll;
	}

	public void setGradeUserAll(String gradeUserAll) {
		this.gradeUserAll = gradeUserAll;
	}
	public String getStateUserAll() {
		return stateUserAll;
	}

	public void setStateUserAll(String stateUserAll) {
		this.stateUserAll = stateUserAll;
	}
	public String getPhone1UserAll() {
		return phone1UserAll;
	}

	public void setPhone1UserAll(String phone1UserAll) {
		this.phone1UserAll = phone1UserAll;
	}
	public String getPhone2UserAll() {
		return phone2UserAll;
	}

	public void setPhone2UserAll(String phone2UserAll) {
		this.phone2UserAll = phone2UserAll;
	}
	public String getQqUserAll() {
		return qqUserAll;
	}

	public void setQqUserAll(String qqUserAll) {
		this.qqUserAll = qqUserAll;
	}
	public String getEmailUserAll() {
		return emailUserAll;
	}

	public void setEmailUserAll(String emailUserAll) {
		this.emailUserAll = emailUserAll;
	}
	public String getAddressUserAll() {
		return addressUserAll;
	}

	public void setAddressUserAll(String addressUserAll) {
		this.addressUserAll = addressUserAll;
	}
	public String getLoginipUserAll() {
		return loginipUserAll;
	}

	public void setLoginipUserAll(String loginipUserAll) {
		this.loginipUserAll = loginipUserAll;
	}
	public String getPhotoUserAll() {
		return photoUserAll;
	}

	public void setPhotoUserAll(String photoUserAll) {
		this.photoUserAll = photoUserAll;
	}
	public Calendar getLogintimeUserAll() {
		return logintimeUserAll;
	}

	public void setLogintimeUserAll(Calendar logintimeUserAll) {
		this.logintimeUserAll = logintimeUserAll;
	}
	public Integer getNoticeUserAll() {
		return noticeUserAll;
	}

	public void setNoticeUserAll(Integer noticeUserAll) {
		this.noticeUserAll = noticeUserAll;
	}
	public Integer getMessageUserAll() {
		return messageUserAll;
	}

	public void setMessageUserAll(Integer messageUserAll) {
		this.messageUserAll = messageUserAll;
	}
	public Integer getTaskUserAll() {
		return taskUserAll;
	}

	public void setTaskUserAll(Integer taskUserAll) {
		this.taskUserAll = taskUserAll;
	}
	public Integer getTeamidUserAll() {
		return teamidUserAll;
	}

	public void setTeamidUserAll(Integer teamidUserAll) {
		this.teamidUserAll = teamidUserAll;
	}
	public Integer getScoreUserAll() {
		return scoreUserAll;
	}

	public void setScoreUserAll(Integer scoreUserAll) {
		this.scoreUserAll = scoreUserAll;
	}
}
