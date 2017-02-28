package commands;

import backend.VariableManager;

public class RemainderCommand extends AbstractCommand {

	private static final Integer NUM_OF_EXPRESSIONS = 2;

	public RemainderCommand(String instruction, VariableManager variables) {
		super(instruction, variables, NUM_OF_EXPRESSIONS);

	}

	@Override
	public Double getValue() {
		return (Double) myArguments.get(0) % (Double) myArguments.get(1);
	}

	@Override
	protected Double executeCommand() {
		this.changeToFinished();
		return getValue();
	}

}
