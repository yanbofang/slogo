package commands;

import java.util.ArrayList;
import java.util.Arrays;

import backend.CommandHandler;
import backend.Turtle;
import backend.VariableManager;

public interface Command {

	void add(Object... args);
	
	void clearArguments();

	String getInstruction();

	Object getArguments(int k);

	boolean isFinished();

//	Double getValue(Turtle t);

	Double executeCommand(Turtle turtle, VariableManager vars);
	
	void resetCommand();

	Integer getNumOfExpressions();
}
