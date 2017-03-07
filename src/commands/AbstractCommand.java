package commands;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import turtles.Turtle;
import turtles.TurtleManagerCommandAPI;
import backend.ParserException;
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
	protected TurtleManagerCommandAPI myTurtleManager;

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

	public void clearArguments() {
		myArguments.clear();
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
	public Double executeCommand(TurtleManagerCommandAPI turtles, Double k) {
		myTurtleManager = turtles;
		myTurtle = turtles.getTurtle(k);
		System.out.println(myArguments);
		ArrayList<Object> newArgs = new ArrayList<Object>();
		for (Object o : myArguments) {
			if (o instanceof AbstractCommand) {
				Command c = (Command) o;
				newArgs.add(c.executeCommand(turtles, k));
			} else {
				// try {
				// newArgs.add((Double) o);
				// } catch (Exception e) {
				// try {
				// newArgs.add(Double.parseDouble((String) o));
				// } catch (Exception f) {
				try {
					System.out.println("This is o: " + (String) o);
					// System.out.println("USERMETHODCOMMAND: " +
					// myUserMethods.getUserMethodCommand((String) o));
					if (myVariables.get((String) o) != null) {
						System.out.println(myVariables.get((String) o));
						newArgs.add(myVariables.get((String) o).getValue());
						// } else if
						// (myUserMethods.getUserMethodCommand((String) o) !=
						// null) {
						// newArgs.add(myUserMethods.getUserMethodCommand((String)
						// o));
					} else {
						newArgs.add(o);
					}
				} catch (Exception g) {
					newArgs.add(o);
				}
			}
		}
		// }
		// }
		this.changeToFinished();
		System.out.println("This is args: " + newArgs);
		return this.getValue(newArgs);
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
					} else if (myUserMethods.getUserMethodCommand((String) o) != null) {
						returnList.add(myUserMethods.getUserMethodCommand((String) o).executeCommand(myTurtleManager, myTurtle.getID()));
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

}
