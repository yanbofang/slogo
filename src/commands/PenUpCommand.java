package commands;

import java.util.List;

import backend.UserMethodManager;
import backend.VariableManager;

public class PenUpCommand extends AbstractCommand {

	private static final Integer NUM_OF_EXPRESSIONS = 0;

	public PenUpCommand(String instruction, VariableManager variables,
			UserMethodManager methods) {
		super(instruction, variables, methods, NUM_OF_EXPRESSIONS);
		setRunAllTurtles(true);
	}

	@Override
	public Double getValue(List<Object> args, VariableManager vars) {
		setValue(0.0);
		getTurtleManager().setPenState(false, getTurtle().getID());
		return getValue();
	}

}
