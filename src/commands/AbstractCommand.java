package commands;

import java.util.ArrayList;
import java.util.Arrays;

public abstract class AbstractCommand implements Command{


	protected String myInstruction;
	protected ArrayList<Object> myArguments;
	protected boolean finished;

	public AbstractCommand(String instruction) {
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

	public abstract Double getValue();

	// public abstract int getNumOfParameters();
	public abstract void executeCommand();

}
