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
		Double exp1 = (Double) args.get(0);
		if (exp1 == 0.0) {
			setValue(1.0);
		} else {
			setValue(0.0);
		}
		return getValue();
	}

}
