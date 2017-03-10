package commands;

import java.util.List;

import backend.UserMethodManager;
import backend.VariableManager;

public class NotCommand extends AbstractCommand {

	private static final Integer NUM_OF_EXPRESSIONS = 1;
	
	public NotCommand(String instruction, VariableManager variables,
			UserMethodManager methods) {
		super(instruction, variables, methods, NUM_OF_EXPRESSIONS);
	}

	@Override
	public Double getValue(List<Object> args, VariableManager vars) {
		Double exp1 = (Double) args.get(args.size()-1);
		if (exp1 == 0.0) {
			myValue = 1.0;
		} else {
			myValue = 0.0;
		}
		return myValue;
	}

}
