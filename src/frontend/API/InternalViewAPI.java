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
	 * index of color
	 */
	public void changeBackground(Double a);
	
	/**
	 * Change the image of all active turtles with a specified image
	 * @param a
	 * Image to use
	 */
	public void changeImage(Image a);
	
	/**
	 * Change the color of the pen for all active turtles
	 * @param a
	 * 6 hexadecimal values specifying a color i.e. "FFFFFF"
	 */
	public void changePenColor(double a);
	
	/**
	 * Change the stroke width of the pen for all active turtles
	 * @param size
	 * size of the stroke
	 */
	public void setPenSize(double size);
	
	/**
	 * Set the pen up or down for all active turtles
	 * @param b
	 * True being the pen is down and will draw a line
	 */
	public void setPenState(boolean b);
	
	/**
	 * Change the language of the input commands, will reset the lines and other workspace parameters
	 * @param a
	 * Language to be used such as "English"
	 */
	public void changeLanguage(String a);
	
	/**
	 * Clears all variables in the current workspace
	 */
	public void clearVariables();
	
	/**
	 * Clears all user defined methods in the current workspace
	 */
	public void clearMethods();
	
	/**
	 * Clears the history in the current workspace
	 */
	public void clearHistory();
	
	/**
	 * Clears all the lines in the current turtle area
	 */
	public void clearLines();
	
	/**
	 * Allows subcomponents access to turtlemanager
	 * @return
	 * turtlemanager for the current workspace
	 */
	public TurtleManager getTurtleManager();
	
	/**
	 * Sets current parameters as the default settings for future workspaces
	 */
	public void setDefaultWorkspace();
	
	/**
	 * Saves the current settings as a workspace
	 * @param s
	 * Name of the file to save workspace to
	 */
	public void saveWorkspace(String s);
	
	/**
	 * Loads the workspace from file and set current parameters to the loaded workspace
	 * @param s
	 * Name of the file to load
	 */
	public void loadWorkspace(String s);

	/**
	 * Opens a new workspace with default settings
	 * @throws Exception 
	 */
	public void newWorkspace() throws Exception;
	
}
