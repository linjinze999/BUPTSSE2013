package com.buptsse.spm.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import com.alibaba.fastjson.JSONObject;
import com.buptsse.spm.domain.Exam;
import com.buptsse.spm.service.IExamService;

import com.opensymphony.xwork2.ActionSupport;


/**
 * @author libing
 * @date 2015年11月20日 下午4:17
 * @description 选课的service层接口定义 
 * @modify
 * @modifyDate 
 */
public class ExamAction extends ActionSupport{
	private static final long serialVersionUID = 1L;
	private static Logger LOG = LoggerFactory.getLogger(ExamAction.class);
	@Resource
	private IExamService examService;
	public List examList = new ArrayList();
	public Exam exam;
	public String examName;
	public String number;	
	String[] result=new String[10];
	int rightNumber=0 ;
	int score=0;

	/**
	 * 查询所有网上测试列表
	 * @return
	 * @throws Exception
	 */
	public String findExamList() throws Exception{
		
		examList = examService.findAllExam();
		
		Map paramMap = new HashMap();
				
		
		return "success";
	}	
	
	
	
	/**
	 * 增加试题
	 * @return
	 * @throws Exception
	 */
	public String addQuestion() throws Exception{
		String msg = "";
		
		int number = examService.findExamMaxId(exam.getExamName());
		
		System.out.println("最大编号："+number);
		exam.setNumber(number+1);
		boolean flag = examService.insertExam(exam);
		//boolean flag = false;
		if(flag){
			msg = "1";//表示保存成功
		}else{
			msg = "2";//表示保存失败
		}
		try {
			ServletActionContext.getResponse().getWriter().write(msg);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
		return null;
	}		
	


	/**
	 * 查询某个测试题下所有的问题
	 * @return
	 */
	public String queryQuestion(){
		
		examList = examService.findExamByName(examName);
		if(examList!=null&&examList.size()>0){
			Exam exam = (Exam) examList.get(0);
			examName =exam.getExamName();
		}
		 
		return "success";
	}

	
	
	/**
	 * 校验测试题答案
	 * @return
	 */
	public String checkAnswer(){
		String yourAnswer ="";
		examList = examService.findExamByName(examName);
		for(int i=0;i<examList.size();i++){
			Exam exam = (Exam) examList.get(i);
			if(exam.getAnswerRight().equals(result[i])){
				rightNumber++;
			}
		}
		score = rightNumber*10;
				
		return "success";
	}	
	
	
	
	/**
	 * 删除测试题
	 * @return
	 */
	public String deleteExam(){
		boolean flag=false;
		String msg="";
		examList = examService.findExamByName(examName);
		for(int i=0;i<examList.size();i++){
			Exam exam = (Exam) examList.get(i);
			flag = examService.deleteExam(exam.getExamName(), exam.getNumber());
			if(!flag){
				break;
			}
		}
		if(flag){
			msg = "删除成功！";//表示删除成功
		}else{
			msg = "删除失败，请联系管理员！";//表示保存失败
		}
		
		String str=JSONObject.toJSONString(msg);
		try {
			ServletActionContext.getResponse().getWriter().write(str);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		return null;
	}		
	
	
	
	/**
	 * 根据测试题名称和序号删除问题
	 * @return
	 */
	public String deleteQuestion(){
		boolean flag=false;
		String msg="";
		
		flag = examService.deleteExam(examName, new Integer(number));

		if(flag){
			msg = "删除成功！";//表示删除成功
		}else{
			msg = "删除失败，请联系管理员！";//表示保存失败
		}
		
		String str=JSONObject.toJSONString(msg);
		try {
			ServletActionContext.getResponse().getWriter().write(str);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		return null;
	}		
	
	
	public IExamService getExamService() {
		return examService;
	}


	public void setExamService(IExamService examService) {
		this.examService = examService;
	}



	public List getExamList() {
		return examList;
	}


	public void setExamList(List examList) {
		this.examList = examList;
	}


	public Exam getExam() {
		return exam;
	}

	public void setExam(Exam exam) {
		this.exam = exam;
	}

	public String getExamName() {
		return examName;
	}

	public void setExamName(String examName) {
		this.examName = examName;
	}
	

	public String[] getResult() {
		return result;
	}

	public void setResult(String[] result) {
		this.result = result;
	}	

	public int getRightNumber() {
		return rightNumber;
	}

	public void setRightNumber(int rightNumber) {
		this.rightNumber = rightNumber;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}	
	
	
}
