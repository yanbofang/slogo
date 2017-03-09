package turtles;

import java.util.Collection;
import java.util.List;

public interface TurtleManagerCommandAPI {

	Turtle getTurtle(Double id);
	void addActiveTurtles(Collection<Double> newActives);
	public void setPenState(boolean b, Double id);
	public void setPenColor(double d, Double id);
	public void setBackground(Double index);
	public void setPenSize(double d, Double id);
	public void addColor(Double index, Double r, Double g, Double b);
	
	//FOR TESTING
	List<Turtle> allTurtles();
}
