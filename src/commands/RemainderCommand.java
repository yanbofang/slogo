package commands;

import java.util.List;

import backend.UserMethodManager;
import backend.VariableManager;

public class RemainderCommand extends AbstractCommand {

	private static final Integer NUM_OF_EXPRESSIONS = 2;

	public RemainderCommand(String instruction, VariableManager variables, UserMethodManager methods) {
		super(instruction, variables, methods, NUM_OF_EXPRESSIONS);
	}

	@Override
	public Double getValue(List<Object> args) {
		return (Double) args.get(0) % (Double) args.get(1);
	}

}
