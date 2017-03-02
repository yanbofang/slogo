package commands;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import backend.ParserException;
import backend.Turtle;
import backend.UserMethodManager;
import backend.VariableManager;

public abstract class AbstractCommand implements Command {

	protected String myInstruction;
	protected ArrayList<Object> myArguments;
	protected boolean finished;
	protected Turtle myTurtle;
	protected Double myValue;
	protected VariableManager myVariables;
	protected Integer myNumOfExpressions;
	protected UserMethodManager myUserMethods;

	public AbstractCommand(String instruction, VariableManager variables, UserMethodManager methods) {
		myArguments = new ArrayList<Object>();
		myVariables = variables;
		myInstruction = instruction.toLowerCase();
		myUserMethods = methods;
		finished = false;
	}

	public AbstractCommand(String instruction, VariableManager variables, UserMethodManager methods,
			int numOfExpressions) {
		this(instruction, variables, methods);
		myNumOfExpressions = numOfExpressions;
	}

	public void add(Object... args) {
		for (Object each : args) {
			myArguments.add(each);
		}
	}

	public Integer getNumOfExpressions() {
		return myNumOfExpressions;
	}

	public String getInstruction() {
		return myInstruction;
	}

	public Object getArguments(int k) {
		return myArguments.get(k);
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

	public Double getValue(Turtle turtle) {
		myTurtle = turtle;
		return getValue(myArguments);
	}

	public abstract Double getValue(List<Object> args);

	/**
	 * 
	 * @return - value that we want to send to UI to be displayed
	 */
	public Double executeCommand(Turtle turtle) {
		myTurtle = turtle;
		ArrayList<Object> newArgs = new ArrayList<Object>();
		for (Object o : myArguments) {
			if (o instanceof AbstractCommand) {
				Command c = (Command) o;
				newArgs.add(c.executeCommand(turtle));
			} else {
				try {
					newArgs.add((Double) o);
				}  catch (Exception e) {
					 try {
						 newArgs.add(Double.parseDouble((String) o));
					 } catch (Exception f) {
						 try {
							 if (myVariables.get((String) o) != null) {
								 newArgs.add(myVariables.get((String) o).getValue());
							 } else if (myUserMethods.get((String) o) != null) {
								 newArgs.add(myUserMethods.get((String) o).getListOfCommands());
							 } else {
								 newArgs.add(o);
							 }
						 } catch (Exception g) {
							 newArgs.add(o);
						 }
					}
				}
			}
		}
		System.out.println(newArgs);
		this.changeToFinished();
		return getValue(newArgs);
	}

}
