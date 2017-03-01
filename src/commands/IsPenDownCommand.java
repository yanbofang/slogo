package commands;

import backend.UserMethodManager;
import backend.VariableManager;

public class IsPenDownCommand extends AbstractCommand {

	private static final Integer NUM_OF_EXPRESSION = 0;
	
	public IsPenDownCommand(String instruction, VariableManager variables,
			UserMethodManager methods) {
		super(instruction, variables, methods, NUM_OF_EXPRESSION);
	}

	@Override
	public Double getValue() {
		if (myTurtle.getFuturePen()) {
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
