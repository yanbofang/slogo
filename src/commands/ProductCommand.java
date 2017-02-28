package commands;

import backend.UserMethodManager;
import backend.VariableManager;

public class ProductCommand extends AbstractCommand {

	private static final Integer NUM_OF_EXPRESSIONS = 2;

	public ProductCommand(String instruction, VariableManager variables, UserMethodManager methods) {
		super(instruction, variables, methods, NUM_OF_EXPRESSIONS);
	}

	@Override
	public Double getValue() {
		myValue = (Double) myArguments.get(0) * (Double) myArguments.get(1);
		return myValue;
	}

	@Override
	public Double executeCommand() {
		this.changeToFinished();
		return getValue();
	}

}
