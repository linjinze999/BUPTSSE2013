package code4;

public class Rectangle {
	public static class rectangleclass{
		private double width;
		private double height;
		private String color;
		
		public rectangleclass(double width,double height){
			this.width = width;
			this.height = height;
			this.color = "white";
		}
		public rectangleclass(){
			this.width = 1;
			this.height = 1;
			this.color = "white";
		}
		
		double getArea(){return this.width * this.height;}
		double getPerimeter(){return 2*(this.width + this.height);}
		String getColor(){return color;}
		
		void setColor(String color){this.color = color;}
		void setWidth(double width){this.width = width;}
		void setHeight(double Height){this.height = Height;}
	}
	
	public static void main(String[] args){
		rectangleclass box1 = new rectangleclass();
		rectangleclass box2 = new rectangleclass(4, 40);
		rectangleclass box3 = new rectangleclass(3.5, 35.9);

		String Color = "Red";

		box1.setColor(Color);
		box2.setColor(Color);
		box3.setColor(Color);

		System.out.println("The color of all boxes is: " + box1.getColor() + "\n");

		System.out.println("The perimeter of the first box is: " + box1.getPerimeter());
		System.out.println("The perimeter of the second box is: " + box2.getPerimeter());
		System.out.println("The perimeter of the third box is: " + box3.getPerimeter() + "\n");

		System.out.println("The area of the first box is: " + box1.getArea());
		System.out.println("The area of the second box is: " + box2.getArea());
		System.out.println("The area of the third box is: " + box3.getArea() + "\n");
		}

}
