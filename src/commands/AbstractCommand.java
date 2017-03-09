package commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import turtles.Turtle;
import turtles.TurtleManagerCommandAPI;
import backend.ParserException;
import backend.UserMethod;
import backend.UserMethodManager;
import backend.VariableManager;

public abstract class AbstractCommand implements Command {
	
	private Integer myNumOfExpressions;
	protected String myInstruction;
	protected ArrayList<Command> myArguments;
	protected ArrayList<Object> myConvertedArguments;
	protected boolean finished;
	protected Turtle myTurtle;
	protected Double myValue;
	protected VariableManager myVariables;
	protected UserMethodManager myUserMethods;
	protected TurtleManagerCommandAPI myTurtleManager;
	protected boolean runNested; //NEED TO GO THROUGH AND ADD TO ALL NECESSARY COMMANDS
	protected boolean runAllTurtles; //NEED TO GO THROUGH AND ADD TO ALL NECESSARY COMMANDS
	//protected boolean infiniteArguments; //NEED TO GO THROUGH AND ADD TO ALL NECESSARY COMMANDS

	public AbstractCommand(String instruction, VariableManager variables, UserMethodManager methods) {
		myArguments = new ArrayList<Command>();
		myNumOfExpressions = 0;
		myVariables = variables;
		myInstruction = instruction.toLowerCase();
		myUserMethods = methods;
		finished = false;
		runNested = true;
		runAllTurtles = false;
		//infiniteArguments = true;
	}

	public AbstractCommand(String instruction, VariableManager variables, UserMethodManager methods,
			int numOfExpressions) {
		this(instruction, variables, methods);
		myNumOfExpressions = numOfExpressions;
	}

	public void add(Command ... args) {
		for (Command each : args) {
			myArguments.add(each);
		}
	}

	public Integer getNumOfExpressions() {
		return myNumOfExpressions;
	}
	
	protected void setNumOfExpressions(int k) {
		myNumOfExpressions = k;
	}

	public String getInstruction() {
		return myInstruction;
	}

	public Command getArguments(int k) {
		return myArguments.get(k);
	}
	
	public int getCurrentArgumentSize() {
		return myArguments.size();
	}
	
	public List<Command> getAllArguments() {
		return myArguments;
	}

	public boolean isFinished() {
		return finished;
	}

	protected void changeToFinished() {
		finished = true;
	}

	public void resetCommand() {
		finished = false;
	}

	public abstract Double getValue(List<Object> args, VariableManager localVariables);

	/**
	 * 
	 * @return - value that we want to send to UI to be displayed
	 */

	public Double executeCommand(TurtleManagerCommandAPI turtles, VariableManager vars, Double k) {
		myTurtleManager = turtles;
		VariableManager localVariables = vars;
		myTurtle = turtles.getTurtle(k);
		myConvertedArguments = convertArguments(myArguments, localVariables, true);
		this.changeToFinished();
		return this.getValue(myConvertedArguments, localVariables);
	}

	public void performBeforeExecution() {
		return;
	}

	protected ArrayList<Object> convertArguments(List<Command> list, VariableManager localVariables, boolean nest) {
		ArrayList<Object> newArgs = new ArrayList<Object>();
		for (Command o : list) {
			try {
				if (nest) {
					Double value = o.executeCommand(myTurtleManager, localVariables, myTurtle.getID());
					if (value != null) {
						newArgs.add(value);
					} else {
						throw new ParserException(String.format("WRONG INPUT %s", o));
					}
				} else {
					newArgs.add(o);
				}
			} catch (Exception e) {
				throw new ParserException(String.format("WRONG INPUT %s", o));
			}

		}
		return newArgs;
	}
	
	protected ArrayList<Object> convertArguments(Command list, VariableManager vars, boolean nest) {
		ArrayList<Command> temp = new ArrayList<Command>();
		temp.add(list);
		return convertArguments(temp, vars, nest);
	}
	
	public boolean getRunTurtles() {
		return runAllTurtles;
	}
	/*
	public boolean infiniteArguments() {
		return infiniteArguments;
	}*/

	protected List<Command> unNestList(Command c) {
		return c.getAllArguments();
	}
	

}
