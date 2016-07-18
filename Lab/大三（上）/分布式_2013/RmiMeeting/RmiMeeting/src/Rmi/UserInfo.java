package Rmi;

public class UserInfo {//用户信息
	private String name = "client";//用户名
	private String password = "client";//用户密码
	
	public UserInfo(String name, String password){
		//初始化
		this.setName(name);
		this.setPassword(password);
	}
	//getter
	public String getName(){
		return this.name;
	}
	
	public String getPassword(){
		return this.password;
	}
	//setter
	public void setName(String name){
		this.name = name;
	}
	
	public void setPassword(String password){
		this.password = password;
	}
}
