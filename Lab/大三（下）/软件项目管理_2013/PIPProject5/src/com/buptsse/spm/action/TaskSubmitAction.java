package com.buptsse.spm.action;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.buptsse.spm.dao.impl.AntSqlExec;
import com.buptsse.spm.dao.impl.MySqlDao;
import com.buptsse.spm.domain.MyArgument;
import com.buptsse.spm.domain.Team;
import com.buptsse.spm.service.ArgumentService;
import com.buptsse.spm.service.TeamService;

public class TaskSubmitAction {
	private static Logger LOG = LoggerFactory.getLogger(TaskSubmitAction.class);
	private File document_deploy;
	private String document_deployFileName;
	private File document_deploy_db;
	private String document_deploy_dbFileName;
	private String projectName;
	private File document_code;
	private String document_codeFileName;
	private File document_require;
	private String document_requireFileName;
	private File document_design;
	private String document_designFileName;
	private File document_detail;
	private String document_detailFileName;
	private String dataBaseName;
	private String[] functionTestUseCase;
	private int userId;
	private MyArgument myargument;
	@Resource
	private TeamService teamService;
	@Resource
	private ArgumentService argumentService;
	
	/*-----------------检测远程服务器是否打开--------------------*/
	public String openDeployPage(){
		JSONObject jo = new JSONObject();
		try{
			searchArgument();
			URL url = new URL(findIpPath());
			URLConnection con = url.openConnection();
			java.io.BufferedReader in;
			in = new java.io.BufferedReader(new java.io.InputStreamReader(con.getInputStream()));
			con.setConnectTimeout(3000);
		    con.setReadTimeout(4000);
		    String s;
		    while((s = in.readLine()) != null) {
		        if (s.length() > 0) {
		            //若服务器打开，则返回部署页面的url
		        	jo.put("url", findIpPath()+"manager/html");
		        	break;
		            }
		        }
		    in.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		try {
			ServletActionContext.getResponse().getWriter().write(jo.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	/*-----------------远程数据库创建--------------------*/
	public String deployDatabase(){
		String str="";
		BufferedReader br = null;
		ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
		try{
			if(dataBaseName==null || "".equals(dataBaseName)){
				str="错误：未检测到数据库名称！";
			}else if(document_deploy_db==null){
				str="错误：未检测到sql文件！";
			}else{
				searchArgument();
				//保存sql文件
				String uploadPath=findFilePath();
		        File uploadDir=new File(uploadPath);
		        if(!uploadDir.exists()){
		            uploadDir.mkdirs();
		        }
		        boolean success = false;
		        File uploadFile=new File(uploadPath,document_deploy_dbFileName);
		        if(uploadFile.exists()){
		        	if(uploadFile.delete()){
		        		success = document_deploy_db.renameTo(uploadFile);
		        	}
		        }else{
		        	success = document_deploy_db.renameTo(uploadFile);
		        }
		        if(!success){
		        	str="失败：数据库文件上传失败";
		        }else{
		        	//远程创建数据库
		        	String user=findMysqlUser();
					String password=findMysqlPassword();
					String filePath=uploadFile.getPath();
					String dbip=findMysqlIp();
					String port=findMysqlPort();
					MySqlDao ms = new MySqlDao(dbip,user,password);
					if(ms.getStat()==null){
						str="错误：未能连接到数据库服务器！";
					}else{
						if(!ms.executeUpdate("use "+dataBaseName)){
							//未有该名字数据库，先创建数据库
							if(ms.executeUpdate("CREATE DATABASE " + dataBaseName)){
								//执行sql文件
								AntSqlExec ase = new AntSqlExec("com.mysql.jdbc.Driver","jdbc:mysql://" + dbip+":"+port+"/"+dataBaseName,user,password);
								try{
									ase.execSql(filePath);
									str="成功：创建数据库成功！";
								}catch(Exception e1){
									e1.printStackTrace();
									str="错误：新数据库导入sql文件出错！";
								}
							}else{
								str="错误：创建数据库失败！";
							}
						}else{
							//已有同名数据库，直接执行sql文件
							AntSqlExec ase = new AntSqlExec("com.mysql.jdbc.Driver","jdbc:mysql://" + dbip+":"+port+"/"+dataBaseName,user,password);
							try{
								ase.execSql(filePath);
								str="成功：修改数据库成功！";
							}catch(Exception e1){
								e1.printStackTrace();
								str="错误：已有数据库导入sql文件出错！";
							}
						}
					}
		        }
			}
	        System.out.println(str);
			
			ServletActionContext.getResponse().getWriter().write(str);
		}catch(Exception e){
			e.printStackTrace();
			str="错误：执行时发生未知错误。";
			try {
				ServletActionContext.getResponse().getWriter().write(str);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}finally {
            if (br != null) {
                try {
                    br.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
		return null;
	}
	/*-----------------检测项目是否成功部署--------------------*/
	public String isDeployOK(){
		JSONObject jo = new JSONObject();
		try{
			if(projectName==null || "".equals(projectName)){
				jo.put("error", "错误：项目名为空。");
			}else{
				searchArgument();
				boolean open = true;
				try{
					URL url = new URL(findIpPath());
					URLConnection con = url.openConnection();
					java.io.BufferedReader in;
					in = new java.io.BufferedReader(new java.io.InputStreamReader(con.getInputStream()));
					con.setConnectTimeout(3000);
				    con.setReadTimeout(4000);
				    String s;
				    while((s = in.readLine()) != null) {
				        if (s.length() > 0) {
				            //先检测远程服务器是否打开
				        	break;
				            }
				        }
				    in.close();
				}catch(Exception e){
					e.printStackTrace();
					open=false;
					jo.put("error", "错误：远程服务器未开启！");
				}
				if(open){
					URL url = new URL(findIpPath()+projectName);
					URLConnection con = url.openConnection();
					java.io.BufferedReader in;
					in = new java.io.BufferedReader(new java.io.InputStreamReader(con.getInputStream()));
					con.setConnectTimeout(3000);
				    con.setReadTimeout(4000);
				    String s;
				    while((s = in.readLine()) != null) {
				        if (s.length() > 0) {
				            //项目成功部署，返回项目url
				        	Team myteam = this.teamService.searchTeamByMember(userId);
				        	myteam.setUrl(findIpPath()+projectName);
				        	this.teamService.updateTeamTsak(myteam);
				        	myteam.setDeployScore(10);
				        	myteam.setLoginScore(10);
				        	this.teamService.updateTeamScore(myteam);
				        	jo.put("success", findIpPath()+projectName);
				        	break;
				        }
				    }
				    in.close();
				}
			}
			ServletActionContext.getResponse().getWriter().write(jo.toString());
		}catch(Exception e){
			e.printStackTrace();
			jo.put("error", "错误：项目未部署！");
			try {
				ServletActionContext.getResponse().getWriter().write(jo.toString());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return null;
	}
	/*-----------------程序提交--------------------*/
	public String codeSubmit(){
		String str="";
		ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
		try{
			if(document_code==null){
				str="失败：未检测到上传文件！";
			}else{
				searchArgument();
		        String uploadPath=findFilePath();
		        File uploadDir=new File(uploadPath);
		        if(!uploadDir.exists()){
		            uploadDir.mkdirs();
		        }
		        boolean success = false;
		        File uploadFile=new File(uploadPath,document_codeFileName);
		        if(uploadFile.exists()){
		        	if(uploadFile.delete()){
		        		success = document_code.renameTo(uploadFile);
		        	}
		        }else{
		        	success = document_code.renameTo(uploadFile);
		        }
		        if(success){
		        	str="成功：程序上传成功！";
		        	Team myteam = this.teamService.searchTeamByMember(userId);
		        	myteam.setCode("path="+uploadFile.getPath());
		        	this.teamService.updateTeamTsak(myteam);
		        	myteam.setCode1Score(10);
		        	myteam.setCode2Score(10);
		        	this.teamService.updateTeamScore(myteam);
		        }
		        else
		        	str="失败：程序上传失败";
		        System.out.println(str);
			}
			ServletActionContext.getResponse().getWriter().write(str);
		}catch(Exception e){
			e.printStackTrace();
			str="错误：上传时发生未知错误。";
			try {
				ServletActionContext.getResponse().getWriter().write(str);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return null;
	}
	/*--------------------功能测试--------------------*/
	public String functionTest(){
		JSONObject jo = new JSONObject();
		try{
			if(functionTestUseCase==null || functionTestUseCase.length<5){
				jo.put("error", "错误：测试集未完整！");
			}else if("".equals(functionTestUseCase[0])){
				jo.put("error", "错误：测试1信息不完整！");
			}else if("".equals(functionTestUseCase[1])){
				jo.put("error", "错误：测试2信息不完整！");
			}else if("".equals(functionTestUseCase[2])){
				jo.put("error", "错误：测试3信息不完整！");
			}else if("".equals(functionTestUseCase[3])){
				jo.put("error", "错误：测试4信息不完整！");
			}else if("".equals(functionTestUseCase[4])){
				jo.put("error", "错误：测试5信息不完整！");
			}else{
				if(!functionTestUseCase[0].startsWith("http")){
					functionTestUseCase[0]="http://"+functionTestUseCase[0];
				}
				if(!functionTestUseCase[1].startsWith("http")){
					functionTestUseCase[1]="http://"+functionTestUseCase[1];
				}
				if(!functionTestUseCase[2].startsWith("http")){
					functionTestUseCase[2]="http://"+functionTestUseCase[2];
				}
				if(!functionTestUseCase[3].startsWith("http")){
					functionTestUseCase[0]="http://"+functionTestUseCase[0];
				}
				if(!functionTestUseCase[3].startsWith("http")){
					functionTestUseCase[3]="http://"+functionTestUseCase[3];
				}
				if(!functionTestUseCase[4].startsWith("http")){
					functionTestUseCase[4]="http://"+functionTestUseCase[4];
				}
				ExecutorService pool = Executors.newFixedThreadPool(5);
				ConnectTimeTest test1 = new ConnectTimeTest(functionTestUseCase[0]);
				ConnectTimeTest test2 = new ConnectTimeTest(functionTestUseCase[1]);
				ConnectTimeTest test3 = new ConnectTimeTest(functionTestUseCase[2]);
				ConnectTimeTest test4 = new ConnectTimeTest(functionTestUseCase[3]);
				ConnectTimeTest test5 = new ConnectTimeTest(functionTestUseCase[4]);
				Future f1 = pool.submit(test1);
				Future f2 = pool.submit(test2);
				Future f3 = pool.submit(test3);
				Future f4 = pool.submit(test4);
				Future f5 = pool.submit(test5);
				long[] times = {(Long) f1.get(),(Long) f2.get(),(Long) f3.get(),(Long) f4.get(),(Long) f5.get()};
				int linkScore=connectTimeToLinkScore(times);
				int functionScore=connectTimeToFunctionScore(times);
				Team myteam = this.teamService.searchTeamByMember(userId);
	        	myteam.setLinkScore(linkScore);
	        	myteam.setFunctionScore(functionScore);
	        	this.teamService.updateTeamScore(myteam);
				System.out.println("链接得分："+ linkScore);
				System.out.println("功能得分："+ functionScore);
				pool.shutdown();
				jo.put("success", "成功：测试成功！");
			}
			ServletActionContext.getResponse().getWriter().write(jo.toString());
		}catch(Exception e){
			e.printStackTrace();
			jo.put("error", "错误：测试发生错误！");
			try {
				ServletActionContext.getResponse().getWriter().write(jo.toString());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return null;
	}
	
	//http://www.pagescoring.com/website-speed-test/?url=www.baidu.com
	//https://tools.pingdom.com/#!/cBlFHT/www.baidu.com
	public String performanceTest(){
		JSONObject jo = new JSONObject();
		try{
			/** HtmlUnit请求web页面 */  
			/*
			//freemarker.log.Logger.selectLoggerLibrary(freemarker.log.Logger.LIBRARY_NONE);
			WebClient wc = new WebClient(BrowserVersion.FIREFOX_24);
			//final WebClient wc = new WebClient();
			wc.getOptions().setUseInsecureSSL(true);  
			wc.getOptions().setJavaScriptEnabled(true); // 启用JS解释器，默认为true
			wc.getOptions().setCssEnabled(false); // 禁用css支持  
			wc.getOptions().setThrowExceptionOnScriptError(false); // js运行错误时，是否抛出异常  
			wc.getOptions().setTimeout(100000); // 设置连接超时时间 ，这里是10S。如果为0，则无限期等待  
			wc.getOptions().setDoNotTrackEnabled(false);
			final HtmlPage page = wc.getPage("https://gtmetrix.com/");
			//HtmlForm form = page.getFormByName("urlform");
			List<HtmlForm> forms = page.getForms();
			HtmlForm form = forms.get(0);
			HtmlElement button = form.getElementsByTagName("button").get(0);
			HtmlTextInput textField = form.getInputByName("url");
			textField.setValueAttribute("http://www.baidu.com");
			HtmlPage page2=button.click();
			System.out.println(page2.getElementsByTagName("h1").get(0).asXml().toString());
			
			
			while(page2.getElementsByTagName("h1").get(0).asXml().toString().contains("Analyzing")){
				System.out.println(page2.getElementsByTagName("h1").get(0).toString());
				Thread.sleep(1000);
			}
			//HtmlPage page2=wc.getPage((WebRequest) button.click());
			System.out.println(page2.asXml());
			System.out.println(page2.getUrl());
			wc.closeAllWindows();
			*/
			/*
			System.out.println("-------------1-------------");
			final WebDriver webDriver = new HtmlUnitDriver();//InternetExplorerDriver();//new ChromeDriver();//Firefox
			System.out.println("-------------2-------------");
			String url = "https://gtmetrix.com/";  
			webDriver.get(url);
			System.out.println("-------------3-------------");
	        WebElement webElement = webDriver.findElements(By.tagName("form")).get(0);
	        WebElement textField = webElement.findElement(By.name("url"));
	        textField.sendKeys("http://www.baidu.com");
	        System.out.println("-------------4-------------");
	        webElement.submit();
	        System.out.println("-------------5-------------");
	        System.out.println(webDriver.findElement(By.tagName("h1")).getText().toString());
	        
	        while(webDriver.findElement(By.tagName("h1")).getText().toString().contains("Analyzing")){
	        	System.out.println(webDriver.findElement(By.tagName("h1")).getText().toString());
	        	Thread.sleep(2000);
	        }
	        */
	        /*
	        (new WebDriverWait(webDriver, 1000)).until(new ExpectedCondition<Boolean>() {
	            public Boolean apply(WebDriver d) {
	            	System.out.println(webDriver.findElement(By.tagName("h1")).getText().toString());
	                return d.findElement(By.tagName("h1")).getText().toString().contains("Analyzing");  
	           }  
	       });*/
			jo.put("success", "成功：测试成功！");
			ServletActionContext.getResponse().getWriter().write(jo.toString());
		}catch(Exception e){
			e.printStackTrace();
			jo.put("error", "错误：测试发生错误！");
			try {
				ServletActionContext.getResponse().getWriter().write(jo.toString());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		return null;
	}
	
	public String isCodeSubmit(){
		JSONObject jo = new JSONObject();
		try{
			if(userId<=0){
				jo.put("error", "错误：未找到用户！");
			}else{
				int result = this.teamService.isCodeSubmit(userId);
				if(result<=0){
					jo.put("error", "错误：代码未提交！");
				}else{
					jo.put("success", "成功：代码已提交！");
				}
			}
			ServletActionContext.getResponse().getWriter().write(jo.toString());
		}catch(Exception e){
			e.printStackTrace();
			jo.put("error", "错误：未找到用户！");
			try {
				ServletActionContext.getResponse().getWriter().write(jo.toString());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return null;
	}
	
	public String isFunctionTest(){
		JSONObject jo = new JSONObject();
		try{
			if(userId<=0){
				jo.put("error", "错误：未找到用户！");
			}else{
				int result = this.teamService.isFunctionTest(userId);
				if(result<=0){
					jo.put("error", "错误：未做功能测试！");
				}else{
					jo.put("success", "成功：功能测试已做完！");
				}
			}
			ServletActionContext.getResponse().getWriter().write(jo.toString());
		}catch(Exception e){
			e.printStackTrace();
			jo.put("error", "错误：未找到用户！");
			try {
				ServletActionContext.getResponse().getWriter().write(jo.toString());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return null;
	}
	
	public String isPerformanceTest(){
		JSONObject jo = new JSONObject();
		try{
			if(userId<=0){
				jo.put("error", "错误：未找到用户！");
			}else{
				int result = this.teamService.isPerformanceTest(userId);
				if(result<=0){
					jo.put("error", "错误：未做性能测试！");
				}else{
					jo.put("success", "成功：性能测试已做完！");
				}
			}
			ServletActionContext.getResponse().getWriter().write(jo.toString());
		}catch(Exception e){
			e.printStackTrace();
			jo.put("error", "错误：未找到用户！");
			try {
				ServletActionContext.getResponse().getWriter().write(jo.toString());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return null;
	}
	
	public String isDocumentSubmit(){
		JSONObject jo = new JSONObject();
		try{
			if(userId<=0){
				jo.put("error", "错误：未找到用户！");
			}else{
				int result1 = this.teamService.isDocumentRequireSubmit(userId);
				int result2 = this.teamService.isDocumentDesignSubmit(userId);
				int result3 = this.teamService.isDocumentDetailSubmit(userId);
				if(result1>0 && result2>0 && result3>0){
					jo.put("success", "成功：文档已提交！");
				}else{
					String error = "";
					if(result1<=0){
						error+="需求 ";
					}
					if(result2<=0){
						error+="概要 ";
					}
					if(result3<=0){
						error+="详细 ";
					}
					jo.put("error", "错误："+error+"文档未提交！");
				}
			}
			ServletActionContext.getResponse().getWriter().write(jo.toString());
		}catch(Exception e){
			e.printStackTrace();
			jo.put("error", "错误：未找到用户！");
			try {
				ServletActionContext.getResponse().getWriter().write(jo.toString());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return null;
	}
	
	/*-----------------文档提交--------------------*/
	public String documentSubmit(){
		String str="";
		ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
		try{
			if(document_require==null && document_design==null && document_detail==null){
				str="错误：未检测到文件！";
			}else{
				searchArgument();
		        String uploadPath=findFilePath();
		        File uploadDir=new File(uploadPath);
		        if(!uploadDir.exists()){
		            uploadDir.mkdirs();
		        }
		        boolean success1 = true;
		        boolean success2 = true;
		        boolean success3 = true;
		        int suc1 = 1;
		        int suc2 = 1;
		        int suc3 = 1;
		        Team myteam = this.teamService.searchTeamByMember(userId);
		        if(document_require!=null){
		        	File uploadFile1 = new File(uploadPath,document_requireFileName);
			        if(uploadFile1.exists()){
			        	if(uploadFile1.delete()){
			        		success1 = document_require.renameTo(uploadFile1);
			        	}else{
			        		success1 = false;
			        	}
			        }else{
			        	success1 = document_require.renameTo(uploadFile1);
			        }
			        if(success1){
			        	myteam.setRequireDocu(uploadFile1.getPath());
			        	suc1=this.teamService.updateTeamTsak(myteam);
		        	}
		        }
		        if(document_design!=null){
		        	File uploadFile2 = new File(uploadPath,document_designFileName);
			        if(uploadFile2.exists()){
			        	if(uploadFile2.delete()){
			        		success2 = document_design.renameTo(uploadFile2);
			        	}else{
			        		success2 = false;
			        	}
			        }else{
			        	success2 = document_design.renameTo(uploadFile2);
			        }
			        if(success2){
		        		myteam.setDesignDocu(uploadFile2.getPath());
		        		suc2=this.teamService.updateTeamTsak(myteam);
		        	}
		        }
		        if(document_detail!=null){
		        	File uploadFile3 = new File(uploadPath,document_detailFileName);
			        if(uploadFile3.exists()){
			        	if(uploadFile3.delete()){
			        		success3 = document_detail.renameTo(uploadFile3);
			        	}else{
			        		success3 = false;
			        	}
			        }else{
			        	success3 = document_detail.renameTo(uploadFile3);
			        }
			        if(success3){
		        		myteam.setDetailDocu(uploadFile3.getPath());
		        		suc3=this.teamService.updateTeamTsak(myteam);
		        	}
		        }
		        if(success1 && success2 && success3){
		        	if(suc1>0 && suc2>0 && suc3>0)
		        		str="成功：文档上传成功！";
		        	else{
		        		str="错误：文档上传成功，但";
		        		 if(suc1<=0)
				        		str+="需求 ";
		        		 if(suc1<=0)
				        		str+="概要 ";
		        		 if(suc1<=0)
				        		str+="详细 ";
		        		 str+="文档录入数据库失败。";
		        	}
		        	String require = document_requireFileName;
		        	String design = document_designFileName;
		        	String detail = document_detailFileName;
		        	int score=4;
		        	if(require.startsWith("BUPT-SPM-") && require.contains("-SRS")){
		        		score+=2;
		        	}
		        	if(require.startsWith("BUPT-SPM-") && require.contains("-HLD")){
		        		score+=2;
		        	}
		        	if(require.startsWith("BUPT-SPM-") && require.contains("-DLD")){
		        		score+=2;
		        	}
		        	myteam.setDocument1Score(score);
		        	myteam.setDocument2Score(10);
		        	this.teamService.updateTeamScore(myteam);
		        }
		        else{
		        	str="失败：文档上传失败";
		        }
		        System.out.println(str);
			}
			ServletActionContext.getResponse().getWriter().write(str);
		}catch(Exception e){
			e.printStackTrace();
			str="错误：上传时发生未知错误。";
			try {
				ServletActionContext.getResponse().getWriter().write(str);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return null;
	}
	
	
	class ConnectTimeTest implements Callable{
		String urlAddress;
		
		public ConnectTimeTest(String urlAddress){
			this.urlAddress = urlAddress;
		}

		@Override
		public Object call() throws Exception {
			// TODO Auto-generated method stub
			try{
				URL url = new URL(urlAddress);
				Date start = new Date();
				URLConnection con = url.openConnection();
				java.io.BufferedReader in;
				in = new java.io.BufferedReader(new java.io.InputStreamReader(con.getInputStream()));
				con.setConnectTimeout(10000);
			    con.setReadTimeout(10000);
			    Date stop = new Date();
			    long time = stop.getTime() - start.getTime();
			    return time;
			}catch(Exception e){
				return -1;
			}
		}
	}
	/*
	class PageWait implements Callable{
		HtmlPage page;
		
		public PageWait(HtmlPage page){
			this.page = page;
		}

		@Override
		public Object call() throws Exception {
			// TODO Auto-generated method stub
			Thread.sleep(5000);
			return page;
		}
	}
	*/
	
	private void searchArgument(){
		myargument=argumentService.searchArgument();
	}
	private String findIpPath(){//返回远程服务器IP地址
		String result="";
		if(myargument!=null){
			if(myargument.getDeployIp().startsWith("http")){
				result += myargument.getDeployIp();
			}else{
				result += "http://"+myargument.getDeployIp();
			}
			if(myargument.getDeployPort()!=null  && !"".equals(myargument.getDeployPort())){
				result += ":"+myargument.getDeployPort();
			}
			result += "/";
			
		}
		return result;
		//return "http://localhost:8081/";
	}
	private String findFilePath(){//返回该学生团队文件存储路径
		String result="E:/PIPProjects";
		if(myargument.getDocuPath()!=null && !"".equals(myargument.getDocuPath())){
			result = myargument.getDocuPath();
		}
		try{
			if(userId>0){
				Team myteam=teamService.searchTeamByMember(userId);
				if(myteam!=null)
					result+="/"+myteam.getName();
			}
		}catch(Exception e){
			
		}
		return result;
	}
	private String findMysqlUser(){//返回mysql账户
		return myargument.getDbAccount();
	}
	private String findMysqlPassword(){//返回mysql密码
		return myargument.getDbPassword();
	}
	private String findMysqlIp(){//返回mysql IP
		return myargument.getDbIp();
	}
	private String findMysqlPort(){//返回mysql端口
		return myargument.getDbPort();
	}
	private int connectTimeToFunctionScore(long[] times){
		int score=10;
		for(int i=0;i<times.length;i++){
			if(times[i]<0)
				score-=2;
		}
		return score;
	}
	private int connectTimeToLinkScore(long[] times){
		int count=0;
		for(int i=0;i<times.length;i++){
			if(times[i]>0 && times[i]<2000)
				count++;
		}
		if(count>3)
			return 10;
		else if(count==2)
			return 5;
		else
			return 3;
	}

	public File getDocument_deploy() {
		return document_deploy;
	}

	public void setDocument_deploy(File document_deploy) {
		this.document_deploy = document_deploy;
	}

	public File getDocument_deploy_db() {
		return document_deploy_db;
	}

	public void setDocument_deploy_db(File document_deploy_db) {
		this.document_deploy_db = document_deploy_db;
	}

	public String getDocument_deployFileName() {
		return document_deployFileName;
	}

	public void setDocument_deployFileName(String document_deployFileName) {
		this.document_deployFileName = document_deployFileName;
	}

	public String getDocument_deploy_dbFileName() {
		return document_deploy_dbFileName;
	}

	public void setDocument_deploy_dbFileName(String document_deploy_dbFileName) {
		this.document_deploy_dbFileName = document_deploy_dbFileName;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public File getDocument_code() {
		return document_code;
	}
	public void setDocument_code(File document_code) {
		this.document_code = document_code;
	}
	public String getDocument_codeFileName() {
		return document_codeFileName;
	}
	public void setDocument_codeFileName(String document_codeFileName) {
		this.document_codeFileName = document_codeFileName;
	}
	public String getDataBaseName() {
		return dataBaseName;
	}
	public void setDataBaseName(String dataBaseName) {
		this.dataBaseName = dataBaseName;
	}
	public String[] getFunctionTestUseCase() {
		return functionTestUseCase;
	}
	public void setFunctionTestUseCase(String[] functionTestUseCase) {
		this.functionTestUseCase = functionTestUseCase;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public File getDocument_require() {
		return document_require;
	}
	public void setDocument_require(File document_require) {
		this.document_require = document_require;
	}
	public String getDocument_requireFileName() {
		return document_requireFileName;
	}
	public void setDocument_requireFileName(String document_requireFileName) {
		this.document_requireFileName = document_requireFileName;
	}
	public File getDocument_design() {
		return document_design;
	}
	public void setDocument_design(File document_design) {
		this.document_design = document_design;
	}
	public String getDocument_designFileName() {
		return document_designFileName;
	}
	public void setDocument_designFileName(String document_designFileName) {
		this.document_designFileName = document_designFileName;
	}
	public File getDocument_detail() {
		return document_detail;
	}
	public void setDocument_detail(File document_detail) {
		this.document_detail = document_detail;
	}
	public String getDocument_detailFileName() {
		return document_detailFileName;
	}
	public void setDocument_detailFileName(String document_detailFileName) {
		this.document_detailFileName = document_detailFileName;
	}


}
