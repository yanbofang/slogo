package commands;

import java.util.List;

import backend.UserMethodManager;
import backend.VariableManager;

public class TurtlesCommand extends AbstractCommand {

	private static final int NUM_OF_EXPRESSIONS = 0;

	public TurtlesCommand(String instruction, VariableManager variables, UserMethodManager methods) {
		super(instruction, variables, methods, NUM_OF_EXPRESSIONS);
	}

	@Override
	public Double getValue(List<Object> args, VariableManager vars) {
		Double returnValue = new Double(myTurtleManager.allTurtles().size());
		return returnValue;
	}

}
