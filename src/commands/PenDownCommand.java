package commands;

import java.util.List;

import backend.UserMethodManager;
import backend.VariableManager;

public class PenDownCommand extends AbstractCommand {
	
	private static final Integer  NUM_OF_EXPRESSIONS = 0;

	public PenDownCommand(String instruction, VariableManager variables,
			UserMethodManager methods) {
		super(instruction, variables, methods, NUM_OF_EXPRESSIONS);
		runAllTurtles= true;
	}

	@Override
	public Double getValue(List<Object> args) {
		myValue = 1.0;
		myTurtle.setPen(true);
		return myValue;
	}
}
