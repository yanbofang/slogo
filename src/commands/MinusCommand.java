package commands;

import java.util.List;

import backend.UserMethodManager;
import backend.VariableManager;

public class MinusCommand extends AbstractCommand {

	private static final Integer NUM_OF_EXPRESSIONS = 1;

	public MinusCommand(String instruction, VariableManager variables, UserMethodManager methods) {
		super(instruction, variables, methods, NUM_OF_EXPRESSIONS);

	}

	@Override
	public Double getValue(List<Object> args, VariableManager vars) {
		setValue(-1.0 * (Double) args.get(0));
		return getValue();
	}

}
