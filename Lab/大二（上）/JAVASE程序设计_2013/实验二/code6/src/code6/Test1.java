package code6;

public class Test1 {
	public static void main(String[] args) {
		GeometricObject[] objects = {new Square(),new Square(),
				new Square(),new Circle(),new Circle()};
		for(int i = 0; i < objects.length; i++){
			GeometricObject object = objects[i];
	        if (object instanceof Colorable) {
	            ((Colorable) object).howToColor();
	        }
	        else {
	        	System.out.println("Not colorable");
	        }
		}
	}
}

interface Colorable{
	void howToColor();
}
abstract class GeometricObject{
	public GeometricObject() {}
}
class Square extends GeometricObject implements Colorable{
	public Square(){}
	public void howToColor(){
	    System.out.println("Color the sides");
	}
}
class Circle extends GeometricObject{
	public Circle(){}
}