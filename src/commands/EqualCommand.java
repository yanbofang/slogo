package commands;

import java.util.ArrayList;
import java.util.List;

import backend.UserMethodManager;
import backend.VariableManager;

public class EqualCommand extends AbstractCommand {

	private static final Integer NUM_OF_EXPRESSIONS = 2;
	
	public EqualCommand(String instruction, VariableManager variables,
			UserMethodManager methods) {
		super(instruction, variables, methods, NUM_OF_EXPRESSIONS);
	}

	@Override
	public Double getValue(List<Object> args, VariableManager vars) {
		Double reference = (Double) args.get(0);
		ArrayList<Double> compareList = new ArrayList<Double>();
		args.stream()
			.mapToDouble(d -> (Double) d)
			.filter(d -> d == reference)
			.forEach(d -> compareList.add(d));
		if (compareList.size() == args.size()) {
			myValue = 1.0;
		} else {
			myValue = 0.0;
		}
		return myValue;
	}

}
