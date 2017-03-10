package commands;

import java.util.List;

import backend.UserMethodManager;
import backend.VariableManager;

public class OrCommand extends AbstractCommand {

	private static final Integer NUM_OF_EXPRESSIONS = 2;
	
	public OrCommand(String instruction, VariableManager variables,
			UserMethodManager methods) {
		super(instruction, variables, methods, NUM_OF_EXPRESSIONS);
	}

	@Override
	public Double getValue(List<Object> args, VariableManager vars) {
		double[] testArray = args.stream()
				.mapToDouble(d-> (Double) d)
				.filter(d -> d != 0.0)
				.toArray();
		if (testArray.length > 0) {
			myValue = 1.0;
		} else {
			myValue = 0.0;
		}
		return myValue;
	}
}
