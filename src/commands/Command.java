package commands;

import java.util.ArrayList;

public abstract class Command {
	
	private String myInstruction;
	private ArrayList<Object> myArguments;
	
	
	
	public Command(String instruction) {
			myArguments = new ArrayList<Object>();
			myInstruction = instruction;
	}
	
	public void add(Object ... args) {
		for (Object each : args) {
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
	
	public abstract Double getValue();
	

}
