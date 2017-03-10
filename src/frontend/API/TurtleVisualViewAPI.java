package frontend.API;

import turtles.TurtleManager;

public interface TurtleVisualViewAPI extends SubcomponentAPI {

	/**
	 * Updates active turtles
	 */
	public void updateActive();
	
	/**
	 * Sets turtle manager to gain turtle information from
	 * @param tIn
	 * Incoming turtle manager
	 */
	public void setTurtleManager(TurtleManager tIn);
}
