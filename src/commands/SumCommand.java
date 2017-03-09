package commands;

import java.util.ArrayList;
import java.util.List;

import backend.ParserException;
import backend.UserMethodManager;
import backend.VariableManager;

public class SumCommand extends AbstractCommand {

	private static final Integer NUM_OF_EXPRESSIONS = 2;

	public SumCommand(String instruction, VariableManager variables, UserMethodManager methods) {
		super(instruction, variables, methods, NUM_OF_EXPRESSIONS);
	}

	@Override
	public Double getValue(List<Object> args, VariableManager vars) {
		Double total = 0.0;
		args.forEach(s -> { s = (Double) s;
							total += s;};
		total += (Double) s;
		return total;
	}


}
