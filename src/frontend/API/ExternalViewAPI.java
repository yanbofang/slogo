package frontend.API;

import java.util.Observable;

import coordinate.Coordinate;
import javafx.scene.Node;
import javafx.scene.image.Image;
import turtles.Pen;
import turtles.Turtle;
import turtles.TurtleManager;
import turtles.TurtleManagerAPI;
/**
 * API for View class
 * Serves as communicator between frontend and backend
 * Serves as central hub for front end
 * @author Gordon
 *
 */
public interface ExternalViewAPI {
	/**
	 * Returns a Coordinate of the bottom-right most corner of the turtle's area
	 * @return
	 */
	public Coordinate getBounds();
	
	/**
	 * Update the frontend display of variables generated in the environment
	 * @param a
	 * The name of the variable
	 * @param b
	 * The value of the variable
	 */
	public void updateVar(String a, String b);
	
	/**
	 * Display an error with a specific reason
	 * @param a
	 * The specific error
	 */
	public void showError(String a);
	
	/**
	 * Update the frontend display of user defined methods
	 * @param a
	 * The method selected
	 */
	public void updateUMethod(String a);
	
	/**
	 * Update frontend information based on observable that called this command
	 * @param arg0
	 * 4 possible observables to call this method:
	 * ViewObservable: A change in what views to display
	 * TurtleManager: if arg1 is null, the list of active turtles has been changed.
	 * If arg1 is a Turtle, then a new turtle has been added to the environment
	 * Turtle: if arg1 is null, there is a command to clear screen.
	 * If arg1 is an ArrayList<> it will be an ArrayList of coordinates containing a turtle's previous and next coordinates
	 * If arg1 is a boolean, then the turtle needs to be shown if the boolean is true and hidden otherwise.
	 * If arg1 is a double, then the turtle needs to change its image to the index
	 * ColorPalette: if null, the background color needs to be changed according to a instance value that had been changed
	 * If SingleColor, a color has been added or changed in the ColorPalette.
	 * @param arg1
	 */
	public void update(Observable arg0, Object arg1);
	
	/**
	 * give View access to a TurtleManager instance that is utilized in the backend
	 * @param tmIn
	 * TurtleManager instance that is being used by the backend
	 */
	public void setTurtleManager(TurtleManager tmIn);
}
