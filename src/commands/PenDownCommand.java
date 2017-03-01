package commands;

import backend.UserMethodManager;
import backend.VariableManager;

public class PenDownCommand extends AbstractCommand {
	
	private static final Integer  NUM_OF_EXPRESSIONS = 0;

	public PenDownCommand(String instruction, VariableManager variables,
			UserMethodManager methods) {
		super(instruction, variables, methods, NUM_OF_EXPRESSIONS);
	}

	@Override
	public Double getValue() {
		myValue = 1.0;
		myTurtle.setFuturePen(true);
		return myValue;
	}

	@Override
	public Double executeCommand() {
		this.changeToFinished();
		myTurtle.setPen(true);
		return myValue;
	}

}
