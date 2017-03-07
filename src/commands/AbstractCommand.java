package commands;

import java.util.ArrayList;
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

	protected String myInstruction;
	protected ArrayList<Object> myArguments;
	protected ArrayList<Object> myConvertedArguments;
	protected boolean finished;
	protected Turtle myTurtle;
	protected Double myValue;
	protected VariableManager myVariables;
	protected Integer myNumOfExpressions;
	protected UserMethodManager myUserMethods;
	protected TurtleManagerCommandAPI myTurtleManager;
	protected boolean runNested;
	protected boolean runAllTurtles;

	public AbstractCommand(String instruction, VariableManager variables, UserMethodManager methods) {
		myArguments = new ArrayList<Object>();
		myVariables = variables;
		myInstruction = instruction.toLowerCase();
		myUserMethods = methods;
		finished = false;
		runNested = true;
		runAllTurtles = false;
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

	public abstract Double getValue(List<Object> args);

	/**
	 * 
	 * @return - value that we want to send to UI to be displayed
	 */
	public Double executeCommand(TurtleManagerCommandAPI turtles, Double k) {
		myTurtleManager = turtles;
		myTurtle = turtles.getTurtle(k);
		myConvertedArguments = convertArguments(myArguments, true);
		this.changeToFinished();
		return this.getValue(myConvertedArguments);
	}
	
	public void performBeforeExecution() {
		return;
	}
	

	protected ArrayList<Object> convertArguments(List<Object> list, boolean nest) {
		ArrayList<Object> newArgs = new ArrayList<Object>();
		for (int k = 0; k < list.size(); k++) {
			Object o = list.get(k);
			if (o instanceof AbstractCommand) {
				Command c = (Command) o;
				if (nest) {
					newArgs.add(c.executeCommand(myTurtleManager, myTurtle.getID()));
				} else {
					newArgs.add(c);
				}
			} else if (o instanceof String) {
				if (myVariables.get((String) o) != null){
					newArgs.add(myVariables.get((String) o).getValue());
				} else if (myUserMethods.getUserMethod((String) o) != null) {
					UserMethod method = (UserMethod) myUserMethods.getUserMethod((String) o);
					UserMethodCommand methodCommand = new UserMethodCommand((String) o, myVariables, myUserMethods, 
							method);
					List<Object> args = list.subList(list.indexOf(o)+1, list.indexOf(o)+methodCommand.getNumOfExpressions()+1 );
					methodCommand.add(args);
					if (nest) {
						newArgs.add(methodCommand.executeCommand(myTurtleManager, myTurtle.getID()));
					} else {
						newArgs.add(methodCommand);
					}
					k = list.indexOf(args.get(args.size()-1)) +1;
				} else {
					newArgs.add((String) o);
				}
			} else {
				newArgs.add(convertArguments((List<Object>) o,  runNested));
			}
		}
		System.out.println("This is args: " + newArgs);
		return newArgs;
	}
	
	public boolean getRunTurtles() {
		return runAllTurtles;
	}
	
	
/*
	private List<Object> methodVarCheck(List<Object> o) {
		List<Object> check = new ArrayList<Object>();
		for (Object each: o) {
			if (each instanceof String) {
				if (myUserMethods.getUserMethod((String) each) != null) {
					UserMethod method = (UserMethod) myUserMethods.getUserMethod((String) each);
					UserMethodCommand methodCommand = new UserMethodCommand((String) each, myVariables, myUserMethods, 
							method);
					check.add(methodCommand);
				} else {
					check.add(each);
				}
			} else {
				determineObject(check, each);
			}
		}
		return check;
	}
	
	private void determineObject(List<Object> newArgs, Object o) {
		if (o instanceof AbstractCommand) {
			Command c = (Command) o;
			newArgs.add(c.executeCommand(myTurtleManager, myTurtle.getID()));
		} else if (o instanceof String) {
			if (myVariables.get((String) o) != null){
				newArgs.add(myVariables.get((String) o).getValue());
			} else if (myUserMethods.getUserMethod((String) o) != null) {
				UserMethod method = (UserMethod) myUserMethods.getUserMethod((String) o);
				UserMethodCommand methodCommand = new UserMethodCommand((String) o, myVariables, myUserMethods, 
						method);
				
				//List<Object> args = list.subList(list.indexOf(o)+1, list.indexOf(o)+methodCommand.getNumOfExpressions()+1 );
				//methodCommand.add(args);
				newArgs.add(methodCommand.executeCommand(myTurtleManager, myTurtle.getID()));
				//k = list.indexOf(args.get(args.size()-1)) +1;
			} else {
				newArgs.add((String) o);
			}
		} else {
			System.out.println("hereerererer" + o);
			newArgs.add(methodVarCheck((List<Object>) o));
		}
	}
	
	protected List<Object> checkList(Object o) {
		List<Object> returnList = new ArrayList<Object>();
		List<Object> newList = (List<Object>) o;
		for (Object each : newList) {
			if (each instanceof Command) {
				Command c = (Command) each;
				returnList.add(c.executeCommand(myTurtleManager, myTurtle.getID()));
			} else {
				try {
					if (myVariables.get((String) o) != null) {
						returnList.add(myVariables.get((String) o).getValue());
					} else {
						returnList.add(each);
					}
				} catch (Exception e) {
					returnList.add(each);
				}
			}
		}
		return returnList;
	}
	*/

}
