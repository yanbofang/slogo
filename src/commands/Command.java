package commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import turtles.Turtle;
import turtles.TurtleManagerCommandAPI;
import backend.CommandHandler;
import backend.VariableManager;

public interface Command {

	void add(Command... args);

	String getInstruction();

	Command getArguments(int k);
	
	List<Command> getAllArguments();

	boolean isFinished();

	Double executeCommand(TurtleManagerCommandAPI turtles, VariableManager vars, Double k);

	void resetCommand();

	Integer getNumOfExpressions();

	void performBeforeExecution();

	boolean getRunTurtles();
	
	public int getCurrentArgumentSize();
}
