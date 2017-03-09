package commands;

import java.util.List;

import backend.UserMethodManager;
import backend.VariableManager;

public class XCoordinateCommand extends AbstractCommand {

	private static final Integer NUM_OF_EXPRESSIONS = 0;
	
	public XCoordinateCommand(String instruction, VariableManager variables,
			UserMethodManager methods) {
		super(instruction, variables, methods, NUM_OF_EXPRESSIONS);
		runAllTurtles = true;
	}

	@Override
	public Double getValue(List<Object> args) {
		myValue = myTurtle.getLocation(true).getX();
		return myValue;
	}

}
