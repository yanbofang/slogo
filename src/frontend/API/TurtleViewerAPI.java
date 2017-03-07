package frontend.API;

import coordinate.Coordinate;
import javafx.scene.Node;
import turtles.Pen;

/**
 * API for turtle area subcomponent
 * The graphical area that contains the turtle
 * @author Gordon and Faith
 *
 */
public interface TurtleViewerAPI extends SubcomponentAPI{
	/**
	 * Get the size of the subcomponent as the bottom-rightmost point
	 * @return
	 * Coordinate containing the bottom-rightmost point
	 */
	public Coordinate getBounds();
	
	/**
	 * Change the background color of the turtle area
	 * @param a
	 * 6 hexidecimal string of color (ex: 3c3c3c)
	 */
	public void setBackgroundColor(String a);
	
	/**
	 * Change the pen color of the turtle area
	 * @param a
	 * 6 hexidecimal string of color (ex: 3c3c3c)
	 */
	public void setPenColor(String a);
	
	/**
	 * Places turtle on the screen
	 * @param a
	 * Node containing the turtle
	 */
	public void placeTurtle(Node a);
	
	/**
	 * Adds a line to the screen that indicates the change in position for the turtle
	 * @param oldC
	 * Coordinate indicating the previous position of the turtle
	 * @param newC
	 * Coordinate indicating the new position of the turtle
	 * Node containing the turtle
	 */
	public void changePosition(Coordinate oldC, Coordinate newC, Pen pen);
	
	/**
	 * Removes all previously created lines from the screen
	 */
	public void clear();
}
