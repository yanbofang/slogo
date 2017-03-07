package commands;

import java.util.List;

import backend.UserMethodManager;
import backend.VariableManager;

public class IDCommand extends AbstractCommand {

	private static final int NUM_OF_EXPRESSIONS = 0;
	public IDCommand(String instruction, VariableManager variables,
			UserMethodManager methods) {
		super(instruction, variables, methods, NUM_OF_EXPRESSIONS);
		runAllTurtles = true;
	}
	@Override
	public Double getValue(List<Object> args) {
		return myTurtle.getID();
	}

}
