package code6;

public class Test2 {
	public static void main(String[] args){
		Octagon oc = new Octagon(5);
		System.out.println(oc.toString());
		try {
			Octagon oct=(Octagon)oc.clone();
			System.out.println("Compare:"+oc.compareTo(oct));
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

class Octagon extends GeometricObject implements Comparable<Octagon>,Cloneable{
	private double side=1.0;
	Octagon(){}
	Octagon(double side){
		this.side=side;
	}
	
	double getSide(){return side;}
	void setSide(double side){this.side=side;}
	double getArea(){return (2 + (4 / (Math.sqrt(2))) * side * side); }
	double getPerimeter(){return side*8;}
	
	public String toString() {
		return "Area: " + getArea() + "\nPerimeter: " + getPerimeter();
    }
	public int compareTo(Octagon octagon) {
        if(getArea() > octagon.getArea()) 
            return 1;
        else if(getArea() < octagon.getArea()) 
                return -1;
        else
            return 0;
    }
	public Object clone() throws CloneNotSupportedException{
		return super.clone();
	}


}