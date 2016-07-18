package Rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.xml.crypto.Data;

import Client.CallBackInterface;

public class MeetingImpl extends UnicastRemoteObject implements MeetingInterface{//RMI接口实现
	private List<UserInfo> users = new ArrayList<UserInfo>();
	private List<MeetingInfo> meetings = new ArrayList<MeetingInfo>();
	
	public MeetingImpl() throws RemoteException {
		super();
	}

	@Override
	public int UserRegister(String name, String password) throws RemoteException{
		// 用户注册
		for(int i=0;i<users.size();i++){
			if(name.compareTo(users.get(i).getName())==0){
				return 0;//用户名已存在
			}
		}
		UserInfo new_user = new UserInfo(name,password);
		users.add(new_user);
		return 1;//注册成功
	}

	@Override
	public int UserLogin(String name, String password) throws RemoteException{
		// 用户登录
		for(int i=0;i<users.size();i++){
			if(name.compareTo(users.get(i).getName())==0){
				if(password.compareTo(users.get(i).getPassword())==0){
					return 1;//登录成功
				}
				else
					return 0;//密码错误
			}
		}
		return 2;//无该用户
	}

	@Override
	public int MeetingAdd(String description_label, String start_user,
			String book_user, Date start_time, Date end_time) throws RemoteException{
		// 添加会晤
		for(int i=0;i<meetings.size();i++){
			if(meetings.get(i).getStartUser().compareTo(start_user)==0){
				if(meetings.get(i).getBookUser().compareTo(book_user)==0)
					return 2;//已有相同对象的会晤
				else if(meetings.get(i).getStartTime()==start_time && meetings.get(i).getEndTime()==end_time)
					return 3;//时间冲突
			}
		}
		for(int j=0;j<users.size();j++){
			if(users.get(j).getName().compareTo(book_user)==0){
				MeetingInfo new_meeting = new MeetingInfo(description_label,start_user,book_user,start_time,end_time);
				meetings.add(new_meeting);
				return 1;//添加会晤成功
			}
		}
		return 0;//找不到用户或未知错误
	}

	@Override
	public int MeetingDelete(String description_label, String user_name,
			String password) throws RemoteException{
		// 删除会晤
		boolean true_userinfo=false;
		for(int i=0;i<users.size();i++){
			if(user_name.compareTo(users.get(i).getName())==0){
				if(password.compareTo(users.get(i).getPassword())==0)
					true_userinfo=true;//用户名密码正确
			}
		}
		if(true_userinfo){
			for(int i=0;i<meetings.size();i++){
				if(meetings.get(i).getDescriptionLabel().compareTo(description_label)==0 && 
						meetings.get(i).getStartUser().compareTo(user_name)==0){
					meetings.remove(i);
					return 1;//删除成功
				}
			}
			return 2;//找不到该标签
		}
		else
			return 0;//用户名密码错误
	}
	
	@Override
	public int MeetingClear(String user_name, String password) throws RemoteException{
		// 清除会晤
		boolean true_userinfo=false;
		for(int i=0;i<users.size();i++){
			if(user_name.compareTo(users.get(i).getName())==0){
				if(password.compareTo(users.get(i).getPassword())==0)
					true_userinfo=true;//用户名密码正确
			}
		}
		if(true_userinfo){
			for(int i=0;i<meetings.size();i++){
				if(meetings.get(i).getStartUser().compareTo(user_name)==0){
					meetings.remove(i);
					i--;
				}
			}
			return 1;//清除成功
		}
		else
			return 0;//用户名密码错误
	}

	@Override
	public void MeetingSearch(String user_name, Date start_time,
			Date end_time, CallBackInterface callback) throws RemoteException{
		// 查询会晤
		for(int i=0;i<meetings.size();i++){
			if(user_name.compareTo(meetings.get(i).getStartUser())==0 ||
					user_name.compareTo(meetings.get(i).getBookUser())==0){//找到用户
				if(start_time.compareTo(meetings.get(i).getStartTime())<0 &&
						end_time.compareTo(meetings.get(i).getEndTime())>0){//确认时间范围
					callback.getOneSearchResult(meetings.get(i).getDescriptionLabel(),meetings.get(i).getStartUser(),
							meetings.get(i).getBookUser(),meetings.get(i).getStartTime(),meetings.get(i).getEndTime());
				}
			}
		}
	}	
}
