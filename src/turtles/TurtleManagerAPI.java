package turtles;

import java.util.List;

public interface TurtleManagerAPI {

	List<TurtleAPI> getActiveTurtles();
	
	List<TurtleAPI> allTurtles();
	
	TurtleAPI getAPI(Double id);

}
