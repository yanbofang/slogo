package commands;

import java.util.List;

import backend.UserMethodManager;
import backend.VariableManager;

public class HideTurtleCommand extends AbstractCommand {
	
	private static final Integer NUM_OF_EXPRESSION = 0;
	
	public HideTurtleCommand(String instruction, VariableManager variables,
			UserMethodManager methods) {
		super(instruction, variables, methods, NUM_OF_EXPRESSION);
	}

	@Override
	public Double getValue(List<Object> args) {
		myValue = 0.0;
		myTurtle.setShow(false);
		return myValue;
	}

}
