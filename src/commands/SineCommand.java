package commands;

import java.util.List;

import backend.UserMethodManager;
import backend.VariableManager;


public class SineCommand extends AbstractCommand {

	private static final Integer NUM_OF_EXPRESSIONS = 1;
	
	public SineCommand(String instruction, VariableManager variables,
			UserMethodManager methods) {
		super(instruction, variables, methods, NUM_OF_EXPRESSIONS);
	}

	@Override
	public Double getValue(List<Object> args, VariableManager vars) {
		return Math.sin(Math.toRadians((Double) args.get(args.size()-1)));
	}

}
