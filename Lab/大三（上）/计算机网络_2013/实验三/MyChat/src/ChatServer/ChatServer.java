/*
 * 聊天服务器，先开启此服务器再开启客户端登陆界面
 * */
package ChatServer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;




public class ChatServer extends JFrame{
	private ServerSocket ss = null;//服务器
	private boolean started = false;//服务器启动情况
	List<ChatClient> clients = new ArrayList<ChatClient>();//客户端线程
	List<String> users_name = new ArrayList<String>();//客户端线程对应客户名
	
	private JPanel panel_startServer,panel_server,panel_users,panel_message,panel_sendmessage,panel;
	private JTextArea text_port,text_user,text_sendmessage,text_receivemessage;
	private JLabel label_startServer;
	private JButton button_startServer,button_sendmessage;
	private JComboBox box_sendto;
	
	public void creatServerWindow(){//初始化界面
		label_startServer = new JLabel("服务器端口号:");
		label_startServer.setFont(new Font("服务器端口号", 5,19));
		button_startServer = new JButton("启动服务器");
		text_port = new JTextArea();
		text_port.setText("4444");
		text_port.setPreferredSize(new Dimension(100,25));
		text_port.setBorder(BorderFactory.createLineBorder(Color.gray));
		
		panel_startServer = new JPanel();
		panel_startServer.add(label_startServer);
		panel_startServer.add(text_port);
		panel_startServer.add(button_startServer);
		panel_startServer.setPreferredSize(new Dimension(480,65));
		panel_startServer.setBorder(BorderFactory.createTitledBorder("服务器信息"));
		
		panel_server = new JPanel();
		panel_server.setPreferredSize(new Dimension(500,72));
		panel_server.add(panel_startServer);
		
		text_user = new JTextArea();
		text_user.setRows(23);
		text_user.setColumns(13);
		text_user.setEditable(false);
		text_user.setLineWrap(true);
		//text_user.setBorder(BorderFactory.createLineBorder(Color.gray));
		panel_users = new JPanel();
		panel_users.add(new JScrollPane(text_user));
		panel_users.setBorder(BorderFactory.createTitledBorder("在线用户"));
		
		box_sendto = new JComboBox();
		box_sendto.addItem("所有用户");
		box_sendto.setPreferredSize(new Dimension(310,25));
		text_receivemessage = new JTextArea();
		text_receivemessage.setRows(18);
		text_receivemessage.setColumns(28);
		text_receivemessage.setEditable(false);
		text_receivemessage.setLineWrap(true);
		//text_receivemessage.setBorder(BorderFactory.createLineBorder(Color.gray));
		text_sendmessage = new JTextArea();
		text_sendmessage.setRows(3);
		text_sendmessage.setColumns(22);
		text_sendmessage.setLineWrap(true);
		//text_sendmessage.setBorder(BorderFactory.createLineBorder(Color.gray));
		button_sendmessage = new JButton("发送");
		button_sendmessage.setPreferredSize(new Dimension(60,53));
		panel_sendmessage = new JPanel();
		panel_sendmessage.add(box_sendto);
		panel_sendmessage.add(new JScrollPane(text_receivemessage));
		panel_sendmessage.add(new JScrollPane(text_sendmessage));
		panel_sendmessage.add(button_sendmessage);
		panel_sendmessage.setPreferredSize(new Dimension(327,400));
		panel_sendmessage.setBorder(BorderFactory.createTitledBorder("发送消息"));
		
		panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.add(panel_server,BorderLayout.NORTH);
		panel.add(panel_sendmessage,BorderLayout.WEST);
		panel.add(panel_users,BorderLayout.EAST);
		
		this.add(panel);
		this.setVisible(true);
		this.setSize(500, 550);
		this.setLocationRelativeTo(getOwner());
		this.setResizable(false); 
		this.setTitle("服务器");
		
		this.addWindowListener(new WindowAdapter(){//关闭窗口事件
			public void windowClosing(WindowEvent e) {
				if(started){
					stop();
				}
                System.exit(0);
			}
		});
		
		button_startServer.addActionListener(new ActionListener(){//开启服务器按钮事件

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(!started){
					start();
				}
				else{
					stop();
				}
			}});
		
		button_sendmessage.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				mySend();
			}});
	}
	
	public void start() {//开启服务器
        try {
            ss = new ServerSocket(Integer.parseInt(text_port.getText()));
            started = true;
            button_startServer.setText("停止服务器");
            text_receivemessage.setText(text_receivemessage.getText() + "开启服务器" + '\n');
            accept ac=new accept();
            new Thread(ac).start();
        } catch (BindException e) {
        	//text_message.setText(text_message.getText() + "启动失败：端口使用中" + '\n');
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	class accept implements Runnable{//在后端接受客户端
		public accept(){
			
		}
		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
	        	while (started) {
	                Socket s = ss.accept(); //接收客户端
	                ChatClient c = new ChatClient(s);
	                new Thread(c).start(); //启动线程
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		}
		
	}
	
	public void stop(){//停止服务器
		try {
			started = false;
			ss.close();
			button_startServer.setText("启动服务器");
			text_receivemessage.setText(text_receivemessage.getText() + "停止服务器" + '\n');
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void mySend(){//服务器发送消息
		if(started){
			String sendto = box_sendto.getSelectedItem().toString();
			String message = text_sendmessage.getText();
			text_sendmessage.setText("");
			if(sendto.compareTo("所有用户")==0)
			{
				text_receivemessage.setText(text_receivemessage.getText() + " [群聊] 服务器：" + message + '\n');
				for (int i = 0; i < clients.size(); i++) {
	                ChatClient c = clients.get(i);
	                c.send("1" + " [群聊] 服务器：" + message);
	            }
			}
			else
			{
				text_receivemessage.setText(text_receivemessage.getText() + " [私聊] 服务器：" + message + '\n');
				for (int i = 0; i < users_name.size(); i++) {
					String un = users_name.get(i);
					if(sendto.compareTo(un)==0){
						ChatClient c = clients.get(i);
	                    c.send("1" + " [私聊] 服务器：" + message);
	                    break;
					}
	                
	            }
			}
		}
		else{
			text_receivemessage.setText(text_receivemessage.getText() + "Error：服务器未开启！" + '\n');
		}
	}
	
	class ChatClient implements Runnable{//一个客户端线程(含服务器接收消息、客户端互相接发消息)
		private Socket s;
        DataInputStream dis = null;
        DataOutputStream dos = null;
        boolean bConnected = false;
        boolean exist = false;
        
        public ChatClient(Socket s) {
            this.s = s;
            try {
                dis = new DataInputStream(s.getInputStream());
                dos = new DataOutputStream(s.getOutputStream());
                bConnected = true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        void send(String str) {
            try {
                dos.writeUTF(str);
            } catch (SocketException e) {
            	
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
				String name = dis.readUTF();
				for(int n = 0; n < users_name.size(); n++){
					String nm = users_name.get(n);
					if(name.compareTo(nm)==0){
						exist = true;
					}
				}
				if(exist){//用户名已经存在
					dos.writeUTF("1");
					dis.close();
                    s.close();
                    dos.close();
                    text_receivemessage.setText(text_receivemessage.getText() + name + "重复登录失败" + '\n');//服务器的重复上线显示
                    bConnected = false;
                    exist = false;
				}
				else{//用户名不存在
					text_receivemessage.setText(text_receivemessage.getText() + name + "上线了" + '\n');//服务器的上线显示
					box_sendto.addItem(name);//新增服务器发送对象
					text_user.setText(text_user.getText()+ name +'\n');//新增在线用户
	                clients.add(this); //添加客户端
	                users_name.add(name);
	                for (int i = 0; i < clients.size(); i++) {//已有客户端上线提醒
                        ChatClient c = clients.get(i);
                        c.send("01"+name);
                    }
	                
	                for(int j=0;j<users_name.size()-1;j++){//给新客户端发送已有用户
	                	send("01" + users_name.get(j));
	                }
	                
					while(bConnected){//接受客户端消息
						String str = dis.readUTF();
						if(str.startsWith("0")){//客户端退出
							str = str.substring(1);
							for(int n = 0; n < users_name.size(); n++){
								String nm = users_name.get(n);
								if(str.compareTo(nm)==0){
									clients.get(n).bConnected=false;//移除该客户端
									clients.remove(n);
									users_name.remove(n);
									text_receivemessage.setText(text_receivemessage.getText() + str + "下线了" + '\n');//服务器的下线提醒
									text_user.setText(text_user.getText().replaceFirst(str+'\n', ""));//“在线用户”删除下线用户
									box_sendto.removeItem(str);//移除发送对象
									str = "00" + str;
									bConnected = false;
									for (int i = 0; i < clients.size(); i++) {//客户端的下线提醒
				                        ChatClient c = clients.get(i);
				                        c.send(str);
				                    }
									break;
								}
							}
						}
						else{//发送消息
							str = str.substring(1);
							String sendto = null;
							int p = str.indexOf(":");
							sendto=String.copyValueOf(str.toCharArray(), 0, p);
							str = str.substring(p+1);
							if(sendto.compareTo("所有用户")==0){//群聊
								str = " [群聊] " + str;
								text_receivemessage.setText(text_receivemessage.getText() + str + '\n');
								for (int i = 0; i < clients.size(); i++) {
			                        ChatClient c = clients.get(i);
			                        c.send("1"+str);
			                    }
							}
							else if(sendto.compareTo("服务器")==0){//私聊服务器
								text_receivemessage.setText(text_receivemessage.getText() + " [私聊] " + str + '\n');
								p = str.indexOf(":");
								sendto=String.copyValueOf(str.toCharArray(), 0, p);
								for (int i = 0; i < users_name.size(); i++) {
									String un = users_name.get(i);
									if(sendto.compareTo(un)==0){
										ChatClient c = clients.get(i);
				                        c.send("1" + " [私聊] " + str);
				                        break;
									}
			                        
			                    }
							}
							else{//私聊客户端
								for (int i = 0; i < users_name.size(); i++) {
									String un = users_name.get(i);
									if(sendto.compareTo(un)==0){
										ChatClient c = clients.get(i);
				                        c.send("1"+" [私聊] " + str);
				                        break;
									}
			                        
			                    }
								p = str.indexOf(":");
								sendto=String.copyValueOf(str.toCharArray(), 0, p);
								for (int i = 0; i < users_name.size(); i++) {
									String un = users_name.get(i);
									if(sendto.compareTo(un)==0){
										ChatClient c = clients.get(i);
				                        c.send("1"+" [私聊] " + str);
				                        break;
									}
			                        
			                    }
							}
							
						}
						
					}
				}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
                if (dis != null)
                    if (s != null)
                        try {
                            dis.close();
                            s.close();
                            dos.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
            }
			
		}}
	
	public ChatServer(){//类的初始化
		this.creatServerWindow();
	}
	
	public static void main(String[] args) {
		new ChatServer();
    }
	
}
