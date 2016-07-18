package Client;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import Rmi.MeetingImpl;
import Rmi.MeetingInfo;
import Rmi.MeetingInterface;

public class Client {
	private static String name = "";
	private static String password = "";
	
	public static void main(String[] args){
		try {
			MeetingInterface obj = (MeetingInterface)Naming.lookup("rmi://localhost:9000/RmiMeeting");
			if(args[0].compareTo("1")==0){//login
				System.out.println("**** 用户登录 ****");
				name=args[1];
				password=args[2];
				System.out.println("请输入用户："+name);
				System.out.println("请输入密码："+password);
				int islogin = obj.UserLogin(name, password);
				System.out.print("结果：");
				switch(islogin){
					case 0:
						System.out.println("Error：密码错误！");
						break;
					case 1:
						System.out.println("用户 "+name+" 登录成功！");
						break;
					case 2:
						System.out.println("Error：不存在该用户！");
						break;
				}
			}else if(args[0].compareTo("2")==0){//regist
				System.out.println("**** 注册用户 ****");
				name=args[1];
				password=args[2];
				System.out.println("请输入用户："+name);
				System.out.println("请输入密码："+password);
				int islogin = obj.UserRegister(name, password);
				System.out.print("结果：");
				switch(islogin){
					case 0:
						System.out.println("Error：用户已存在！");
						break;
					case 1:
						System.out.println("用户 "+ name +" 注册成功！");
						break;
				}
			}else if(args[0].compareTo("3")==0){//add meeting
				System.out.println("**** 添加会晤 ****");
				String description_label = args[1];
				name = args[2];
				String book_user = args[3];
				Date start_time = getInputDate(args[4],args[5]);
				Date end_time = getInputDate(args[6],args[7]);
				System.out.println("请输入标识："+description_label);
				System.out.println("请输入发起用户："+name);
				System.out.println("请输入预约用户："+ book_user);
				System.out.println("请输入开始时间："+args[4]+" "+args[5]);
				System.out.println("请输入结束时间："+args[6]+" "+args[7]);
				int result = obj.MeetingAdd(description_label, name, book_user, start_time, end_time);
				System.out.print("结果：");
				switch(result){
					case 0:
						System.out.println("Error：找不到用户或未知错误！会晤创建失败。");
						break;
					case 1:
						System.out.println("会晤 "+description_label+" 创建成功！");
						break;
					case 2:
						System.out.println("Error：已有与该对象的会晤！");
						break;
					case 3:
						System.out.println("Error：与已有会晤时间冲突！");
						break;
				}
			}else if(args[0].compareTo("4")==0){//search meeting
				System.out.println("**** 查询会晤 ****");
				CallBackImpl callback = new CallBackImpl();
				name=args[1];
				Date start_time = getInputDate(args[2],args[3]);
				Date end_time = getInputDate(args[4],args[5]);
				System.out.println("请输入查询用户："+name);
				System.out.println("请输入开始时间："+args[2]+" "+args[3]);
				System.out.println("请输入结束时间："+args[5]+" "+args[5]);
				obj.MeetingSearch(name, start_time, end_time,callback);
				List<MeetingInfo> my_meetings = callback.getMyMeetings();
				System.out.println("用户 "+name+" 的会晤查询结果：");
				System.out.println("编号 " + "标签 " + "发起 " + "预约 " + "开始时间" + '\t' + '\t' + '\t' + " " + "结束时间");
				for(int i=0;i<my_meetings.size();i++){
					System.out.println(new String(i+1+"    ") + my_meetings.get(i).getDescriptionLabel() +" "+ my_meetings.get(i).getStartUser() + " " + 
							my_meetings.get(i).getBookUser()+ " " + my_meetings.get(i).getStartTime() + " " + my_meetings.get(i).getEndTime());
				}
			}else if(args[0].compareTo("5")==0){//delete meeting
				System.out.println("**** 删除会晤 ****");
				String my_name = args[1];
				String my_password = args[2];
				String description_label = args[3];
				System.out.println("请输入用户："+ my_name);
				System.out.println("请输入密码："+ my_password);
				System.out.println("请输入标签："+ description_label);
				int result = obj.MeetingDelete(description_label, my_name, my_password);
				System.out.print("结果：");
				switch(result){
					case 0:
						System.out.println("Error：用户名密码错误！");
						break;
					case 1:
						System.out.println("会晤 "+description_label+" 删除成功！");
						break;
					case 2:
						System.out.println("Error：找不到该标签！");
						break;
				}
			}else if(args[0].compareTo("6")==0){//clear meeting
				System.out.println("**** 清除会晤 ****");
				String my_name = args[1];
				String my_password = args[2];
				System.out.println("请输入用户："+ my_name);
				System.out.println("请输入密码："+ my_password);
				int result = obj.MeetingClear(my_name, my_password);
				System.out.print("结果：");
				switch(result){
				case 0:
					System.out.println("Error：用户名密码错误！");
					break;
				case 1:
					System.out.println("用户 "+my_name+" 会晤清除成功！");
					break;
				}
			}
			
		} catch (Exception e) {
			System.out.println("Error:与服务器连接失败！");
		}finally{
			System.out.println("退出");
		}
	}
	
	public static Date getInputDate(String str1,String str2){//获取正确时间格式
		Date date = new Date();
		String str=str1+" "+str2;
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			date = sdf.parse(str);
		} catch (ParseException e) {
			System.out.println("Error：错误的时间格式！");
		}
		return date;
	}
}