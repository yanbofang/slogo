package turtles;

import java.util.List;

public interface TurtleManagerAPI {

	List<Turtle> getActiveTurtles();
	
	List<Turtle> allTurtles();
	
	Turtle getAPI(Double id);

}
