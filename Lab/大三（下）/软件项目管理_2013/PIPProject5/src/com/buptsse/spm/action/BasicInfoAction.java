package com.buptsse.spm.action;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;

import com.alibaba.fastjson.JSONObject;
import com.buptsse.spm.domain.BasicInfo;
import com.buptsse.spm.service.IBasicInfoService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 
 * @author yifei xue
 * @date 2015年11月7日 上午9:30:13
 * @description 实现基本信息的显示，包括：学术水平、案例分析、课程特色、联系我们、教学大纲、知识点索引、参考书目、教学团队、教学计划
 * @modify Chen Tian
 * @modifyDate 2015年11月7日 上午11:56
 * @description 实现基本信息的显示，包括：课程简介，考评方式，考试大纲，校企合作，模拟试卷
 */

public class BasicInfoAction extends ActionSupport {
	@Resource
	private IBasicInfoService iBasicInfoService;
	public String name;
	public String content;
	protected BasicInfo basicInfo;
	public String picturePath = "";
	public List<File> upload;

	

	
	public String enterBasicInfo() {
		
		System.out.println("**********进入跳转页面**********："+name);
		if("exerciseMain".equals(name)){
			basicInfo = iBasicInfoService.findBasicInfoByName("exercise0");			
		}else{
			basicInfo = iBasicInfoService.findBasicInfoByName(name);			
		}
		
		basicInfo.setContent(changeFormat(basicInfo.getContent()));

		
		System.out.println("basicInfo.getContent():"+basicInfo.getContent());
		
		return name;
	}
	
	public String editBasicInfo() {
		
		System.out.println("**********进入基本信息修改页面**********："+name);
		if("exerciseMain".equals(name)){
			basicInfo = iBasicInfoService.findBasicInfoByName("exercise0");			
		}else{
			basicInfo = iBasicInfoService.findBasicInfoByName(name);			
		}
		System.out.println("basicInfo.getContent():"+basicInfo.getContent());
		
		return "success";
	}	
	
	public String updateBasicInfo() {
		
		System.out.println("**********进入基本信息修改确认页面**********："+basicInfo.getName());
		System.out.println("**********进入基本信息修改确认页面**********："+basicInfo.getIdCourseInfo());
		boolean flag=false;
		Map<String, Object> map=new HashMap<String, Object>();
		try{
			flag = iBasicInfoService.saveOrUpdate(basicInfo);
		}catch(Exception e){
			e.printStackTrace();
			flag=false;
		}		
		if(flag){
			map.put("code", "1");
			map.put("message", "修改成功！");
		}else{
			map.put("code", "2");
			map.put("message", "修改失败，请联系管理员！");
		}
		String str=JSONObject.toJSONString(map);
		try {
			ServletActionContext.getResponse().getWriter().write(str);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
		System.out.println("basicInfo.getContent():"+basicInfo.getContent());
		
		return null;
	}	
	
	
/*
	public String enterExerciseInfo() {
		return content;
//		BasicinfoDao exerciseDao = new BasicinfoDaoImpl();
//		content = exerciseDao.findBasicinfo(name);
//		char type = name.charAt(name.length() - 1);
//		Map request = (Map) ActionContext.getContext().get("request");
//		request.put("exerciseinfocontent", content);
//		request.put("exerciseinfoname", name);
//		request.put("exerciseinfotype", type);
//		String content2 = changeFormat(content);
//		request.put("exerciseinfocontentdisplay", content2);
//		return "success";
	}

	public String renterExerciseInfo() {
		return content;
		// char type = name.charAt(name.length()-1);
//		char type = name.charAt(8);
//		BasicinfoDao exerciseDao = new BasicinfoDaoImpl();
//		if (type == 'x') {
//			name = "exercise0";
//			// type = "0";
//		}
//		content = exerciseDao.findBasicinfo(name);
//		System.out.println("11111");
//		Map session = (Map) ActionContext.getContext().getSession();
//		session.put("exerciseinfocontent", content);
//		session.put("exerciseinfoname", name);
//		session.put("exerciseinfotype", type);
//		String content2 = changeFormat(content);
//		System.out.print(content2);
//		System.out.println("");
//		session.put("exerciseinfocontentdisplay", content2);
//		return "success" + type;
	}

	public void updatePicture(){
//		try {
//			// ȡ����ǰ̨����·��
//			DateFormat format2 = new SimpleDateFormat("yyyyMMddHHmmss");
//			String reTime = format2
//					.format(new Date(System.currentTimeMillis()));
//			InputStream is = new FileInputStream(upload.get(0));
//			OutputStream os = new FileOutputStream(
//					"C:\\Users\\Administrator\\Desktop\\�����ھ�����\\����\\SPM\\WebRoot\\pic\\"
//							+ reTime + ".jpg");
//			byte buffer[] = new byte[1024];
//			int count = 0;
//			while ((count = is.read(buffer)) > 0) {
//				os.write(buffer, 0, count);
//			}
//			os.close();
//			is.close();
//			this.picturePath = "../pic/" + reTime + ".jpg";
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}	
	
	public String updateBasicinfo() {
//		BasicinfoDao infoDao = new BasicinfoDaoImpl();
//		try{
//			upload.get(0);
//		}catch(Exception e){
//			infoDao.modifyBasicinfo(this.name, this.content, this.picturePath);
//			return "success";
//		}
//		updatePicture();
//		infoDao.modifyBasicinfo(this.name, this.content, this.picturePath);
		return "success";
	}	
	*/
	public String changeFormat(String content) {
		for (int i = 1; i < content.length();) {
			String left = content.substring(0, i);
			String right = content.substring(i + 1);

			if (content.charAt(i) == ' ') {
				content = left + "&nbsp;" + right;
				i += 7;
			} else if (content.charAt(i) == '\t') {
				content = left + "&nbsp;&nbsp;&nbsp;&nbsp;" + right;
				i += 25;
			} else if (content.charAt(i) == '\n' || content.charAt(i) == '\r') {
				content = left + "<br/>" + right;
				i += 6;
			} 
			else {
				i++;
			}
		}
		return content;
	}
	

	public void setBasicinfo(BasicInfo basicInfo) {
		this.basicInfo = basicInfo;
	}

	public String getPicturePath() {
		return picturePath;
	}

	public void setPicturePath(String picturePath) {
		this.picturePath = picturePath;
	}

	public List<File> getUpload() {
		return upload;
	}

	public void setUpload(List<File> upload) {
		this.upload = upload;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public BasicInfo getBasicinfo() {
		return this.basicInfo;
	}	
	
	
	public BasicInfo getBasicInfo() {
		return basicInfo;
	}

	public void setBasicInfo(BasicInfo basicInfo) {
		this.basicInfo = basicInfo;
	}	
	
	
	public IBasicInfoService getiBasicInfoService() {
		return iBasicInfoService;
	}

	public void setiBasicInfoService(IBasicInfoService iBasicInfoService) {
		this.iBasicInfoService = iBasicInfoService;
	}	
	
}