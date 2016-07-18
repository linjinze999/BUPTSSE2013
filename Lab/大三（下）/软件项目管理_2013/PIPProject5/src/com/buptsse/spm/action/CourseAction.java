package com.buptsse.spm.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.buptsse.spm.domain.Code;
import com.buptsse.spm.domain.Course;
import com.buptsse.spm.domain.User;
import com.buptsse.spm.service.ICodeService;
import com.buptsse.spm.service.ISelectCourseService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;


/**
 * @author libing
 * @date 2015年11月17日 下午4:17
 * @description 有关课程处理的action
 * @modify
 * @modifyDate 
 */
public class CourseAction extends ActionSupport{

	private static final long serialVersionUID = 1L;
	private static Logger LOG = LoggerFactory.getLogger(CourseAction.class);
	private User user;
	private Course course;
	protected String stdId="";
	protected String classId="";
	protected String name="";
	protected String status="";
	protected String syear="";
	
	private String operateType;	

	@Resource
	private ISelectCourseService selectCourseService;
	
	@Resource
	private ICodeService codeService;
	
	 /** 
	  * 分页查询所有课程列表
	 * @return
	 */
	public String listCourse(){
		
		
		int page=Integer.parseInt(ServletActionContext.getRequest().getParameter("page"));
		int rows=Integer.parseInt(ServletActionContext.getRequest().getParameter("rows"));
			
		Map paramMap = new HashMap();
		paramMap.put("studentId", stdId);
		paramMap.put("classId", classId);
		paramMap.put("name", name);
		paramMap.put("status", status);
		paramMap.put("syear", syear);
		
		List<Course> list = selectCourseService.findPage(paramMap,page, rows);
		
		for(Course course:list){
			Code code =  codeService.findCodeName("status", course.getStatus());
			String codeName =code.getCodeName();
			course.setStatus(codeName);
		}

		//查询总条数
		Long total = selectCourseService.count(paramMap);
		
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", total);
		String str=JSONObject.toJSONString(map);
		try {
			ServletActionContext.getResponse().getWriter().write(str);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	

	
	
	/**
	 * 更新课程信息，可批量更新
	 * @return
	 */
	public String updateCourse(){
		boolean result = false;
		String str = "";
		String[] studentIds  = ServletActionContext.getRequest().getParameterValues("studentIds[]");  
		for (int i = 0; i < studentIds.length; i++) { 
			if("U".equals(operateType)){
				//将未确认的确认
				result = selectCourseService.changeStatus(studentIds[i], 2);
				if(result){
					str = "确认成功！";
				}else{
					str = "确认失败，请联系管理员！";
				}
			}
			if("C".equals(operateType)){
				//将未确认或者已确认的取消
				result = selectCourseService.changeStatus(studentIds[i], 3);
				if(result){
					str = "取消成功！";
				}else{
					str = "取消失败，请联系管理员！";
				}
			}			
			
			if("D".equals(operateType)){
				//将已确认的删除
				result = selectCourseService.changeStatus(studentIds[i], 4);
				if(result){
					str = "删除成功！";
				}else{
					str = "删除失败，请联系管理员！";
				}
			}
			
		}

		String message = JSONObject.toJSONString(str);
		try {
			ServletActionContext.getResponse().getWriter().write(message);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	
		return null;
	}	
	
	

	
	/**
	 * 增加选课信息
	 * @return
	 */
	public String insertCourse(){
		Map<String, Object> map=new HashMap<String, Object>();
		Course courseExit = selectCourseService.findCourse(course.getStudentId());
		if(courseExit!=null){
			map.put("code", "2");
			map.put("message", "学号为"+course.getStudentId()+"的学生已选课成功，请勿重复选课！");			
		}else{
			//初始化状态
			course.setStatus("1");
			boolean flag=false;
			try{
				flag = selectCourseService.insertCourse(course);
			}catch(Exception e){
				e.printStackTrace();
				flag=false;
			}

			if(flag){
				map.put("code", "1");
				map.put("message", "选课成功！");
			}else{
				map.put("code", "2");
				map.put("message", "选课失败，请联系管理员！");
			}			
		}

		String str=JSONObject.toJSONString(map);
		System.out.println("str"+str);
		
		try {
			ServletActionContext.getResponse().getWriter().write(str);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return null;
	}	
	
	

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public String getStdId() {
		return stdId;
	}

	public void setStdId(String stdId) {
		this.stdId = stdId;
	}

	
	public String getOperateType() {
		return operateType;
	}

	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}

	public String getClassId() {
		return classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getSyear() {
		return syear;
	}


	public void setSyear(String syear) {
		this.syear = syear;
	}

	public ICodeService getCodeService() {
		return codeService;
	}

	public void setCodeService(ICodeService codeService) {
		this.codeService = codeService;
	}
		
}
