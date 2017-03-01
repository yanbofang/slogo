package commands;

import backend.UserMethodManager;
import backend.VariableManager;

public class NotEqualCommand extends AbstractCommand {

	private static final Integer NUM_OF_EXPRESSIONS = 2;
	
	public NotEqualCommand(String instruction, VariableManager variables,
			UserMethodManager methods) {
		super(instruction, variables, methods, NUM_OF_EXPRESSIONS);
	}

	@Override
	public Double getValue() {
		Double exp1 = (Double) myArguments.get(0);
		Double exp2 = (Double) myArguments.get(1);
		if (exp1 != exp2) {
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
