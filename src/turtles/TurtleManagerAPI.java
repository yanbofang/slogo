package turtles;

import java.util.List;

public interface TurtleManagerAPI {

	/**
	 * @return - list of all active turtles (returns list of actual Turtles)
	 */
	List<Turtle> getActiveTurtles();
	
	/**
	 * @return List of all turtles, active or not 
	 */
	List<Turtle> allTurtles();
	
	/**
	 * @return - API for a specific turtle of give id
	 */
	Turtle getAPI(Double id);
	

}
