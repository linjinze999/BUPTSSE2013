package com.buptsse.spm.domain;

import java.io.Serializable;
import java.math.BigDecimal;
/**
 * @author xinyu han
 * @date 2015年11月01日 下午2:34
 * @description	Course表的信息记录
 * @modify
 * @modifyDate 
 */
public class Course implements Serializable{
	private static final long serialVersionUID = 1L;
		private String studentId="";
		private String name="";
		private String classId="";
		private String email="";
		private String telno="";
		private String status="";
		private BigDecimal dailyGrade= new BigDecimal(0.00);
		private BigDecimal midGrade= new BigDecimal(0.00);
		private BigDecimal finalGrade= new BigDecimal(0.00);
		private BigDecimal practiceGrade= new BigDecimal(0.00);
		private BigDecimal totalGrade= new BigDecimal(0.00);
		private String syear;		
		
		public String getSyear() {
			return syear;
		}
		public void setSyear(String syear) {
			this.syear = syear;
		}
		public String getStudentId() {
			return studentId;
		}
		public void setStudentId(String studentId) {
			this.studentId = studentId;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getClassId() {
			return classId;
		}
		public void setClassId(String classId) {
			this.classId = classId;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getTelno() {
			return telno;
		}
		public void setTelno(String telno) {
			this.telno = telno;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}


		public BigDecimal getDailyGrade() {
			return dailyGrade;
		}
		public void setDailyGrade(BigDecimal dailyGrade) {
			this.dailyGrade = dailyGrade;
		}
		public BigDecimal getMidGrade() {
			return midGrade;
		}
		public void setMidGrade(BigDecimal midGrade) {
			this.midGrade = midGrade;
		}
		public BigDecimal getFinalGrade() {
			return finalGrade;
		}
		public void setFinalGrade(BigDecimal finalGrade) {
			this.finalGrade = finalGrade;
		}
		public BigDecimal getPracticeGrade() {
			return practiceGrade;
		}
		public void setPracticeGrade(BigDecimal practiceGrade) {
			this.practiceGrade = practiceGrade;
		}
		public BigDecimal getTotalGrade() {
			return totalGrade;
		}
		public void setTotalGrade(BigDecimal totalGrade) {
			this.totalGrade = totalGrade;
		}
		public static long getSerialversionuid() {
			return serialVersionUID;
		}
		
		
		
}
