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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;




public class ChatServer extends JFrame{
	private ServerSocket ss = null;//服务器
	private boolean started = false;//服务器启动情况
	List<ChatClient> clients = new ArrayList<ChatClient>();//客户端线程
	List<String> users_name = new ArrayList<String>();//客户端线程对应客户名
	
	private JPanel panel_startServer,panel_users,panel_message,panel;
	private JTextArea text_port,text_user,text_message;
	private JLabel label_startServer;
	private JButton button_startServer;
	
	public void creatServerWindow(){//初始化界面
		label_startServer = new JLabel("服务器端口号：");
		label_startServer.setFont(new Font("服务器端口号", 5,20));
		button_startServer = new JButton("启动服务器");
		text_port = new JTextArea();
		text_port.setText("4444");
		text_port.setPreferredSize(new Dimension(100,25));
		text_port.setBorder(BorderFactory.createLineBorder(Color.gray));
		
		panel_startServer = new JPanel();
		panel_startServer.add(label_startServer);
		panel_startServer.add(text_port);
		panel_startServer.add(button_startServer);
		panel_startServer.setBorder(BorderFactory.createTitledBorder("服务器信息"));
		
		text_user = new JTextArea();
		text_user.setRows(26);
		text_user.setColumns(14);
		text_user.setEditable(false);
		text_user.setBorder(BorderFactory.createLineBorder(Color.gray));
		panel_users = new JPanel();
		panel_users.add(text_user);
		panel_users.setBorder(BorderFactory.createTitledBorder("在线用户"));
		
		text_message = new JTextArea();
		text_message.setRows(26);
		text_message.setColumns(27);
		text_message.setEditable(false);
		text_message.setBorder(BorderFactory.createLineBorder(Color.gray));
		panel_message = new JPanel();
		panel_message.add(text_message);
		panel_message.setBorder(BorderFactory.createTitledBorder("消息管理"));
		
		panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.add(panel_startServer,BorderLayout.NORTH);
		panel.add(panel_message,BorderLayout.WEST);
		panel.add(panel_users,BorderLayout.EAST);
		
		this.add(panel);
		this.setVisible(true);
		this.setSize(500, 600);
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
	}
	
	public void start() {//开启服务器
        try {
            ss = new ServerSocket(Integer.parseInt(text_port.getText()));
            text_message.setText(text_message.getText() + "启动服务器" + '\n');
            started = true;
            button_startServer.setText("停止服务器");
            accept ac=new accept();
            new Thread(ac).start();
        } catch (BindException e) {
        	text_message.setText(text_message.getText() + "启动失败：端口使用中" + '\n');
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
			ss.close();
			text_message.setText(text_message.getText() + "停止服务器" + '\n');
			started = false;
			button_startServer.setText("启动服务器");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	class ChatClient implements Runnable{//一个客户端线程
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
					text_message.setText(text_message.getText() + name + "重上线失败" + '\n');//服务器的上线失败显示
					dos.writeUTF("1");
					dis.close();
                    s.close();
                    dos.close();
                    bConnected = false;
                    exist = false;
				}
				else{//用户名不存在
					text_message.setText(text_message.getText() + name + "上线了" + '\n');//服务器的上线显示
					text_user.setText(text_user.getText()+ name +'\n');
	                clients.add(this); //添加客户端
	                users_name.add(name);
	                for (int i = 0; i < clients.size(); i++) {//已有客户端上线提醒
                        ChatClient c = clients.get(i);
                        c.send("01"+name);
                    }
	                
	                for(int j=0;j<users_name.size();j++){//给新客户端发送已有用户
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
									text_message.setText(text_message.getText() + str + "下线了" + '\n');//服务器的下线提醒
									text_user.setText(text_user.getText().replaceFirst(str+'\n', ""));
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
								for (int i = 0; i < clients.size(); i++) {
			                        ChatClient c = clients.get(i);
			                        c.send("1"+str);
			                    }
							}
							else{//私聊
								for (int i = 0; i < users_name.size(); i++) {
									String un = users_name.get(i);
									if(sendto.compareTo(un)==0){
										ChatClient c = clients.get(i);
				                        c.send("1"+str);
				                        break;
									}
			                        
			                    }
								p = str.indexOf(":");
								sendto=String.copyValueOf(str.toCharArray(), 0, p);
								for (int i = 0; i < users_name.size(); i++) {
									String un = users_name.get(i);
									if(sendto.compareTo(un)==0){
										ChatClient c = clients.get(i);
				                        c.send("1"+str);
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
	
	public ChatServer(){
		this.creatServerWindow();
	}
	
	public static void main(String[] args) {
		new ChatServer();
    }
	
}
