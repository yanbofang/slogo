package commands;

import backend.VariableManager;

public class DifferenceCommand extends AbstractCommand {

	private static final Integer NUM_OF_EXPRESSIONS = 2;

	public DifferenceCommand(String instruction, VariableManager variables) {
		super(instruction, variables, NUM_OF_EXPRESSIONS);

	}

	@Override
	public Double getValue() {
		return (Double) myArguments.get(0) - (Double) myArguments.get(1);
	}

	@Override
	public Double executeCommand() {
		return getValue();
	}

}
