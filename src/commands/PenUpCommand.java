package commands;

import backend.UserMethodManager;
import backend.VariableManager;

public class PenUpCommand extends AbstractCommand {

	private static final Integer NUM_OF_EXPRESSIONS = 0;

	public PenUpCommand(String instruction, VariableManager variables,
			UserMethodManager methods) {
		super(instruction, variables, methods, NUM_OF_EXPRESSIONS);
	}

	@Override
	public Double getValue() {
		myValue = 0.0;
		myTurtle.setFuturePen(false);
		return myValue;
	}

	@Override
	public Double executeCommand() {
		this.changeToFinished();
		myTurtle.setPen(false);
		return myValue;
	}

}
