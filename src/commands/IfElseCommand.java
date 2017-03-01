package commands;

import java.util.List;

import backend.UserMethodManager;
import backend.VariableManager;

public class IfElseCommand extends ConditionalCommand {

	private static final Integer NUM_OF_EXPRESSIONS = 3;

	public IfElseCommand(String instruction, VariableManager variables, UserMethodManager methods) {
		super(instruction, variables, methods, NUM_OF_EXPRESSIONS);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Double getValue(List<Object> args) {
		return (Double) args.get(0) == 0.0 ? execute((Double) args.get(0), (List<Command>) args.get(2), true)
				: execute((Double) args.get(0), (List<Command>) args.get(1), true);
	}

}
