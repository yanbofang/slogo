package turtles;

import java.util.HashMap;

public class Shapes {

	
	private HashMap<Double, String> myShapes;
	private Double myShape;
	
	
	public Shapes() {
		myShapes = new HashMap<Double, String>();
	}
	
	public void setShape(Double index) {
		myShape = index;
	}
	
	public Double getShape() {
		return myShape;
	}
}
