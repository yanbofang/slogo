package commands;

import java.util.List;

import backend.UserMethodManager;
import backend.VariableManager;

public class RandomCommand extends AbstractCommand {

	private static final Integer NUM_OF_EXPRESSIONS = 1;
	
	public RandomCommand(String instruction, VariableManager variables,
			UserMethodManager methods) {
		super(instruction, variables, methods, NUM_OF_EXPRESSIONS);
	}

	@Override
	public Double getValue(List<Object> args, VariableManager vars) {
		Double max = (Double) args.get(0);
		return Math.random() * (max - 0) + 0;
	}
	
	

}
