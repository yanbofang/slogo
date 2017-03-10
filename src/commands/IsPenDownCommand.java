package commands;

import java.util.List;

import backend.UserMethodManager;
import backend.VariableManager;

public class IsPenDownCommand extends AbstractCommand {

	private static final Integer NUM_OF_EXPRESSION = 0;
	
	public IsPenDownCommand(String instruction, VariableManager variables,
			UserMethodManager methods) {
		super(instruction, variables, methods, NUM_OF_EXPRESSION);
		setRunAllTurtles(true);
	}

	@Override
	public Double getValue(List<Object> args, VariableManager vars) {
		if (getTurtle().getPen().showPen()) {
			setValue(1.0);
		} else {
			setValue(0.0);
		}
		return getValue();
	}

}
