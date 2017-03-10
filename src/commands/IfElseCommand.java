package commands;

import java.util.List;

import backend.UserMethodManager;
import backend.VariableManager;

public class IfElseCommand extends ConditionalCommand {

	private static final Integer NUM_OF_EXPRESSIONS = 3;

	public IfElseCommand(String instruction, VariableManager variables, UserMethodManager methods) {
		super(instruction, variables, methods, NUM_OF_EXPRESSIONS);
		setRunAllTurtles(true);
	}

	@Override
	public Double getValue(List<Object> args, VariableManager vars) {
		return (Double) args.get(0) == 0.0 ? execute((Double) args.get(0), (Command) args.get(2), true, vars)
				: execute((Double) args.get(0), (Command) args.get(1), true, vars);
	}

}
