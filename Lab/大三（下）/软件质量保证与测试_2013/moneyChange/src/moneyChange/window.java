package moneyChange;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class window extends JFrame{
	private urlconnection uc = new urlconnection();
	//private JPanel panel_message;
	private JLabel tip=new JLabel("选择转换类型:");
	private JLabel error=new JLabel();
	private JLabel money=new JLabel("我的金额:");
	private JLabel line=new JLabel("——");
	private JLabel tomoney=new JLabel("转换金额:");
	private JTextField money_t = new JTextField();
	private JTextField tomoney_t = new JTextField();
	private JPanel panel = new JPanel();
	private JPanel panel1 = new JPanel();
	private JPanel panel2 = new JPanel();
	private JPanel panel3 = new JPanel();
	private JPanel panel4 = new JPanel();
	private JPanel panel5 = new JPanel();
	private JComboBox moneyType = new JComboBox();
	private JComboBox tomoneyType = new JComboBox();
	private JButton submit = new JButton("转换");
	private JButton reset = new JButton("清空");
	public void createWindow(){
		moneyType.addItem("人民币-CNY");
		moneyType.addItem("美元-USD");
		moneyType.addItem("欧元-EUR");
		moneyType.addItem("英镑-GBP");
		moneyType.addItem("日元-JPY");
		moneyType.addItem("港币-HKD");
		moneyType.addItem("加拿大元-CAD");
		moneyType.addItem("澳大利亚元-AUD");
		moneyType.addItem("印度卢比-INR");
		moneyType.addItem("新台币-TWD");
		moneyType.setPreferredSize(new Dimension(100,30));
		tomoneyType.addItem("美元-USD");
		tomoneyType.addItem("人民币-CNY");
		tomoneyType.addItem("欧元-EUR");
		tomoneyType.addItem("英镑-GBP");
		tomoneyType.addItem("日元-JPY");
		tomoneyType.addItem("港币-HKD");
		tomoneyType.addItem("加拿大元-CAD");
		tomoneyType.addItem("澳大利亚元-AUD");
		tomoneyType.addItem("印度卢比-INR");
		tomoneyType.addItem("新台币-TWD");
		tomoneyType.setPreferredSize(new Dimension(100,30));
		panel1.add(tip);
		panel1.add(moneyType);
		panel1.add(line);
		panel1.add(tomoneyType);
		money_t.setPreferredSize(new Dimension(150,30));
		panel2.add(money);
		panel2.add(money_t);
		tomoney_t.setPreferredSize(new Dimension(150,30));
		tomoney_t.setEditable(false);
		panel3.add(tomoney);
		panel3.add(tomoney_t);
		submit.setPreferredSize(new Dimension(90,30));
		reset.setPreferredSize(new Dimension(90,30));
		panel4.add(submit);
		panel4.add(reset);
		error.setPreferredSize(new Dimension(300,30));
		panel5.add(error);
		panel.add(panel1);
		panel.add(panel2);
		panel.add(panel3);
		panel.add(panel4);
		panel.add(panel5);
		this.setLocationRelativeTo(getOwner());
		this.setVisible(true);
		this.setSize(350, 260);
		this.add(panel);
		this.setResizable(false); 
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("货币转换系统");
		
		
		reset.addActionListener(new ActionListener(){//清空按钮事件
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				try {
					money_t.setText("");
					tomoney_t.setText("");
					error.setText("");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}});
		submit.addActionListener(new ActionListener(){//转换按钮事件
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
				try{
					double mymoney = Double.parseDouble(money_t.getText());
					String waibi = tomoneyType.getSelectedItem().toString().split("-")[1];
					String qian = moneyType.getSelectedItem().toString().split("-")[1];
					try {
						String result=uc.exchangeRate(waibi, qian);
						double rate = Double.parseDouble(result);
						tomoney_t.setText(String.valueOf(mymoney*rate));
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						error.setText("错误：查询失败！请检查网络情况。");
					}
				}catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					error.setText("错误：请输入正确的金额！");
				}
				
			}});
	}
	
	public window(){
		this.createWindow();
	}
	
	public static void main(String[] args) {
		new window();
    }
}
