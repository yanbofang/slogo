package commands;

import backend.VariableManager;

public class MinusCommand extends AbstractCommand {

	private static final Integer NUM_OF_EXPRESSIONS = 1;

	public MinusCommand(String instruction, VariableManager variables) {
		super(instruction, variables, NUM_OF_EXPRESSIONS);

	}

	@Override
	public Double getValue() {
		myValue = -1.0 * (Double) myArguments.get(0);
		return myValue;
	}

	@Override
	public Double executeCommand() {
		this.changeToFinished();
		return getValue();
	}

}
