package frontend.API;

import coordinate.Coordinate;
import javafx.scene.Node;
import turtles.Pen;
import turtles.Turtle;

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
	 * Double of an index of a color in colorPalette
	 */
	public void setBackgroundColor(Double a);
	
	/**
	 * Places turtle on the screen
	 * @param a
	 * Node containing the turtle
	 */
	public void placeTurtle(Node a);
	
	/**
	 * Removes turtle on the screen
	 * @param a
	 * Node containing the turtle
	 */
	public void removeTurtle(Node a);
	
	/**
	 * Adds a line to the screen that indicates the change in position for the turtle
	 * @param oldC
	 * Coordinate indicating the previous position of the turtle
	 * @param newC
	 * Coordinate indicating the new position of the turtle
	 * Node containing the turtle
	 */
	public void changePosition(Coordinate oldC, Coordinate newC, Pen pen, Turtle turtle);
	
	/**
	 * Removes all previously created lines from the screen
	 */
	public void clear();
	
	/**
	 * Returns a boolean indicating whether the root contains node n
	 * @return
	 * @param n
	 * Node containing the turtle
	 */
	public boolean containsTurtle(Node n);
	
	/**
	 * Adds a index color pair to the colorPalette
	 * @param indexIn
	 * User defined index number
	 * @param colorIn	
	 * String of color name
	 */
	public void updateColor(double indexIn, String colorIn);
	
	/**
	 * Returns index of current background color
	 * @return
	 */
	public double getBackgroundColor();
}
