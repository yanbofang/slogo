package commands;

import java.util.ArrayList;
import java.util.Arrays;

import turtles.Turtle;
import turtles.TurtleManagerCommandAPI;
import backend.CommandHandler;

import backend.VariableManager;

public interface Command {

	void add(Object... args);

	// void clearArguments();

	String getInstruction();

	Object getArguments(int k);

	boolean isFinished();

	Double executeCommand(TurtleManagerCommandAPI turtles, VariableManager vars, Double k);

	void resetCommand();

	Integer getNumOfExpressions();

	void performBeforeExecution();

	boolean getRunTurtles();
}
