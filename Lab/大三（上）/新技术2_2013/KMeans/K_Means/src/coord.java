
public class coord {
	//坐标信息
	private String name;
	private double x;
	private double y;
	//无参初始化
	public coord(){
		this.name = "";
		this.x=0.0;
		this.y=0.0;
	}
	//有参初始化
	public coord(String name,double x,double y){
		this.name=name;
		this.x=x;
		this.y=y;
	}
	//与另一个坐标的距离
	public double getDistance(coord anothercoord){
		return Math.sqrt(Math.pow(this.x-anothercoord.getX(),2) + Math.pow(this.y-anothercoord.getY(), 2));
	}
	//getter和setter
	public String getName(){
		return this.name;
	}
	public void setName(String name){
		this.name=name;
	}
	
	public double getX(){
		return this.x;
	}
	public void setX(double x){
		this.x=x;
	}
	
	public double getY(){
		return this.y;
	}
	public void setY(double y){
		this.y=y;
	}
}
