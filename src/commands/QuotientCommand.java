package commands;

import java.util.List;

import backend.UserMethodManager;
import backend.VariableManager;

public class QuotientCommand extends AbstractCommand {

	private static final Integer NUM_OF_EXPRESSIONS = 2;

	public QuotientCommand(String instruction, VariableManager variables, UserMethodManager methods) {
		super(instruction, variables, methods, NUM_OF_EXPRESSIONS);
	}

	@Override
	public Double getValue(List<Object> args, VariableManager vars) {
		Double[] start = {(Double) args.get(0)};
		args.remove(start);
		args.stream()
			.forEach(d -> start[0] /= (Double) d);
		return start[0];
	}


}
