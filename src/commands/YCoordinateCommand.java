package commands;

import java.util.List;

import backend.UserMethodManager;
import backend.VariableManager;

public class YCoordinateCommand extends AbstractCommand {

	private static final Integer NUM_OF_EXPRESSIONS = 0;
	
	public YCoordinateCommand(String instruction, VariableManager variables,
			UserMethodManager methods) {
		super(instruction, variables, methods, NUM_OF_EXPRESSIONS);
	}

	@Override
	public Double getValue(List<Object> args) {
		myValue = myTurtle.getLocation(false).getY();
		return myValue;
	}


}
