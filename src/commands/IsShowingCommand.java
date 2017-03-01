package commands;

import backend.UserMethodManager;
import backend.VariableManager;

public class IsShowingCommand extends AbstractCommand {

	private static final Integer NUM_OF_EXPRESSIONS = 0;
	public IsShowingCommand(String instruction, VariableManager variables,
			UserMethodManager methods) {
		super(instruction, variables, methods, NUM_OF_EXPRESSIONS);
		
	}
	
	@Override
	public Double getValue() {
		if (myTurtle.getFutureShow()) {
			myValue = 1.0;
		} else {
			myValue = 0.0;
		}
		return myValue;
	}
	@Override
	public Double executeCommand() {
		this.changeToFinished();
		return myValue;
	}

}
