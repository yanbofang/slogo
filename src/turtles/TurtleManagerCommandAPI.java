package turtles;

import java.util.Collection;
import java.util.List;

public interface TurtleManagerCommandAPI {

	/**
	 * @return - Actual turtle for given ID
	 */
	Turtle getTurtle(Double id);
	
	/**
	 * adds all turtles of given ID to active turtles (if turtle doesn't exist, it creates it)
	 */
	void addActiveTurtles(Collection<Double> newActives);
	
	/**
	 * Sets Pen State ( showing or hiding ), b, of turtle with ID of id
	 */
	public void setPenState(boolean b, Double id);
	
	/**
	 * Sets Pen Color to index, d, of turtle with ID of id
	 */
	public void setPenColor(double d, Double id);
	
	/**
	 * Sets background to index, index
	 */
	public void setBackground(Double index);
	
	/**
	 * Sets Pen State ( showing or hiding ), b, of turtle with ID of id
	 */
	public void setPenSize(double d, Double id);
	
	/**
	 * Adds new color to ColorPalette, with a given index and RGB value
	 */
	public void addColor(Double index, Double r, Double g, Double b);
	
	//FOR TESTING
	/**
	 * @return List of all turtles, active or not 
	 */
	List<Turtle> allTurtles();
}
