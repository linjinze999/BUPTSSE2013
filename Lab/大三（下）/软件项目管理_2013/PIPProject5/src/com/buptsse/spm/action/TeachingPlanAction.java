package com.buptsse.spm.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import com.alibaba.fastjson.JSONObject;
import com.buptsse.spm.domain.TeachingPlan;
import com.buptsse.spm.service.ITeachingPlanService;
import com.buptsse.spm.service.ISelectCourseService;

import com.opensymphony.xwork2.ActionSupport;


/**
 * @author libing
 * @date 2015年11月25日 下午4:17
 * @description 实现教案相关功能 
 * @modify
 * @modifyDate 
 */
public class TeachingPlanAction extends ActionSupport{

	private static final long serialVersionUID = 1L;
	private static Logger LOG = LoggerFactory.getLogger(TeachingPlanAction.class);
	@Resource
	private ITeachingPlanService teachingPlanService;
	
	public List teachingPlanList = new ArrayList();
	public List pieceList = new ArrayList();	
	
	public TeachingPlan teachingPlan;

	// 上传文件存放路径
	private final static String UPLOADDIR = "/download";
	// 上传文件集合
	private List<File> file;	
	// 上传文件名集合
	private List<String> fileFileName;	


	



	/**
	 * 查询所有的授课教案
	 * @return
	 * @throws Exception
	 */
	public String findTeachingPlanList() throws Exception{
		
		pieceList = teachingPlanService.findAllPiece();
		//teachingPlanList = teachingPlanService.findAllTeachingPlan();
		
		return "success";
	}	
	
	
	
	/**
	 * 删除教案
	 * @return
	 * @throws Exception
	 */
	public String deleteTeachingPlan() throws Exception{
		
		String msg = "";
		
		boolean flag = teachingPlanService.deleteTeachingPlan(teachingPlan.getId());
		if(flag){
			msg = "删除成功！";//表示删除成功
		}else{
			msg = "删除失败，请联系管理员！";//表示保存失败
		}
		String str=JSONObject.toJSONString(msg);
		
		System.out.println("json中的信息："+str);
		try {
			ServletActionContext.getResponse().getWriter().write(str);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
		return null;
	}	
	
	
	
	
	/**
	 * 增加教案
	 * @return
	 * @throws Exception
	 */
	public String insertTeachingPlan() throws Exception{
		
		String msg = "";		
		
		try {
			
			InputStream in = new FileInputStream(file.get(0));
			String dir = ServletActionContext.getRequest().getRealPath(
					UPLOADDIR);
			System.out.println(dir);

			File fileLocation = new File(dir);
			// 此处也可以在应用根目录手动建立目标上传目录
			if (!fileLocation.exists()) {
				boolean isCreated = fileLocation.mkdir();
				if (!isCreated) {
					// 目标上传目录创建失败,可做其他处理,例如抛出自定义异常等,一般应该不会出现这种情况。
					return "error";
				}
			}

			String fileName = this.getFileFileName().get(0);
			
			teachingPlan.setFilePath(fileName);
			File uploadFile = new File(dir, fileName);
			OutputStream out = new FileOutputStream(uploadFile);
			byte[] buffer = new byte[1024 * 1024];
			int length;
			while ((length = in.read(buffer)) > 0) {
				out.write(buffer, 0, length);
			}

			// 文件地点
			String fileWholeLocation = dir + "\\" + fileName;
			System.out.println(fileWholeLocation);

			File file = new File(fileWholeLocation);

			in.close();
			out.close();
			msg = "1";		
			
		} catch (Exception ex) {
			msg = "2";
			System.out.println("上传失败!");
			ex.printStackTrace();
		} 		
		
		
		//设置文件下载路径
		teachingPlan.setFilePath("/download/"+teachingPlan.getFilePath());	
		boolean flag = teachingPlanService.insertTeachingPlan(teachingPlan);

		
		if(flag&&"1".equals(msg)){
			msg = "文件上传成功！";//表示保存成功
		}else{
			msg = "文件上传失败，请联系管理员！";//表示保存失败
		}
		try {
			ServletActionContext.getResponse().getWriter().write(msg);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
		return null;
	}		
	



	
	
	public ITeachingPlanService getTeachingPlanService() {
		return teachingPlanService;
	}


	public void setTeachingPlanService(ITeachingPlanService TeachingPlanService) {
		this.teachingPlanService = TeachingPlanService;
	}



	public List getTeachingPlanList() {
		return teachingPlanList;
	}


	public void setTeachingPlanList(List teachingPlanList) {
		this.teachingPlanList = teachingPlanList;
	}


	public TeachingPlan getTeachingPlan() {
		return teachingPlan;
	}

	public void setTeachingPlan(TeachingPlan teachingPlan) {
		this.teachingPlan = teachingPlan;
	}
	

	public List getPieceList() {
		return pieceList;
	}

	public void setPieceList(List pieceList) {
		this.pieceList = pieceList;
	}	
	
	
	
	public List<File> getFile() {
		return file;
	}



	public void setFile(List<File> file) {
		this.file = file;
	}



	public List<String> getFileFileName() {
		return fileFileName;
	}



	public void setFileFileName(List<String> fileFileName) {
		this.fileFileName = fileFileName;
	}



	public static String getUploaddir() {
		return UPLOADDIR;
	}
	
	
	
}
