package code5;

public class Test {
	public static void main(String[] args){
		Triangle test=new Triangle(1,1.5,1);
		test.setColor("Yellow");
		test.setFilled(true);
		System.out.println(test.toString());
		System.out.println("The area is:"+test.getArea());
		System.out.println("The perimeter is:"+test.getPerimeter());
		System.out.println("The color is:"+test.getColor());
		System.out.println("The filled is:"+test.getFilled());
	}
}
