package commands;



import java.util.ArrayList;
import java.util.List;

public abstract class Command {
	
	protected String myInstruction;
	protected ArrayList<Object> myArguments;
	
	
	
	public Command(String instruction, Object ... args) {
			myArguments = new ArrayList<Object>();
			myInstruction = instruction.toLowerCase();
			for (Object each : args) {
				myArguments.add(each);
			}
	}


	public String getInstruction() {
		return myInstruction;
	}
	
	public Object getArguments(int k) {
		return myArguments.get(k);
	}
	
	public abstract Double getValue();

	public abstract void executeCommand();
	

}
