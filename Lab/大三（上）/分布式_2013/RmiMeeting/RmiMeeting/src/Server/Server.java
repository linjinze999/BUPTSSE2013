package Server;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.Scanner;

import Rmi.MeetingImpl;

public class Server {
	public static void main(String[] args){
		boolean start = false;
		try{
			MeetingImpl obj=new MeetingImpl();
			LocateRegistry.createRegistry(9000);//注册服务器
            Naming.bind("//localhost:9000/RmiMeeting", obj);//绑定对象
            start = true;
			System.out.println("服务器绑定对象成功");
			System.in.read();
		}catch (Exception e) {
			System.out.println("服务器初始绑定出错");
		}finally{
			if(start){
				try {
					Naming.unbind("//localhost:9000/RmiMeeting");//解除绑定
					start = false;
					System.out.println("服务器对象解除成功");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}



