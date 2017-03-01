package commands;

import java.util.List;

import backend.UserMethodManager;
import backend.VariableManager;

public class AndCommand extends AbstractCommand{
	
	private static final Integer NUM_OF_EXPRESSIONS = 2;
	
	public AndCommand(String instruction, VariableManager variables,
			UserMethodManager methods) {
		super(instruction, variables, methods, NUM_OF_EXPRESSIONS);
	}

	@Override
	public Double getValue(List<Object> args) {
		Double exp1 = (Double) args.get(0);
		Double exp2 = (Double) args.get(1);
		if (exp1 != 0.0 && exp2 != 0.0) {
			myValue = 1.0;
		} else {
			myValue = 0.0;
		}
		return myValue;
	}

}
