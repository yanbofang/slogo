package frontend.API;

import coordinate.Coordinate;
import javafx.scene.Node;
import javafx.scene.image.Image;
import turtles.Pen;
import turtles.Turtle;
import turtles.TurtleManager;

public interface InternalViewAPI {
	/**
	 * Have Model run the given string as code
	 * @param a
	 * Text input from user
	 */
	public void runCommand(String a);
	
	/**
	 * Add turtle to be displayed in the UI
	 * @param n
	 * Node of turtle
	 */
	public void addTurtle(Node n);
	
	/**
	 * Remove turtle from display in the UI
	 * @param n
	 * Node of turtle
	 */
	public void removeTurtle(Node n);
	
	/**
	 * Checks if current turtle is on display
	 * @param n
	 * Node of turtle
	 * @return
	 * True if turtle is on display
	 */
	public boolean containsTurtle(Node n);
	
	/**
	 * Have the backend update a variable changed in the UI
	 * @param a
	 * The name of the variable
	 * @param b
	 * The value of the variable
	 */
	public void changeVariable(String a, String b);
	
	/**
	 * Change the background color of the turtle's area
	 * @param a
	 * 6 hexadecimal values specifying a color i.e. "FFFFFF"
	 */
	public void changeBackground(String a);
	
	/**
	 * Change the image of the turtle with a specified image
	 * @param a
	 * Image to use
	 */
	public void changeImage(Image a);
	
	/**
	 * Change the color of the pen
	 * @param a
	 * 6 hexadecimal values specifying a color i.e. "FFFFFF"
	 */
	public void changePenColor(double a);
	
	/**
	 * Change the language of the input commands
	 * @param a
	 * Language to be used such as "English"
	 */
	public void changeLanguage(String a);
	
	
	
	/**
	 * Set Turtle
	 * @param n
	 */
//	public void setTurtle(TurtleManager tm);

	public void clearLines();

//	public void setPen(Boolean b);
}
