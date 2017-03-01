package commands;

import backend.UserMethodManager;
import backend.VariableManager;

public class HeadingCommand extends AbstractCommand {

	private static final Integer NUM_OF_EXPRESSION = 0;
	
	public HeadingCommand(String instruction, VariableManager variables,
			UserMethodManager methods) {
		super(instruction, variables, methods, NUM_OF_EXPRESSION);
	}

	@Override
	public Double getValue() {
		myValue = myTurtle.getFutureRotate();
		return myValue;
	}

	@Override
	public Double executeCommand() {
		this.changeToFinished();
		return myValue;
	}

}
