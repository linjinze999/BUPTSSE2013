/*
 * 客户端连接判断
 * */
package ChatClient;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import javax.swing.JOptionPane;

public class LoginWindowConnect extends Thread{
	private String Name;
	private String IP;
	private int Port;
	private LoginWindow lw;
	private DataInputStream dis;
	private DataOutputStream dos;
	private Socket socket;
	
	public LoginWindowConnect( String IP, int Port,String Name,LoginWindow lw) throws IOException{
		this.IP=IP;
		this.Port=Port;
		this.Name=Name;
		this.lw=lw;
		if(this.isConnect(IP, Port)){//连接服务器
			if(!this.isHaveName(Name)){
				lw.setVisible(false);
				new ClientWindow(this,this.Name);//打开客户端
			}
			else{//用户名已存在
				dos.close();
	            dis.close();
	            socket.close();
	            JOptionPane.showMessageDialog(lw, "用户名已存在，请重新输入", "登陆结果", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	private boolean isConnect(String IP, int Port){//连接服务器
		try {
			this.socket = new Socket(IP,Port);
			dis = new DataInputStream(socket.getInputStream());
			dos = new DataOutputStream(socket.getOutputStream());
			return true;
		}catch (Exception e) {
			JOptionPane.showMessageDialog(lw, "服务器可能还没启动, 请确定服务器正常", "无法连接到服务器", JOptionPane.ERROR_MESSAGE);
			return false;
		} 
	}
	
	private boolean isHaveName(String Name){//用户名是否存在
		try {
			dos.writeUTF(Name);
			String str;
			try {
				str = dis.readUTF();
				if(str.compareTo("1")==0)
					return true;
				else
					return false;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}		
	}
	
	public Socket getSocket(){
		return this.socket;
	}
	
	public DataInputStream getDis(){
		return this.dis;
	}
	
	public DataOutputStream getDos(){
		return this.dos;
	}
	

}
