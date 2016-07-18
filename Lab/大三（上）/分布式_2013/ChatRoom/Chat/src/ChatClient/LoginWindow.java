/*
 * 客户端登陆，客户端首先开启此窗口
 * */
package ChatClient;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class LoginWindow extends JFrame{
	private JLabel Lable_IP,Lable_Post,Lable_Name;
	private JTextField TextField_IP,TextField_Post,TextField_Name;
	private JButton Button_login,Button_reset;
	private JPanel panel;
	private void createLoginWindow(){//登陆界面
		Lable_IP = new JLabel("服务器：");
		Lable_Post = new JLabel("端口号：");
		Lable_Name = new JLabel("用户名：");
		
		TextField_IP = new JTextField(20);
		TextField_Post = new JTextField(20);
		TextField_Name = new JTextField(20);
		
		TextField_IP.setText("127.0.0.1");
		TextField_Post.setText("4444");
		
		Button_login = new JButton("登陆");
		Button_reset = new JButton("重置");
		
		Button_login.addActionListener(new ActionListener(){//登陆按钮响应事件
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				LoginWindow.this.login();
			}
		});
		
		Button_reset.addActionListener(new ActionListener(){//重设按钮响应事件
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				LoginWindow.this.reset();
			}
		});
		
		panel = new JPanel();
		panel.add(Lable_IP);
		panel.add(TextField_IP);
		panel.add(Lable_Post);
		panel.add(TextField_Post);
		panel.add(Lable_Name);
		panel.add(TextField_Name);
		panel.add(Button_login);
		panel.add(Button_reset);
		panel.setBorder(BorderFactory.createTitledBorder(null, "用户登录", TitledBorder.CENTER, TitledBorder.ABOVE_TOP, new Font(Font.DIALOG, Font.ITALIC, 20), Color.RED));
		
		this.setLocationRelativeTo(getOwner());
		this.setVisible(true);
		this.setSize(330, 200);
		this.add(panel);
		this.setResizable(false); 
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("客户端");
	}
	
	public void login(){//登陆事件
		try {
			new LoginWindowConnect(TextField_IP.getText(),Integer.parseInt(TextField_Post.getText()),TextField_Name.getText(),this);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void reset(){//重设事件
		TextField_IP.setText("127.0.0.1");
		TextField_Post.setText("4444");
		TextField_Name.setText("");
	}
	
	public LoginWindow(){
		this.createLoginWindow();
	}
	
	public static void main(String[] args){
		new LoginWindow();
	} 
}
