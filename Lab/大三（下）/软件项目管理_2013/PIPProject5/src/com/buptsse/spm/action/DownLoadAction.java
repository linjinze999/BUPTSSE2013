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


import com.buptsse.spm.domain.DownLoad;
import com.buptsse.spm.service.IDownLoadService;

import com.opensymphony.xwork2.ActionSupport;


/**
 * @author libing
 * @date 2015年11月01日 下午4:17
 * @description 选课的service层接口定义 
 * @modify
 * @modifyDate 
 */
public class DownLoadAction extends ActionSupport{

	private static final long serialVersionUID = 1L;
	private static Logger LOG = LoggerFactory.getLogger(DownLoadAction.class);
	@Resource
	private IDownLoadService downLoadService;
	
	public List downLoadList = new ArrayList();

	public DownLoad downLoad;
	
	public String id;

	// 上传文件存放路径
	private final static String UPLOADDIR = "/download";
	// 上传文件集合
	private List<File> file;	
	// 上传文件名集合
	private List<String> fileFileName;	




	/**
	 * 查询所有可下载信息列表
	 * @return
	 * @throws Exception
	 */
	public String findDownLoadList() throws Exception{
		downLoadList = downLoadService.findAllDownLoad();
		return "success";
	}	

	/**
	 * 增加可下载信息
	 * @return
	 * @throws Exception
	 */
	public String insertDownLoad() throws Exception{
		
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
			
			downLoad.setFilePath(fileName);
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
			msg = "文件上传成功！";		
			
		} catch (Exception ex) {
			msg = "文件上传失败，请联系管理员！";
			System.out.println("上传失败!");
			ex.printStackTrace();
		} 
		
		ServletActionContext.getResponse().getWriter().write(msg);
		
		downLoad.setFilePath("/download/"+downLoad.getFilePath());
		boolean flag = downLoadService.insertDownLoad(downLoad);
		downLoadList = downLoadService.findAllDownLoad();


		return null;
		
	}		

	
	/**
	 * 删除下载信息
	 * @return
	 * @throws Exception
	 */
	public String deleteDownLoad() throws Exception{
		boolean flag = downLoadService.deleteDownLoad(id);
		downLoadList = downLoadService.findAllDownLoad();
		if(flag){
			return "success";
		}else{
			return "error";
		}
		
	}	
	
	
	public IDownLoadService getDownLoadService() {
		return downLoadService;
	}


	public void setDownLoadService(IDownLoadService downLoadService) {
		this.downLoadService = downLoadService;
	}



	public List getDownLoadList() {
		return downLoadList;
	}


	public void setDownLoadList(List downLoadList) {
		this.downLoadList = downLoadList;
	}


	public DownLoad getDownLoad() {
		return downLoad;
	}

	public void setDownLoad(DownLoad downLoad) {
		this.downLoad = downLoad;
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}	
	
	
}
