package commands;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import backend.UserMethodManager;
import backend.VariableManager;

public class NotEqualCommand extends AbstractCommand {

	private static final Integer NUM_OF_EXPRESSIONS = 2;
	
	public NotEqualCommand(String instruction, VariableManager variables,
			UserMethodManager methods) {
		super(instruction, variables, methods, NUM_OF_EXPRESSIONS);
	}

	@Override
	public Double getValue(List<Object> args, VariableManager vars) {
		Set<Double> mySet = new HashSet<Double>();	
		args.stream()
			.mapToDouble(d-> (Double) d)
			.forEach(d -> mySet.add((Double) d));
		if (mySet.size() == args.size()) {
			myValue = 1.0;
		} else {
			myValue = 0.0;
		}
		return myValue;
	}

}
