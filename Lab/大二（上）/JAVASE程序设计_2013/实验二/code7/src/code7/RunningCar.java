package code7;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class RunningCar extends JFrame{
	public static void main(String[] args){
		JFrame frame=new JFrame();
		frame.add(new Interface());
		frame.setSize(500,400);
		frame.setTitle("Running Car");
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
}

class CarData{
	public static int CarNumber=4;
	public static JLabel[] CarName= new JLabel[50];
	public static JTextField[] SpeedText = new JTextField[50];
	public static int[] CarSpeed=new int[50];
	public static Car[] car=new Car[50];
	public static boolean Move=false;
	public static JButton Star=new JButton("Star");
	public static JButton Stop=new JButton("Stop");
	public static JButton ReStar=new JButton("ReStar");
	public static JButton Add=new JButton("Add Car");
	public static JButton Delete=new JButton("Delete Car");
	
}

class Interface extends JPanel{
	private JPanel SpeedJP = new JPanel();
	private JPanel ControlJP = new JPanel(new GridLayout());
	private JPanel Top = new JPanel(new GridLayout(2,1));
	private JPanel RunCar = new JPanel();
	public Interface(){
		ControlJP.add(CarData.Star);
		ControlJP.add(CarData.Stop);
		ControlJP.add(CarData.ReStar);
		ControlJP.add(CarData.Add);
		ControlJP.add(CarData.Delete);
		CarData.Star.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				CarData.Move=true;
			}});
		CarData.Stop.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				CarData.Move=false;
			}});
		CarData.ReStar.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				CarData.Move=false;
				for(int n=0;n<CarData.CarNumber;n++){
					CarData.car[n].setX(5);
				}
			}});
		CarData.Add.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(CarData.CarNumber<50){
					CarData.CarName[CarData.CarNumber]=new JLabel("Car"+(CarData.CarNumber+1)+":");
					CarData.SpeedText[CarData.CarNumber]=new JTextField("0",3);
					SpeedJP.add(CarData.CarName[CarData.CarNumber]);
					SpeedJP.add(CarData.SpeedText[CarData.CarNumber]);
					CarData.car[CarData.CarNumber]=new Car(CarData.CarNumber+1);
					RunCar.setLayout(new GridLayout(CarData.CarNumber+1,1));
					RunCar.add(CarData.car[CarData.CarNumber]);
					RunCar.validate();
					SpeedJP.validate();
					validate();
					CarData.CarNumber++;
				}
				else{
					JOptionPane.showMessageDialog(null,"车辆数目已达上限！");
				}
			}});
		CarData.Delete.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(CarData.CarNumber>0){
					CarData.CarNumber--;
					SpeedJP.remove(CarData.SpeedText[CarData.CarNumber]);
					SpeedJP.remove(CarData.CarName[CarData.CarNumber]);
					RunCar.remove(CarData.car[CarData.CarNumber]);
					RunCar.setLayout(new GridLayout(CarData.CarNumber,1));
					RunCar.validate();
					SpeedJP.validate();
					validate();
				}
				else{
					JOptionPane.showMessageDialog(null,"已无车辆！");
				}
			}});
		
		for(int n=0;n<CarData.CarNumber;n++){
			CarData.CarName[n]=new JLabel("Car"+(n+1)+":");
			SpeedJP.add(CarData.CarName[n]);
			CarData.SpeedText[n]=new JTextField("0",3);
			SpeedJP.add(CarData.SpeedText[n]);
		}
		Top.add(SpeedJP,BorderLayout.NORTH);
		Top.add(ControlJP,BorderLayout.CENTER);
		
		setLayout(new BorderLayout());
		RunCar.setLayout(new GridLayout(CarData.CarNumber,1));
		for(int n=0;n<CarData.CarNumber;n++){
			CarData.car[n]=new Car(n+1);
			RunCar.add(CarData.car[n]);
		}
		add(RunCar,BorderLayout.CENTER);
		add(Top,BorderLayout.NORTH);
		Timer timer = new Timer(50,new TimerListener());
		timer.start();
	}
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		if(CarData.Move){
			for(int n=0;n<CarData.CarNumber;n++){
				CarData.CarSpeed[n]=Integer.parseInt(CarData.SpeedText[n].getText());
				CarData.car[n].XAddSpeed(CarData.CarSpeed[n]);
			}
		}
		
	}
	class TimerListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			repaint();
		}
	}
}

class Car extends JPanel{
	private int x=5;
	private int y=0;
	private int CarWidth,CarHeight;
	private int number=0;
	void XAddSpeed(int speed){
		this.x+=speed;
	}
	void setX(int x){
		this.x=x;
	}
	public Car(int number){
		this.number=number;
	}
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		CarHeight=getHeight()/4;
		CarWidth=getWidth()/6;
		y=getHeight()/4;
		g.drawRect(5, 1, getWidth()-10, getHeight()-1);
		g.setFont(new Font("Tahoma", Font.BOLD, getHeight()/2));
		g.drawString(number+"",getWidth()/2 , 2*getHeight()/3);
		if(x<getWidth()-10-CarWidth){
			int PolyX[]={x+CarWidth/3,x+2*CarWidth/3,x+5*CarWidth/6,x+CarWidth/6};
			int PolyY[]={4*y/3,4*y/3,2*y,2*y};
			g.setColor(Color.RED);
			g.fillPolygon(PolyX,PolyY,PolyX.length);
			g.setColor(Color.GREEN);
			g.fillRect(x, 2*y, CarWidth, CarHeight);
			g.setColor(Color.BLACK);
			g.fillOval(x+CarWidth/4-CarHeight/2,3*y,CarHeight,CarHeight);
			g.fillOval(x+3*CarWidth/4-CarHeight/2,3*y,CarHeight,CarHeight);
		}
		else{
			x=getWidth()-5-CarWidth;
			int PolyX[]={x+CarWidth/3,x+2*CarWidth/3,x+5*CarWidth/6,x+CarWidth/6};
			int PolyY[]={4*y/3,4*y/3,2*y,2*y};
			g.setColor(Color.RED);
			g.fillPolygon(PolyX,PolyY,PolyX.length);
			g.setColor(Color.GREEN);
			g.fillRect(x, 2*y, CarWidth, CarHeight);
			g.setColor(Color.BLACK);
			g.fillOval(x+CarWidth/4-CarHeight/2,3*y,CarHeight,CarHeight);
			g.fillOval(x+3*CarWidth/4-CarHeight/2,3*y,CarHeight,CarHeight);
		}
	}

}