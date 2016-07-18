package com.buptsse.spm.action;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.buptsse.spm.domain.ConfigInfo;
import com.buptsse.spm.domain.Course;
import com.buptsse.spm.service.IConfigInfoService;
import com.buptsse.spm.service.ISelectCourseService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;


/**
 * @author libing
 * @date 2015年11月20日
 * @description 实现邮件处理相关功能
 * @modify
 * @modifyDate 
 */
public class EmailAction extends ActionSupport{

	private static final long serialVersionUID = 1L;
	private static Logger LOG = LoggerFactory.getLogger(EmailAction.class);
	@Resource
	private ISelectCourseService selectCourseService;
	@Resource
	public IConfigInfoService configInfoService;
	
	public IConfigInfoService getConfigInfoService() {
		return configInfoService;
	}

	public void setConfigInfoService(IConfigInfoService configInfoService) {
		this.configInfoService = configInfoService;
	}

	public String emailTo; // 收件人邮箱地址
	public String emailFrom; // 发件人邮箱地址
	public String password; // 发件人邮箱密码
	public String subject = "test"; // 邮件主题
	public String content = "sdf"; // 邮件内容
	public String smtpStyle; // 邮箱的smtp类型
	
	public String msg = "";


	// 设置smtp的类型
	public void setEmailStyle() {
		String emailFrom2 = this.emailFrom;
		String style = emailFrom2.substring(emailFrom2.indexOf('@') + 1,
				emailFrom2.indexOf('.'));
		this.smtpStyle = "smtp." + style + ".com";
	}
	
	/**
	 * 发邮件通知功能
	 * @return
	 * @throws Exception
	 */
	public String emailNotify() throws Exception{
		System.out.println("********后台发邮件****");
		
		emailTo = "";
		//emailFrom = "buptgogame@163.com";
		//password = "buptgogame123";
		ConfigInfo configInfo = new ConfigInfo();
		configInfo = configInfoService.findByTypeAndCode("Email", "userName");
		emailFrom = configInfo.getConfigValue();
		configInfo = configInfoService.findByTypeAndCode("Email", "passWord");
		password = configInfo.getConfigValue();		
		System.out.println("****************emailFrom**************:"+emailFrom+"*********password*************:"+password);
		setEmailStyle();		
		
		
		String msg ="";
		String studentIds  = ServletActionContext.getRequest().getParameter("studentIds[]"); 
		
		String[] stuIdArray = studentIds.split(",");
		try {
			for (int i = 0; i < stuIdArray.length; i++) {
				
				Course course = selectCourseService.findCourse(stuIdArray[i]);
				
				if(course.getFinalGrade().compareTo(new BigDecimal(60))==-1){
					sendEmail("预警通知", course.getName()+"同学，您的成绩为"+course.getFinalGrade()+"分，请留意课程平台的补考相关通知。", course.getEmail());	
					msg = "邮件主题: 预警通知</br>收件人: "+course.getName()+"("+course.getEmail()+")</br>"+"邮件内容:</br>&nbsp;&nbsp;&nbsp;&nbsp;"+course.getName()+"同学，您的成绩为"+course.getFinalGrade()+"分，请留意课程平台的补考相关通知。</br>";
				}else{
					
					sendEmail("成绩通知", "您的课程成绩为"+course.getFinalGrade()+"分，详情请登录教务系统查询，请继续努力。", course.getEmail());
					msg = "邮件主题: 成绩通知</br>收件人: "+course.getName()+"("+course.getEmail()+")</br>"+"邮件内容:</br>&nbsp;&nbsp;&nbsp;&nbsp;"+course.getName()+"同学，您的课程成绩为"+course.getFinalGrade()+"分，详情请登录教务系统查询，请继续努力。</br>";
				}
				
				if(i>0){
					msg = "群发通知成绩成功！</br>";
				}
				
			}	
			
			Map request=(Map)ActionContext.getContext().get("request");
			request.put("msg",msg);
			return "success";			
			
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			msg = "邮件发送失败，请联系管理员！";
			Map request=(Map)ActionContext.getContext().get("request");
			request.put("msg",msg);
			return "error";		
		}
	
	}	
	
	
	
	
	/**
	 * 邮件发送方法,供邮件发送功能调用
	 * @param subject 主题
	 * @param content 内容
	 * @param emailTo 收件人
	 * @throws Exception
	 */
	public void sendEmail(String subject, String content, String emailTo)
	throws Exception {
		Properties props = new Properties();
		props.put("mail.smtp.host", smtpStyle);
		props.put("mail.smtp.auth", "true"); // 
		
		Session sendMailSession = Session.getInstance(props, null);
		
		Transport transport = sendMailSession.getTransport("smtp");
		// 根据邮箱地址和密码连接邮箱
		
		transport.connect(smtpStyle, emailFrom, password);
		Message newMessage = new MimeMessage(sendMailSession);
		
		// 设置mail主题
		newMessage.setSubject(subject);
		
		// 设置发信人地址
		String strFrom = new String(emailFrom.getBytes(), "iso-8859-1");
		newMessage.setFrom(new InternetAddress(strFrom));
		
		Address addressTo[] = { new InternetAddress(emailTo) };
		newMessage.setRecipients(Message.RecipientType.TO, addressTo);
		
		// 设置mail正文
		newMessage.setSentDate(new java.util.Date());
		newMessage.setText(content);
		
		newMessage.saveChanges(); // 保存发送信息
		transport.sendMessage(newMessage, newMessage
				.getRecipients(Message.RecipientType.TO)); // 发送邮件
		
		transport.close();
		// Transport.send(newMessage);

	}

	public ISelectCourseService getSelectCourseService() {
		return selectCourseService;
	}

	public void setSelectCourseService(ISelectCourseService selectCourseService) {
		this.selectCourseService = selectCourseService;
	}		
	




		
}
