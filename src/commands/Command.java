package commands;

import java.util.ArrayList;
import java.util.Arrays;

public abstract class Command {


	protected String myInstruction;
	protected ArrayList<Object> myArguments;
	protected boolean finished;

	public Command(String instruction) {
		myArguments = new ArrayList<Object>();
		myInstruction = instruction.toLowerCase();
		finished = false;
	}
	
	public void add(Double ... args) {
		for (Double each : args) {
			myArguments.add(each);
		}
	}
	
	public abstract int getNumberExpressions();


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

	public abstract Double getValue();

	// public abstract int getNumOfParameters();
	public abstract void executeCommand();

}
