package commands;

import java.util.ArrayList;
import java.util.Arrays;

import backend.CommandHandler;
import backend.Turtle;

public interface Command {

	void add(Object... args);

	String getInstruction();

	Object getArguments(int k);

	boolean isFinished();

	Double getValue(Turtle t);

	Double executeCommand(Turtle turtle);
	
	void resetCommand();

	Integer getNumOfExpressions();
}
