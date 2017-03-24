package controller;

import java.util.HashMap;

import turtles.Pen;

public class TurtleInfo {
	private HashMap<String, Double> stats;
	private Pen pen;
	private boolean isActive;

	public double getID() {
		return stats.get("id");
	}

	public double getXPos() {
		return stats.get("xpos");
	}

	public double getYPos() {
		return stats.get("ypos");
	}

	public double getHeading() {
		return stats.get("angle");
	}
	
	public boolean getActive() {
		return isActive;
	}

	public boolean getPenDown() {
		return pen.showPen();
	}

	public double getPenSize() {
		return pen.getSize();
	}

	public double getPenColor() {
		return pen.getColor();
	}

	public String toString() {
		return String.format(
				"Turtle ID: %f\n" + 
				"Active: %b\n" + 
				"X Position: %f " + 
				"\nY Position: %f\n" + 
				"Heading: %f\n" + 
				"Pen Down: %b\n" + 
				"Pen Size: %f\n" + 
				"Pen Color Index: %d",
				getID(), 
				getActive(), 
				getXPos(), 
				getYPos(), 
				getHeading(), 
				getPenDown(), 
				getPenSize(), 
				getPenColor());
	}
}
