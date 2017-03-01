package commands;

import java.util.ArrayList;
import backend.Turtle;
import backend.UserMethodManager;
import backend.VariableManager;

public abstract class AbstractCommand implements Command{


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
	
	public AbstractCommand(String instruction, VariableManager variables, UserMethodManager methods, int numOfExpressions){
		this(instruction, variables, methods);
		myNumOfExpressions = numOfExpressions;
	}
	
	public void add(Object ... args) {
		for (Object each : args) {
			try {
				myArguments.add((Double) each);
			}  catch (Exception e) {
				 try {
					 myArguments.add(Double.parseDouble((String) each));
				 } catch (Exception f) {
					 myArguments.add(each);
				 }
			}
		}
	}
	
	public Integer getNumOfExpressions(){
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

	public Double getValue(Turtle turtle) {
		myTurtle = turtle;
		return getValue();
	}
	public abstract Double getValue();

	/**
	 * 
	 * @return - value that we want to send to UI to be displayed
	 */
	public abstract Double executeCommand();

}
