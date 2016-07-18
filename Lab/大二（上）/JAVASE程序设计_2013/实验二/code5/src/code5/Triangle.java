package code5;

public class Triangle extends GeometricObject{
	private double side1=1.0;
	private double side2=1.0;
	private double side3=1.0;
	
	Triangle(){}
	Triangle(double side1,double side2,double side3){
		this.side1=side1;
		this.side2=side2;
		this.side3=side3;
	}
	
	void setSide1(double side1){this.side1=side1;}
	void setSide2(double side2){this.side2=side2;}
	void setSide3(double side3){this.side3=side3;}
	double getSide1(){return side1;}
	double getSide2(){return side2;}
	double getSide3(){return side3;}
	
	double getArea(){
		return Math.sqrt((getPerimeter()/2)*(getPerimeter()/2-side1)*
				(getPerimeter()/2-side2)*(getPerimeter()/2-side3));
	}
	double getPerimeter(){
		return side1+side2+side3;
	}
	public String toString(){
		return "Triangle: side1 = " + side1 + " side2 = " + side2 +
				  " side3 = " + side3;

	}
}
