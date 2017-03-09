package commands;

import java.util.List;

import backend.UserMethodManager;
import backend.VariableManager;

public class ShowTurtleCommand extends AbstractCommand {

	private static final Integer NUM_OF_EXPRESSIONS = 0;
	
	public ShowTurtleCommand(String instruction, VariableManager variables,
			UserMethodManager methods) {
		super(instruction, variables, methods, NUM_OF_EXPRESSIONS);
		runAllTurtles = true;
	}
	@Override
	public Double getValue(List<Object> args) {
		myValue = 1.0;
		myTurtle.setShow(true);
		return myValue;
	}

	
	
}

