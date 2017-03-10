package frontend.API;

import turtles.TurtleManager;

public interface StateViewAPI extends SubcomponentAPI{
	/**
	 * Set the turtle manager to the instance used by the program
	 * @param tmIn
	 * turtle manager used by the program
	 */
	public void setTurtleManager(TurtleManager tmIn);
	
	/**
	 * Update the list of turtles to display and their information
	 * @param turtleID
	 * Turtle to be added
	 */
	public void updateStatus(Double turtleID);
}
