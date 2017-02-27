package commands;

import backend.VariableManager;

public class ProductCommand extends AbstractCommand {

	private static final Integer NUM_OF_EXPRESSIONS = 2;

	public ProductCommand(String instruction, VariableManager variables) {
		super(instruction, variables, NUM_OF_EXPRESSIONS);

	}

	@Override
	public Double getValue() {
		myValue = (Double) myArguments.get(0) * (Double) myArguments.get(1);
		return myValue;
	}

	@Override
	protected Double executeCommand() {
		this.changeToFinished();
		return getValue();
	}

}
