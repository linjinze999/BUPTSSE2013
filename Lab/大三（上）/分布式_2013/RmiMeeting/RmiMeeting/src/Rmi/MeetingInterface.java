package Rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.xml.crypto.Data;

import Client.CallBackInterface;

public interface MeetingInterface extends Remote{//RMI接口
	public int UserRegister(String name,String password)throws RemoteException;//用户注册
	public int UserLogin(String name,String password)throws RemoteException;//用户登录
	public int MeetingAdd(String description_label,String start_user,String book_user,Date start_time,Date end_time)throws RemoteException;//添加会晤
	public int MeetingDelete(String description_label,String user_name,String password)throws RemoteException;//删除会晤
	public int MeetingClear(String user_name,String password)throws RemoteException;//清除会晤
	public void MeetingSearch(String user_name,Date start_time,Date end_time,CallBackInterface callback)throws RemoteException;//查询会晤
}
