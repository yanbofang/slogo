package commands;

import java.util.ArrayList;
import java.util.Arrays;

import turtles.Turtle;
import turtles.TurtleManagerCommandAPI;
import backend.CommandHandler;

public interface Command {

	void add(Object... args);
	
	//void clearArguments();

	String getInstruction();

	Object getArguments(int k);

	boolean isFinished();

	//Double getValue(Turtle t);

	Double executeCommand(TurtleManagerCommandAPI turtles, Double k);
	
	void resetCommand();

	Integer getNumOfExpressions();
	
	void performBeforeExecution();
	
	boolean getRunTurtles();
}
