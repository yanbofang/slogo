package commands;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import backend.UserMethodManager;
import backend.VariableManager;

public class NotEqualCommand extends AbstractCommand {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1150959654000201655L;
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
			setValue(1.0);
		} else {
			setValue(0.0);
		}
		return getValue();
	}

}
