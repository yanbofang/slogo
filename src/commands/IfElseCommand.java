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
		Double returnValue = 0.0;
		int k = 0;
		while ( k < args.size()) {
			returnValue = (Double) args.get(k) == 0.0 ? execute((Double) args.get(k), (Command) args.get(k+2), true, vars)
					: execute((Double) args.get(k), (Command) args.get(k+1), true, vars);
			k += 3;
		}
		return returnValue;
	}

}
