package commands;

import backend.UserMethodManager;
import backend.VariableManager;

public class NotCommand extends AbstractCommand {

	private static final Integer NUM_OF_EXPRESSIONS = 1;
	
	public NotCommand(String instruction, VariableManager variables,
			UserMethodManager methods) {
		super(instruction, variables, methods, NUM_OF_EXPRESSIONS);
	}

	@Override
	public Double getValue() {
		Double exp1 = (Double) myArguments.get(0);
		if (exp1 == 0.0) {
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
