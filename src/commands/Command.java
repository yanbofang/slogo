package commands;

import java.util.ArrayList;
import java.util.Arrays;

import backend.CommandHandler;
import backend.Turtle;

public abstract class Command {


	protected String myInstruction;
	protected ArrayList<Object> myArguments;
	protected boolean finished;
	protected Double myValue;
	protected Turtle myTurtle;
	
	
	public Command(String instruction) {
		myArguments = new ArrayList<Object>();
		myInstruction = instruction.toLowerCase();
		finished = false;
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

	/**
	 * 
	 * @return - value that we want Parser to receive
	 */
	public abstract Double getValue();

	// public abstract int getNumOfParameters();
	public Double execute(Turtle turtle) {
		myTurtle = turtle;
		return executeCommand();
	}
	
	/**
	 * 
	 * @return - value that we want to send to UI to be displaye
	 */
	protected abstract Double executeCommand();
	

}
