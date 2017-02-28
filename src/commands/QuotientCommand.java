package commands;

import backend.UserMethodManager;
import backend.VariableManager;

public class QuotientCommand extends AbstractCommand {

	private static final Integer NUM_OF_EXPRESSIONS = 2;

	public QuotientCommand(String instruction, VariableManager variables, UserMethodManager methods) {
		super(instruction, variables, methods, NUM_OF_EXPRESSIONS);
	}

	@Override
	public Double getValue() {
		return (Double) myArguments.get(0) / (Double) myArguments.get(1);
	}

	@Override
	public Double executeCommand() {
		return getValue();
	}

}
