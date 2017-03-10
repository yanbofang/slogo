package commands;

import java.util.ArrayList;
import java.util.List;

import backend.UserMethodManager;
import backend.VariableManager;

public class AndCommand extends AbstractCommand {

	private static final Integer NUM_OF_EXPRESSIONS = 2;

	public AndCommand(String instruction, VariableManager variables, UserMethodManager methods) {
		super(instruction, variables, methods, NUM_OF_EXPRESSIONS);
	}

	@Override
	public Double getValue(List<Object> args, VariableManager localVariables) {
		List<Double> correctList = new ArrayList<Double>();
		args.stream()
			.mapToDouble(d -> (Double) d)
			.filter(d -> d != 0.0)
			.forEach(d -> correctList.add(d));
		if (correctList.size() == args.size()) {
			setValue(1.0);
		} else {
			setValue(0.0);
		}
		return getValue();
	}

}
