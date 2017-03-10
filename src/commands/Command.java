package commands;

import java.util.ArrayList;
import java.util.List;

import turtles.Turtle;
import turtles.TurtleManagerCommandAPI;
import backend.UserMethodManager;
import backend.VariableManager;

public interface Command extends java.io.Serializable {

	void add(Command... args);

	Command getArguments(int k);

	List<Command> getAllArguments();

	Double executeCommand(TurtleManagerCommandAPI turtles, VariableManager vars, Double k);

	void resetCommand();

	Integer getNumOfExpressions();

	void setNumOfExpressions(int myNumOfExpressions);
	
	String getInstruction();

	void setInstruction(String myInstruction);

	ArrayList<Command> getArguments();

	void setArguments(ArrayList<Command> myArguments);

	ArrayList<Object> getConvertedArguments();

	void setConvertedArguments(ArrayList<Object> myConvertedArguments);

	boolean isFinished();

	void setFinished(boolean myFinished);

	Turtle getTurtle();

	void setTurtle(Turtle myTurtle);

	Double getValue();

	void setValue(Double myValue);

	VariableManager getVariables();

	void setVariables(VariableManager myVariables);

	UserMethodManager getUserMethods();

	void setUserMethods(UserMethodManager myUserMethods);

	TurtleManagerCommandAPI getTurtleManager();

	void setTurtleManager(TurtleManagerCommandAPI myTurtleManager);

	boolean isRunNested();

	void setRunNested(boolean myRunNested);

	boolean isRunAllTurtles();

	void setRunAllTurtles(boolean myRunAllTurtles);

	void performBeforeExecution();

	public int getCurrentArgumentSize();
}
