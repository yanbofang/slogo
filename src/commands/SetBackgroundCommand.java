package commands;

import java.util.List;

import backend.UserMethodManager;
import backend.VariableManager;

public class SetBackgroundCommand extends AbstractCommand {

	private static final int NUM_OF_EXPRESSIONS = 1;
	public SetBackgroundCommand(String instruction, VariableManager variables,
			UserMethodManager methods) {
		super(instruction, variables, methods, NUM_OF_EXPRESSIONS);
	}
	@Override
	public Double getValue(List<Object> args, VariableManager localVariables) {
		Double value = (Double) args.get(args.size()-1);
		myTurtleManager.setBackground(value);
		return value;
	}

}
