package commands;

import java.util.ArrayList;
import java.util.List;
import turtles.Turtle;
import turtles.TurtleManagerCommandAPI;
import backend.ParserException;
import backend.UserMethodManager;
import backend.VariableManager;

public abstract class AbstractCommand implements Command {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7877106052459852818L;
	private Integer myNumOfExpressions;
	private String myInstruction;
	private ArrayList<Command> myArguments;
	private ArrayList<Object> myConvertedArguments;
	private boolean myFinished;
	private Turtle myTurtle;
	private Double myValue;
	private VariableManager myVariables;
	private UserMethodManager myUserMethods;
	private TurtleManagerCommandAPI myTurtleManager;
	private boolean myRunNested; // NEED TO GO THROUGH AND ADD TO ALL NECESSARY
									// COMMANDS
	private boolean myRunAllTurtles; // NEED TO GO THROUGH AND ADD TO ALL
										// NECESSARY COMMANDS
	// protected boolean infiniteArguments; //NEED TO GO THROUGH AND ADD TO ALL
	// NECESSARY COMMANDS

	public AbstractCommand(String instruction, VariableManager variables, UserMethodManager methods) {
		myArguments = new ArrayList<Command>();
		myNumOfExpressions = 0;
		myVariables = variables;
		myInstruction = instruction.toLowerCase();
		myUserMethods = methods;
		myFinished = false;
		myRunNested = true;
		myRunAllTurtles = false;
		// infiniteArguments = true;
	}

	public AbstractCommand(String instruction, VariableManager variables, UserMethodManager methods,
			int numOfExpressions) {
		this(instruction, variables, methods);
		myNumOfExpressions = numOfExpressions;
	}

	public Integer getNumOfExpressions() {
		return myNumOfExpressions;
	}

	public void setNumOfExpressions(int myNumOfExpressions) {
		this.myNumOfExpressions = myNumOfExpressions;
	}

	public String getInstruction() {
		return myInstruction;
	}

	public void setInstruction(String myInstruction) {
		this.myInstruction = myInstruction;
	}

	public ArrayList<Command> getArguments() {
		return myArguments;
	}

	public void setArguments(ArrayList<Command> myArguments) {
		this.myArguments = myArguments;
	}

	public ArrayList<Object> getConvertedArguments() {
		return myConvertedArguments;
	}

	public void setConvertedArguments(ArrayList<Object> myConvertedArguments) {
		this.myConvertedArguments = myConvertedArguments;
	}

	public boolean isFinished() {
		return myFinished;
	}

	public void setFinished(boolean myFinished) {
		this.myFinished = myFinished;
	}

	public Turtle getTurtle() {
		return myTurtle;
	}

	public void setTurtle(Turtle myTurtle) {
		this.myTurtle = myTurtle;
	}

	public Double getValue() {
		return myValue;
	}

	public void setValue(Double myValue) {
		this.myValue = myValue;
	}

	public VariableManager getVariables() {
		return myVariables;
	}

	public void setVariables(VariableManager myVariables) {
		this.myVariables = myVariables;
	}

	public UserMethodManager getUserMethods() {
		return myUserMethods;
	}

	public void setUserMethods(UserMethodManager myUserMethods) {
		this.myUserMethods = myUserMethods;
	}

	public TurtleManagerCommandAPI getTurtleManager() {
		return myTurtleManager;
	}

	public void setTurtleManager(TurtleManagerCommandAPI myTurtleManager) {
		this.myTurtleManager = myTurtleManager;
	}

	public boolean isRunNested() {
		return myRunNested;
	}

	public void setRunNested(boolean myRunNested) {
		this.myRunNested = myRunNested;
	}

	public boolean isRunAllTurtles() {
		return myRunAllTurtles;
	}

	public void setRunAllTurtles(boolean myRunAllTurtles) {
		this.myRunAllTurtles = myRunAllTurtles;
	}

	public void add(Command... args) {
		for (Command each : args) {
			myArguments.add(each);
		}
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

	protected void changeToFinished() {
		myFinished = true;
	}

	public void resetCommand() {
		myFinished = false;
	}

	public abstract Double getValue(List<Object> args, VariableManager localVariables);

	// /**
	// *
	// * @return - value that we want to send to UI to be displayed
	// */
	// public Double executeCommand(TurtleManagerCommandAPI turtles,
	// VariableManager vars, Double k) {
	// myTurtleManager = turtles;
	// VariableManager localVariables = new VariableManager();
	// localVariables.addAll(vars.getVariableMap());
	// myTurtle = turtles.getTurtle(k);
	// myConvertedArguments = argumentsToConvert(localVariables);//
	// convertArguments(myArguments, // true);
	// this.changeToFinished();
	// return this.getValue(myConvertedArguments, localVariables);
	// }
	//

	/**
	 * 
	 * @return - value that we want to send to UI to be displayed
	 */
	public Double executeCommand(TurtleManagerCommandAPI turtles, VariableManager vars, Double k) {
		myTurtleManager = turtles;
		VariableManager localVariables = new VariableManager();
		localVariables.addAll(vars.getVariableMap());
		myTurtle = turtles.getTurtle(k);
		myConvertedArguments = argumentsToConvert(localVariables);// convertArguments(myArguments,
																	// // true);
		this.changeToFinished();
		return this.getValue(myConvertedArguments, localVariables);
	}

	protected ArrayList<Object> argumentsToConvert(VariableManager vars) {
		ArrayList<Object> convArgs = convertArguments(myArguments, vars, true);
		return convArgs;
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

	protected List<Command> unNestList(Command c) {
		return c.getAllArguments();
	}

}
