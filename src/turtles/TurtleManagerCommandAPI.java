package turtles;

import java.util.Collection;
import java.util.List;

public interface TurtleManagerCommandAPI {

	Turtle getTurtle(Double id);
	void addActiveTurtles(Collection<Double> newActives);
	
	//FOR TESTING
	List<Turtle> allTurtles();
}
