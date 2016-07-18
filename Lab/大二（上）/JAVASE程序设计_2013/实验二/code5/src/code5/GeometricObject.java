package code5;

public class GeometricObject {
	private String color = "White";
	private boolean filled = false;
	
	GeometricObject(String color,boolean filled){
		this.color=color;
		this.filled=filled;
	}
	GeometricObject(){}
	
	void setColor(String color){this.color=color;}
	void setFilled(boolean filled){this.filled=filled;}
	String getColor(){return color;}
	boolean getFilled(){return filled;}
	

}
