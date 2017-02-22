package commands;

import java.util.ArrayList;
import java.util.List;

public abstract class Command {
	
	private String myInstruction;
	private ArrayList<String> myArguments;
	
	
	
	public Command(String instruction, String ... args) {
			myArguments = new ArrayList<String>();
			myInstruction = instruction;
			for (String each : args) {
				myArguments.add(each);
			}
	}


	public String getInstruction() {
		return myInstruction;
	}
	
	public String getArguments(int k) {
		return myArguments.get(k);
	}
	
	public abstract Double getValue();
	

}
