package com.buptsse.spm.domain;

import java.io.Serializable;
import java.util.Calendar;

/**
 * @author Jinze Lin
 * @date 2016年04月21日 上午8:16
 * @description	team表的信息记录
 * @modify
 * @modifyDate 
 */
public class Team implements Serializable{
	private int id;					//团队ID
	private String name;			//团队名称
	private String member;			//团队成员(id,以“；”分隔)
	private Integer money;			//团队spm币
	private String code;			//上传的代码
	private String url;				//项目部署地址
	private String requireDocu;		//需求文档
	private String designDocu;		//设计文档
	private String detailDocu;		//详细设计文档
	private Integer score;			//总评分
	private Integer deployScore;	//部署评分
	private Integer loginScore;		//登录评分
	private Integer functionScore;	//功能评分
	private Integer performanceScore;//性能评分
	private Integer code1Score;		//代码评分1
	private Integer code2Score;		//代码评分2
	private Integer document1Score;	//文档评分1
	private Integer document2Score;	//文档评分2
	private Integer linkScore;		//链接评分
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMember() {
		return member;
	}
	public void setMember(String member) {
		this.member = member;
	}
	public Integer getMoney() {
		return money;
	}
	public void setMoney(Integer money) {
		this.money = money;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getRequireDocu() {
		return requireDocu;
	}
	public void setRequireDocu(String requireDocu) {
		this.requireDocu = requireDocu;
	}
	public String getDesignDocu() {
		return designDocu;
	}
	public void setDesignDocu(String designDocu) {
		this.designDocu = designDocu;
	}
	public String getDetailDocu() {
		return detailDocu;
	}
	public void setDetailDocu(String detailDocu) {
		this.detailDocu = detailDocu;
	}
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	public Integer getDeployScore() {
		return deployScore;
	}
	public void setDeployScore(Integer deployScore) {
		this.deployScore = deployScore;
	}
	public Integer getLoginScore() {
		return loginScore;
	}
	public void setLoginScore(Integer loginScore) {
		this.loginScore = loginScore;
	}
	public Integer getFunctionScore() {
		return functionScore;
	}
	public void setFunctionScore(Integer functionScore) {
		this.functionScore = functionScore;
	}
	public Integer getPerformanceScore() {
		return performanceScore;
	}
	public void setPerformanceScore(Integer performanceScore) {
		this.performanceScore = performanceScore;
	}
	public Integer getLinkScore() {
		return linkScore;
	}
	public void setLinkScore(Integer linkScore) {
		this.linkScore = linkScore;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Integer getCode1Score() {
		return code1Score;
	}
	public void setCode1Score(Integer code1Score) {
		this.code1Score = code1Score;
	}
	public Integer getCode2Score() {
		return code2Score;
	}
	public void setCode2Score(Integer code2Score) {
		this.code2Score = code2Score;
	}
	public Integer getDocument1Score() {
		return document1Score;
	}
	public void setDocument1Score(Integer document1Score) {
		this.document1Score = document1Score;
	}
	public Integer getDocument2Score() {
		return document2Score;
	}
	public void setDocument2Score(Integer document2Score) {
		this.document2Score = document2Score;
	}
}
