package commands;

import java.util.List;
import turtles.TurtleManagerCommandAPI;
import backend.VariableManager;

public interface Command extends java.io.Serializable {

	void add(Command... args);

	List<Command> getAllArguments();

	Double executeCommand(TurtleManagerCommandAPI turtles, VariableManager vars, Double k);

	void resetCommand();

	Integer getNumOfExpressions();
	
	String getInstruction();

	boolean isFinished();

	boolean isRunAllTurtles();

	void performBeforeExecution();
	
	int getCurrentArgumentSize();
}
